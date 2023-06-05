package com.example.proekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_profile);

        TextView textView = findViewById(R.id.w1);
        textView.setOnClickListener(this);
        ImageView imageView = findViewById(R.id.imageView_chat);
        imageView.setOnClickListener(this);

        TextView textView1 = findViewById(R.id.w2);
        textView1.setOnClickListener(this);
        ImageView imageView1 = findViewById(R.id.imageView_chat2);
        imageView1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.w1:
                Intent intent = new Intent(TaskProfileActivity.this, TPActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView_chat:
                Intent intent_chat = new Intent(TaskProfileActivity.this, ChatActivity.class);
                intent_chat.putExtra("chat", "pt1");
                startActivity(intent_chat);
                break;
            case R.id.w2:
                Intent intent1 = new Intent(TaskProfileActivity.this, TPActivity.class);
                startActivity(intent1);
                break;
            case R.id.imageView_chat2:
                Intent intent_chat1 = new Intent(TaskProfileActivity.this, ChatActivity.class);
                intent_chat1.putExtra("chat", "pt2");
                startActivity(intent_chat1);
                break;
        }
    }

}