package com.example.crystal.addressbook.Tab.Message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crystal.addressbook.DB.AddressDBHandler;
import com.example.crystal.addressbook.DB.MessageDBHandler;
import com.example.crystal.addressbook.R;

public class ShowMessageActivity extends AppCompatActivity {
    EditText etMessage;
    String receiver, message;
    TextView tvReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);

        etMessage = (EditText) findViewById(R.id.etMessage);
        tvReceiver = (TextView) findViewById(R.id.tvPartner);

        Intent intent = getIntent();
        receiver = intent.getStringExtra("Name");
        tvReceiver.setText(receiver);
    }

    public void onClick(View view)  {
        MessageDBHandler messageDB = MessageDBHandler.getInstance(getApplicationContext());
        switch (view.getId()) {
            case R.id.btnSend : {
                message = etMessage.getText().toString();

                messageDB.INSERT(receiver, "00000000000", message);
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnDelete : {
                if (!messageDB.checkExist(receiver)) {
                    AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
                    messageDB.DELETE(addressDB.findPhone(receiver));
                }
                else messageDB.DELETE(receiver);
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                finish();
                break;
            }
        }
    }
}
