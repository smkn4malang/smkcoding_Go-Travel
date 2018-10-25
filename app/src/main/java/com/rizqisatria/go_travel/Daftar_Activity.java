package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Daftar_Activity extends AppCompatActivity {

    private Button btndaftar;
    private RadioButton radioButton, radioButton1;
    private EditText Inptemail, nomer;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private RadioGroup jenis;
    private String JK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_);

        jenis = (RadioGroup) findViewById(R.id.jenis);

        Inptemail = (EditText) findViewById(R.id.email);
        nomer = (EditText) findViewById(R.id.nomer);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btndaftar = (Button) findViewById(R.id.button);
        radioButton = (RadioButton) findViewById(R.id.pria);
        radioButton1 = (RadioButton) findViewById(R.id.wanita);
        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("User");

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Inptemail.getText().toString().trim();
                String password = nomer.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Masukkan Email Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password Anda Kurang, Minim 6 angka", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Daftar_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Daftar_Activity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    RadioButton();
                                    ValueEventListener valueEventListener = ref.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            User u =new User(
                                                    Inptemail.getText().toString(),
                                                    nomer.getText().toString(),
                                                    JK
                                            );
                                            ref.child(Objects.requireNonNull(auth.getCurrentUser()).getUid()).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    auth.signOut();
                                                    startActivity(new Intent(Daftar_Activity.this, GOTravel_Activity.class));
                                                    finish();
                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    public void RadioButton(){

        int RadioButtonID = jenis.getCheckedRadioButtonId();

            RadioButton selected = (RadioButton) findViewById(RadioButtonID);
            JK = selected.getText().toString();

    }
}