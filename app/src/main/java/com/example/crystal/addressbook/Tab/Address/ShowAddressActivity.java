package com.example.crystal.addressbook.Tab.Address;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crystal.addressbook.DB.AddressDBHandler;
import com.example.crystal.addressbook.DB.CallListDBHandler;
import com.example.crystal.addressbook.R;
import com.example.crystal.addressbook.Tab.Message.SendMessageActivity;

import java.util.jar.Manifest;

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
        Intent intent = getIntent();
        String name = intent.getExtras().getString("Name");

        AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
        String result = addressDB.getInfo(name);
        String[] info = result.split(":");

        tvName.setText(info[1]);
        tvPhone.setText(info[2]);
        tvOrganization.setText(info[3]);
        tvEmail.setText(info[4]);
        tvMemo.setText(info[5]);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCall : {
                CallListDBHandler callDB = CallListDBHandler.getInstance(getApplicationContext());
                callDB.INSERT(0, tvPhone.getText().toString());


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int permissionResult = checkSelfPermission(android.Manifest.permission.CALL_PHONE);
                    if (permissionResult == PackageManager.PERMISSION_DENIED) {
                        if (shouldShowRequestPermissionRationale(android.Manifest.permission.CALL_PHONE)) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(ShowAddressActivity.this);
                            dialog.setTitle("need permission")
                                    .setMessage("This function needed to obtain permission for calling. Did you want to continue?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[] {android.Manifest.permission.CALL_PHONE}, 1000);
                                            }
                                        }
                                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(ShowAddressActivity.this, "Cancel function", Toast.LENGTH_SHORT).show();
                                }
                            }).create().show();
                        }
                        else {
                            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1000);
                        }
                    }
                    else {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tvPhone.getText().toString()));
                        startActivity(intent);
                    }
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tvPhone.getText().toString()));
                    startActivity(intent);
                }

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
                intent.putExtra("Name", tvName.getText().toString());
                startActivity(intent);
                break;
            }
            case R.id.btnDelete : {
                AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
                addressDB.DELETE(tvName.getText().toString());
                Toast.makeText(getApplicationContext(), "Complete deleting node", Toast.LENGTH_SHORT).show();
            }
            case R.id.btnCancel : {
                finish();
                break;
            }
        }
    }
}
