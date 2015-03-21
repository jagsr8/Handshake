package com.handshake.handshake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.handshake.handshake.util.SystemUiHider;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class SelectUserTypeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_user_type);
    }

    public void chooseStudent(View view) {
        Intent intent = new Intent(this, JobSeekerActivity.class);
        startActivity(intent);
    }

    public void chooseEmployer(View view) {
        Intent intent = new Intent(this, EmployeeViewActivity.class);
        startActivity(intent);
    }
}
