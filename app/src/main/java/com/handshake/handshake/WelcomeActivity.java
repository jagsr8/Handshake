package com.handshake.handshake;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
    }

    public void toNext(View view) {
        Intent i = new Intent(this, ChooseFairActivity.class);
        startActivity(i);
    }
}
