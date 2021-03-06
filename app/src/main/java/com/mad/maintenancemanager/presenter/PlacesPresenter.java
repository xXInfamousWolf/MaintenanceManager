package com.mad.maintenancemanager.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mad.maintenancemanager.api.DatabaseHelper;
import com.mad.maintenancemanager.model.MaintenanceTask;
import com.mad.maintenancemanager.model.TempPlace;

import java.util.List;

/**
 * Class that provides Google place information from place IDs
 */

public class PlacesPresenter {


    private GoogleApiClient mGoogleApiClient;
    private Context mContext;
    private GoogleMap mGoogleMap;

    public PlacesPresenter(Context context, GoogleApiClient googleApiClient, GoogleMap googleMap) {
        mContext = context;
        mGoogleApiClient = googleApiClient;
        mGoogleMap = googleMap;
    }

    /**
     * Sets the task places on the mapview and stores the needed information to start a fragment
     */
    public void setTaskPlaces() {
        DatabaseHelper.getInstance().getTradieTasks(new DatabaseHelper.IExternalTasksListener() {
            @Override
            public void onTasks(final List<MaintenanceTask> tasks) {
                for (MaintenanceTask task : tasks) {
                    String placeID = task.getTaskLocationData();
                    Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeID)
                            .setResultCallback(new ResultCallback<PlaceBuffer>() {
                                @Override
                                public void onResult(PlaceBuffer places) {
                                    if (places.getStatus().isSuccess() && places.getCount() > 0) {
                                        final Place myPlace = places.get(0);
                                        Marker m = mGoogleMap.addMarker(new MarkerOptions()
                                                .position(myPlace.getLatLng())
                                                .title(myPlace.getName().toString()));
                                        m.setTag(new TempPlace(myPlace.getId(), myPlace.getName().toString()));
                                    } else {
                                    }

                                    places.release();
                                }
                            });
                }

            }
        });
    }


}
