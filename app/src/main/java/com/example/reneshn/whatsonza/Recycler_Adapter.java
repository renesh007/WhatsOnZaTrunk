package com.example.reneshn.whatsonza;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ReneshN on 2016/11/08.
 */

public class Recycler_Adapter extends RecyclerView.Adapter<RecyclerView_Holder> {

    private ArrayList<Host> arrayList;
    private Context context;

    public Recycler_Adapter(ArrayList<Host> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView_Holder holder, int position) {
        final Host model = arrayList.get(position);

        RecyclerView_Holder mainHolder = (RecyclerView_Holder) holder;// holder

        // setting data over views
        mainHolder.list_title.setText(model.getEventList().getName());
        System.out.println("NAME: " + model.getEventList().getName());
        mainHolder.list_location.setText(model.getEventList().getVenue().getName());
        System.out.println("LOCATION NAME: " + model.getEventList().getVenue().getName());
        mainHolder.list_date.setText(model.getEventList().getStartTime());
        System.out.println("DATE: "+model.getEventList().getStartTime() );
        Picasso.with(context)
                .load(model.getEventList().getPictureURL())
                .fit()
                .into(mainHolder.list_imageView);
        System.out.println("\n_________________________________"+ position +"____________________________________\n");
        // Implement click listener over layout
        mainHolder.setClickListener(new RecyclerView_OnClickListener.OnClickListener() {

            @Override
            public void OnItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.list_layout:

                        // Show a toast on clicking layout
                        Toast.makeText(context,
                                "You have clicked " + model.getName(),
                                Toast.LENGTH_LONG).show();
                        break;

                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }
    @Override
    public RecyclerView_Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.card_view, viewGroup, false);
        RecyclerView_Holder listHolder = new RecyclerView_Holder(mainGroup);
        return listHolder;

    }

}
