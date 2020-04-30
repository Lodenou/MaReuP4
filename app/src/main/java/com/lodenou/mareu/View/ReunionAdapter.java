package com.lodenou.mareu.View;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lodenou.mareu.Controller.ApiService;
import com.lodenou.mareu.Event.DeleteReunionEvent;
import com.lodenou.mareu.Model.Reunion;
import com.lodenou.mareu.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;

import static com.lodenou.mareu.Controller.ApiService.REUNION_LIST_ALPHABETIC;
import static com.lodenou.mareu.Controller.ApiService.getReunionListAlphabetic;


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
    public void onBindViewHolder(@NonNull ReunionViewHolder holder, int position) {
        final Reunion reunion = ListReunion.get(position);

        // get item of the list REUNION_LIST_ALPHABETIC in terms of the list position
        holder.mTextViewReu.setText(REUNION_LIST_ALPHABETIC.get(position));

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
