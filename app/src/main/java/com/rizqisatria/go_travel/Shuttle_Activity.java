package com.rizqisatria.go_travel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class Shuttle_Activity extends AppCompatActivity {
    public static final String extra = "extra";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Spinner spinner2;
    Spinner spinner;
    Spinner spinner1;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult, getspinner;
    private Button btDatePicker;
    private Button btPesan;
    private String jemput;
    private String tujuan;
    private EditText jumlah;
    private TextView tanggal;
    private TextView price;
    String show;
    Integer harga = 0;
    FirebaseDatabase database;
    DatabaseReference ref;
    Order order;
    Intent intent;
    ProgressBar progressBar;
    String Tharga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle_);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pesanan");

        price = (TextView) findViewById(R.id.harga);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Order");
        order = new Order();

        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);

        spinner = (Spinner) findViewById(R.id.spinner1);
        String[] countries = {"Tabanan", "Kuta", "Denpasar"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, countries);
        spinner.setAdapter(adapter);

        spinner1 = (Spinner) findViewById(R.id.spinner2);
        String[] countries1 = {"Denpasar", "Kuta", "Tabanan"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, countries1);
        spinner1.setAdapter(adapter1);

        spinner2 = (Spinner) findViewById(R.id.spinner3);
        String[] countries2 = {"1", "2", "3", "4"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, countries2);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String plhpesanan = spinner2.getSelectedItem().toString();
                Integer plh = Integer.parseInt(plhpesanan);
                String jmpt = spinner.getSelectedItem().toString();
                switch (jmpt) {
                    case "Tabanan":
                        harga = plh * 15000;
                        Tharga = String.valueOf(harga);
                        price.setText(Tharga);
                        break;
                    case "Kuta":
                        harga = plh * 50000;
                        Tharga = String.valueOf(harga);
                        price.setText(Tharga);
                        break;
                    case "Denpasar":
                        harga = plh * 35000;
                        Tharga = String.valueOf(harga);
                        price.setText(Tharga);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dateFormatter = new

                SimpleDateFormat("dd-MM-yyyy", Locale.US);

        jemput = spinner1.getSelectedItem().toString().trim();
        tujuan = spinner.getSelectedItem().toString().trim();
        spinner2.getSelectedItem().toString().trim();
        tanggal = (TextView)findViewById(R.id.tampil);
        show = tanggal.getText().toString().trim();
        btPesan = (Button)findViewById(R.id.pesan1);
        btPesan.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                final pesanan pesan = new pesanan(spinner1.getSelectedItem().toString(), spinner.getSelectedItem().toString(), spinner2.getSelectedItem().toString(), tanggal.getText().toString(),price.getText().toString());
                progressBar.setVisibility(View.VISIBLE);
                final String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(pesan).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(Shuttle_Activity.this, TampilShuttle_Activity.class);
                            //intent.putExtra("jml", spinner1.getSelectedItem().toString());
                            intent.putExtra(extra, key);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        tvDateResult = (TextView)

                findViewById(R.id.tampil);

        btDatePicker = (Button)

                findViewById(R.id.date);
        btDatePicker.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }

    private void showDateDialog() {

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tvDateResult.setText(" " + dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }


    public void insert(View view) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}


