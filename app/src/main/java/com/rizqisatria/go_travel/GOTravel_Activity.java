package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GOTravel_Activity extends AppCompatActivity {
    Button btnlogin;
    TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gotravel_);
        Button button = (Button) findViewById(R.id.button);
        TextView text = (TextView) findViewById(R.id.textView2) ;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GOTravel_Activity.this, Fitur_Activity.class);
                startActivity(intent);
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (GOTravel_Activity.this, Daftar_Activity.class);
                startActivity(intent);
            }
        });
    }
}
