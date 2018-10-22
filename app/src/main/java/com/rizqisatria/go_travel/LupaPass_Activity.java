package com.rizqisatria.go_travel;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPass_Activity extends AppCompatActivity {

    private EditText email;
    private Button reset;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_pass_);
        reset =(Button) findViewById(R.id.button);
        email = (EditText)findViewById(R.id.email);

        auth = FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Email = email.getText().toString();

                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(getApplicationContext(), "Email Empty",Toast.LENGTH_SHORT).show();
                }else{
                    auth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "Reset email Sended to "+Email,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
