package com.example.proekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Objects;

public class TPActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    private String currentUserID;

    private DatabaseReference rootRef;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private String profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpactivity);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        rootRef = FirebaseDatabase.getInstance().getReference();

        currentUserID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();


        editText = findViewById(R.id.task1);
        button = findViewById(R.id.button);

        info();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TProfile tProfile = new TProfile();
                EditText[] mass = {editText};

                int count = 0;
                for (EditText el: mass) {
                    count ++;

                    if (Objects.equals(tProfile.getAnswer1Number(count), el.getText().toString())) {
                        el.setBackgroundColor(getColor(R.color.green));
                        profile += "1." + (count + "_");
                        Toast.makeText(TPActivity.this, profile, Toast.LENGTH_SHORT).show();
                    }
                    else if (!el.getText().toString().equals("")) {
                        el.setBackgroundColor(getColor(R.color.red));
                    }
                }
                updatainfo();
            }
        });

    }

    private void updatainfo() {
        rootRef.child("Users").child(currentUserID).child("profileTask").setValue(profile);
    }

    private void info() {
        rootRef.child("Users").child(currentUserID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        profile = Objects.requireNonNull(dataSnapshot.child("profileTask").getValue()).toString();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}