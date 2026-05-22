package com.example.njehub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njehub.R;
import com.example.njehub.models.InfoItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private Context context;
    private List<InfoItem> infoItems;

    public InfoAdapter(Context context, List<InfoItem> infoItems) {
        this.context = context;
        this.infoItems = infoItems;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_info, parent, false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        InfoItem item = infoItems.get(position);

        holder.txtInfoIcon.setText(item.getIcon());
        holder.txtInfoTitle.setText(item.getTitle());
        holder.txtInfoDescription.setText(item.getDescription());

        holder.itemView.setOnClickListener(v ->
                Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show()
        );
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_fade_slide);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return infoItems.size();
    }

    public static class InfoViewHolder extends RecyclerView.ViewHolder {

        TextView txtInfoIcon, txtInfoTitle, txtInfoDescription;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtInfoIcon = itemView.findViewById(R.id.txtInfoIcon);
            txtInfoTitle = itemView.findViewById(R.id.txtInfoTitle);
            txtInfoDescription = itemView.findViewById(R.id.txtInfoDescription);
        }
    }
}