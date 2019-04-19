package com.bywaleed.eventon;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {

    String title;
    String description;
    String organisation;
    String date;
    String time;
    String booking;
    String location;
    String mapLocation;
    String price;
    int background;
    int logo;


    public Event() {
    }

    public Event(String title, String description, String organisation, String date, String time, String booking, String location, String mapLocation, String price, int background, int logo) {
        this.title = title;
        this.description = description;
        this.organisation = organisation;
        this.date = date;
        this.time = time;
        this.booking = booking;
        this.location = location;
        this.mapLocation = mapLocation;
        this.price = price;
        this.background = background;
        this.logo = logo;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(String mapLocation) {
        this.mapLocation = mapLocation;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    protected Event(Parcel in) {
        background = in.readInt();
        logo = in.readInt();
        title = in.readString();
        date = in.readString();
        time = in.readString();
        description = in.readString();
        booking = in.readString();
        location = in.readString();
        mapLocation = in.readString();
        organisation = in.readString();
        price = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(background);
        dest.writeInt(logo);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(description);
        dest.writeString(booking);
        dest.writeString(location);
        dest.writeString(mapLocation);
        dest.writeString(organisation);
        dest.writeString(price);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}