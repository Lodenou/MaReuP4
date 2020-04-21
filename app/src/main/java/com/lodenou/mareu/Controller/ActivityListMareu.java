package com.lodenou.mareu.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lodenou.mareu.Model.Reunion;
import com.lodenou.mareu.R;
import com.lodenou.mareu.View.ReunionViewHolder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;





public class ActivityListMareu extends AppCompatActivity {

    private List <Reunion> mReunionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mareu);

        //FIXME NE MARCHE PAS
        //We took informations from ActivityForm
        Intent intent = getIntent();
        String myGettedEditedText1 = intent.getStringExtra("edittext1");
        String myGettedEditedText2 = intent.getStringExtra("edittext2");
        String myGettedSpinner = intent.getStringExtra("spinner");


        //FIXME FAIT CRASH L APP
      //  TextView mTextViewTime = findViewById(R.id.textView_time);
      //  mTextViewTime.setText(myGettedEditedText1);




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent activity2Intent = new Intent(getApplicationContext(), ActivityForm.class);
                startActivity(activity2Intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // Test for the RecyclerView
    private ArrayList<Reunion> initReunions() {
        ArrayList<Reunion> list = new ArrayList<>();

        list.add(new Reunion("Cinque Terre", "The coastline, the five villages in Italy.", "https://bit.ly/CBImageCinque", "edddd"));
        list.add(new Reunion("Paris", "Paris is the capital city of France", "eeeeeeezzzzzz", "ddzzdzddzdzqdzqdzqdz"));
        list.add(new Reunion("Rio de Janeiro", "Rio has been one of Brazil's most popular destinations.", "https://bit.ly/CBImageRio","zddzdzddzdz"));
        list.add(new Reunion("Sydney", "Sydney is the state capital of New South Wales.", "https://bit.ly/CBImageSydney", "ddzdzqqdzqdz"));

        return list;
    }



}
