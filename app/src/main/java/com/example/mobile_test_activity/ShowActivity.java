package com.example.mobile_test_activity;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;




public class ShowActivity extends AppCompatActivity {
    ImageView Image2;
    TextView Email2,first_name2,last_name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        Image2=findViewById(R.id.ivImage2);
        Email2=findViewById(R.id.tvEmail2);
        first_name2= findViewById(R.id.tvFirstName2);
        last_name2=findViewById(R.id.tvLastName2);


                Bundle bundle = getIntent().getExtras();

                String  avatar = bundle.getString("Avatar");
                String  email = bundle.getString("Email");
                String  first_name = bundle.getString("First_name");
                String  Last_name = bundle.getString("Last_name");

                Glide.with(this).load(avatar).into(Image2);
                Email2.setText(email);
                first_name2.setText(first_name);
                last_name2.setText(Last_name);









    }
}