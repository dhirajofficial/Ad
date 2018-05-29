package com.example.dhiraj.ad;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dhiraj.ad.Database.DatabaseHelper;

public class HomePageActivity extends AppCompatActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);

    ListView listView;
    String Title[] = {"car","vacancy","house","car","vacancy","house"};
    String[] description = {"This is a car. You can rent it.","The post for pre primary teacher is vacant","This house is at sale","This is a car. You can rent it.","The post for pre primary teacher is vacant","This house is at sale"};
    String[] contact= {"9800000000","9811111111","9822222222","9800000000","9811111111","9822222222"};
    Integer[] imgid= {R.drawable.car,R.drawable.vacancy,R.drawable.house,R.drawable.car,R.drawable.vacancy,R.drawable.house};
//    Integer[] imgid= {R.drawable.car};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Cursor res = myDb.getAllAds();
        int count = res.getCount();

        String phoneNumber[] = new String[count];
        String adTitle[] = new String[count];
        String adDescription[] = new String[count];

        int index = 0;
        while (res.moveToNext())
        {
            phoneNumber[index] = res.getString(0);
            adTitle[index] = res.getString(1);
            adDescription[index] = res.getString(2);
            index++;
        }


//        ListView lv = findViewById(R.id.adList);
//        String[] cars = {"toyota","mercedes","BMW"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(lv.getContext(),android.R.layout.simple_list_item_1,cars);
//        lv.setAdapter(adapter);

        listView = findViewById(R.id.adList);
        DisplayAdActivity displayAdActivity = new DisplayAdActivity(this,adTitle,adDescription,phoneNumber,imgid);
//        DisplayAdActivity displayAdActivity = new DisplayAdActivity(this,Title,description,contact,imgid);
        listView.setAdapter(displayAdActivity);
    }
}
