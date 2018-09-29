package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;

public class Profil_Activity extends AppCompatActivity {

    ImageView edit;
    ImageView balik;
    Button logoutProfil;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_);

        edit = (ImageView) findViewById(R.id.edit);
        balik = (ImageView) findViewById(R.id.balik);
        logoutProfil = (Button) findViewById(R.id.logoutprofil);
        auth = FirebaseAuth.getInstance();

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
        logoutProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent2 = new Intent(Profil_Activity.this, GOTravel_Activity.class);
                startActivity(intent2);

            }
        });

        }

    }

