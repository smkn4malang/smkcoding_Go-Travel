package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class EditProfil_Activity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    private FirebaseAuth Auth;
    private String JK;
    Button btsave;
    RadioButton pria,wanita;
    RadioGroup jenis;
    String saldo;
    private EditText nomor, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil_);

        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("User");
        Auth = FirebaseAuth.getInstance();

        pria = (RadioButton)findViewById(R.id.pria);
        wanita = (RadioButton)findViewById(R.id.wanita);

        jenis = (RadioGroup)findViewById(R.id.jenis);
        email =(EditText) findViewById(R.id.email);
        nomor = (EditText) findViewById(R.id.nomor);
        btsave = (Button) findViewById(R.id.save);

        getData();

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
                Intent intent = new Intent(EditProfil_Activity.this, Profil_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getData(){
        ref.child(Objects.requireNonNull(Auth.getCurrentUser()).getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    email.setText(Objects.requireNonNull(user).getEmail());
                    nomor.setText(user.getNomer());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void update(){
        ref.child(Objects.requireNonNull(Auth.getCurrentUser()).getUid()).child("saldo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                saldo = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        RadioButton();
        if (JK == null){
            Toast.makeText(getApplicationContext(), "Jenis Kelamin Kosong",Toast.LENGTH_SHORT).show();
        }
        User user = new User(
                email.getText().toString(),
                nomor.getText().toString(),
                JK,
                saldo
        );
        ref.child(Objects.requireNonNull(Auth.getCurrentUser()).getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void RadioButton(){
        int RadioButtonID = jenis.getCheckedRadioButtonId();

        RadioButton selected = (RadioButton) findViewById(RadioButtonID);
        JK = selected.getText().toString();

    }
}
