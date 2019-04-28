package eva.interview.backbase.cities;

import java.util.List;

//Using mvp architecture but i personally prefer mvvm.
public interface Cities {

    interface Model {
        void filterCities(String name);

        void getAllCities();
    }

    interface Presenter {
        void filterCity(String query);

        void onCitiesLoaded(List<City> cities);

        void onLoading();
    }

    interface View {
        void showCities(List<City> cities);

        void showLoading();
    }

}
