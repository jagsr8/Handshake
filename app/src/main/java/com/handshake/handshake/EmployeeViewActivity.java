package com.handshake.handshake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.handshake.handshake.util.SystemUiHider;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class EmployeeViewActivity extends Activity {

    String companyChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);
        Intent intent = getIntent();
        companyChoice = intent.getStringExtra("companySelection");
    }


}
