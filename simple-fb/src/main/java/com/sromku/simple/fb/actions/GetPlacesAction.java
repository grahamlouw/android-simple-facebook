package com.sromku.simple.fb.actions;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.reflect.TypeToken;
import com.sromku.simple.fb.SessionManager;
import com.sromku.simple.fb.entities.Account;
import com.sromku.simple.fb.entities.Place;
import com.sromku.simple.fb.listeners.OnActionListener;
import com.sromku.simple.fb.utils.Errors;
import com.sromku.simple.fb.utils.GraphPath;
import com.sromku.simple.fb.utils.Logger;
import com.sromku.simple.fb.utils.Utils;

import java.util.List;
import java.util.Locale;

public class GetPlacesAction extends GetAction<List<Place>> {

    private double mLatitude;
    private double mLongitude;
    private int mDistance;
    private int mLimit;

    public GetPlacesAction(SessionManager sessionManager) {
        super(sessionManager);

        mLatitude = 0.0f;
        mLongitude = 0.0f;
        mDistance = 0;
        mLimit = 20;
    }

    @Override
    protected String getGraphPath() {
        return "search"; // + GraphPath.ACCOUNTS;
    }

    @Override
    protected Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("type", "place");
        bundle.putString("center", String.format(
                Locale.US,
                "%f,%f",
                mLatitude,
                mLongitude));
        bundle.putInt("distance", mDistance);
        bundle.putInt("limit", mLimit);
        return(bundle);
    }

    @Override
    protected List<Place> processResponse(GraphResponse response) {
        Utils.DataResult<Place> dataResult = Utils.convert(response, new TypeToken<Utils.DataResult<Place>>() {}.getType());
        return dataResult.data;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public void setDistance(int distance) {
        mDistance = distance;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }
}
