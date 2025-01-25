package com.kairos.trashtrack;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomResource implements Parcelable {  // Renamed to CustomResource
    private String title;
    private String description;
    private int imageResId;

    public CustomResource(String title, String description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    protected CustomResource(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageResId = in.readInt();
    }

    public static final Creator<CustomResource> CREATOR = new Creator<CustomResource>() {
        @Override
        public CustomResource createFromParcel(Parcel in) {
            return new CustomResource(in);
        }

        @Override
        public CustomResource[] newArray(int size) {
            return new CustomResource[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(imageResId);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
