package com.handshake.handshake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;

public class JobSeekerActivity extends Activity {

    private Intent intent; // gets the Myo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_job_seeker);
        Hub hub = Hub.getInstance();
        if (!hub.init(this)) {
            Toast.makeText(this, "Couldn't initialize Hub", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Hub.getInstance().attachToAdjacentMyo();
        Hub.getInstance().setLockingPolicy(Hub.LockingPolicy.STANDARD);
    }

    private DeviceListener mListener = new AbstractDeviceListener() {
        @Override
        public void onConnect(Myo myo, long timestamp) {
            System.out.println("5");
            //Toast.makeText(mContext, "Myo Connected!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDisconnect(Myo myo, long timestamp) {
            //Toast.makeText(mContext, "Myo Disconnected!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            System.out.println("heyyy");
            //Toast.makeText(mContext, "Pose: " + pose, Toast.LENGTH_SHORT).show();

            //TODO: Do something awesome.
        }
    };
}
