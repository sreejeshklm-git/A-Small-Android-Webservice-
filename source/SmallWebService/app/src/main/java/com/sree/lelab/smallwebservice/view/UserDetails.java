package com.sree.lelab.smallwebservice.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.sree.lelab.smallwebservice.R;

import java.util.HashMap;

public class UserDetails extends AppCompatActivity {
    TextView id,name,username,email,street,suite,city,zipcode,lat,lng,phone,website,cname,catchPhrase,cbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Intent intent = getIntent();
        HashMap<String, String> hashMapUsrDtl = (HashMap<String, String>) intent.getSerializableExtra("hashMap");
        getView();
        displayValues(hashMapUsrDtl);
       }

   //user details text views
    private void getView() {
        id=findViewById(R.id.id);
        name=findViewById(R.id.name);
        username=findViewById(R.id.uName);
        email=findViewById(R.id.email);
        street=findViewById(R.id.street);
        suite=findViewById(R.id.suite);
        city=findViewById(R.id.city);
        zipcode=findViewById(R.id.zipcode);
        lat=findViewById(R.id.lat);
        lng=findViewById(R.id.lng);
        phone=findViewById(R.id.phone);
        website=findViewById(R.id.website);
        cname=findViewById(R.id.cname);
        catchPhrase=findViewById(R.id.cPhrase);
        cbs=findViewById(R.id.bs);
    }
    //set values to views
    private void displayValues(HashMap<String, String> hashMapUsrDtl) {
        id.setText(hashMapUsrDtl.get("id"));
        name.setText(hashMapUsrDtl.get("name"));
        username.setText(hashMapUsrDtl.get("username"));
        email.setText(hashMapUsrDtl.get("email"));
        street.setText(hashMapUsrDtl.get("street"));
        suite.setText(hashMapUsrDtl.get("suite"));
        city.setText(hashMapUsrDtl.get("city"));
        zipcode.setText(hashMapUsrDtl.get("zipcode"));
        lat.setText(hashMapUsrDtl.get("lat"));
        lng.setText(hashMapUsrDtl.get("lng"));
        phone.setText(hashMapUsrDtl.get("phone"));
        website.setText(hashMapUsrDtl.get("website"));
        cname.setText(hashMapUsrDtl.get("cname"));
        catchPhrase.setText(hashMapUsrDtl.get("catchPhrase"));
        cbs.setText(hashMapUsrDtl.get("bs"));
    }
}
