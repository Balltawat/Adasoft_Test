package com.example.adasofttext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class searchData extends AppCompatActivity {
    private ArrayList<PersonModel> personModelArrayList;
    private DBHelper dbHelper;
    private PersonRVAdapter personRVAdapter;
    private RecyclerView personRV;
    private String TAG = "Read";
    private TextView myText = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String searchkey = extras.getString("searchkey");

            personModelArrayList = new ArrayList<>();
            dbHelper = new DBHelper(searchData.this);

            personModelArrayList = dbHelper.searchPerson(searchkey);
            if(personModelArrayList.isEmpty()){
                Toast.makeText(this, "ไม่มีข้อมูลในฐานข้อมูล", Toast.LENGTH_SHORT).show();
            }else{
                personRVAdapter = new PersonRVAdapter(personModelArrayList, searchData.this);
                personRV = findViewById(R.id.idPerson);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(searchData.this, RecyclerView.VERTICAL, false);
                personRV.setLayoutManager(linearLayoutManager);

                personRV.setAdapter(personRVAdapter);
            }
        }




    }
}