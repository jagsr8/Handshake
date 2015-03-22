package com.handshake.handshake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class ChooseFairActivity extends Activity {
    ListView listView ;
    final List<String> fairs = new ArrayList<String>();
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    @Override


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_fair);

        // Get ListView object from xml




        ParseQuery<ParseObject> query = ParseQuery.getQuery("Fair");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> fairList, ParseException e) {
                if (e == null) {
                    //Log.d("name", "Retrieved " + fairList.size() + " fairs");
                    for (ParseObject o : fairList) {
                        fairs.add(o.getString("name"));
                    }
                    Collections.sort(fairs);
                    helper();
                } else {
                    Log.d("name", "Error: " + e.getMessage());
                }
            }
        });
        Log.d("debug", "next");



    }

    private void helper() {
        String[] values = new String[fairs.size()];
        Log.d("Debug", "Now");
//        for (int i = 0; i < fairs.size(); i++) {
////            Log.d("name is", fairs.get(i));
//            values[i] = fairs.get(i);
//            i++;
//        }
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, fairs);


        // Assign adapter to ListView
        listView.setAdapter(adapter);


        // ListView Item Click Listener


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
//                ListEntry entry= (ListEntry) parent.getAdapter().getItem(position);
                Log.v("position--", ""+ position);
                String anyString = fairs.get(position);
                Intent intent = new Intent(ChooseFairActivity.this, SelectUserTypeActivity.class);
                intent.putExtra(EXTRA_MESSAGE, anyString);
                startActivity(intent);
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_fair, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
