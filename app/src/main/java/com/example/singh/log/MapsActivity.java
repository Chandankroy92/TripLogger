  package com.example.singh.log;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.example.singh.log.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    public void onSearch(View view)
    {
        EditText et=(EditText)findViewById(R.id.etsearch);
        String location=et.getText().toString();
        List<Address> addressList=null;
        if(location!=null||!location.equals(""))
        {
            Geocoder geocoder=new Geocoder((this));
            try
            {
                addressList=geocoder.getFromLocationName(location, 1);
            }
            catch(IOException e)
            {
                e. printStackTrace();
            }
            Address address=addressList.get(0);
            LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    public void setChangeType(View view) {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE) {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        } else if (mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }



    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
