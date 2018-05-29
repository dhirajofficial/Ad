package com.example.dhiraj.ad;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhiraj.ad.Database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    Button home,view;
    DatabaseHelper myDb;
    EditText phoneNumber,adTitle,adDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = findViewById(R.id.home);
        view = findViewById(R.id.view);
        myDb = new DatabaseHelper(this);
        phoneNumber = findViewById(R.id.phoneNumber);
        adTitle = findViewById(R.id.adTitle);
        adDescription = findViewById(R.id.adDescription);



        insertDate();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAll();
            }
        });
    }

    public void insertDate(){

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(adTitle.getText().toString());
                System.out.println(adDescription.getText().toString());

                boolean isInserted = myDb.insertData(phoneNumber.getText().toString(),adTitle.getText().toString(),adDescription.getText().toString());
                if(isInserted == true){
                    Intent intent = new Intent(MainActivity.this,HomePageActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"Data Inserted!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data Not Inserted!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void viewAll()
    {
        Cursor res = myDb.getAllAds();
//                        Cursor res = myDb.getAllData(cust_phone.getText().toString());
        if(res.getCount() == 0)
        {
            showMessage("Shit","no Users have posted ads.");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("Phone: "+ res.getString(0)+"\n");
            buffer.append("Title: "+ res.getString(1)+"\n");
            buffer.append("Description: "+ res.getString(2)+"\n\n");
        }

        showMessage("ADS",buffer.toString());
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
