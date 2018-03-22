package com.training.vungoctuan.moviedb.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.training.vungoctuan.moviedb.util.Constant;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class Movie implements Parcelable {
    @SerializedName(Constant.ApiResultKey.API_MOVIE_KEY_ID)
    @Expose
    private int mId;
    @SerializedName(Constant.ApiResultKey.API_MOVIE_KEY_TITLE)
    @Expose
    private String mTitle;
    @SerializedName(Constant.ApiResultKey.API_MOVIE_KEY_VOTE_AVERAGE)
    @Expose
    private float mVoteAverage;
    @SerializedName(Constant.ApiResultKey.API_MOVIE_KEY_POSTER_PATH)
    @Expose
    private String mPosterPath;
    @SerializedName(Constant.ApiResultKey.API_MOVIE_KEY_BACKDROP_PATH)
    @Expose
    private String mBackdropPath;
    @SerializedName(Constant.ApiResultKey.API_MOVIE_KEY_OVERVIEW)
    @Expose
    private String mOverview;
    @SerializedName(Constant.ApiResultKey.API_MOVIE_KEY_RELEASE_DATE)
    @Expose
    private String mReleaseDate;
    private boolean mIsFavourite;

    public Movie() {
    }

    protected Movie(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mVoteAverage = in.readFloat();
        mPosterPath = in.readString();
        mBackdropPath = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
        mIsFavourite = in.readByte() != 0;
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

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public boolean isFavourite() {
        return mIsFavourite;
    }

    public void setFavourite(boolean favourite) {
        mIsFavourite = favourite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mTitle);
        parcel.writeFloat(mVoteAverage);
        parcel.writeString(mPosterPath);
        parcel.writeString(mBackdropPath);
        parcel.writeString(mOverview);
        parcel.writeString(mReleaseDate);
        parcel.writeByte((byte) (mIsFavourite ? 1 : 0));
    }
}
