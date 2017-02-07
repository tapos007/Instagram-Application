package com.example.tapos.instragramandroidapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class UserlistActivity extends AppCompatActivity {

    ListView userListView;
    ArrayList<String> users = new ArrayList<>();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.userlistmenu, menu);//Menu Resource, Menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.share:

                return true;
            case R.id.logout:

                ParseUser.logOut();
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(this, "logout successfully", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        userListView = (ListView) findViewById(R.id.userListView);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    for (ParseUser user:
                         objects) {
                        users.add(user.getUsername());
                    }
                    ArrayAdapter adapter = new ArrayAdapter(UserlistActivity.this, android.R.layout.simple_list_item_1, users);
                    userListView.setAdapter(adapter);
                } else {
                    // Something went wrong.
                }
            }
        });
    }
}
