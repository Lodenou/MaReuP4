package com.lodenou.mareu.Event;

import com.lodenou.mareu.Model.Reunion;

public class DeleteReunionEvent {





    // reunion to delete
    public Reunion reunion;


    // contructor
    public DeleteReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }
}
