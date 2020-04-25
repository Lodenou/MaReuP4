package com.lodenou.mareu.Controller;

import com.lodenou.mareu.Model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiService {

    private static List<Reunion> mReunions = new ArrayList<>();




    public static List<Reunion> getmReunions() {
        return mReunions;
    }

    public static void setmReunions(List<Reunion> mReunions) {
        ApiService.mReunions = mReunions;
    }

    // delete a reunion when it's called
    public static void deleteReunion(Reunion reunion) {
        mReunions.remove(reunion);
    }



    // Used in adapter @onBindViewHolder
    public static List<String> REUNION_LIST_ALPHABETIC = Arrays.asList(

            ("Réunion A"),
            ("Réunion B"),
            ("Réunion C"),
            ("Réunion D"),
            ("Réunion E"),
            ("Réunion F"),
            ("Réunion G"),
            ("Réunion H"),
            ("Réunion I"),
            ("Réunion J"),
            ("Réunion K"),
            ("Réunion L"),
            ("Réunion M"),
            ("Réunion N"),
            ("Réunion O"),
            ("Réunion P")

    );

    public static List<String> getReunionListAlphabetic() {
        return REUNION_LIST_ALPHABETIC;
    }
}
