package com.lodenou.mareu.View;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lodenou.mareu.R;

public class ReunionViewHolder extends RecyclerView.ViewHolder {

    // Image de statut de reunion
    @BindView(R.id.imageView_alerte) ImageView mImageViewAlerte;
    // Numéro de réunion (Reunion A, Reunion B ...)
    @BindView(R.id.textView_reu) TextView mTextViewReu;
    // Salle de réunion
    @BindView(R.id.textView_room) TextView mTextViewRoom;
    // Heure de reunion
    @BindView(R.id.textView_time) TextView mTextViewTime;
    // Email des participants
    @BindView(R.id.textView_email) TextView mTextViewEmail;
    // bouton poubelle
    @BindView(R.id.imageButton_bin) ImageButton mImageButtonBin;

    public ReunionViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


  //  public void updateMaReunion(Reunion reunion){
  //      this.textView.setText(reunion.getLogin());
  //  }
}
