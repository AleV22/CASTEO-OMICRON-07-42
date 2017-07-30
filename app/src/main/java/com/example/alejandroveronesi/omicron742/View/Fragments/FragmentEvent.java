package com.example.alejandroveronesi.omicron742.View.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alejandroveronesi.omicron742.Model.POJO.Event;
import com.example.alejandroveronesi.omicron742.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.alejandroveronesi.omicron742.R.id.parent;


public class FragmentEvent extends Fragment implements AdapterView.OnItemSelectedListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_event, container, false);

        //Get the widgets reference from XML layout
        final TextView tv = (TextView) view.findViewById(R.id.tv);
        NumberPicker np = (NumberPicker) view.findViewById(R.id.np);

        //Set TextView text color
        tv.setTextColor(getResources().getColorStateList(R.color.colorPrimaryDark));

        //Set the minimum value of NumberPicker
        np.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        np.setMaxValue(59);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);

        //Set a value change listener for NumberPicker
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                tv.setText( "T limite = " + newVal  );
            }
        });

        Spinner spinner = (Spinner) view.findViewById(R.id.menuContactos);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.planets_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        String sSelected = parent.getItemAtPosition(pos).toString();
        Toast.makeText(getContext(),sSelected,Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
