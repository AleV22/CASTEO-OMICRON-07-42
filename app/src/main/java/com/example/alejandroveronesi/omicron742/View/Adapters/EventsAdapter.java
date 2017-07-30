package com.example.alejandroveronesi.omicron742.View.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alejandroveronesi.omicron742.Model.POJO.Event;
import com.example.alejandroveronesi.omicron742.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class EventsAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private List<Event> eventsList;
    private Context context;
    private View.OnClickListener myListener;


    //Constructor
    public EventsAdapter(List<Event> eventsList, Context context) {
        this.eventsList = eventsList;
        this.context = context;
    }

    public void setMyListener(View.OnClickListener myListener) {
        this.myListener = myListener;
    }

    public void setTracksList(List<Event> eventsList) {
        this.eventsList = eventsList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view1, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Event event = eventsList.get(position);
        ViewHolder myViewHolder = (ViewHolder) holder;
        myViewHolder.loadData(event);
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public Event getItem(Integer position) {
        return eventsList.get(position);
    }

    @Override
    public void onClick(View v) {
        if (myListener != null) {
            myListener.onClick(v);
        }
    }

    //ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView eventName;
        private TextView eventTime;
        private TextView contact;

        public ViewHolder(View itemView) {
            super(itemView);

            eventName = itemView.findViewById(R.id.textViewName);
            eventTime = itemView.findViewById(R.id.textViewTime);
            contact = itemView.findViewById(R.id.textViewContact);

        }

        public void loadData(Event event) {

            eventName.setText(event.getEventName());
            eventTime.setText(event.getEventTime().toString());
            contact.setText(event.getContact());

        }
    }
}
