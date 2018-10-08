package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Profil_Activity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageView edit;
    ImageView balik;
    Button logoutProfil;
    FirebaseAuth auth;
    private TextView email, nomor, jk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_);

        edit = (ImageView) findViewById(R.id.edit);
        balik = (ImageView) findViewById(R.id.balik);
        logoutProfil = (Button) findViewById(R.id.logoutprofil);

        email=(TextView) findViewById(R.id.email);
        nomor = (TextView) findViewById(R.id.nomer);
        jk = (TextView) findViewById(R.id.jk);

        auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");

        userInput();

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

        private void userInput(){
            databaseReference.child(Objects.requireNonNull(auth.getCurrentUser()).getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        User user = dataSnapshot.getValue(User.class);
                        email.setText(Objects.requireNonNull(user).getEmail());
                        nomor.setText(user.getNomer());
                        jk.setText(user.getJenis());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

