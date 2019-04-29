package eva.interview.backbase.cities.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.List;

import eva.interview.backbase.R;
import eva.interview.backbase.cities.Cities;
import eva.interview.backbase.cities.CitiesPresenterImpl;
import eva.interview.backbase.cities.City;
import eva.interview.backbase.cities.ui.adapter.CitySelectedListener;
import eva.interview.backbase.cities.ui.fragment.ListFragment;
import eva.interview.backbase.cities.ui.fragment.MapFragment;

public class CitiesActivity extends AppCompatActivity implements Cities.View {

    private ListFragment listFragment;
    private MapFragment mapFragment;
    private Cities.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
        mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);

        if (savedInstanceState == null) {
            presenter = new CitiesPresenterImpl(this, getApplicationContext());
            presenter.filterCity("");
            if (isPortrait()) {
                hideFragment(mapFragment);
            }
        } else {
            presenter = (Cities.Presenter) getLastCustomNonConfigurationInstance();
            presenter.changeView(this);
            listFragment.setSelectedCity(presenter.getSelectedCity());
            if (!isPortrait()){
                showFragment(listFragment);
                showFragment(mapFragment);
            }
        }

        listFragment.setSearchListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.filterCity(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listFragment.setSelectedCityListener(new CitySelectedListener() {
            @Override
            public void onCitySelected(City city) {
                if (isPortrait()) {
                    showFragment(mapFragment);
                    hideFragment(listFragment);
                }
                mapFragment.setCity(city);
                presenter.setSelectedCity(city);
            }
        });
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    public void showCities(List<City> cities) {
        listFragment.showList(cities);
    }

    @Override
    public void showLoading() {
        listFragment.showLoading();
    }

    @Override
    public void onBackPressed() {
        if (isPortrait() && !mapFragment.isHidden()) {
            hideFragment(mapFragment);
            showFragment(listFragment);
        } else {
            super.onBackPressed();
        }
    }

    private boolean isPortrait() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    private void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }
}
