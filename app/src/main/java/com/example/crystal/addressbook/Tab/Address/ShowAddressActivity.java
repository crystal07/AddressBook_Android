package com.example.crystal.addressbook.Tab.Address;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crystal.addressbook.DB.AddressDBHandler;
import com.example.crystal.addressbook.DB.CallListDBHandler;
import com.example.crystal.addressbook.R;
import com.example.crystal.addressbook.Tab.Message.SendMessageActivity;

public class ShowAddressActivity extends AppCompatActivity {

    TextView tvName, tvOrganization, tvPhone, tvEmail, tvMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_address);

        tvName = (TextView) findViewById(R.id.tvName);
        tvOrganization = (TextView) findViewById(R.id.tvOrganization);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvMemo = (TextView) findViewById(R.id.tvMemo);

        //DB에서 자료 가져와서 설정하기
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCall : {
                CallListDBHandler callDB = CallListDBHandler.getInstance(getApplicationContext());
                callDB.INSERT(0, tvPhone.getText().toString());

                //intent
                break;
            }
            case R.id.btnMessage : {
                Intent intent = new Intent(this, SendMessageActivity.class);
                intent.putExtra("target", tvPhone.getText().toString());
                startActivity(intent);
                finish();
            }
            case R.id.btnEdit : {
                Intent intent = new Intent(this, AddAddressActivity.class);
                intent.putExtra("Phone", tvPhone.getText().toString());
                startActivity(intent);
                break;
            }
            case R.id.btnDelete : {
                AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
                addressDB.DELETE(tvPhone.getText().toString());
                Toast.makeText(getApplicationContext(), "Complete deleting node", Toast.LENGTH_SHORT);
            }
            case R.id.btnCancel : {
                finish();
                break;
            }
        }
    }
}
