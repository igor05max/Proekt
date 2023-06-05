package com.example.proekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_for_users extends AppCompatActivity {
    private String id_user;
    private FirebaseUser currentUser;
    private String photo;
    private DatabaseReference rootRef;
    private CircleImageView circleImageView;
    private EditText editText, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_for_users);

        Intent intent = getIntent();
        id_user = intent.getStringExtra("id_user");

        rootRef = FirebaseDatabase.getInstance().getReference();

        circleImageView = findViewById(R.id.profile_image);

        editText = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextMultiLine);

        userInformation();

    }

    private void userInformation() {
        rootRef.child("Users").child(id_user)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists() && dataSnapshot.child("name").exists()) {
                            String UserName = Objects.requireNonNull(dataSnapshot.child("name").getValue()).toString();
                            String UserAbout = Objects.requireNonNull(dataSnapshot.child("about").getValue()).toString();

                            editText.setText(UserName);
                            editText2.setText(UserAbout);

                            photo = Objects.requireNonNull(dataSnapshot.child("picture").getValue()).toString();
                            switch (photo) {
                                case "boy_level_0":
                                    circleImageView.setImageResource(R.drawable.boy_level_0);
                                    break;
                                case "boy_level_1":
                                    circleImageView.setImageResource(R.drawable.boy_level_1);
                                    break;
                                case "boy_level_2":
                                    circleImageView.setImageResource(R.drawable.boy_level_2);
                                    break;
                                case "girl_level_0":
                                    circleImageView.setImageResource(R.drawable.girl_level_0);
                                    break;
                                case "girl_level_1":
                                    circleImageView.setImageResource(R.drawable.girl_level_1);
                                    break;
                                case "girl_level_2":
                                    circleImageView.setImageResource(R.drawable.girl_level_2);
                                    break;
                            }


                        }
                        else {
                            circleImageView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}