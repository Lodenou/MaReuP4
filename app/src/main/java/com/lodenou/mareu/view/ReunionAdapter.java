package com.lodenou.mareu.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lodenou.mareu.controller.ActivityZoom;
import com.lodenou.mareu.event.DeleteReunionEvent;
import com.lodenou.mareu.model.Reunion;
import com.lodenou.mareu.R;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class ReunionAdapter extends RecyclerView.Adapter<ReunionViewHolder> {


    // List
    final private List<Reunion> ListReunion;
    private Date filterDate;
    private String filterRoom;

    public void setFiltreDate(Date filtreDate) {
        this.filterDate = filtreDate;
    }

    public void setFiltreRoom(String filtreRoom) {
        this.filterRoom = filtreRoom;
    }

    // Constructor
    public ReunionAdapter(List<Reunion> listReunion) {
        ListReunion = listReunion;
    }

    @NonNull
    @Override
    public ReunionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crée un ViewHolder et inflate son fichier xml
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_mareu, parent, false);
        return new ReunionViewHolder(view);
    }

    // Met à jour la View avec une réunion
    @Override
    public void onBindViewHolder(@NonNull final ReunionViewHolder holder, int position) {
        final Reunion reunion = verifFilter().get(position);

        // get the subject of the meeting
        holder.mTextViewReu.setText(reunion.getSubjectReu());

        // random color for the alert image
        holder.mImageViewAlerte.setColorFilter(getRandomColor());

        SimpleDateFormat sdf = new SimpleDateFormat("HH'h'mm", Locale.FRANCE);
//        mChooseTime.setText(sdf.format(c.getTime()));
        holder.mTextViewTime.setText(" - " + sdf.format(reunion.getTimeReu()) + " - ");
//        holder.mTextViewTime.setText(" - " + reunion.getTimeReu() + " - ");
        holder.mTextViewRoom.setText(reunion.getRoomReu());
        holder.mTextViewEmail.setText(reunion.getEmailReu());

        // Delete when the bin is clicked
        holder.mImageButtonBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteReunionEvent(reunion));
            }
        });

        // zoom for emails on click on item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ActivityZoom.class);
                intent.putExtra("id", reunion.getEmailReu());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }


    // Retourne le total d'items dans la liste
    @Override
    public int getItemCount() {
        return verifFilter().size();
    }

    public int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    private List<Reunion> verifFilter() {

//        mListReunion.removeIf(mReunion -> (!(mReunion.getTimeReu().after(date)&& mReunion.getTimeReu().before(calendar.getTime()))));

        // Si filters = null return the normal list
        if (filterRoom == null && filterDate == null) {
            return ListReunion;
        }
        List<Reunion> resultat = new ArrayList<>();

        for (Reunion reunion : ListReunion) {

            if (reunion.getRoomReu().equals(filterRoom)) {

                resultat.add(reunion);
            }

            if (filterDate != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(filterDate);
                calendar.add(Calendar.HOUR, 1);
                if ((reunion.getTimeReu().after(filterDate) && reunion.getTimeReu().before(calendar.getTime()))) {
                    resultat.add(reunion);
                }

            }
        }
        return resultat;
    }


}
