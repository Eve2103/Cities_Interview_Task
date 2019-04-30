package eva.interview.backbase.cities;

import java.util.List;

import eva.interview.backbase.util.asset.AssetRetriever;

public class CitiesPresenterImpl implements Cities.Presenter {

    private Cities.View view;
    private Cities.Model model;
    private City selectedCity;

    public CitiesPresenterImpl(Cities.View view) {
        this.view = view;
        this.model = new CitiesModelImpl(this, AssetRetriever.getInstance());
    }

    @Override
    public void filterCity(String query) {
        if (query == null || query.isEmpty())
            model.getAllCities();
        else
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

    @Override
    public void changeView(Cities.View view) {
        this.view = view;
    }

    @Override
    public City getSelectedCity() {
        return selectedCity;
    }

    @Override
    public void setSelectedCity(City city) {
        selectedCity = city;
    }
}
