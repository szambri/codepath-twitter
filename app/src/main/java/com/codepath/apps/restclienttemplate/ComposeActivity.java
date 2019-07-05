package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.parceler.Parcels;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    public static final String RESULT_TWEET_KEY = "result_tweet";
    EditText etTweetInput;
    Button btnSendTweet;
    TwitterClient client;
    Button btnCancelCompose;
    ImageView ivCompseAvatar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etTweetInput = findViewById(R.id.etTweetInput);
        btnSendTweet = findViewById(R.id.btnSendTweet);
        btnCancelCompose = findViewById(R.id.btnCancelCompose);
        ivCompseAvatar = findViewById(R.id.ivCompseAvatar);

        btnSendTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTweet();
            }
        });

        btnCancelCompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComposeActivity.this, TimelineActivity.class);
                startActivity(intent);
            }
        });

        client = TwitterApp.getRestClient(this);

        //TODO

//        Glide.with(context)
//                .load(tweet.user.profileImageUrl)
//                .bitmapTransform(new RoundedCornersTransformation(context, 30, 0))
//                .into(holder.ivCompseAvatar)
//        ;
    }
    private void sendTweet() {
        client.sendTweet(etTweetInput.getText().toString(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode ==200) {
                    try {
                        JSONObject responseJson = new JSONObject(new String(responseBody));
                        Tweet resultTweet = Tweet.fromJSON(responseJson);

                        //return result to calling activity
                        Intent resultData = new Intent();
                        //TODO parcelable on tweet item
                  //      resultData.putExtra(RESULT_TWEET_KEY, resultTweet.toString());
                        resultData.putExtra(RESULT_TWEET_KEY, Parcels.wrap(resultTweet));
                        setResult(RESULT_OK, resultData);
                        finish();
                    } catch (JSONException e) {
                        Log.e("ComposeActivity", "Error parsing response", e);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
