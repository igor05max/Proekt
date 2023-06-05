package com.example.proekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    private ListView listView;
    private EditText messageField;
    private DatabaseReference messageRef;
    private FirebaseUser currentUser;
    private List<Message> messagesList = new ArrayList<>();
    private ArrayAdapter<Message> adapter;
    private String photoUser;

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
        DatabaseReference userRef2 = usersRef.child(idUser).child("picture");
        userRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    photoUser = dataSnapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        listView = findViewById(R.id.list_of_messages);
        messageField = findViewById(R.id.messageField);

        adapter = new ArrayAdapter<Message>(this, R.layout.message_item, messagesList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = convertView;
                Message message = messagesList.get(position);
                if (message.getId_username().equals(currentUser.getUid())) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.message_user, null);

                }
                else {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.message_item, null);

                }
                TextView usernameView = view.findViewById(R.id.username);
                TextView messageTextView = view.findViewById(R.id.message_text);
                TextView time = view.findViewById(R.id.time);

                CircleImageView circleImageView = view.findViewById(R.id.profile_image);

                switch (message.getUserPhoto()) {
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

                time.setText(message.getMoscowTime());
                usernameView.setText(message.getNameUser());
                messageTextView.setText(message.getMessage());



                return view;
            }
        };
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
            Message message = new Message(idUser, nameUser, messageText, photoUser);
//            Toast.makeText(this, message.getMoscowTime(), Toast.LENGTH_SHORT).show();
            messageRef.push().setValue(message);
            messageField.setText("");
        } else {
            Toast.makeText(this, "Введите сообщение", Toast.LENGTH_SHORT).show();
        }
    }

}