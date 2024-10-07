package com.example.mymobileapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rateus extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView ratingValue, thankingText;
    private EditText commentEditText;
    private Button submitRating;
    private float userRating;

    private DatabaseReference ratingReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rateus);

        ratingBar = findViewById(R.id.ratingId);
        ratingValue = findViewById(R.id.ratingValueId);
        commentEditText = findViewById(R.id.commentId);
        thankingText = findViewById(R.id.thankingId);
        submitRating = findViewById(R.id.ratingSubmitId);

        ratingReference = FirebaseDatabase.getInstance().getReference("Rating");   //create Rating node in realtime database
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = firebaseUser.getUid();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                userRating = rating;
                ratingValue.setText(String.valueOf(userRating));
            }
        });

        submitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitRating(userId);
            }
        });
    }

    private void submitRating(String userId) {
        final String comment = commentEditText.getText().toString().trim();

        if (TextUtils.isEmpty(comment)) {
            Toast.makeText(this, "Please enter a comment", Toast.LENGTH_SHORT).show();
            return;
        }

        RatingDetails ratingDetails = new RatingDetails(userId, userRating, comment);  //create a object/instance

        ratingReference.child(userId).setValue(ratingDetails)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            thankingText.setText("Thank you for rating us");
                            commentEditText.setText(""); // Clear the comment field after submission
                        } else {
                            thankingText.setText("Error");
                            Toast.makeText(Rateus.this, "Failed to submit rating", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
