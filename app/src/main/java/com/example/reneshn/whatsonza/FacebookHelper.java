package com.example.reneshn.whatsonza;

import android.content.Context;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by renesh on 2016-11-09.
 */

public class FacebookHelper {

    private final String eventFields =
            "id," +
                    "type," +
                    "name," +
                    "place," +
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
            "events{" + eventFields + "}";
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

    public void getEventIdAsync(AccessToken token, GraphRequest.Callback callback) {
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

    public void getEventDetailsAsync(AccessToken token, ArrayList<String> ids, GraphRequest.Callback callback) {
        GraphRequest request = GraphRequest.newGraphPathRequest(
                token,
                "/",
                callback);

        Bundle parameters = new Bundle();
        String idList = "";
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size() - 1) {
                idList += ids.get(i);
            } else {
                idList += ids.get(i) + ",";
            }
        }
        parameters.putString("ids", idList);
        parameters.putString("fields", fields.toString());
        request.setParameters(parameters);
        request.executeAsync();
    }


    public Host parseEvent(JSONObject hostData,JSONObject eventData) {
        Event_model out = new Event_model();
        Host host = new Host();
        JSONArray arr;

        try {
            //host details
            host.setAbout(hostData.optString("about",""));
            host.setCategory(hostData.optString("category",""));
            host.setId(hostData.optInt("id",0));
            host.setName(hostData.optString("name",""));
            host.setPictureURL(parsePicture(hostData.getJSONObject("picture")));
            host.setLocation(parseLocation(hostData.getJSONObject("location")));
            //event details
            out.setName(eventData.optString("name",""));
            out.setDescription(eventData.optString("description",""));
            out.setStartTime(eventData.optString("start_time",""));
            out.setEndTime(eventData.optString("end_time",""));
            out.setCategory(eventData.optString("category",""));
            out.setPictureURL(parsePicture(eventData.getJSONObject("picture")));
            out.setVenue(parseVenue(eventData.getJSONObject("place")));
            out.setStats(parseStats(eventData));
            host.setEventList(out);
        }catch(Exception e){
            System.out.println("ERROR:" + e.getMessage());
        }
        return host;
    }

    public Venue parseVenue(JSONObject response) throws JSONException {
        Venue venue = new Venue();
        venue.setId(response.optInt("id",0));
        venue.setName(response.optString("name",""));
        venue.setLocation(parseLocation(response.getJSONObject("location")));

        return venue;
    }

    public EventLocation parseLocation(JSONObject response) {
        EventLocation el = new EventLocation();
        el.setCity(response.optString("city",""));
        el.setCountry(response.optString("country",""));
        el.setLatitude(response.optDouble("latitude",0.0));
        el.setLongitude(response.optDouble("longitude",0.0));
        el.setStreet(response.optString("street",""));
        el.setZip(response.optInt("zip",0));

        return el;
    }
    public Stats parseStats(JSONObject response) throws JSONException {
        Stats stats = new Stats();

        stats.setAttending(response.optInt("attending_count",0));
        stats.setDeclined(response.optInt("declined_count",0));
        stats.setMaybe(response.optInt("maybe_count",0));
        stats.setNoReply(response.optInt("noreply_count",0));
        return stats;
    }

    public String parsePicture(JSONObject response) throws JSONException {
        String picURL;

        picURL = response.getJSONObject("data").optString("url","");
        return picURL;
    }
}
