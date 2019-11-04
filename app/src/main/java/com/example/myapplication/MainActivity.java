package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private TextView control;
    private Button led;
    DatabaseReference databaseReference;
    String name;
    private  Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // databaseReference = FirebaseDatabase.getInstance().getReference("led");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child("id");
        control = findViewById(R.id.editText);
        led = findViewById(R.id.buttonbutton);
        next = findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,speech.class);
                startActivity(intent);
            }
        });
        led.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savelrd();

            }
        });
    }
    public  void savelrd(){
        String command = control.getText().toString().trim();
        //String key = databaseReference.push().getKey();
        firebase data = new firebase(command);
        //databaseReference.child(key).setValue(data);
        databaseReference.setValue(data);

        Toast.makeText(getApplicationContext(),"data added",Toast.LENGTH_LONG).show();


    }
}
