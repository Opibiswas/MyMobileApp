package com.example.mymobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Home extends AppCompatActivity {

    CardView detect,upload,shareImage,map;
    private RatingBar ratingBar;
    private TextView ratingValue;
    DatabaseReference ratedReference;

    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        detect = findViewById(R.id.detectId);
        upload = findViewById(R.id.uploadId);
        shareImage = findViewById(R.id.LeavesId);
        map = findViewById(R.id.mapId);


        ratingBar = findViewById(R.id.showRatingId);
        ratingValue = findViewById(R.id.showRatingValueId);
        ratedReference = FirebaseDatabase.getInstance().getReference().child("Rating");


        webView = findViewById(R.id.webViews);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/index.html");

        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,BlogPostActivity.class);
                startActivity(i);
            }
        });



        detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(Home.this, Detection.class);
                startActivity(ii);
            }
        });


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iiii = new Intent(Home.this,MapActivity.class);
                startActivity(iiii);
            }
        });




        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iii = new Intent(Home.this,PhotoUploadActivity.class);
                startActivity(iii);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        ratedReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int totalUser = (int) dataSnapshot.getChildrenCount();
                float sumOfRating = 0;

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object totalRating = map.get("rating");
                    float rating = Float.parseFloat(String.valueOf(totalRating));
                    sumOfRating += rating;

                    float averageRating = (sumOfRating/totalUser);

                    ratingBar.setRating(averageRating);
                    ratingValue.setText("Rating "+String.valueOf(Math.floor(averageRating)));

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.userProfileId) {
            startActivity(new Intent(Home.this, ProfileClass.class));
            return true;
        } else if (itemId == R.id.logoutMenuId) {
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(Home.this, Login.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            return true;
        } else if (itemId == R.id.ratingMenuId) {
            startActivity(new Intent(Home.this, Rateus.class));
            return true;
        } else if (itemId == R.id.videoId) {
            startActivity(new Intent(Home.this, Video.class));
            return true;
        } else if (itemId == R.id.animationId) {
            startActivity(new Intent(Home.this, Animation.class));
            return true;
        }
        else if (itemId == R.id.currencyId) {
            startActivity(new Intent(Home.this, CurrencyActivity.class));
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }


}