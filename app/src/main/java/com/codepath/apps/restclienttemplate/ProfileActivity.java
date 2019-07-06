package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
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


    Tweet tweet;
    ImageView ivProfileImage;
    ImageView ivBanner;
    TextView tvUserName;
    TextView tvFollowers;
    TextView tvFollowing;
    TextView tvBio;
    TextView tvHandle;
    long uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvHandle = (TextView) findViewById(R.id.tvHandle);
        tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        ivBanner = (ImageView) findViewById(R.id.ivBanner);
        tvBio = (TextView) findViewById(R.id.tvBio);
        getUserDetails();
  //      getUserPhotos();

    }
    public void getUserDetails()
    {
        TwitterApp.getRestClient(this).getUserProfile(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = new String(responseBody);
                try{
                    JSONObject response = new JSONObject(responseString);
                    tvUserName.setText(response.getString("name"));
                    tvHandle.setText("@" + response.getString("screen_name"));
                    tvBio.setText(response.getString("description"));
                    tvFollowers.setText(response.getString("followers_count"));
                    tvFollowing.setText(response.getString("friends_count"));
                    uid = response.getLong("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void getUserPhotos()
    {
        TwitterApp.getRestClient(this).getUserPhotos(uid, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = new String(responseBody);
                try{
                    JSONObject response = new JSONObject(responseString);
                    Glide.with(ProfileActivity.this)
                            .load(response.getString("profile_image_url"))
                            .bitmapTransform(new RoundedCornersTransformation(ProfileActivity.this, 30, 0))
                            .into(ivProfileImage);
                    Glide.with(ProfileActivity.this)
                            .load(response.getString("profile_banner_url"))
                            .bitmapTransform(new RoundedCornersTransformation(ProfileActivity.this, 30, 0))
                            .into(ivBanner);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
