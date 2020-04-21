package com.lodenou.mareu.Controller;

import androidx.appcompat.app.AppCompatActivity;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;



import com.lodenou.mareu.View.NothingSelectedSpinnerAdapter;
import com.lodenou.mareu.R;


public class ActivityForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] rooms = { "Bowser", "Daisy", "Luigi", "Mario", "Peach", "Toad"};
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
        // -----------------------------------------------------------------------------------------------------------------------------------
        //SPINNER SETTINGS

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        final Spinner spin = (Spinner) findViewById(R.id.Spinner);
        spin.setOnItemSelectedListener(this);


        //Creating the ArrayAdapter instance having the rooms' list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, rooms);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner and nothingselected
        spin.setAdapter(new NothingSelectedSpinnerAdapter(
                aa,
                R.layout.contact_spinner_row_nothing_selected,
                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                this));

        spin.setPrompt("Selectionnez une salle");


        // ------------------------------------------------------------------------------------------------------------------------------------
        final EditText fieldForm1 = findViewById(R.id.fields_1_form);
        final EditText fieldForm2 = findViewById(R.id.fields_2_form);
        // ------------------------------------------------------------------------------------------------------------------------------------

        // Validate Button
        Button mValidateButton = findViewById(R.id.validate_button);
        mValidateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the fields are empty or not
                if ((isEmpty1(fieldForm1) || isEmpty2(fieldForm2)|| isEmpty3(spin) ) == true ) {
                    Toast.makeText(getApplicationContext(),"Remplissez tous les champs", Toast.LENGTH_LONG).show();
                }
                else {
                    getFormInfos();
                    //TODO A REMPLIR POUR QUE CELA SAVE LES INFOS DE L ACTIVITE 2 ET INCORPORENT DANS LA LISTE DE L ACTIVITE 1
                    // Need to refresh ActivityListMareu
                    finish(); }
            }
        });
    }

    // Send form infos to ActivityListMareu
    public void getFormInfos() {
        EditText mEditText1 = findViewById(R.id.fields_1_form);
        EditText mEditText2 = findViewById(R.id.fields_2_form);

        // redondant, peut poser problème
        final Spinner spin = (Spinner) findViewById(R.id.Spinner);

        String myEditedText1 = mEditText1.getText().toString();
        String myEditedText2 = mEditText2.getText().toString();
        String spinnerText = spin.getSelectedItem().toString();

        Intent intent = new Intent(this, ActivityListMareu.class);
        intent.putExtra("edittext1", myEditedText1);
        intent.putExtra("edittext2", myEditedText2);
        intent.putExtra("spinner", spinnerText);

    }

    // OVERRIDES FOR SPINNER
        //When we select item in the spinner
        @Override
        public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
            //Toast.makeText(getApplicationContext(),rooms[position] , Toast.LENGTH_LONG).show();
            //moche
        }
        // default choice (no selected item in the spinner)
        @Override
        public void onNothingSelected (AdapterView < ? > parent){
            //TODO A REMPLIR
    }


    // fields empty methods
    private boolean isEmpty1(EditText fieldForm1) {
        return fieldForm1.getText().toString().trim().length() == 0;
    }
    private boolean isEmpty2(EditText fieldForm2) {
        return fieldForm2.getText().toString().trim().length() == 0;
    }
    //Bidouillage qui marche
    private boolean isEmpty3 (Spinner spin) {
        int selectedItemOfMySpinner = spin.getSelectedItemPosition();
        Object actualPositionOfMySpinner = spin.getItemAtPosition(selectedItemOfMySpinner);
        return actualPositionOfMySpinner == null;
    }




}









