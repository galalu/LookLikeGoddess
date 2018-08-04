package com.anddev.ndg.looklikegoddess;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutActivity extends AppCompatActivity {
    private TextView textTimer;

    private Button StartTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);


                   //Initializing the views
            textTimer = (TextView) findViewById(R.id.mtextTimer);

            StartTimer = (Button) findViewById(R.id.buttonSend);

            StartTimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long maxTimeInMilliseconds = 30000;// in your case

                    startTimer(maxTimeInMilliseconds, 1000);
                }
            });

    }


        public void startTimer(final long finish, long tick) {
            CountDownTimer t;
            t = new CountDownTimer(finish, tick) {

                public void onTick(long millisUntilFinished) {
                    long remainedSecs = millisUntilFinished / 1000;
                    textTimer.setText("" + (remainedSecs / 60) + ":" + (remainedSecs % 60));// manage it accordign to you
                }

                public void onFinish() {
                    textTimer.setText("00:00:00");
                    Toast.makeText(WorkoutActivity.this, "Finish", Toast.LENGTH_SHORT).show();

                    cancel();
                }
            }.start();
        }

}
