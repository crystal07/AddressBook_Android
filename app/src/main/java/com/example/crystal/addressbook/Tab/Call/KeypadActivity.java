package com.example.crystal.addressbook.Tab.Call;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crystal.addressbook.DB.CallListDBHandler;
import com.example.crystal.addressbook.R;
import com.example.crystal.addressbook.Tab.Address.ShowAddressActivity;
import com.example.crystal.addressbook.Tab.Message.SendMessageActivity;

public class KeypadActivity extends AppCompatActivity {
    private String Phone = "";
    private TextView tvPhone;
    private final String TAG = "SJ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keypad);

        tvPhone = (TextView) findViewById(R.id.tvPhone);
    }

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btn1 : {
                Phone+="1";
                break;
            }
            case R.id.btn2 : {
                Phone+="2";
                break;
            }
            case R.id.btn3 : {
                Phone+="3";
                break;
            }
            case R.id.btn4 : {
                Phone+="4";
                break;
            }
            case R.id.btn5 : {
                Phone+="5";
                break;
            }
            case R.id.btn6 : {
                Phone+="6";
                break;
            }
            case R.id.btn7 : {
                Phone+="7";
                break;
            }
            case R.id.btn8 : {
                Phone+="8";
                break;
            }
            case R.id.btn9 : {
                Phone+="9";
                break;
            }
            case R.id.btn0 : {
                Phone+="0";
                break;
            }
            case R.id.btnDelete : {
                if (Phone.length()>0) Phone = Phone.substring(0, Phone.length()-1);
                else;
                break;
            }
            case R.id.btnMessage : {
                Intent intent = new Intent(this, SendMessageActivity.class);
                intent.putExtra("Phone", Phone);
                startActivity(intent);
                break;
            }
            case R.id.btnCall : {
                CallListDBHandler callDB = CallListDBHandler.getInstance(getApplicationContext());
                callDB.INSERT(1, Phone);

                //intent
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int permissionResult = checkSelfPermission(android.Manifest.permission.CALL_PHONE);
                    if (permissionResult == PackageManager.PERMISSION_DENIED) {
                        if (shouldShowRequestPermissionRationale(android.Manifest.permission.CALL_PHONE)) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(KeypadActivity.this);
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
                                    Toast.makeText(KeypadActivity.this, "Cancel function", Toast.LENGTH_SHORT).show();
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

                Phone="";
                break;
            }
        }
        tvPhone.setText(Phone);
    }
}
