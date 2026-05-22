package com.example.njehub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.njehub.utils.QRCodeHelper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njehub.R;
import com.example.njehub.models.Participant;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder> {

    private Context context;
    private List<Participant> participantList;

    public ParticipantAdapter(Context context, List<Participant> participantList) {
        this.context = context;
        this.participantList = participantList;
    }

    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_participant, parent, false);
        return new ParticipantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder holder, int position) {
        Participant participant = participantList.get(position);

        holder.txtParticipantName.setText(participant.getName());
        holder.txtParticipantEmail.setText(participant.getEmail());
        holder.txtParticipantRole.setText(participant.getRole());
        holder.txtParticipantQr.setText("QR ID: " + participant.getQrCode());

        holder.itemView.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            View dialogView = LayoutInflater.from(context)
                    .inflate(R.layout.dialog_qr, null);

            ImageView imgQr = dialogView.findViewById(R.id.imgQr);
            TextView txtQrValue = dialogView.findViewById(R.id.txtQrValue);

            txtQrValue.setText(participant.getQrCode());

            Bitmap qrBitmap = QRCodeHelper.generateQRCode(
                    participant.getQrCode(),
                    600,
                    600
            );

            imgQr.setImageBitmap(qrBitmap);

            builder.setView(dialogView);
            builder.show();
        });
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_fade_slide);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return participantList.size();
    }

    public static class ParticipantViewHolder extends RecyclerView.ViewHolder {

        TextView txtParticipantName, txtParticipantEmail, txtParticipantRole, txtParticipantQr;

        public ParticipantViewHolder(@NonNull View itemView) {
            super(itemView);

            txtParticipantName = itemView.findViewById(R.id.txtParticipantName);
            txtParticipantEmail = itemView.findViewById(R.id.txtParticipantEmail);
            txtParticipantRole = itemView.findViewById(R.id.txtParticipantRole);
            txtParticipantQr = itemView.findViewById(R.id.txtParticipantQr);
        }
    }
}