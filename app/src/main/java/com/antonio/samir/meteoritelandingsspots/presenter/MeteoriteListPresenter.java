package com.antonio.samir.meteoritelandingsspots.presenter;


import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.antonio.samir.meteoritelandingsspots.model.Meteorite;
import com.antonio.samir.meteoritelandingsspots.service.local.AddressService;
import com.antonio.samir.meteoritelandingsspots.service.repository.MeteoriteRepositoryFactory;
import com.antonio.samir.meteoritelandingsspots.service.repository.database.MeteoriteDao;
import com.antonio.samir.meteoritelandingsspots.service.local.MeteoriteService;
import com.antonio.samir.meteoritelandingsspots.service.local.MeteoriteServiceFactory;
import com.antonio.samir.meteoritelandingsspots.util.GPSTracker;
import com.antonio.samir.meteoritelandingsspots.util.NetworkUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Presenter layer responsible for manage the interactions between the activity and the services
 */
public class MeteoriteListPresenter implements AddressService.RecoveryAddressDelegate {

    private static final String TAG = MeteoriteListPresenter.class.getSimpleName();
    private static MeteoriteListView mView;
    private MeteoriteService meteoriteFetchService;
    private WeakReference<Context> mContextReference = null;
    private GPSTracker mGpsTracker;

    public MeteoriteListPresenter(Context context) {
        mContextReference = new WeakReference<>(context);
    }

    public LiveData<List<Meteorite>> getMeteorites() {
        final LiveData<List<Meteorite>> data = meteoriteFetchService.getMeteorites(this);

        final List<Meteorite> meteorites = data.getValue();
        final boolean isNotEmpty = (meteorites != null && (meteorites.size() > 0));

        if (isNotEmpty) {
            mView.hideList();
        }

        if (mContextReference.get() != null && !NetworkUtil.hasConnectivity(mContextReference.get())) {
            mView.unableToFetch();
        }

        return data;
    }


    public void attachView(MeteoriteListView meteoriteListView) {
        mView = meteoriteListView;
        if (mContextReference.get() != null) {
            mGpsTracker = new GPSTracker(meteoriteListView.getGPSDelegate());
            meteoriteFetchService = MeteoriteServiceFactory.getMeteoriteService(mContextReference.get(), mGpsTracker);
            new AddressService().recoveryAddress(this);
        }
    }

    public void updateLocation() {
        if (mGpsTracker != null) {
            mGpsTracker.startLocationService();
        }
    }

    public LiveData<Meteorite> getMeteorite(Meteorite meteorite) {
        final Context context = mContextReference.get();
        LiveData<Meteorite> meteoriteLiveData = null;
        if (context != null) {
            final MeteoriteDao meteoriteDao = MeteoriteRepositoryFactory.getMeteoriteDao(context);
            meteoriteLiveData = meteoriteDao.getMeteoriteById(String.valueOf(meteorite.getId()));
        }
        return meteoriteLiveData;
    }

    @Override
    public void started() {
        mView.addressRecoveryStarted();
    }

    @Override
    public void finished() {
        mView.addressRecoveryFinished();
    }
}
