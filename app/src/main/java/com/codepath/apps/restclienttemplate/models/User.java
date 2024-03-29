package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {

    public User() {}

    // list attributes

    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;
    public String profileBannerUrl;

    //deserialize
    public static User fromJSON(JSONObject json) throws JSONException {
        User user = new User();

        user.name = json.getString("name");
        user.uid = json.getLong("id");
        user.screenName = "@"+json.getString("screen_name");
        user.profileImageUrl = json.getString("profile_image_url");
        user.profileBannerUrl = json.getString("profile_banner_url");

        return user;
    }
}
