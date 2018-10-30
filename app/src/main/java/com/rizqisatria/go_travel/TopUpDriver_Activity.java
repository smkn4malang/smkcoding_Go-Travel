package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class TopUpDriver_Activity extends AppCompatActivity {

    Button button;
    private EditText nominal;
    private Integer ppn = 3000;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_driver_);

        nominal =(EditText)findViewById(R.id.Nominal);
        button = (Button) findViewById(R.id.button1);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer Nominal = Integer.parseInt(nominal.getText().toString());
                Bundle extras = new Bundle();
                extras.putInt("Nominal",Nominal);
                extras.putInt("ppn",ppn);
                Intent intent = new Intent(TopUpDriver_Activity.this, Bukti_Activity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }

}
