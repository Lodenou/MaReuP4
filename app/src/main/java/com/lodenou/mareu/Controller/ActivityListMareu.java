package com.lodenou.mareu.Controller;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lodenou.mareu.Event.DeleteReunionEvent;
import com.lodenou.mareu.Model.Reunion;
import com.lodenou.mareu.R;
import com.lodenou.mareu.View.ReunionAdapter;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_LONG;


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


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // on click on fab, creat new ActivityForm and start it
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_menu1:

                filterPerHour();
                return true;
            case R.id.action_menu2:

                filterPerRoom();
                return true;
            default:
                return super.onOptionsItemSelected(item);
    }}




    private void filterPerHour() {
        List<Reunion> mListReunion = ApiService.getmReunions();
        Comparator<Reunion> compareById = new Comparator<Reunion>() {
            @Override
            public int compare(Reunion o1, Reunion o2) {
                return o1.getTimeReu().compareTo(o2.getTimeReu());
            }
        };

        Collections.sort(mListReunion, compareById);
        mAdapter.notifyDataSetChanged();
    }

    private void filterPerRoom() {
        List<Reunion> mListReunion = ApiService.getmReunions();
        Comparator<Reunion> compareById = new Comparator<Reunion>() {
            @Override
            public int compare(Reunion o1, Reunion o2) {
                return o1.getRoomReu().compareTo(o2.getRoomReu());
            }
        };

        Collections.sort(mListReunion, compareById);
        mAdapter.notifyDataSetChanged();
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
