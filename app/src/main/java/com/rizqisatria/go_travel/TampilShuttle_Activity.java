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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.OnClick;

public class TampilShuttle_Activity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button logout;
    TextView pesan3;
    private FirebaseAuth Auth;
    private TextView mJemput;
    private TextView mTujuan;
    private TextView mJumlah;
    private TextView mTanggal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_shuttle_);

        Auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pesanan");

        mJemput = (TextView) findViewById(R.id.jemput);
        mJumlah = (TextView) findViewById(R.id.jumlah);
        mTujuan = (TextView) findViewById(R.id.tujuan);
        mTanggal = (TextView) findViewById(R.id.tanggal);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pesanan");
        String key = getIntent().getStringExtra(Drop_Activity.extra);
        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pesanan psn = dataSnapshot.getValue(pesanan.class);
                mJemput.setText(psn.getJemput());
                mTujuan.setText(psn.getTujuan());
                mJumlah.setText(psn.getJumlah());
                mTanggal.setText(psn.getTanggal());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       pesan3 = (TextView) findViewById(R.id.pesan3) ;


        logout = (Button) findViewById(R.id.logout2);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.signOut();
                Intent intent2 = new Intent(TampilShuttle_Activity.this, GOTravel_Activity.class);
                startActivity(intent2);
            }
        });

    }
//    public void Shows(){
//        String jemput_kota = (String) bundle.get("JEMPUT_KOTA");
//        mJemput.setText(jemput_kota);
//
//        String tujuan = (String) bundle.get("TUJUAN_KOTA");
//        mTujuan.setText(tujuan);
//
//        String jumlah = (String) bundle.get("JUMLAH_PESANAN");
//        mJumlah.setText(jumlah);
//
//        String tanggal = (String) bundle.get("TANGGAL_PESANAN");
//        mTanggal.setText(tanggal);
//    }

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
