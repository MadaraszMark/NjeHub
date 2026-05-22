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
import com.example.njehub.models.Section;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    private Context context;
    private List<Section> sectionList;

    public SectionAdapter(Context context, List<Section> sectionList) {
        this.context = context;
        this.sectionList = sectionList;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_section, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        Section section = sectionList.get(position);

        holder.txtSectionTime.setText(section.getTime());
        holder.txtSectionTitle.setText(section.getTitle());
        holder.txtSectionSpeaker.setText(section.getSpeaker());
        holder.txtSectionRoom.setText(section.getRoom());

        holder.itemView.setOnClickListener(v ->
                Toast.makeText(context, section.getDescription(), Toast.LENGTH_SHORT).show()
        );
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_fade_slide);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder {

        TextView txtSectionTime, txtSectionTitle, txtSectionSpeaker, txtSectionRoom;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSectionTime = itemView.findViewById(R.id.txtSectionTime);
            txtSectionTitle = itemView.findViewById(R.id.txtSectionTitle);
            txtSectionSpeaker = itemView.findViewById(R.id.txtSectionSpeaker);
            txtSectionRoom = itemView.findViewById(R.id.txtSectionRoom);
        }
    }
}