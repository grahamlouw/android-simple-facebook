package com.sromku.simple.fb.actions;

import android.os.Bundle;

import com.facebook.GraphResponse;
import com.google.gson.reflect.TypeToken;
import com.sromku.simple.fb.SessionManager;
import com.sromku.simple.fb.entities.Account;
import com.sromku.simple.fb.entities.Place;
import com.sromku.simple.fb.utils.GraphPath;
import com.sromku.simple.fb.utils.Utils;

import java.util.List;

public class GetPlacesAction extends GetAction<List<Place>> {

    private double mLatitude;
    private double mLongitude;
    private int mDistance;

    public GetPlacesAction(SessionManager sessionManager) {
        super(sessionManager);

        mLatitude = 0.0f;
        mLongitude = 0.0f;
        mDistance = 0;
    }

    @Override
    protected String getGraphPath() {
        return "search"; // + GraphPath.ACCOUNTS;
    }

    @Override
    protected Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("type", "place");
        bundle.putString("center", mLatitude + "," + mLongitude);
        bundle.putInt("distance", mDistance);
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
}
