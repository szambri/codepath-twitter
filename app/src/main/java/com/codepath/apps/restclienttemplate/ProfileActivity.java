package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.AsyncHttpClient;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
}
