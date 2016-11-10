package com.example.reneshn.whatsonza;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renesh on 2016-11-09.
 */

public class FacebookHelper {

    public FacebookHelper(Context context) {
        FacebookSdk.sdkInitialize(context);
    }

    public IdResponseDTO getEventId(){
        final IdResponseDTO responseList = new IdResponseDTO();
        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/search",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        JSONObject jObj = response.getJSONObject();
                        Log.d("FACEBOOK",jObj.toString());
                        ArrayList<IdResponseDTO.Data> listData = new ArrayList<IdResponseDTO.Data>();
                        JSONArray arr = null;

                        try {
                            arr = jObj.getJSONArray("data");


                        for(int i = 0 ; i < arr.length(); i++){
                            IdResponseDTO.Data tempData = new IdResponseDTO.Data();
                            tempData.setId(arr.getJSONObject(i).getString("id"));
                            listData.add(tempData);
                        }
                            responseList.setData(listData);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("center", "-29.858680,31.021840");
        parameters.putString("distance", "1000");
        parameters.putString("fields", "id");
        parameters.putString("limit", "10");
        parameters.putString("type", "place");
        request.setParameters(parameters);
        request.executeAsync();
        return responseList;
    }
}
