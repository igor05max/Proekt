package com.example.proekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Photo extends AppCompatActivity {

    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;
    private LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6;
    private Button button;
    private String answer = "boy_level_0";
    private FirebaseAuth mAuth;
    private String currentUserID;
    private DatabaseReference rootRef;

    private String UserName;
    private String UserAbout;
    private String baseTask;
    private String profileTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        rootRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);

        button = findViewById(R.id.buttonPhoto);


        linearLayout1 = findViewById(R.id.boy_1_layout);
        linearLayout2 = findViewById(R.id.boy_2_layout);
        linearLayout3 = findViewById(R.id.boy_3_layout);
        linearLayout4 = findViewById(R.id.girl_1_layout);
        linearLayout5 = findViewById(R.id.girl_2_layout);
        linearLayout6 = findViewById(R.id.girl_3_layout);


        LinearLayout[] mass = {linearLayout1, linearLayout2, linearLayout3,
                linearLayout4, linearLayout5, linearLayout6};

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (LinearLayout el: mass) {
                    el.setBackgroundColor(Color.WHITE);
                }
                linearLayout1.setBackgroundColor(Color.BLUE);
                answer = "boy_level_0";
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (LinearLayout el: mass) {
                    el.setBackgroundColor(Color.WHITE);
                }
                linearLayout2.setBackgroundColor(Color.BLUE);
                answer = "boy_level_1";
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (LinearLayout el: mass) {
                    el.setBackgroundColor(Color.WHITE);
                }
                linearLayout3.setBackgroundColor(Color.BLUE);
                answer = "boy_level_2";
            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (LinearLayout el: mass) {
                    el.setBackgroundColor(Color.WHITE);
                }
                linearLayout4.setBackgroundColor(Color.BLUE);
                answer = "girl_level_0";
            }
        });

        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (LinearLayout el: mass) {
                    el.setBackgroundColor(Color.WHITE);
                }
                linearLayout5.setBackgroundColor(Color.BLUE);
                answer = "girl_level_1";
            }
        });

        linearLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (LinearLayout el: mass) {
                    el.setBackgroundColor(Color.WHITE);
                }
                linearLayout6.setBackgroundColor(Color.BLUE);
                answer = "girl_level_2";
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child("picture").setValue(answer);
                Intent intent = new Intent(Photo.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}