package eva.interview.backbase.cities.ui.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eva.interview.backbase.R;
import eva.interview.backbase.cities.City;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private List<City> cityList = new ArrayList<>();

    private CitySelectedListener listener;
    private View.OnClickListener infoClickListener;

    private int selectedItemPosition = -1;
    private City selectedCity;

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
        selectedItemPosition = cityList.indexOf(selectedCity);
        notifyDataSetChanged();
    }

    public void setCitySelectedListener(CitySelectedListener listener) {
        this.listener = listener;
    }

    public void setInfoClickListener(View.OnClickListener listener) {
        infoClickListener = listener;
    }

    public void setSelectedCity(City city) {
        selectedCity = city;
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        private View root;
        private TextView titleTextView;
        private TextView locationTextView;
        private Button infoBtn;

        CityViewHolder(@NonNull final View itemView) {
            super(itemView);

            root = itemView;
            titleTextView = itemView.findViewById(R.id.nameText);
            locationTextView = itemView.findViewById(R.id.locationText);
            infoBtn = itemView.findViewById(R.id.infoBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        notifyItemChanged(selectedItemPosition);
                        selectedItemPosition = -1;
                        listener.onCitySelected(cityList.get(getAdapterPosition()));
                        selectedItemPosition = getAdapterPosition();
                        notifyItemChanged(selectedItemPosition);
                    }
                }
            });
            if (infoClickListener != null)
                infoBtn.setOnClickListener(infoClickListener);
        }

        void bind(final City cityInfo) {
            titleTextView.setText(cityInfo.getName() + ", " + cityInfo.getCountry());
            String loc = "Lat: " + cityInfo.getCoord().getLat() + " Lon: " + cityInfo.getCoord().getLon(); //Todo
            locationTextView.setText(loc);
            root.setBackgroundColor(selectedItemPosition == getAdapterPosition() ? Color.LTGRAY : Color.WHITE);
        }
    }

}
