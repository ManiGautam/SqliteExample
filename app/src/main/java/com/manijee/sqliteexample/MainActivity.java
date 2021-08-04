package com.manijee.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Helper helper;
EditText name,contact;
TextView tv;
Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper=new Helper(this,"Employeedb",null,1);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv_message);
        name=findViewById(R.id.edt_name);
        contact=findViewById(R.id.edt_contact);
        save=findViewById(R.id.btnsave);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.btnsave:
                 String msg=helper.onSave(name.getText().toString(),contact.getText().toString());
                 tv.setText(msg);
              break;
      }
    }
}