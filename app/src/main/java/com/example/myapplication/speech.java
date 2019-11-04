package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.speech.RecognizerIntent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.ArrayList;

public class speech extends AppCompatActivity {

    private Button speak;
    private TextView txvResult;
    private  TextView show;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child("id");
        setContentView(R.layout.activity_speech);
        speak = findViewById(R.id.button2);
        txvResult =findViewById(R.id.Result);
        show =findViewById(R.id.textView);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tell();
            }
        });
    }

    public void tell() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "not support", Toast.LENGTH_LONG).show();
            ;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txvResult.setText(result.get(0));
                    String a;
                    a = result.get(0);
                    show.setText(a);
                    firebase firedata = new firebase(a);
                    databaseReference.setValue(firedata);
                }
                break;
        }
    }
}
