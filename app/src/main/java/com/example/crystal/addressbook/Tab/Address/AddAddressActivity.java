package com.example.crystal.addressbook.Tab.Address;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crystal.addressbook.DB.AddressDBHandler;
import com.example.crystal.addressbook.DB.CallListDBHandler;
import com.example.crystal.addressbook.R;

public class AddAddressActivity extends AppCompatActivity {

    EditText etName, etPhone, etOrganization, etEmail, etMemo;
    String name, phone, organization, email, memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etOrganization = (EditText) findViewById(R.id.etOrganization);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etMemo = (EditText) findViewById(R.id.etMemo);

        AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
        if (name != null) {
            String result = addressDB.getInfo(name);
            String[] info = result.split(":");

            etName.setText(info[1]);
            etPhone.setText(info[2]);
            etOrganization.setText(info[3]);
            etEmail.setText(info[4]);
            etMemo.setText(info[5]);
        }

        addressDB.INSERT("me", "00000000000", "null", "null", "null");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd : {
                name = etName.getText().toString();
                phone = etPhone.getText().toString();
                organization = etOrganization.getText().toString();
                email = etEmail.getText().toString();
                memo = etMemo.getText().toString();

                AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
                String tmp = addressDB.getInfo(name);
                if (tmp != null) addressDB.DELETE(name);

                if (name.length()<=0)  {
                    Toast.makeText(getApplicationContext(), "input name", Toast.LENGTH_SHORT).show();
                    break;
                }
                else if (phone.length()<=0) {
                    Toast.makeText(getApplicationContext(), "input phone number", Toast.LENGTH_SHORT).show();
                    break;
                }

                else {
                    if (organization.length()<=0) organization = "null";
                    if (email.length()<=0) email = "null";
                    if (memo.length()<=0) memo = "null";
                    addressDB.INSERT(name, phone, organization, email, memo);
                }
            }
            case R.id.btnCancel : {
                Log.e("SJ", "onClick: before finishing activity");
                finish();
                break;
            }
        }
    }
}
