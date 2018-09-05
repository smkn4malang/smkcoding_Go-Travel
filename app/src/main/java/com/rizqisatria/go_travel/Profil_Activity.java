package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Profil_Activity extends AppCompatActivity {

    ImageView edit;
    ImageView balik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_);

        edit = (ImageView) findViewById(R.id.edit);
        balik = (ImageView) findViewById(R.id.balik);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profil_Activity.this, EditProfil_Activity.class);
                startActivity(intent);
            }
        });
        balik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Profil_Activity.this, Fitur_Activity.class);
                startActivity(intent1);
            }
        });
    }
}
