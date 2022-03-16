package com.example.adasofttext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText eName , eSurname , eIDNumber , eAge , eSearch;
    private Button btnAdd , btnView , btnSearch;
    private DBHelper dbHelper;
    private String TAG = "Read" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eIDNumber = findViewById(R.id.etxtIDNumber);
        eName = findViewById(R.id.etxtName);
        eSurname = findViewById(R.id.etxtSurname);
        eAge = findViewById(R.id.etxtAge);
        eSearch = findViewById(R.id.etxtSearch);

        btnSearch = findViewById(R.id.btnSearch);
        btnAdd = findViewById(R.id.btnAddData);
        btnView = findViewById(R.id.btnViewData);
        btnSearch = findViewById(R.id.btnSearch);

        dbHelper = new DBHelper(MainActivity.this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String pidnumber = eIDNumber.getText().toString() ;
                String pname = eName.getText().toString();
                String psurname = eSurname.getText().toString();
                int page = Integer.parseInt(eAge.getText().toString());

                if (pidnumber.length() != 13 || pname.isEmpty() || psurname.isEmpty() || page < 0) {
                    Toast.makeText(MainActivity.this, "กรุณากรอกข้อมูล", Toast.LENGTH_SHORT).show();
                }else{
                    dbHelper.addNewCourse(pidnumber, pname, psurname, page);

                    Toast.makeText(MainActivity.this, "เพิ่มข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();
                    eIDNumber.setText("");
                    eName.setText("");
                    eSurname.setText("");
                    eAge.setText("");
                }


            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewPerson.class);
                startActivity(i);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Search Text");
                String psearch = eSearch.getText().toString();
                if(psearch.isEmpty()){
                    Toast.makeText(MainActivity.this, "กรุณากรอกข้อมูล", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(MainActivity.this, searchData.class);
                    i.putExtra("searchkey",psearch);
                    startActivity(i);
                }

            }
        });
    }

}

//id
//name varchar
//surname varchar
//idnumber varchar
//age int