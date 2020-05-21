package com.lodenou.mareu;

import com.lodenou.mareu.controller.DummyReunionApiService;
import com.lodenou.mareu.di.DI;
import com.lodenou.mareu.model.Reunion;
import com.lodenou.mareu.view.ReunionAdapter;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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


    @Test
    public void addMeetingWithSuccess() {
        int x = 0;
        List<Reunion> reunionList = DI.getNewInstanceApiService().getmReunions();
        x = reunionList.size();
        DI.getNeighbourApiService().getmReunions().add(mReunion);
        assertTrue(reunionList.size() == x + 1);
    }

    @Test
    public void deleteMeetingWithSuccess() {
        //add a reunion to get 1 element in the list
        DI.getNewInstanceApiService().getmReunions().add(mReunion);
        Reunion reunionToDelete = DI.getNeighbourApiService().getmReunions().get(0);
        DI.getNeighbourApiService().deleteReunion(reunionToDelete);
        assertFalse(DI.getNeighbourApiService().getmReunions().contains(reunionToDelete));
    }

    @Test
    public void hourFilterWorks() {
        List<Reunion> mListReunion1 = new ArrayList<>();
        Calendar calendar0 = Calendar.getInstance();
        calendar0.set(2020, 5, 13, 9, 59, 59);
        Date date0 = calendar0.getTime();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020, 5, 13, 9, 59, 59);
        Date date1 = calendar1.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2020, 5, 13, 12, 59, 59);
        Date date2 = calendar2.getTime();

        Reunion r1 = new Reunion("r1", date0, "Daisy", "hhhd@gg.dd");
        Reunion r2 = new Reunion("r2", date1, "Daisy", "hhhd@gg.dd");
        Reunion r3 = new Reunion("r3", date2, "Daisy", "hhhd@gg.dd");
        mListReunion1.add(r1);
        mListReunion1.add(r2);
        mListReunion1.add(r3);
        mAdapter = new ReunionAdapter(mListReunion1);

        mAdapter.setFilterDate(date0);
        List<Reunion> reunions = mAdapter.verifFilter();

        assertTrue(reunions.contains(r1));
        assertTrue(reunions.contains(r2));
        assertFalse(reunions.contains(r3));
    }

    @Test
    public void roomFilterWorks() {
        List<Reunion> mListReunion1 = new ArrayList<>();
        Calendar calendar0 = Calendar.getInstance();
        calendar0.set(2020, 5, 13, 9, 59, 59);
        Date date0 = calendar0.getTime();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020, 5, 13, 9, 59, 59);
        Date date1 = calendar1.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2020, 5, 13, 12, 59, 59);
        Date date2 = calendar2.getTime();

        Reunion r1 = new Reunion("r1", date0, "Bowser", "hhhd@gg.dd");
        Reunion r2 = new Reunion("r2", date1, "Bowser", "hhhd@gg.dd");
        Reunion r3 = new Reunion("r3", date2, "Daisy", "hhhd@gg.dd");
        mListReunion1.add(r1);
        mListReunion1.add(r2);
        mListReunion1.add(r3);
        mAdapter = new ReunionAdapter(mListReunion1);

        mAdapter.setFilterRoom("Bowser");
        List<Reunion> reunions = mAdapter.verifFilter();

        assertTrue(reunions.contains(r1));
        assertTrue(reunions.contains(r2));
        assertFalse(reunions.contains(r3));

    }
    @Test
    public void testGetmReunions() {
        DI.getNewInstanceApiService().getmReunions().clear();
        assertFalse(DI.getNeighbourApiService().getmReunions().contains(mReunion));
        DI.getNeighbourApiService().getmReunions().add(mReunion);
        assertTrue(DI.getNeighbourApiService().getmReunions().contains(mReunion));

    }
}