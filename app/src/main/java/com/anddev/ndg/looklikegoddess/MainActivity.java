package com.anddev.ndg.looklikegoddess;

import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anddev.ndg.looklikegoddess.models.ExerciseList;
import com.anddev.ndg.looklikegoddess.network.ExercisesAPI;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    FirebaseAuth mFirebaseAuth;

    FirebaseUser mFirebaseUser;
    TextView mNameView;
    ImageView ivExercise;
    Button timerButton;
    TextView timerTextView;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ArrayList<Integer> preWorkoutExercisesIds;
    ArrayList<Integer> workoutExercisesIds;
    ArrayList<Integer> stretchExercisesIds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intstantiateUser();
        mNameView = findViewById(R.id.tvname);
        Button btn = findViewById(R.id.btn_auth);
        ivExercise = findViewById(R.id.iv_exercise);
        final List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult( AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),
                        RC_SIGN_IN);
            }
        });
        timerButton = findViewById(R.id.btntimer);
        timerTextView = findViewById(R.id.tvtimer);


        Integer[] intArray = new Integer[] { 354, 326, 432, 411 };
        preWorkoutExercisesIds = new ArrayList<>(Arrays.asList(intArray));

        Integer[] intWorkoutArray = new Integer[] { 98, 207, 105, 113, 408, 177, 389 };
        workoutExercisesIds = new ArrayList<>(Arrays.asList(intWorkoutArray));

        Integer[] intStretchArray = new Integer[]{166, 383, 429};
        stretchExercisesIds = new ArrayList<>(Arrays.asList(intStretchArray));


        final CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                timerTextView.setText("Done !");
            }
        };

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.start();
            }
        });

        Button start = findViewById(R.id.btnnew);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExersicesActivity.class);
                intent.putExtra(ExersicesActivity.EXERCISES_ARRAY_EXTRA, preWorkoutExercisesIds );
               startActivity(intent);
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    updateUI(user);


                } else {
                    // User is signed out
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        };


    }



    @Override
    public void onStart() {
        super.onStart();
     mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    private void updateUI(FirebaseUser currentUser) {
    //    mNameView.setText(currentUser.getEmail());
    }

    private void intstantiateUser(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
    }

    private void createAccount(String email, String password){
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                          //  Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            if(user!=null){
                            updateUI(user);}
                        } else {
                            // If sign in fails, display a message to the user.
                          //  Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                          //  updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                updateUI(user);
               // Toast.makeText(MainActivity.this, user.getDisplayName(), Toast.LENGTH_LONG);


                // ...
            } else {

                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_LONG);
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}
