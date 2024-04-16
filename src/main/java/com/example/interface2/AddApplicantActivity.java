package com.example.interface2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddApplicantActivity extends AppCompatActivity {
    private EditText editTextFullName;
    private EditText editTextPhoneNumber;
    private EditText editTextEgeScore;
    private EditText editTextPriority;
    private EditText editTextProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_applicant);

        editTextFullName = findViewById(R.id.editTextFullName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEgeScore = findViewById(R.id.editTextEgeScore);
        editTextPriority = findViewById(R.id.editTextPriority);
        editTextProfile = findViewById(R.id.editTextProfile);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = editTextFullName.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                int egeScore = 0;
                if (!editTextEgeScore.getText().toString().isEmpty()) {
                    egeScore = Integer.parseInt(editTextEgeScore.getText().toString());
                }
                int priority = 0;
                if (!editTextPriority.getText().toString().isEmpty()) {
                    priority = Integer.parseInt(editTextPriority.getText().toString());
                }
                String profile = editTextProfile.getText().toString();

                if (!fullName.isEmpty() && !phoneNumber.isEmpty()) {
                    String newApplicantData = fullName + phoneNumber + " " + egeScore + " " + priority + " " + profile;

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newApplicantData", newApplicantData);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(AddApplicantActivity.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}