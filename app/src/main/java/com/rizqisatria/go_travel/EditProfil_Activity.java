package com.rizqisatria.go_travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditProfil_Activity extends AppCompatActivity {
    Button btsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil_);

        btsave = (Button) findViewById(R.id.save);
        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfil_Activity.this, Profil_Activity.class);
                startActivity(intent);
            }
        });
    }
}
