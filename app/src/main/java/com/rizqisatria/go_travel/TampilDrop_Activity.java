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

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class TampilDrop_Activity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button logout;
    TextView pesan2;
    FirebaseAuth auth;
    private TextView mJemput;
    private TextView mTujuan;
    private TextView mJumlah;
    private TextView mTanggal;
    private TextView mPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_drop_);

        mJemput = (TextView) findViewById(R.id.jemput);
        mJumlah = (TextView) findViewById(R.id.jumlah);
        mTujuan = (TextView) findViewById(R.id.tujuan);
        mTanggal = (TextView) findViewById(R.id.tanggal);
        mPrice = (TextView) findViewById(R.id.total);
        auth = FirebaseAuth.getInstance();

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
                    mPrice.setText(psn.getPrice());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pesan2=(TextView) findViewById(R.id.pesan2);

        logout = (Button) findViewById(R.id.logout1);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent2 = new Intent(TampilDrop_Activity.this, GOTravel_Activity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
    public void pesan(View view) {
        String formattedNumber = "6281336227548";
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
