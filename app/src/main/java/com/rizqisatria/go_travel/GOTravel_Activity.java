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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GOTravel_Activity extends AppCompatActivity {
    Button btnlogin;
    TextView txtview;
    EditText nama , nomor;
    ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gotravel_);

        TextView text = (TextView) findViewById(R.id.textView2) ;
        nama = (EditText) findViewById(R.id.nama);
        nomor = (EditText) findViewById(R.id.nomor);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnlogin = (Button) findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = nama.getText().toString();
                final String nomer = nomor.getText().toString();

                if (TextUtils.isEmpty(id)){
                    Toast.makeText(getApplicationContext(), "Masukkan Email !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(nomer)){
                    Toast.makeText(getApplicationContext(), "Masukkan Nomor !",Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(id,nomer)
                        .addOnCompleteListener(GOTravel_Activity.this, new OnCompleteListener<AuthResult>(){
                    @Override
                            public void onComplete(@NonNull Task<AuthResult> task){
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()){
                            if(nomer.length() < 11) {
                                Toast.makeText(getApplicationContext(), "Nomer Anda Kurang!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Gagal Login!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Intent intent = new Intent(GOTravel_Activity.this, Fitur_Activity.class);
                            startActivity (intent);
                            finish();
                        }
                    }
                });

            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (GOTravel_Activity.this, Daftar_Activity.class);
                startActivity(intent);
            }
        });
    }
}
