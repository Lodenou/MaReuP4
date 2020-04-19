package com.lodenou.mareu.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TimePicker;


import com.lodenou.mareu.R;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class ActivityForm extends AppCompatActivity {


    EditText mChooseTime;
    TimePickerDialog mTimePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        iniatiateEverything();

    }

    @Override
    public void onBackPressed() {
        // End the current activity when pressing on back button
        finish();
    }

    @Override
    // Back button setting
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }




    // Clean the onCreate
    private void iniatiateEverything() {
        // bouton Back en haut à gauche
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        // Timepicker
        mChooseTime = findViewById(R.id.fields_1_form);
        mChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityForm.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        mChooseTime.setText(hourOfDay + "h" + minutes);
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });


        //FIXME marche mal + on ne peut pas cliquer dessus
        //Attach the scrollchoice from @activity_form.xml
        final ScrollChoice mScrollChoice = (ScrollChoice)  findViewById(R.id.scroll_choice);

        //List for scrolling menu 
        final List<String> dataScroll = new ArrayList<>();
            dataScroll.add("Bowser");
            dataScroll.add("Daisy");
            dataScroll.add("Luigi");
            dataScroll.add("Mario");
            dataScroll.add("Peach");
            dataScroll.add("Toad");


        // Default choice
        mScrollChoice.addItems(dataScroll,5);

         // Attach listener to listen to know which item was selected
        mScrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                Log.d("webi",name);
            }
        });


        mScrollChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        }




    }









