package com.rizqisatria.go_travel;

import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class TampilShuttle_Activity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,ref;
    Button logout;
    private String Saldo,price;
    TextView pesan3,saldoView;
    private FirebaseAuth Auth;
    private TextView mJemput;
    private TextView mTujuan;
    private TextView mJumlah;
    private TextView mTanggal;
    private TextView mPrice,SaldoAkhr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_shuttle_);

        Auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pesanan");
        ref = firebaseDatabase.getReference("User");

        mJemput = (TextView) findViewById(R.id.jemput);
        mJumlah = (TextView) findViewById(R.id.jumlah);
        mTujuan = (TextView) findViewById(R.id.tujuan);
        mTanggal = (TextView) findViewById(R.id.tanggal);
        mPrice = (TextView) findViewById(R.id.total);
        saldoView = (TextView)findViewById(R.id.Saldo);
        SaldoAkhr =(TextView)findViewById(R.id.SaldoAkhir);

        getPesanan();
        prosesbayar();

        pesan3 = (TextView) findViewById(R.id.pesan3) ;

        logout = (Button) findViewById(R.id.logout2);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(TampilShuttle_Activity.this, Fitur_Activity.class);
                startActivity(intent2);
                finish();
            }
        });

    }

    private void getPesanan(){
        String key = getIntent().getStringExtra(Shuttle_Activity.extra);
        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pesanan psn = dataSnapshot.getValue(pesanan.class);
                mJemput.setText(Objects.requireNonNull(psn).getJemput());
                mTujuan.setText(psn.getTujuan());
                mJumlah.setText(psn.getJumlah());
                mTanggal.setText(psn.getTanggal());
                mPrice.setText(psn.getPrice());
                price=psn.getPrice();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(getApplicationContext(),"Price "+mPrice.getText().toString(),Toast.LENGTH_SHORT).show();
    }

    private void prosesbayar(){
        ref.child(Objects.requireNonNull(Auth.getCurrentUser()).getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    Saldo = Objects.requireNonNull(user).getSaldo();
                    saldoView.setText(Saldo);


//                    String bayar = mPrice.getText().toString();
                    Integer SaldoC= Integer.parseInt(Saldo);
                    Integer Pay = Integer.parseInt(mPrice.getText().toString());

                    if(SaldoC <= Pay){
                        Toast.makeText(getApplicationContext(),"Saldo anda Tidak cukup",Toast.LENGTH_SHORT).show();
                    }else{
                        final Integer SaldoAkhir = SaldoC - Pay;
                        final String Finalsaldo = String.valueOf(SaldoAkhir);
                        SaldoAkhr.setText(Finalsaldo);

                        ref.child(Objects.requireNonNull(Auth.getCurrentUser()).getUid()).child("saldo").setValue(Finalsaldo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"Transaksi Berhasil"+Finalsaldo,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
}

