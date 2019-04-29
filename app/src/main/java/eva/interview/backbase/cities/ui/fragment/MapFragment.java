package eva.interview.backbase.cities.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import eva.interview.backbase.cities.City;

public class MapFragment extends SupportMapFragment {

    private GoogleMap map;
    private City city;

    public void setCity(City city) {
        this.city = city;
        showCity();
    }

    private void showCity() {
        if (map != null && city != null) {
            CameraUpdate cameraUpdate = CameraUpdateFactory
                    .newCameraPosition(CameraPosition.builder()
                            .zoom(12f)
                            .target(new LatLng(city.getCoord().getLat(),
                                    city.getCoord().getLon()))
                            .build());
            map.animateCamera(cameraUpdate);
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                showCity();
            }
        });
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }
}

