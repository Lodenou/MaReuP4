package com.lodenou.mareu;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.lodenou.mareu.controller.ApiService;
import com.lodenou.mareu.model.Reunion;
import com.lodenou.mareu.view.ReunionAdapter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {

    private ApiService mService;
    private Reunion mReunion;
    private ReunionAdapter mAdapter;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void addMeetingWithSuccess() {
        int x = 0;
        List<Reunion> reunionList = mService.getmReunions();
        x = reunionList.size();
        // => ActivityForm l 182  copy of this function to test it
        mService.getmReunions().add(mReunion);
        assertTrue(reunionList.size() == x + 1);
    }

    @Test
    public void deleteMeetingWithSuccess() {
        //add a reunion to get 1 element in the list
        mService.getmReunions().add(mReunion);
        Reunion reunionToDelete = mService.getmReunions().get(0);
        mService.deleteReunion(reunionToDelete);
        assertFalse(mService.getmReunions().contains(reunionToDelete));
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Test
    public void hourFilterWorks() {
        List<Reunion> mListReunion1 = new ArrayList<>();


        Reunion mReunion0;
        Reunion mReunion1;
        Reunion mReunion2;
        mListReunion1.add(mReunion0 = new Reunion("Blablabla", "08h20", "Daisy", "hhhd@gg.dd"));
        mListReunion1.add(mReunion1 = new Reunion("Blablabla", "09h20", "Daisy", "hhhd@gg.dd"));
        mListReunion1.add(mReunion2 = new Reunion("Blablabla", "08h20", "Daisy", "hhhd@gg.dd"));


        mListReunion1.removeIf(mReunion -> (!mReunion.getTimeReu().contains("08h")));


        assertTrue(mListReunion1.contains(mReunion0));
        assertTrue(mListReunion1.contains(mReunion2));
        assertFalse(mListReunion1.contains(mReunion1));

    }

    @Test
    public void roomFilterWorks() {
        List<Reunion> mListReunion1 = new ArrayList<>();
        Reunion mReunion0;
        Reunion mReunion1;
        Reunion mReunion2;
        mListReunion1.add(mReunion0 = new Reunion("Blablabla", "08h20", "Bowser", "hhhd@gg.dd"));
        mListReunion1.add(mReunion1 = new Reunion("Blablabla", "09h20", "Daisy", "hhhd@gg.dd"));
        mListReunion1.add(mReunion2 = new Reunion("Blablabla", "08h20", "Bowser", "hhhd@gg.dd"));


        mListReunion1.removeIf(mReunion -> (!mReunion.getRoomReu().contains("Bowser")));

        assertTrue(mListReunion1.contains(mReunion0));
        assertTrue(mListReunion1.contains(mReunion2));
        assertFalse(mListReunion1.contains(mReunion1));

    }
}