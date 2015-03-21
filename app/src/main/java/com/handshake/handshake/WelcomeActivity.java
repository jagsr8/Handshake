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

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        setContentView(R.layout.activity_welcome);
    }

    public void toNext(View view) {
        Intent i = new Intent(this, ChooseFairActivity.class);
        startActivity(i);
    }
}
