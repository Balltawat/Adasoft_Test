package com.example.adasofttext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    private EditText edtIDNumber, edtName, edtSurname, edtAge;
    private Button btnUpdate,btnDelete;
    private DBHelper dbHelper;
    String idnumber , name, surname, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtIDNumber = findViewById(R.id.eIDNumber);
        edtName = findViewById(R.id.eName);
        edtSurname = findViewById(R.id.eSurname);
        edtAge = findViewById(R.id.eAge);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        dbHelper = new DBHelper(Update.this);

        idnumber = getIntent().getStringExtra("idnumber");
        name = getIntent().getStringExtra("name");
        surname = getIntent().getStringExtra("surname");
        age = getIntent().getStringExtra("age");

        edtIDNumber.setText(idnumber);
        edtName.setText(name);
        edtSurname.setText(surname);
        edtAge.setText(age);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper.updatePerson(edtIDNumber.getText().toString() , edtName.getText().toString() , edtSurname.getText().toString() , Integer.valueOf(edtAge.getText().toString()));

                Toast.makeText(Update.this, "Updated..", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Update.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deletePerson(edtIDNumber.getText().toString());
                Toast.makeText(Update.this, "Deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Update.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}