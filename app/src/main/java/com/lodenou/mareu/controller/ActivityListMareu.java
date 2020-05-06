package com.lodenou.mareu.controller;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lodenou.mareu.event.DeleteReunionEvent;
import com.lodenou.mareu.model.Reunion;
import com.lodenou.mareu.R;
import com.lodenou.mareu.view.ReunionAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ActivityListMareu extends AppCompatActivity {


    // Déclare le recyclerView
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    private ReunionAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mareu);
        ButterKnife.bind(this);

        initList();
        initFab();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.filter_1:
                filterPerHour("07h");
                return true;
            case R.id.filter_2:
                filterPerHour("08h");
                return true;
            case R.id.filter_3:
                filterPerHour("09h");
                return true;
            case R.id.filter_4:
                filterPerHour("10h");
                return true;
            case R.id.filter_5:
                filterPerHour("11h");
                return true;
            case R.id.filter_6:
                filterPerHour("12h");
                return true;
            case R.id.filter_7:
                filterPerHour("13h");
                return true;
            case R.id.filter_8:
                filterPerHour("14h");
                return true;
            case R.id.filter_9:
                filterPerHour("15h");
                return true;
            case R.id.filter_10:
                filterPerHour("16h");
                return true;
            case R.id.filter_11:
                filterPerHour("17h");
                return true;
            case R.id.filter_12:
                filterPerHour("18h");
                return true;
            case R.id.filter_13:
                filterPerHour("19h");
                return true;
            case R.id.filter_14:
                filterPerHour("20h");
                return true;


            case R.id.filter_room_1:
                filterPerRoom("Bowser");
                return true;
            case R.id.filter_room_2:
                filterPerRoom("Daisy");
                return true;
            case R.id.filter_room_3:
                filterPerRoom("Donkey");
                return true;
            case R.id.filter_room_4:
                filterPerRoom("Luigi");
                return true;
            case R.id.filter_room_5:
                filterPerRoom("Mario");
                return true;
            case R.id.filter_room_6:
                filterPerRoom("Peach");
                return true;
            case R.id.filter_room_7:
                filterPerRoom("Tiara");
                return true;
            case R.id.filter_room_8:
                filterPerRoom("Toad");
                return true;
            case R.id.filter_room_9:
                filterPerRoom("Wario");
                return true;
            case R.id.filter_room_10:
                filterPerRoom("Yoshi");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filterPerHour(String hour) {

        List<Reunion> mListReunion = ApiService.getmReunions();

        mListReunion.removeIf(mReunion -> (!mReunion.getTimeReu().contains(hour)));
        mAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filterPerRoom(String room) {
        List<Reunion> mListReunion = ApiService.getmReunions();

        mListReunion.removeIf(mReunion -> (!mReunion.getRoomReu().contains(room)));
        mAdapter.notifyDataSetChanged();

    }

    private void initFab(){
        FloatingActionButton fab = findViewById(R.id.fab);


        // change the color of the fab' cross
        DrawableCompat.setTint(
                DrawableCompat.wrap(fab.getDrawable()),
                ContextCompat.getColor(getApplicationContext(), R.color.colorWhite)
        );

        // on click on fab, creat new ActivityForm and start it
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent activity2Intent = new Intent(getApplicationContext(), ActivityForm.class);
                startActivity(activity2Intent);
            }
        });
    }

    private void initList() {
        final List<Reunion> reunions = ApiService.getmReunions();

        // Create adapter passing the list of users
        this.mAdapter = new ReunionAdapter(reunions);
        // Attach the adapter to the recyclerview to populate items
        this.mRecyclerView.setAdapter(this.mAdapter);
        //Set layout manager to position the items
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {
        ApiService.deleteReunion(event.reunion);
        initList();
    }

}
