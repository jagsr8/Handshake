package com.handshake.handshake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class CompanySelectActivity extends Activity {

    ListView listView;
    final List<String> companies = new ArrayList<String>();
    String fairChoice;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_select);
        Intent intent = getIntent();
        fairChoice = intent.getStringExtra("fairSelection");
        // Get ListView object from xml



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Company");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> fairList, ParseException e) {
                if (e == null) {
                    //Log.d("name", "Retrieved " + fairList.size() + " fairs");
                    for (ParseObject o : fairList) {
                        companies.add(o.getString("name"));
                    }

                } else {
                    Log.d("name", "Error: " + e.getMessage());
                }
            }
        });
        Log.d("debug", "next");



    }

    protected void onPostCreate (Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        String[] values = new String[companies.size()];
        Log.d("Debug", "Now");
        for (int i = 0; i < companies.size(); i++) {
            Log.d("name is", companies.get(i));
            values[i] = companies.get(i);
            i++;
        }
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);


        // ListView Item Click Listener


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
//                ListEntry entry= (ListEntry) parent.getAdapter().getItem(position);
                Intent intent = new Intent(ChooseFairActivity.this, SelectUserTypeActivity.class);
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
