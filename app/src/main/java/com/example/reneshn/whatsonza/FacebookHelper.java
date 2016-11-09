package com.example.reneshn.whatsonza;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

/**
 * Created by renesh on 2016-11-09.
 */

public class FacebookHelper {

    public FacebookHelper(Context context) {
        FacebookSdk.sdkInitialize(context);
    }

    public void getEventId(){
        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/search",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        Log.d("FACEBOOK",response.getJSONObject().toString());
                        //Gson gson = new Gson();
                        //JsonElement jsonElement =  response.getJSONObject();
                        //IdResponseDTO responseDTO = gson.fromJson(jsonElement,IdResponseDTO.class);
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("center", "-29.858680,31.021840");
        parameters.putString("distance", "1000");
        parameters.putString("fields", "id");
        parameters.putString("limit", "1000");
        parameters.putString("type", "place");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
