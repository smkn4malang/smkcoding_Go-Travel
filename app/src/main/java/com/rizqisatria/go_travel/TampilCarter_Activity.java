package com.rizqisatria.go_travel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TampilCarter_Activity extends AppCompatActivity {

    List<SimpleCarter> data = new ArrayList<>();
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carter_);

        ButterKnife.bind (this);

        dataTetep();

        RecyclerAdapter adapter = new RecyclerAdapter(this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    void dataTetep(){
        for(int i = 0; i < 10; i++){
            SimpleCarter model = new SimpleCarter();
            model.setNama("Nama yang ke "+(i+1));
            model.setKelas("Kelas yang ke"+(i+1));
            data.add(model);
        }

    }
}
