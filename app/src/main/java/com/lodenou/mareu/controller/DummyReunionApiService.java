package com.lodenou.mareu.controller;

import com.lodenou.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.List;

// Fake API to get Reunion from everywhere
public class DummyReunionApiService implements ReunionApiService {

    private static List<Reunion> mReunions = new ArrayList<>();


    // Getter setter
    @Override
    public List<Reunion> getmReunions() {
        return mReunions;
    }
    @Override
    public void setmReunions(List<Reunion> mReunions) {
        DummyReunionApiService.mReunions = mReunions;
    }

    // delete a reunion when it's called
    public void deleteReunion(Reunion reunion) {
        mReunions.remove(reunion);
    }

}
