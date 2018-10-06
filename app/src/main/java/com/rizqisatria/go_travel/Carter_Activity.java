package com.rizqisatria.go_travel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Carter_Activity extends AppCompatActivity {

    Spinner spinner;
    Spinner spinner1;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private Button btDatePicker;
    private Button btcari;
    private String jemput;
    private String tujuan;
    private String jumlah;
    private String tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carter_);
        spinner = (Spinner) findViewById(R.id.spinner1);
        String [] countries = {"Surabaya", "Malang", "Sidoarjo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,countries);
        spinner.setAdapter(adapter);

        spinner1 = (Spinner) findViewById(R.id.spinner2);
        String [] countries1 = {"1", "2", "3", "4"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,countries1);
        spinner1.setAdapter(adapter1);


        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        btcari = (Button) findViewById(R.id.cari);
        btcari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Carter_Activity.this, Cari_Activity.class);
                startActivity(intent);
            }
        });
        tvDateResult = (TextView) findViewById(R.id.tampil);
        btDatePicker = (Button) findViewById(R.id.date);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }


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
    public void senddata(){

        Intent intent = new Intent(Carter_Activity.this, TampilShuttle_Activity.class);

        intent.putExtra("KOTA TUJUAN", tujuan);
        intent.putExtra("KOTA JEMPUT", jemput);
        intent.putExtra("JUMLAH PESANAN", jumlah);
        intent.putExtra("TANGAL PESANAN", tanggal);
        startActivity(intent);
    }
}
