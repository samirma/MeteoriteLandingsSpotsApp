package com.antonio.samir.meteoritelandingsspots.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Meteorite implements Parcelable {
    private String mass;

    private String id;

    private String nametype;

    private String recclass;

    private String name;

    private String fall;

    private String year;

    private String reclong;

    private String reclat;

    public String getMass ()
    {
        return mass;
    }

    public void setMass (String mass)
    {
        this.mass = mass;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getNametype ()
    {
        return nametype;
    }

    public void setNametype (String nametype)
    {
        this.nametype = nametype;
    }

    public String getRecclass ()
    {
        return recclass;
    }

    public void setRecclass (String recclass)
    {
        this.recclass = recclass;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getFall ()
    {
        return fall;
    }

    public void setFall (String fall)
    {
        this.fall = fall;
    }

    public String getYear ()
    {
        return year;
    }

    public void setYear (String year)
    {
        this.year = year;
    }

    public String getReclong ()
    {
        return reclong;
    }

    public void setReclong (String reclong)
    {
        this.reclong = reclong;
    }

    public String getReclat ()
    {
        return reclat;
    }

    public void setReclat (String reclat)
    {
        this.reclat = reclat;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mass);
        dest.writeString(this.id);
        dest.writeString(this.nametype);
        dest.writeString(this.recclass);
        dest.writeString(this.name);
        dest.writeString(this.fall);
        dest.writeString(this.year);
        dest.writeString(this.reclong);
        dest.writeString(this.reclat);
    }

    public Meteorite() {
    }

    protected Meteorite(Parcel in) {
        this.mass = in.readString();
        this.id = in.readString();
        this.nametype = in.readString();
        this.recclass = in.readString();
        this.name = in.readString();
        this.fall = in.readString();
        this.year = in.readString();
        this.reclong = in.readString();
        this.reclat = in.readString();
    }

    public static final Parcelable.Creator<Meteorite> CREATOR = new Parcelable.Creator<Meteorite>() {
        @Override
        public Meteorite createFromParcel(Parcel source) {
            return new Meteorite(source);
        }

        @Override
        public Meteorite[] newArray(int size) {
            return new Meteorite[size];
        }
    };
}
