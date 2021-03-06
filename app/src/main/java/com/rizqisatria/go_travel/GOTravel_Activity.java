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
    Button btnlogin, btndaftar;
    TextView txtview;
    EditText nama , nomor;
    ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gotravel_);
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(GOTravel_Activity.this, Fitur_Activity.class));
            finish();
        }

        TextView text = (TextView) findViewById(R.id.textView2) ;
        nama = (EditText) findViewById(R.id.nama);
        nomor = (EditText) findViewById(R.id.nomor);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnlogin = (Button) findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();
        btndaftar = (Button) findViewById(R.id.button2);

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
                    Toast.makeText(getApplicationContext(), "Masukkan Password !",Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                auth.signInWithEmailAndPassword(id,nomer)
                        .addOnCompleteListener(GOTravel_Activity.this, new OnCompleteListener<AuthResult>(){
                    @Override
                            public void onComplete(@NonNull Task<AuthResult> task){
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()){
                            if(nomer.length() < 6) {
                                Toast.makeText(getApplicationContext(), "Password Anda Kurang!", Toast.LENGTH_SHORT).show();
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
        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (GOTravel_Activity.this, Daftar_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (GOTravel_Activity.this, LupaPass_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
          Intent i = new Intent(Intent.ACTION_MAIN);
          i.addCategory(Intent.CATEGORY_HOME);
          i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(i);
    }
}
