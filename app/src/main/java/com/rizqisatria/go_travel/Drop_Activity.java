package com.rizqisatria.go_travel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Drop_Activity extends AppCompatActivity {
    public static final String extra = "extra";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Spinner spinner;
    Spinner spinner1;
    Spinner spinner2;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private Button btDatePicker;
    private Button btnPesan;
    private EditText jumlah;
    private TextView tanggal;
    String show,jemput,tujuan;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pesanan");

        spinner = (Spinner) findViewById(R.id.spinner1);
        String [] countries = {"Surabaya", "Malang", "Sidoarjo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,countries);
        spinner.setAdapter(adapter);

        spinner1 = (Spinner) findViewById(R.id.spinner2);
        String [] countries1 = {"Surabaya", "Malang", "Sidoarjo"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,countries1);
        spinner1.setAdapter(adapter1);

        spinner2 = (Spinner) findViewById(R.id.spinner3);
        String [] countries2 = {"1", "2", "3", "4"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,countries2);
        spinner2.setAdapter(adapter2);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        jemput = spinner1.getSelectedItem().toString();
        spinner2.getSelectedItem().toString();
        tujuan = spinner.getSelectedItem().toString().trim();
        tanggal = (TextView) findViewById(R.id.tampil);

        show = tanggal.getText().toString();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        btnPesan = (Button) findViewById(R.id.pesan1);
        tvDateResult = (TextView) findViewById(R.id.tampil);
        btDatePicker = (Button) findViewById(R.id.date);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });


        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final pesanan pesan=new pesanan(spinner.getSelectedItem().toString(),spinner1.getSelectedItem().toString(),spinner2.getSelectedItem().toString(),tanggal.getText().toString());
                progressBar.setVisibility(View.VISIBLE);
                final String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(pesan).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(Drop_Activity.this, TampilDrop_Activity.class);
                            //intent.putExtra("jml", spinner1.getSelectedItem().toString());
                            intent.putExtra(extra, key);
                            startActivity(intent);

                        }
                    }
                });
            }
        });
    }

    /*
    Config config = new Config(this);
    config.setTujuan(kota);
    config.getTujuan();
    tvJemput.setText(config.getTujuan());
     */
    private void showDateDialog(){

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tvDateResult.setText(" "+dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

}
