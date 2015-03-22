package com.handshake.handshake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.handshake.handshake.util.SystemUiHider;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class EmployeeViewActivity extends Activity {

    String companyChoice;
    int count;
    int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);
        Intent intent = getIntent();
        companyChoice = intent.getStringExtra(CompanySelectActivity.EXTRA_MESSAGE);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Company");
        query.whereEqualTo("name", companyChoice);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject company, ParseException e) {
                if (e == null) {
                    //Log.d("name", "Retrieved " + fairList.size() + " fairs");
                    key = company.getInt("key");
                    String fair = company.getString("fair");
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Fair");
                    query.whereEqualTo("name", fair);
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject fairObject, ParseException e) {
                            if (e == null) {
                                count = fairObject.getInt("count");
                                generateSequence();
                            } else {
                                Log.d("name", "Error: " + e.getMessage());
                            }
                        }
                    });
                } else {
                    Log.d("name", "Error: " + e.getMessage());
                }
            }
        });
    }

    protected void generateSequence() {
        int multiplier;
        String seq = "";
        if (count <= 161) {
            multiplier = (int) ((-75 + Math.sqrt((75 * 75) - 4 * (-25) * (105 - count))) / (-50));
        } else {
            multiplier = (int) ((-75 + Math.sqrt((75 * 75) - 4 * (-25) * (105 - 161))) / (-50));
        }
        final int FIST = 1 + (0 * multiplier);
        final int SPREAD = 2 + (1 * multiplier);
        final int LEFT = 10 + (9 * multiplier);
        final int RIGHT = 26 + (25 * multiplier);

        int temp = count;
        int i = 1;
        while (temp > 0) {
            if (temp >= RIGHT) {
                seq = seq + i + ". Wave Right\n";
                temp = temp - RIGHT;
            } else if (temp >= LEFT) {
                seq = seq + i + ". Wave Left\n";
                temp = temp - LEFT;
            } else if (temp >= SPREAD) {
                seq = seq + i + ". Spread\n";
                temp = temp - SPREAD;
            } else {
                seq = seq + i + ". Fist\n";
                temp = temp - FIST;
            }
            i++;
            Log.d("debug ", seq);
        }
        TextView sequenceDisplay = (TextView)findViewById(R.id.sequence);
        sequenceDisplay.setText(seq);


    }


}
