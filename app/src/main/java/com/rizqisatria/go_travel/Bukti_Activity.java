package com.rizqisatria.go_travel;

import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Bukti_Activity extends AppCompatActivity {

    private TextView nominal,total,PpN;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth Auth;
    private Button simpan;
    private String SaldoAdded;
    private Integer temp,tempPpn, SaldoOri, Total;
    private DatabaseReference           ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bukti_);

        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("User");
        Auth = FirebaseAuth.getInstance();
        nominal = (TextView)findViewById(R.id.nominal);
        total = (TextView)findViewById(R.id.Total);
        simpan = (Button)findViewById(R.id.simpan);
        PpN =(TextView)findViewById(R.id.PpN);

        getNominal();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Bukti_Activity.this, Profil_Activity.class));
                finish();
            }
        });
    }

    public void pesan(View view) {
            String formattedNumber = "6287859706611";
            try {
                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT,"");
                sendIntent.putExtra("jid", formattedNumber +"@s.whatsapp.net");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
            }
            catch (Exception e){
                Toast.makeText(this,"Error/n"+ e.toString(),Toast.LENGTH_SHORT).show();
            }
    }

    private void getNominal(){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        temp = Objects.requireNonNull(extras).getInt("Nominal",0);
        tempPpn = extras.getInt("ppn",0);

        //integer Total
        Total = temp + tempPpn;

        String TotalBayar = String.valueOf(Total);
        String Nominal = String.valueOf(temp);
        String PPN = String.valueOf(tempPpn);

        total.setText(TotalBayar);
        nominal.setText(Nominal);
        PpN.setText(PPN);


        ref.child(Objects.requireNonNull(Auth.getCurrentUser()).getUid()).child("saldo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SaldoOri= Integer.parseInt(dataSnapshot.getValue(String.class));
                Integer saldoUpdate = SaldoOri + temp;
                SaldoAdded = String.valueOf(saldoUpdate);

                ref.child(Auth.getCurrentUser().getUid()).child("saldo").setValue(SaldoAdded).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Top up Berhasil, Jumlah Saldo "+ SaldoAdded,Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
