package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

public class TampilShuttle_Activity extends AppCompatActivity {
    Button logout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_shuttle_);

        logout2 = (Button) findViewById(R.id.logout2);
        logout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TampilShuttle_Activity.this, GOTravel_Activity.class);
                startActivity(intent);
            }
        });
    }

}
