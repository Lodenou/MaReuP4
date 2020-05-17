package com.lodenou.mareu.controller;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
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
import com.lodenou.mareu.di.DI;
import com.lodenou.mareu.event.DeleteReunionEvent;
import com.lodenou.mareu.model.Reunion;
import com.lodenou.mareu.R;
import com.lodenou.mareu.view.ReunionAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ActivityListMareu extends AppCompatActivity {


    // Déclare le recyclerView
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    Date mTheTime = null;

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

    // Allow horizontal screen
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
    public void onRestart() {
        super.onRestart();
        Intent i = new Intent(ActivityListMareu.this, ActivityListMareu.class);  //your class
        startActivity(i);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Selection for the filter menu
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuItem mChooseTime = findViewById(R.id.action_menu1);

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh_button:
                onRestart();
                return true;
            case R.id.action_menu1:
                initiateTimePickerMenu();
                return true;
//                mTheTime = "a";
//                String subTime = mTheTime.substring(0, 3);

//                filterPerHour(subTime);
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
    public void filterPerHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 1);
//        mListReunion.removeIf(mReunion -> (!(mReunion.getTimeReu().after(date)&& mReunion.getTimeReu().before(calendar.getTime()))));
        mAdapter.setFilterDate(date);
        mAdapter.notifyDataSetChanged();
    }

//}

//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filterPerRoom(String room) {
        mAdapter.setFilterRoom(room);
        mAdapter.notifyDataSetChanged();
        //mListReunion.removeIf(mReunion -> (!mReunion.getRoomReu().contains(room)));


    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initiateTimePickerMenu() {
    TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityListMareu.this, (timePicker, hourOfDay, minutes) -> {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, minutes);
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        SimpleDateFormat sdf = new SimpleDateFormat("HH h mm", Locale.FRANCE);
//            mTheTime.setText(sdf.format(c.getTime()));
        mTheTime = c.getTime();
        filterPerHour(mTheTime);
//        mTheTime = sdf.format(c.getTime());
    }, 0, 0, false);
                timePickerDialog.show();

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
        final List<Reunion> reunions = DI.getNeighbourApiService().getmReunions();

        // Create adapter
        this.mAdapter = new ReunionAdapter(reunions);
        // Attach the adapter to the recyclerview to populate items
        this.mRecyclerView.setAdapter(this.mAdapter);
        //Set layout manager to position the items
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {
        DI.getNeighbourApiService().deleteReunion(event.reunion);
        initList();
    }

}
