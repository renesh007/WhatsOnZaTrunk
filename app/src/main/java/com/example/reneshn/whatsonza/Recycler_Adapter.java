package com.example.reneshn.whatsonza;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ReneshN on 2016/11/08.
 */

public class Recycler_Adapter extends RecyclerView.Adapter<RecyclerView_Holder> {

    private ArrayList<Event_model> arrayList;
    private Context context;

    public Recycler_Adapter(ArrayList<Event_model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView_Holder holder, int position) {
        final Event_model model = arrayList.get(position);

        RecyclerView_Holder mainHolder = (RecyclerView_Holder) holder;// holder

        /*TODO*/
        Bitmap image = BitmapFactory.decodeResource(context.getResources(),R.drawable.shivaji);

        // This will convert drawbale image into
        // bitmap

        // setting data over views
        mainHolder.list_title.setText(model.getName());
        mainHolder.list_location.setText(model.getVenue().getName());
        mainHolder.list_date.setText(model.getStartTime());
        mainHolder.list_imageView.setImageBitmap(image);

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
