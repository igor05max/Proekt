package com.example.proekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WRActivity extends AppCompatActivity {

    private WProfile wProfile;
    EditText editText, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wractivity);

        wProfile = new WProfile();


        editText = findViewById(R.id.task1);
        editText2 = findViewById(R.id.task2);
        editText3 = findViewById(R.id.task3);

        Intent intent = getIntent();
        String number = intent.getStringExtra("wariant_number");
        int n = Integer.parseInt(number);
        wProfile.setNumber(n);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText[] mass = {editText, editText2, editText3};
                int count = 0;
                for (EditText el: mass) {
                    count ++;

                    if (Objects.equals(wProfile.getAnswerNumber(count), el.getText().toString())) {
                        el.setBackgroundColor(getColor(R.color.green));
                    }
                    else if (!el.getText().toString().equals("")) {
                        el.setBackgroundColor(getColor(R.color.red));
                    }
                }

            }
        });
    }
}