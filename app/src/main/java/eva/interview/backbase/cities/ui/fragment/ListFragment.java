package eva.interview.backbase.cities.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.List;

import eva.interview.backbase.R;
import eva.interview.backbase.cities.City;
import eva.interview.backbase.cities.ui.CitiesActivity;
import eva.interview.backbase.cities.ui.adapter.CitiesAdapter;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private EditText editText;

    private CitiesAdapter citiesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler);
        progressBar = view.findViewById(R.id.progress);
        editText = view.findViewById(R.id.searchText);

        citiesAdapter = new CitiesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(citiesAdapter);
    }

    public void showList(List<City> cities){
        editText.setEnabled(true);
        citiesAdapter.setCityList(cities);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
        editText.setEnabled(false);
    }

    public void setSearchListener(TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }
}
