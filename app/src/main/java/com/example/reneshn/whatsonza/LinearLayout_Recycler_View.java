package com.example.reneshn.whatsonza;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ReneshN on 2016/11/08.
 */

public class LinearLayout_Recycler_View extends Fragment {

    private static View view;
    private static RecyclerView listRecyclerView;
    private static ArrayList<Host> listArrayList;
    private static Recycler_Adapter adapter;
    String[] getTitle, getLocation, getYear;
    private static RelativeLayout bottomLayout;
    private static LinearLayoutManager mLayoutManager;
    private  ArrayList<ArrayList<String>> ids = new ArrayList<ArrayList<String>>();
    private int parentPos = 0;
    private int childPos = 0;

    private static final int[] images = {R.drawable.shore,
            R.drawable.shivaji, R.drawable.victoria,};
    // Variables for scroll listener
    private boolean userScrolled = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    public LinearLayout_Recycler_View() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_frag, container,
                false);
        FacebookSdk.sdkInitialize(getActivity());
        init();
        populateRecyclerView();
        implementScrollListener();
        return view;
    }

    private void implementScrollListener() {
        listRecyclerView
                .addOnScrollListener(new RecyclerView.OnScrollListener() {

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView,
                                                     int newState) {

                        super.onScrollStateChanged(recyclerView, newState);

                        // If scroll state is touch scroll then set userScrolled
                        // true
                        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                            userScrolled = true;

                        }

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx,
                                           int dy) {

                        super.onScrolled(recyclerView, dx, dy);
                        // Here get the child count, item count and visibleitems
                        // from layout manager

                        visibleItemCount = mLayoutManager.getChildCount();
                        totalItemCount = mLayoutManager.getItemCount();
                        pastVisiblesItems = mLayoutManager
                                .findFirstVisibleItemPosition();

                        // Now check if userScrolled is true and also check if
                        // the item is end then update recycler view and set
                        // userScrolled to false
                        if (userScrolled
                                && (visibleItemCount + pastVisiblesItems) == totalItemCount) {
                            userScrolled = false;

                            updateRecyclerView();
                        }

                    }

                });
    }

    private void populateRecyclerView() {

        listArrayList = new ArrayList<Host>();
        bottomLayout.setVisibility(View.VISIBLE);
        //final ArrayList<ArrayList<String>> ids = new ArrayList<ArrayList<String>>();
        final FacebookHelper fbh = new FacebookHelper(getActivity());
        fbh.getEventIdAsync(AccessToken.getCurrentAccessToken(), new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                JSONObject obj = response.getJSONObject();
                JSONArray arr;

                try {
                    arr = obj.getJSONArray("data");
                    ArrayList<String> temp = new ArrayList<String>();
                    for (int l=1; l < (arr.length()+1); l++) {
                        JSONObject oneByOne = arr.getJSONObject(l-1);
                        if((l%50) > 0){
                        //for(int i = 0 ; i < 50 ; i ++) {
                            System.out.println(oneByOne.opt("id").toString());
                            temp.add(oneByOne.opt("id").toString());
                        }
                        else {
                            ids.add(temp);
                            temp = new ArrayList<String>();
                            System.out.println("");
                            System.out.println("");
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                    fbh.getEventDetailsAsync(AccessToken.getCurrentAccessToken(), ids.get(parentPos), new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject obj = response.getJSONObject();
                            JSONObject arr;
                            ArrayList<String> temp = new ArrayList<String>();
                            int size =  ids.get(parentPos).size();
                            for (int i = 0; i <  size; i++) {
                                String current = ids.get(parentPos).get(i);
                                try {
                                    arr = obj.getJSONObject(current);
                                if(arr.opt("events") != null){
                                    JSONArray data = arr.getJSONObject("events").getJSONArray("data");
                                    for(int j = 0 ; j < data.length() ; j++){
                                        JSONObject dataTemp = data.getJSONObject(j);
                                        listArrayList.add(fbh.parseEvent(arr,dataTemp));

                                        if(listArrayList.size() <=10){
                                            parentPos = i;
                                            childPos = j;
                                            break;
                                        }
                                    }
                                    //temp.add(arr.opt("events").toString());
                                    System.out.println(arr.opt("events").toString());
                                }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                               /* listArrayList.add(new Event_model(i,"Host Example: "+ ids.get(i).get(i),"Description","Tuesday: 08-11-2016",
                                        "Tuesday: 08-11-2016",1000,"Music",new Stats(),
                                        new Venue(i,"Durban","venue info",new ArrayList<String>(), "cover Pic" , "profile pic",new EventLocation())));
                                        */
                            }


                            adapter = new Recycler_Adapter(listArrayList,getActivity());
                            listRecyclerView.setAdapter(adapter);// set adapter on recyclerview
                            adapter.notifyDataSetChanged();// Notify the adapter
                            bottomLayout.setVisibility(View.GONE);
                        }
                    });



            }
        });
    }

    private void init() {
        bottomLayout = (RelativeLayout) view
                .findViewById(R.id.loadItemsLayout_recyclerView);
        // Getting the string array from strings.xml

        getTitle = getActivity().getResources().getStringArray(R.array.title);
        getLocation = getActivity().getResources().getStringArray(
                R.array.location);
        getYear = getActivity().getResources().getStringArray(
                R.array.constructed_year);

        mLayoutManager = new LinearLayoutManager(getActivity());
        listRecyclerView = (RecyclerView) view
                .findViewById(R.id.linear_recyclerview);
        listRecyclerView.setHasFixedSize(true);
        listRecyclerView.setLayoutManager(mLayoutManager);// for
        // linear data display we use linear layoutmanager
    }

    // Method for repopulating recycler view
    private void updateRecyclerView() {

        // Show Progress Layout
        bottomLayout.setVisibility(View.VISIBLE);

        // Handler to show refresh for a period of time you can use async task
        // while commnunicating serve

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                final FacebookHelper fbh = new FacebookHelper(getActivity());
                fbh.getEventDetailsAsync(AccessToken.getCurrentAccessToken(), ids.get(parentPos), new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        JSONObject obj = response.getJSONObject();
                        JSONObject arr;
                        ArrayList<String> temp = new ArrayList<String>();
                        int size = ids.get(parentPos).size();
                        for (int i = parentPos; i < size; i++) {
                            String current = ids.get(parentPos).get(i);
                            try {
                                arr = obj.getJSONObject(current);
                                if (arr.opt("events") != null) {
                                    JSONArray data = arr.getJSONObject("events").getJSONArray("data");
                                    for (int j = childPos; j < data.length(); j++) {
                                        JSONObject dataTemp = data.getJSONObject(j);
                                        listArrayList.add(fbh.parseEvent(arr, dataTemp));

                                        if (listArrayList.size() <= 10) {
                                            parentPos = i;
                                            childPos = j;
                                            break;
                                        }
                                    }
                                    //temp.add(arr.opt("events").toString());
                                    System.out.println(arr.opt("events").toString());
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter.notifyDataSetChanged();// Notify the adapter

                        // Toast for task completion
                        Toast.makeText(getActivity(), "Items Updated.",
                                Toast.LENGTH_SHORT).show();
                        // After adding new data hide the view.
                        bottomLayout.setVisibility(View.GONE);
                    }
                });
            }
        }, 4000);
    }

}
