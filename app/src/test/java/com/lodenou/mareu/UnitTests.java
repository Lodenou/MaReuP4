package com.lodenou.mareu;

import com.lodenou.mareu.Controller.ApiService;
import com.lodenou.mareu.Model.Reunion;

import org.junit.Test;

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

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }



    @Test
    public void addMeetingWithSuccess() {
       int x = 0;
        List<Reunion> reunionList = mService.getmReunions();
        x = reunionList.size();
        mService.getmReunions().add(mReunion);
        assertTrue(reunionList.size() == x+1);
    }



}