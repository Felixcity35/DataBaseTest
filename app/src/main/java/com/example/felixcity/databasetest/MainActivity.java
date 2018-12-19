package com.example.felixcity.databasetest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper  openHelper;
    SQLiteDatabase db;

     private Button button;
     private EditText Fname,Lname,password,email,phone;
   private TextView loginkey ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



         openHelper = new DatabaseHelper(this);
         Fname= findViewById(R.id.txtFname);
         Lname = findViewById(R.id.txtLname);
         password=findViewById(R.id.txtpass);
         email=findViewById(R.id.txtEmail);
         phone = findViewById(R.id.txtPhn);
         button = findViewById(R.id.txtBtn);
         loginkey = findViewById(R.id.txtlogin);



         button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getWritableDatabase();

                String firstN = Fname.getText().toString();
                String lastN = Lname.getText().toString();
                String pass = password.getText().toString();
                String emai = email.getText().toString();
                String phn = phone.getText().toString();
                insertData(firstN,lastN,pass,emai,phn);

                Toast.makeText(getApplicationContext(),"Register Successfully",Toast.LENGTH_LONG).show();

            }
        });

         loginkey.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                 startActivity(intent);
             }
         });
    }

    public void insertData (String fname,String lname,String pass,String email,String phn){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,fname);
        contentValues.put(DatabaseHelper.COL_3,lname);
        contentValues.put(DatabaseHelper.COL_4,pass);
        contentValues.put(DatabaseHelper.COL_5,email);
        contentValues.put(DatabaseHelper.COL_6,phn);
        long id = db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);

    }

}
