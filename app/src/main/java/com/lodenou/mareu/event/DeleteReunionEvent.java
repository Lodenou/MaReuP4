package com.lodenou.mareu.event;

import com.lodenou.mareu.model.Reunion;

public class DeleteReunionEvent {





    // reunion to delete
    public Reunion reunion;


    // contructor
    public DeleteReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }
}
