package com.example.proekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView listView;
    private EditText messageField;
    private DatabaseReference messageRef;
    private FirebaseUser currentUser;
    private List<Message> messagesList = new ArrayList<>();
    private ArrayAdapter<Message> adapter;

    private String idUser;

    private String nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        nameUser = "";


        Intent intent = getIntent();
        String name = intent.getStringExtra("chat");
        messageRef = FirebaseDatabase.getInstance().getReference(name);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        assert currentUser != null;
        idUser = currentUser.getUid();

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        DatabaseReference userRef = usersRef.child(idUser).child("name");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    nameUser = dataSnapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView = findViewById(R.id.list_of_messages);
        messageField = findViewById(R.id.messageField);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messagesList);
        listView.setAdapter(adapter);

        FloatingActionButton btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        messageRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Message message = dataSnapshot.getValue(Message.class);
                messagesList.add(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }


    private void sendMessage() {
        String messageText = messageField.getText().toString();
        if (!messageText.isEmpty()) {


            Message message = new Message(idUser, nameUser, messageText);
            messageRef.push().setValue(message);
            messageField.setText("");
        } else {
            Toast.makeText(this, "Введите сообщение", Toast.LENGTH_SHORT).show();
        }
    }



}