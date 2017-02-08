package com.example.tapos.instragramandroidapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.feedList);

        Intent intent = getIntent();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
        query.whereEqualTo("username", intent.getStringExtra("title"));
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> imageList, ParseException e) {
                if (e == null) {
                    if(imageList.size()>0){
                        for (ParseObject object :
                                imageList) {
                            ParseFile file = (ParseFile) object.get("image");
                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e==null && data!=null){
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                                        ImageView imageView = new ImageView(getApplicationContext());
                                        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                                ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT
                                        ));

                                        imageView.setImageBitmap(bitmap);
                                        linearLayout.addView(imageView);
                                    }
                                }
                            });
                        }
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }
}
