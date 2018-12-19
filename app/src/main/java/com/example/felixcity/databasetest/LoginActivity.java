package com.example.felixcity.databasetest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;


    private EditText password, email;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        openHelper = new DatabaseHelper(this);
        db= openHelper.getReadableDatabase();

        password = findViewById(R.id.txtpass);
        email = findViewById(R.id.txtEmail);

        login = findViewById(R.id.login);
           login.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                     String emai = email.getText().toString();
                     String pass = password.getText().toString();

                     cursor = db.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_5 + "=? AND" + DatabaseHelper.COL_4 + "=?" ,new String[]{emai,pass});
                         if(cursor != null){
                             if(cursor.getCount()>0){
                                 cursor.moveToNext();
                                 Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                             }else {
                                 Toast.makeText(getApplicationContext(),"Login Failure",Toast.LENGTH_LONG).show();
                             }

                         }

               }
           });




    }
}
