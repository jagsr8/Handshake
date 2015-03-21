package com.handshake.handshake;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseObject;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "Aw2wgeVa9ihzU3OqRggicJ2rcFoQFxQYIWDhnaFR", "sgADHHUmzraH6rkEoskMeKpjvkHSq9z0DQWBgZJw");

        populate();

        setContentView(R.layout.activity_welcome);
    }

    private void populate() {
        for (int i = 0; i < 20; i++) {
            ParseObject company = new ParseObject("Company");
            company.put("fair", "Fair 20");
            company.put("name", "Company " + (i + 1));
            company.put("key", i);
            company.saveInBackground();
        }
        for (int i = 0; i < 20; i++) {
            ParseObject company = new ParseObject("Fair");
            company.put("name", "Fair " + (i + 1));
            company.put("count", i + 1);
            company.saveInBackground();
        }
    }

    public void toNext(View view) {
        Intent i = new Intent(this, ChooseFairActivity.class);
        startActivity(i);
    }
}
