package com.example.crystal.addressbook.Tab.Message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crystal.addressbook.DB.MessageDBHandler;
import com.example.crystal.addressbook.R;

public class SendMessageActivity extends AppCompatActivity {
    EditText etReceiver, etSender, etContent;
    String receiver, sender, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        etContent = (EditText) findViewById(R.id.etContent);
        etReceiver = (EditText) findViewById(R.id.etReceiver);
        etSender = (EditText) findViewById(R.id.etSender);
    }

    public void onClick(View view) {
        MessageDBHandler messageDB = MessageDBHandler.getInstance(getApplicationContext());
        switch (view.getId()) {
            case R.id.btnSend: {
                content = etContent.getText().toString();
                receiver = etReceiver.getText().toString();
                sender = etSender.getText().toString();

                messageDB.INSERT(sender, receiver, content);
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                finish();
                break;
            }
        }
    }
}
