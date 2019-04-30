package eva.interview.backbase.cities;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import eva.interview.backbase.util.asset.AssetRetriever;

public class CitiesModelImpl implements Cities.Model {

    private static final String CITIES_FILE = "cities.json";

    private Cities.Presenter presenter;
    private Handler handler;
    private TreeMap<String, City> cities;

    private ExecutorService searchThread;

    private Future currentQuery;

    private AssetRetriever assetRetriever;

    public CitiesModelImpl(@NonNull Cities.Presenter presenter, AssetRetriever assetRetriever) {
        this.presenter = presenter;
        this.assetRetriever = assetRetriever;
        searchThread = Executors.newSingleThreadExecutor();
    }

    @Override
    public void filterCities(final String name) {
        presenter.onLoading();
        handler = new Handler();
        if (cities == null){
            searchThread.execute(new Runnable() {
                @Override
                public void run() {
                    cities = loadCitiesFromAssets();
                    post(new ArrayList<>(cities.values()));
                }
            });
        }

        if (currentQuery != null)
            currentQuery.cancel(true);

        currentQuery = searchThread.submit(new Runnable() {
            @Override
            public void run() {
                if (name == null || name.isEmpty()) {
                    post(new ArrayList<>(cities.values()));
                    return;
                }
                char lastNextChar = ++name.toCharArray()[name.length() - 1];
                post(new ArrayList<>(cities.subMap(name, name.substring(0, name.length() - 1) + lastNextChar).values()));
            }
        });
    }

    @Override
    public void getAllCities() {
        filterCities(null);
    }

    private TreeMap<String, City> loadCitiesFromAssets() {
        try {
            InputStream file = assetRetriever.retrieveFromAssetsAsStream(CITIES_FILE);
            JsonReader jsonReader = new JsonReader(new InputStreamReader(file));
            TreeMap<String, City> treeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            jsonReader.beginArray();
            Gson gson = new Gson();
            while (jsonReader.hasNext()) {
                City city = gson.fromJson(jsonReader, City.class);
                treeMap.put(city.getName() + ", " + city.getCountry(), city);
            }
            jsonReader.endArray();
            jsonReader.close();
            return treeMap;
        } catch (IOException ex) {
            return null; //TODO
        }
    }

    private void post(final List<City> cities) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                presenter.onCitiesLoaded(cities);
            }
        });
    }


}
