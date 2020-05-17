package com.lodenou.mareu;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;

import com.lodenou.mareu.controller.ActivityListMareu;
import com.lodenou.mareu.controller.DummyReunionApiService;
import com.lodenou.mareu.di.DI;
import com.lodenou.mareu.model.Reunion;
import com.lodenou.mareu.view.ReunionAdapter;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {

    private DummyReunionApiService mService;
    private Reunion mReunion;
    private ReunionAdapter mAdapter;
    ActivityListMareu activityListMareu;

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



    @Test
    //FIXME Probablement impossible à tester
    public void hourFilterWorks() {
        List<Reunion> mListReunion1 = new ArrayList<>();
//        List<Reunion> mListReunion1 = DI.getNeighbourApiService().getmReunions();
        Reunion mReunion0;
        Reunion mReunion1;
        Reunion mReunion2;



        Calendar calendar0 = Calendar.getInstance();
        calendar0.set(2020, 5, 13, 9, 59, 59);
        Date date0 = calendar0.getTime();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020, 5, 13, 9, 59, 59);
        Date date1 = calendar1.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2020, 5, 13, 11, 59, 59);
        Date date2 = calendar2.getTime();

        mListReunion1.add(mReunion0 = new Reunion("Blablabla", date0 , "Daisy", "hhhd@gg.dd"));
        mListReunion1.add(mReunion1 = new Reunion("Blablabla", date1, "Daisy", "hhhd@gg.dd"));
        mListReunion1.add(mReunion2 = new Reunion("Blablabla", date2, "Daisy", "hhhd@gg.dd"));
        mReunion0 = mAdapter.verifFilter().get(0);
        mReunion1 = mAdapter.verifFilter().get(1);
        mReunion2 = mAdapter.verifFilter().get(2);


        // test
//            mListReunion1.remove(mReunion2);

//            activityListMareu.filterPerHour(date0);
        mAdapter.setFilterDate(date0);

        assertTrue(mListReunion1.contains(mReunion0));
        assertTrue(mListReunion1.contains(mReunion1));
        assertFalse(mListReunion1.contains(mReunion2));
    }

    @Test
    //FIXME Probablement impossible à tester
    public void roomFilterWorks() {
        List<Reunion> mListReunion1 = new ArrayList<>();
        String filterRoom1;
        Reunion mReunion0;
        Reunion mReunion1;
        Reunion mReunion2;
        Calendar calendar0 = Calendar.getInstance();
        calendar0.set(2020, 5, 13, 9, 59, 59);
        Date date0 = calendar0.getTime();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020, 5, 13, 9, 59, 59);
        Date date1 = calendar1.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2020, 5, 13, 11, 59, 59);
        Date date2 = calendar2.getTime();
        mListReunion1.add(mReunion0 = new Reunion("Blablabla", date0, "Bowser", "hhhd@gg.dd"));
        mListReunion1.add(mReunion1 = new Reunion("Blablabla", date1, "Daisy", "hhhd@gg.dd"));
        mListReunion1.add(mReunion2 = new Reunion("Blablabla", date2, "Bowser", "hhhd@gg.dd"));

//        mAdapter.equals(mReunion0);
        mAdapter.filterRoom = "Bowser";
        mAdapter.setFilterRoom(mAdapter.filterRoom);
        mAdapter.verifFilter();
//        mListReunion1.removeIf(mReunion -> (!mReunion.getRoomReu().contains("Bowser")));
//        mReunion0 = mAdapter.verifFilter().set(0, new Reunion("Blablabla", date0, "Bowser", "hhhd@gg.dd"));
//        mReunion1 = mAdapter.verifFilter().set(1, new Reunion("Blablabla", date1, "Daisy", "hhhd@gg.dd"));
//        mReunion2 = mAdapter.verifFilter().set(2, new Reunion("Blablabla", date2, "Bowser", "hhhd@gg.dd"));
//        mListReunion1.add(mReunion0);
//        mListReunion1.add(mReunion1);
//        mListReunion1.add(mReunion2);
        assertTrue(mListReunion1.contains(mReunion0));
        assertTrue(mListReunion1.contains(mReunion2));
        assertFalse(mListReunion1.contains(mReunion1));

    }
}