package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Entity {

    String mediaUrl;
    String mediaSize;

    public Entity(JSONObject object) throws JSONException {

        JSONObject media = object.getJSONObject("media");
        mediaUrl = media.getString("media_url_https");
        JSONArray posterSizeOptions = media.getJSONArray("sizes");
        mediaSize = posterSizeOptions.optString(3, "w680");
    }
}
