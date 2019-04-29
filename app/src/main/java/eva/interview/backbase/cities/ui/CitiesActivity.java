package eva.interview.backbase.cities.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.List;

import eva.interview.backbase.R;
import eva.interview.backbase.cities.Cities;
import eva.interview.backbase.cities.CitiesPresenterImpl;
import eva.interview.backbase.cities.City;
import eva.interview.backbase.cities.ui.fragment.ListFragment;

public class CitiesActivity extends AppCompatActivity implements Cities.View {

    private ListFragment listFragment;
    private Cities.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
        if (savedInstanceState == null) {
            presenter = new CitiesPresenterImpl(this, getApplicationContext());
            presenter.filterCity("");
        } else {
            presenter = (Cities.Presenter) getLastCustomNonConfigurationInstance();
            presenter.changeView(this);
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
}
