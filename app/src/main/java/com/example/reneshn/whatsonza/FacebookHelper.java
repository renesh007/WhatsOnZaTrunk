package com.example.reneshn.whatsonza;

import android.content.Context;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by renesh on 2016-11-09.
 */

public class FacebookHelper {

    private final String eventFields =
            "id,"+
            "type," +
            "name," +
            "cover.fields(id,source)," +
            "picture.type(large)," +
            "description," +
            "start_time," +
            "end_time," +
            "category," +
            "attending_count," +
            "declined_count," +
            "maybe_count," +
            "noreply_count";
            /*new String[]{"id",
            "type",
            "name",
            "about",
            "emails",
            "cover.fields(id,source)",
            "picture.type(large)",
            "description",
            "start_time",
            "end_time",
            "category",
            "attending_count",
            "declined_count",
            "maybe_count",
            "noreply_count"};*/

    private final String fields = "id," +
    "name," +
            "about," +
            "emails," +
            "cover.fields(id,source)," +
            "picture.type(large)," +
            "location," +
            "events.fields("+ eventFields + ")";
            /*new String[]{"id",
            "name",
            "about",
            "emails",
            "cover.fields(id,source)",
            "picture.type(large)",
            "location",
            "events.fields("+ eventFields.toString() + ")"};
*/


    public FacebookHelper(Context context) {

    }

    public void getEventIdAsync(AccessToken token, GraphRequest.Callback callback){
        GraphRequest request = GraphRequest.newGraphPathRequest(
                token,
                "/search",
                callback);
                /*new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        Log.d("FACEBOOK",response.getJSONObject().toString());
                        //Gson gson = new Gson();
                        //JsonElement jsonElement =  response.getJSONObject();
                        //IdResponseDTO responseDTO = gson.fromJson(jsonElement,IdResponseDTO.class);
                    }
                });*/

        Bundle parameters = new Bundle();
        parameters.putString("center", "-29.858680,31.021840");
        parameters.putString("distance", "1000");
        parameters.putString("fields", "id");
        parameters.putString("limit", "1000");
        parameters.putString("type", "place");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void getEventDetailsAsync(AccessToken token, ArrayList<String> ids , GraphRequest.Callback callback){
        GraphRequest request = GraphRequest.newGraphPathRequest(
                token,
                "/",
                callback);

        Bundle parameters = new Bundle();
        String idList = "";
        for(int i = 0 ; i < ids.size() ; i++){
            if(i == ids.size()-1){
                idList += ids.get(i);
            }else {
                idList += ids.get(i)+",";
            }
        }
        parameters.putString("ids",idList);
        parameters.putString("fields", fields.toString());
        request.setParameters(parameters);
        request.executeAsync();
    }


    public Event_model groupIds(JSONObject response){
        Event_model out = new Event_model();
        JSONArray arr;

        try {
            arr = response.getJSONArray("data");
            for (int l=0; l < arr.length(); l++) {
                JSONObject oneByOne = arr.getJSONObject(l);
                System.out.println(oneByOne.opt("id").toString());
                out.setName(oneByOne.opt("id").toString());
                out.setDescription(oneByOne.opt("id").toString());
                out.setStartTime(oneByOne.opt("id").toString());
                out.setEndTime(oneByOne.opt("id").toString());
                out.setCategory(oneByOne.opt("id").toString());
                System.out.println("");
                System.out.println("");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return out;
    }
}
