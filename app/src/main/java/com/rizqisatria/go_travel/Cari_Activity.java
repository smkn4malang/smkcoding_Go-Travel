package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cari_Activity extends AppCompatActivity {
    Button mesen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_);
        mesen = (Button) findViewById(R.id.mesen);
        mesen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cari_Activity.this, TampilCarter_Activity.class);
                startActivity(intent);
            }
        });
    }
}
