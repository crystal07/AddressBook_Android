package com.example.crystal.addressbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.crystal.addressbook.Tab.TabActivity;


public class MainActivity extends AppCompatActivity {
    final String TAG = "SJ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddress: {
                Intent intent = new Intent(this, TabActivity.class);
                startActivity(intent);
            }
        }
    }
}
