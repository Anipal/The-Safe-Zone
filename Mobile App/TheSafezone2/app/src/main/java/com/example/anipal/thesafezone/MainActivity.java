package com.example.anipal.thesafezone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView reg_v,reg_s, loc_f, loc_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg_v = (CardView)findViewById(R.id.registration_volunteer);
        reg_s = (CardView)findViewById(R.id.registration_safezone);
        loc_f = (CardView)findViewById(R.id.location_friends);
        loc_s = (CardView)findViewById(R.id.location_safezone);
        reg_v.setOnClickListener(this);
        reg_s.setOnClickListener(this);
        loc_f.setOnClickListener(this);
        loc_s.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        Intent i;
        switch (v.getId()) {
            case R.id.registration_volunteer:i = (new Intent(this,registration_volunteer.class));startActivity(i);break;
            case R.id.registration_safezone:i = (new Intent(this,registration_safezone.class));startActivity(i);break;
            case R.id.location_friends:i = (new Intent(this,location_friends.class));startActivity(i);break;
            case R.id.location_safezone:i = (new Intent(this,location_safezone2.class));startActivity(i);break;
            default:break;



        }
    }
}
