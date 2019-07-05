package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ProfileActivity extends AppCompatActivity {

    public final static String API_BASE_URL = "https://api.twitter.com/1.1/account/settings.json";
    public final static String API_KEY_PARAM = "api_key";
    public final static String TAG = "MovieDetails";

    Tweet tweet;
    ImageView ivProfileImage;
    TextView tvUserName;
    TextView tvFollowers;
    TextView tvFollowing;
    TextView tvHandle;
    AsyncHttpClient client;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvHandle = (TextView) findViewById(R.id.tvHandle);
        tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        client = new AsyncHttpClient();

    }
//    private void getConfiguration() {
//        String url = API_BASE_URL;
//        RequestParams params = new RequestParams();
//        params.put("api_key", "4KxocRp2Wh8RZ9cy1KJEjxGVy");
//        client.get(url, params, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                try {
//                    JSONArray results = response.getJSONArray("results");
//                    // get now playing movie list
//                    key = results.getJSONObject(0).getString("key");
//                    Intent i = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
//                    i.putExtra("video_key", key);
//                    MovieDetailsActivity.this.startActivity(i);
//                } catch (JSONException e) {
//                    logError("Failed parsing configuration", e, true);
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                logError("Failed getting configuration", throwable, true);
//            }
//        });
//    }
}
