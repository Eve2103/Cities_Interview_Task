package eva.interview.backbase.cities.ui.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eva.interview.backbase.R;
import eva.interview.backbase.cities.City;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private List<City> cityList = new ArrayList<>();

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CityViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int i) {
        cityViewHolder.bind(cityList.get(i));
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public void setCityList(List<City> cities) {
        cityList = new ArrayList<>(cities);
        notifyDataSetChanged();
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView locationTextView;

        CityViewHolder(@NonNull final View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.nameText);
            locationTextView = itemView.findViewById(R.id.locationText);
        }

        void bind(final City cityInfo) {
            titleTextView.setText(cityInfo.getName() + ", " + cityInfo.getCountry());
            String loc = "Lat: " + cityInfo.getCoord().getLat() + " Lon: " + cityInfo.getCoord().getLon(); //Todo
            locationTextView.setText(loc);
        }
    }

}
