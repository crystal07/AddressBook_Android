package com.example.crystal.addressbook.Tab.Call;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.crystal.addressbook.DB.CallListDBHandler;
import com.example.crystal.addressbook.R;
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
                CallListDBHandler callDB = new CallListDBHandler(getApplicationContext(), "CALLLIST.db", null, 1);
                callDB.INSERT(1, Phone);

                //intent

                Phone="";
            }
        }
        tvPhone.setText(Phone);
    }
}
