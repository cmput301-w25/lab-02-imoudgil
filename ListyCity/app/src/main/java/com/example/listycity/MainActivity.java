package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCityButton, confirmButton, deleteCityButton;
    LinearLayout addCityBlock;
    EditText cityInput;
    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        cityList = findViewById(R.id.city_list);
        addCityButton = findViewById(R.id.add_button);
        confirmButton = findViewById(R.id.confirm_button);
        deleteCityButton = findViewById(R.id.delete_button);
        addCityBlock = findViewById(R.id.add_city_block);
        cityInput = findViewById(R.id.city_input);

        // Initial data for the ListView
        String[] cities = {"Edmonton", "Vancouver"};
        dataList = new ArrayList<>(Arrays.asList(cities));

        // Set the adapter
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        // Show the input block when Add City button is clicked
        addCityButton.setOnClickListener(view -> {
            addCityBlock.setVisibility(View.VISIBLE);  // Show the input block
        });

        // add city after typing it
        confirmButton.setOnClickListener(view -> {
            String newCity = cityInput.getText().toString().trim();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);  // Add the new city
                cityAdapter.notifyDataSetChanged();  // Update the ListView
                cityInput.setText("");  // Clear the input field
                addCityBlock.setVisibility(View.GONE);  // Hide the input block again
            }
        });

        // Deleting a citty after selecting it
        deleteCityButton.setOnClickListener(view -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, selectedCity + " removed from the list", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "No city selected", Toast.LENGTH_SHORT).show();
            }
        });

        // Selecting a city by clicking it
        cityList.setOnItemClickListener((adapterView, view, position, id) -> {
            selectedCity = dataList.get(position);
            Toast.makeText(MainActivity.this, "Selected: " + selectedCity, Toast.LENGTH_SHORT).show();
        });
    }
}
