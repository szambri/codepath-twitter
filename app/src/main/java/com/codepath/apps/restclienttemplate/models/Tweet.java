package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Tweet {

    public Tweet() {}

    // list out the attributes
    public String body;
    public long uid;
//    public Entity entities;
    public User user;
    public String createdAt;
    public String mediaUrl;
    public String url;


    //deserialize the JSON
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        //extract the values from JSOM
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at").substring(0,11)+jsonObject.getString("created_at").substring(26,30);
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        tweet.mediaUrl= " ";
        tweet.url= " ";

        if(jsonObject.has("entities"))
        {
            if(jsonObject.getJSONObject("entities").has("media"))
            {
                tweet.mediaUrl = ((JSONObject) jsonObject.getJSONObject("entities").getJSONArray("media").get(0)).getString("media_url_https");
            }
            else if(jsonObject.getJSONObject("entities").has("url"))
            {
                tweet.url = ((JSONObject) jsonObject.getJSONObject("entities").getJSONArray("url").get(0)).getString("expanded_url");
            }

        }

      //  tweet.entities = Entity.fromJSON(jsonObject.getJSONObject("entities"));
        return tweet;
    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
