package com.lodenou.mareu.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lodenou.mareu.Model.Reunion;
import com.lodenou.mareu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReunionFragment extends Fragment {


    // Déclare le recyclerView
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private List<Reunion> mReunionList;
    private ReunionAdapter mAdapter;

    // Constructeur
    public ReunionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        ButterKnife.bind(this, view);
        // 4 appels durant la création de l'UI
        this.configureRecyclerView();

        //TODO FAIRE UNE METHODE OÙ L INTEGRER
       // this.updateUI(reunions);

        return view;
    }


    private void configureRecyclerView() {

        // Configure RecyclerView, Adapter, LayoutManager & glue it together

            // Reset la liste
            this.mReunionList = new ArrayList<>();
            // Create adapter passing the list of users
            this.mAdapter = new ReunionAdapter(this.mReunionList);
            // Attach the adapter to the recyclerview to populate items
            this.mRecyclerView.setAdapter(this.mAdapter);
            //Set layout manager to position the items
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

//    private void updateUI(List<Reunion> reunions){
//        mReunionList.addAll(reunions);
//        mAdapter.notifyDataSetChanged();
//    }



}
