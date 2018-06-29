package com.amrat.databindingapp.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.amrat.databindingapp.BR;

import java.util.Calendar;

public class Movie extends BaseObservable implements Parcelable {

    private String name;
    private String description;
    private Calendar release;
    private int thumbnail;

    public Movie() {
    }

    public Movie(String name, String description, Calendar release, int thumbnail) {
        this.name = name;
        this.description = description;
        this.release = release;
        this.thumbnail = thumbnail;
    }

    protected Movie(Parcel in) {
        name = in.readString();
        description = in.readString();
        release = (Calendar) in.readSerializable();
        thumbnail = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    @Bindable
    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public Calendar getRelease() {
        return release;
    }

    @Bindable
    public void setRelease(Calendar release) {
        this.release = release;
        notifyPropertyChanged(BR.release);
    }

    @Bindable
    public int getThumbnail() {
        return thumbnail;
    }

    @Bindable
    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
        notifyPropertyChanged(BR.thumbnail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeSerializable(release);
        dest.writeInt(thumbnail);
    }
}