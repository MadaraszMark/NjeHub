package com.example.njehub.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njehub.R;
import com.example.njehub.activities.EventDetailsActivity;
import com.example.njehub.models.Event;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context context;
    private List<Event> eventList;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);

        holder.txtEventName.setText(event.getName());
        holder.txtEventDate.setText(event.getDate());
        holder.txtEventLocation.setText(event.getLocation());
        holder.txtEventStatus.setText(event.getStatus());

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(context, EventDetailsActivity.class);
            intent.putExtra("event_id", event.getId());
            context.startActivity(intent);
        });
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_fade_slide);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        LinearLayout itemRoot;
        TextView txtEventName, txtEventDate, txtEventLocation, txtEventStatus;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            itemRoot = itemView.findViewById(R.id.itemRoot);
            txtEventName = itemView.findViewById(R.id.txtEventName);
            txtEventDate = itemView.findViewById(R.id.txtEventDate);
            txtEventLocation = itemView.findViewById(R.id.txtEventLocation);
            txtEventStatus = itemView.findViewById(R.id.txtEventStatus);
        }
    }
}