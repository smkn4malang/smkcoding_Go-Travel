package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Daftar_Activity extends AppCompatActivity {

    Button btndaftar;
    RadioButton radioButton, radioButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_);
        Button button = (Button) findViewById(R.id.button);
        radioButton = (RadioButton) findViewById(R.id.pria);
        radioButton1 = (RadioButton) findViewById(R.id.wanita);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Daftar_Activity.this, GOTravel_Activity.class);
                if(radioButton.isChecked()){
                    intent.putExtra("Jk", radioButton.getText().toString());
                }
                if(radioButton1.isChecked()){
                    intent.putExtra("Jk", radioButton1.getText().toString());
                }
                startActivity(intent);
            }
        });
    }
}
