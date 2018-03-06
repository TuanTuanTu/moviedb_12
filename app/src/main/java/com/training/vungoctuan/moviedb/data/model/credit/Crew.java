package com.training.vungoctuan.moviedb.data.model.credit;

/**
 * Created by vungoctuan on 3/5/18.
 */
public class Crew {
    private String mId;
    private String mCreditId;
    private String mName;
    private String mJob;
    private String mDepartment;
    private String mProfilePath;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getCreditId() {
        return mCreditId;
    }

    public void setCreditId(String creditId) {
        mCreditId = creditId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getJob() {
        return mJob;
    }

    public void setJob(String job) {
        mJob = job;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public void setDepartment(String department) {
        mDepartment = department;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }
}
