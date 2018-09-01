package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Fitur_Activity extends AppCompatActivity {

    ImageView imgsaldo;
    ImageView imgshuttle;
    ImageView imgdrop;
    ImageView imgcarter;
    ImageView profil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitur_);

        imgshuttle = (ImageView) findViewById(R.id.shuttle);
        imgdrop = (ImageView) findViewById(R.id.drop);
        imgcarter = (ImageView) findViewById(R.id.carter);
        imgsaldo = (ImageView) findViewById(R.id.saldo);
        profil = (ImageView) findViewById(R.id.profil);

        imgshuttle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Fitur_Activity.this, Shuttle_Activity.class);
                startActivity(intent);
            }
        });

        imgsaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Fitur_Activity.this, Saldo_Activity.class);
                startActivity(intent1);
            }
        });

        profil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent (Fitur_Activity.this, Profil_Activity.class );
                startActivity(intent3);
            }
        });
        imgdrop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent (Fitur_Activity.this, Drop_Activity.class );
                startActivity(intent2);
            }
        });
        imgcarter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent (Fitur_Activity.this, Carter_Activity.class );
                startActivity(intent4);
            }
        });
    }

}
