package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.text.BreakIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>{

    List<Tweet> mTweets;
    Context context;
    boolean hasMedia;
    boolean hasUrl;

    // pass in Tweets array
    public TweetAdapter(List<Tweet> tweets) {mTweets = tweets;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }
    // inflate the layout and cache references into ViewHolder

    // bind the values on the position of the element
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get the data according to position
        Tweet tweet = mTweets.get(position);
        // populate the views according to this data
        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        holder.tvDate.setText(getRelativeTimeAgo(tweet.createdAt));
        holder.tvHandle.setText(tweet.user.screenName);

        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, 30, 0))
                .into(holder.ivProfileImage);

        if(tweet.mediaUrl.isEmpty()){
            holder.ivMedia.setVisibility(View.GONE);
        } else {
            holder.ivMedia.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(tweet.mediaUrl)
                    .bitmapTransform(new RoundedCornersTransformation(context, 30, 0))
                    .into(holder.ivMedia);
        }

    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    // create ViewHolder class

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
        ImageView ivMedia = (ImageView) itemView.findViewById(R.id.ivMedia);
        TextView tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) itemView.findViewById(R.id.tvBody);
        TextView tvDate = (TextView) itemView.findViewById(R.id.tvDate);
        TextView tvHandle = (TextView) itemView.findViewById(R.id.tvHandle);

        public ViewHolder(View itemView) {
            super(itemView);

            //perform findViewById lookups
          //  tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
          //  tvBody = (TextView) itemView.findViewById(R.id.tvBody);
           // tvDate = (TextView) itemView.findViewById(R.id.tvDate);
           // tvHandle = (TextView) itemView.findViewById(R.id.tvHandle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Tweet tweet = mTweets.get(position);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                context.startActivity(intent);
            }

        }
    }
    public static Date parseTwitterDate(String date) throws ParseException
    {
        final String twitterFormat = "EEE MMM dd yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);
        return sf.parse(date);
    }
    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
        /*String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_ABBREV_TIME).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;*/
        Date system_date = new Date();
        Date user_date = new Date();
        double diff = Math.floor((user_date.getTime() - system_date.getTime()) / 1000);
        if (diff <= 1) {return "just now";}
        if (diff < 20) {return diff + "s";}
        if (diff < 40) {return "30s";}
        if (diff < 60) {return "45s";}
        if (diff <= 90) {return "1m";}
        if (diff <= 3540) {return Math.round(diff / 60) + "m";}
        if (diff <= 5400) {return "1h";}
        if (diff <= 86400) {return Math.round(diff / 3600) + "h";}
        if (diff <= 129600) {return "1d";}
        if (diff < 604800) {return Math.round(diff / 86400) + "d";}
        if (diff <= 777600) {return "1w";}
        return Math.round(diff / 604800) + "w";
    }
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }
}
