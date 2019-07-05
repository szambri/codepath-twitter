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

public class DetailActivity extends AppCompatActivity {

    Tweet tweet;
    ImageView ivProfileImage;
    TextView tvUserName;
    TextView tvBody;
    TextView tvDate;
    TextView tvHandle;
    AsyncHttpClient client;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvBody = (TextView) findViewById(R.id.tvBody);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvHandle = (TextView) findViewById(R.id.tvHandle);
        client = new AsyncHttpClient();

        //unwrap Tweet and User parcels
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
      //  user = (User) Parcels.unwrap(getIntent().getParcelableExtra(User.class.getSimpleName()));

        tvUserName.setText(tweet.getUser().name);
        tvDate.setText(tweet.getCreatedAt());
        tvBody.setText(tweet.getBody());
        tvHandle.setText(tweet.getUser().screenName);
        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(this, 30, 0))
                .into(ivProfileImage);

    }
    public static String parseTwitterDate(String date) throws ParseException
    {
        final String twitterFormat = "EEE MMM dd yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);
        return sf.parse(date).toString();
    }
}
