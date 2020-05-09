package com.lodenou.mareu.controller;

import com.lodenou.mareu.model.Reunion;

import java.util.List;

public interface ReunionApiService {

    List<Reunion> getmReunions();

    void setmReunions(List<Reunion> mReunions);

    void deleteReunion(Reunion reunion);
}
