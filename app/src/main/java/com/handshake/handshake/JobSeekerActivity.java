package com.handshake.handshake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;

public class JobSeekerActivity extends Activity {

    private Intent intent; // gets the Myo
    private TextView mLockStateView;
    private TextView mTextView;
    private int javi;
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
        Hub.getInstance().setLockingPolicy(Hub.LockingPolicy.NONE);
    }

    private DeviceListener mListener = new AbstractDeviceListener() {
        @Override
        public void onConnect(Myo myo, long timestamp) {
            System.out.println("5");
            //Toast.makeText(mContext, "Myo Connected!", Toast.LENGTH_SHORT).show();
        }

        // onPose() is called whenever a Myo provides a new pose.
        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            // Handle the cases of the Pose enumeration, and change the text of the text view
            // based on the pose we receive.
            switch (pose) {
                case UNKNOWN:
                    mTextView.setText(getString(R.string.hello_world));
                    break;
                case REST:
                case DOUBLE_TAP:
                    int restTextId = R.string.hello_world;
                    switch (myo.getArm()) {
                        case LEFT:
                            System.out.println("1");
                            javi = 1;
                            //restTextId = R.string.arm_left;
                            break;
                        case RIGHT:
                            System.out.println("2");
                            javi = 2;
                            //restTextId = R.string.arm_right;
                            break;
                    }
                    mTextView.setText(getString(restTextId));
                    break;
                case FIST:
                    System.out.println("3");
                    javi = 3;
                    //mTextView.setText(getString(R.string.pose_fist));
                    break;
                case WAVE_IN:
                    System.out.println("4");
                    javi = 4;
                    //mTextView.setText(getString(R.string.pose_wavein));
                    break;
                case WAVE_OUT:
                    System.out.println("7");
                    javi = 7;
                    //mTextView.setText(getString(R.string.pose_waveout));
                    break;
                case FINGERS_SPREAD:
                    System.out.println("6");
                    javi = 6;
                    //mTextView.setText(getString(R.string.pose_fingersspread));
                    break;
            }
            TextView view = (TextView) findViewById(R.id.editText);
            view.setText(javi);
            if (pose != Pose.UNKNOWN && pose != Pose.REST) {
                // Tell the Myo to stay unlocked until told otherwise. We do that here so you can
                // hold the poses without the Myo becoming locked.
                myo.unlock(Myo.UnlockType.HOLD);

                // Notify the Myo that the pose has resulted in an action, in this case changing
                // the text on the screen. The Myo will vibrate.
                myo.notifyUserAction();
            } else {
                // Tell the Myo to stay unlocked only for a short period. This allows the Myo to
                // stay unlocked while poses are being performed, but lock after inactivity.
                myo.unlock(Myo.UnlockType.TIMED);
            }
        }


    };
}
