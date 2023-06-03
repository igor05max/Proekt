package com.example.proekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WariantProfActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wariant_prof);
        TextView textView = findViewById(R.id.w1);
        textView.setOnClickListener(this);
        ImageView imageView = findViewById(R.id.imageView_chat);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.w1:
                Intent intent = new Intent(WariantProfActivity.this, WRActivity.class);
                intent.putExtra("wariant_number", "1");
                startActivity(intent);
                break;
            case R.id.imageView_chat:
                Intent intent_chat = new Intent(WariantProfActivity.this, ChatActivity.class);
                intent_chat.putExtra("chat", "pw1");
                startActivity(intent_chat);
                break;
        }
    }


}