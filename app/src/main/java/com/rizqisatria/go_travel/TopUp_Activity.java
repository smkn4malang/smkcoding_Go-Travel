package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class TopUp_Activity extends AppCompatActivity {

    Button button;
    EditText uang;
    private Integer ppn = 5000;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        button = (Button) findViewById(R.id.button);
        uang = (EditText)findViewById(R.id.uang);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = uang.getText().toString();
                Integer Nominal = Integer.parseInt(temp);
                Bundle extras = new Bundle();
                extras.putInt("Nominal",Nominal);
                extras.putInt("ppn",ppn);
                Intent intent = new Intent(TopUp_Activity.this, Bukti_Activity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }
}
