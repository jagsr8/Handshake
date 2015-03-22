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
import java.util.Collections;
import java.util.List;


public class CompanySelectActivity extends Activity {

    ListView listView;
    final List<String> companies = new ArrayList<String>();
    String fairChoice;
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_fair);
        Intent intent = getIntent();
        fairChoice = intent.getStringExtra(SelectUserTypeActivity.EXTRA_MESSAGE);

        // Get ListView object from xml




        ParseQuery<ParseObject> query = ParseQuery.getQuery("Company");
        query.whereEqualTo("fair", fairChoice);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> companyList, ParseException e) {
                if (e == null) {
                    //Log.d("name", "Retrieved " + fairList.size() + " fairs");
                    for (ParseObject o : companyList) {
                        companies.add(o.getString("name"));
                    }
                    Collections.sort(companies);
                    helper();
                } else {
                    Log.d("name", "Error: " + e.getMessage());
                }
            }
        });
        Log.d("debug", "next");



    }

    private void helper() {
        listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, companies);


        // Assign adapter to ListView
        listView.setAdapter(adapter);


        // ListView Item Click Listener


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
//                ListEntry entry= (ListEntry) parent.getAdapter().getItem(position);
                Log.v("position--", ""+ position);
                String anyString = companies.get(position);
                Intent intent = new Intent(CompanySelectActivity.this, EmployeeViewActivity.class);
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
