package eva.interview.backbase.cities;

import android.content.Context;

import java.util.List;

public class CitiesPresenterImpl implements Cities.Presenter{

    private Cities.View view;
    private Cities.Model model;

    public CitiesPresenterImpl(Cities.View view, Context context){
        this.view = view;
        this.model = new CitiesModelImpl(this, context);
    }

    @Override
    public void filterCity(String query) {
        model.filterCities(query);
    }

    @Override
    public void onCitiesLoaded(List<City> cities) {
        view.showCities(cities);
    }

    @Override
    public void onLoading() {
        view.showLoading();
    }
}
