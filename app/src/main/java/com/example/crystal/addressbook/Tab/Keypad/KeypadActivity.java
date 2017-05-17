package com.example.crystal.addressbook.Tab.Keypad;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crystal.addressbook.DB.AddressDBHandler;
import com.example.crystal.addressbook.DB.CallListDBHandler;
import com.example.crystal.addressbook.R;
import com.example.crystal.addressbook.Tab.Message.SendMessageActivity;

public class KeypadActivity extends AppCompatActivity {
    private String Phone = "";
    private Button tvPhone;
    private final String TAG = "SJ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keypad);

        tvPhone = (Button) findViewById(R.id.tvPhone);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Phone = "";
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
                AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
                Intent intent = new Intent(this, SendMessageActivity.class);

                if (addressDB.findName(Phone) != null) {
                    intent.putExtra("Name", addressDB.findName(Phone));
                    Log.e(TAG, "onClick: not null");
                }
                else {
                    intent.putExtra("Name", Phone);
                    Log.e(TAG, "onClick: null");
                }
                Phone = "";
                startActivity(intent);
                break;
            }
            case R.id.btnCall : {
                if (Phone.length()<=0) break;
                CallListDBHandler callDB = CallListDBHandler.getInstance(getApplicationContext());

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
                        callDB.INSERT(0, Phone);
                        startActivity(intent);
                    }
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tvPhone.getText().toString()));
                    callDB.INSERT(0, Phone);
                    startActivity(intent);
                }

                Phone="";
                break;
            }
        }
        tvPhone.setText(Phone);
    }
}
