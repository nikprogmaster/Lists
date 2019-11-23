package com.example.lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LecturesAdapter adapter;
    private LecturesProvider lecturesProvider = new LecturesProvider();
    private LectorsSpinnerAdapter lectorsSpinnerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLecturesRecycler();
        initLectorsSpinner();
        initWeeksSpinner();
    }

    private void initLectorsSpinner() {
        Spinner spinner = findViewById(R.id.spinner);
        lectorsSpinnerAdapter = new LectorsSpinnerAdapter();
        final List<String> lectors = lecturesProvider.getLectors();
        Collections.sort(lectors);
        lectors.add(0, getResources().getString(R.string.all));
        lectorsSpinnerAdapter.setLectors(lectors);
        spinner.setAdapter(lectorsSpinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    adapter.setLectures(lecturesProvider.getmLectures());
                } else{
                    String lectorName = lectors.get(i);
                    List<Lecture> filteredLectures = lecturesProvider.filterBy(lectorName);
                    adapter.setLectures(filteredLectures);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initWeeksSpinner () {
        Spinner spinner = findViewById(R.id.spinner_weeks);
        spinner.setAdapter(new WeeksSpinnerAdapter());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DisplayMode selectedDisplayMode = DisplayMode.values()[position];
                adapter.setDisplayMode(selectedDisplayMode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initLecturesRecycler() {
        RecyclerView recyclerView = findViewById(R.id.lectures_recycler_view);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LecturesAdapter(getResources());
        recyclerView.setAdapter(adapter);
        adapter.setLectures(lecturesProvider.getmLectures());
    }
}
