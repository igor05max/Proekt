package com.example.proekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private EditText editText, editText2;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference rootRef;
    private String currentUserID;
    private static final int REQUEST_IMAGE_PICK = 1;
    private CircleImageView circleImageView;

    private StorageReference userProfileImageRef;

    private String photo = "boy_level_0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editText = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextMultiLine);

        circleImageView = findViewById(R.id.profile_image);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        rootRef = FirebaseDatabase.getInstance().getReference();
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");

        currentUserID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdataIformation();
            }
        });
        userInformation();


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, Photo.class);
                startActivity(intent);
            }
        });



    }



    private void UpdataIformation() {
        String textName = editText.getText().toString();
        String textAbout = editText2.getText().toString();

        if (TextUtils.isEmpty(textName)) {
            Toast.makeText(this, "Поле с ником посто", Toast.LENGTH_SHORT).show();
        }
        else {


            HashMap<String, Object> profileMap = new HashMap<>();
            profileMap.put("uid", currentUserID);
            profileMap.put("name", textName);
            profileMap.put("about", textAbout);
            profileMap.put("picture", photo);
            profileMap.put("profileTask", "0");
            profileMap.put("baseTask", "0");



            rootRef.child("Users").child(currentUserID).setValue(profileMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ProfileActivity.this, "OK", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void userInformation() {
        rootRef.child("Users").child(currentUserID)
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