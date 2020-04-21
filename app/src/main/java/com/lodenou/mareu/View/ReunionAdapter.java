package com.lodenou.mareu.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lodenou.mareu.Model.Reunion;
import com.lodenou.mareu.R;
import java.util.List;



public class ReunionAdapter extends RecyclerView.Adapter<ReunionViewHolder> {


    // List
    private List <Reunion> ListReunion;



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
        Reunion reunion = ListReunion.get(position);

        //FIXME NE MARCHE PAS ??

//      holder.mTextViewReu.setText(ListReunion.getIdReu());
//        holder.mTextViewTime.setText(ListReunion.getTimeReu());
//        holder.mTextViewRoom.setText(ListReunion.getRoomReu());
//        holder.mTextViewEmail.setText(ListReunion.getEmailReu());
    }



    // Retourne le total d'items dans la liste
    @Override
    public int getItemCount() {
        return this.ListReunion.size();
    }


}
