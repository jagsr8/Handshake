package com.handshake.handshake;

import com.handshake.handshake.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class SelectUserTypeActivity extends Activity {

    String message;
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_user_type);
        Intent intent = getIntent();
        message = intent.getStringExtra(ChooseFairActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTextSize(40);
        textView.setText(message);
    }

    public void chooseStudent(View view) {
        Intent intent = new Intent(this, JobSeekerActivity.class);
        startActivity(intent);
    }

    public void chooseEmployer(View view) {
        Intent intent = new Intent(this, CompanySelectActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}