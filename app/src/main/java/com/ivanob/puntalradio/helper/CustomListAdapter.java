package com.ivanob.puntalradio.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivanob.puntalradio.R;
import com.ivanob.puntalradio.model.TweetBean;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by ivan on 18/9/16.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<TweetBean> tweets;

    public CustomListAdapter(Activity activity, List<TweetBean> tweets) {
        this.activity = activity;
        this.tweets = tweets;
    }

    @Override
    public int getCount() {
        return tweets.size();
    }

    @Override
    public Object getItem(int location) {
        return tweets.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
        ImageView thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);

        // getting movie data for the row
        TweetBean tweet = tweets.get(position);

        // title
        //title.setText(tweet.getUser());

        // rating
        rating.setText(Html.fromHtml(tweet.getTweet()));

        URL url = null;
        try {
            url = new URL(tweet.getUrlImgProfile());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            thumbnail.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // genre
        /*String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);*/

        // release year
        year.setText(tweet.getDate().toString());

        return convertView;
    }

}
