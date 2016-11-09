package com.example.reneshn.whatsonza;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ReneshN on 2016/11/08.
 */

public class RecyclerView_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView list_title, list_location, list_date;
    public ImageView list_imageView;
    public RelativeLayout listLayout;

    private RecyclerView_OnClickListener.OnClickListener onClickListener;

    public RecyclerView_Holder(View itemView) {
        super(itemView);

        this.list_title = (TextView) itemView.findViewById(R.id.list_title);
        this.list_location = (TextView) itemView.findViewById(R.id.list_location);
        this.list_date = (TextView) itemView
                .findViewById(R.id.list_dateconstructed);
        this.list_imageView = (ImageView) itemView
                .findViewById(R.id.list_imageview);

        this.listLayout = (RelativeLayout) itemView.findViewById(R.id.list_layout);

        // Implement click listener over views that we need

        this.listLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // setting custom listener
        if (onClickListener != null) {
            onClickListener.OnItemClick(v, getAdapterPosition());

        }
    }
    public void setClickListener(
            RecyclerView_OnClickListener.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
