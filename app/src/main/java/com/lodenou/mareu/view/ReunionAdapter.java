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

import java.util.List;
import java.util.Random;



public class ReunionAdapter extends RecyclerView.Adapter<ReunionViewHolder> {


    // List
    final private List <Reunion> ListReunion;

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
        final Reunion reunion = ListReunion.get(position);

        // get the subject of the meeting
        holder.mTextViewReu.setText(reunion.getSubjectReu());

        // random color for the alert image
        holder.mImageViewAlerte.setColorFilter(getRandomColor());

        holder.mTextViewTime.setText(" - "+reunion.getTimeReu()+" - ");
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
        return this.ListReunion.size();
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }



}
