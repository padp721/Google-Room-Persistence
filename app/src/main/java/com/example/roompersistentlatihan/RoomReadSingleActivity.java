package com.example.roompersistentlatihan;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.roompersistentlatihan.adapter.JurusanAdapter;
import com.example.roompersistentlatihan.data.factory.AppDatabase;
import com.example.roompersistentlatihan.model.Jurusan;
import com.example.roompersistentlatihan.model.Mahasiswa;

import java.util.List;

public class RoomReadSingleActivity extends AppCompatActivity {

    private AppDatabase db;
    public int jurusanId = 0 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "db_mahasiswa").allowMainThreadQueries().build();

        EditText NamaMahasiswa = findViewById(R.id.namamahasiswa);
        EditText Nim = findViewById(R.id.nim);
        final Spinner spinner           = findViewById(R.id.spinnerJurusan);
        Button btSubmit = findViewById(R.id.bt_submit);
        List<Jurusan> listJurusan       = db.jurusanDAO().getAllJurusan();

        NamaMahasiswa.setEnabled(false);
        Nim.setEnabled(false);
        spinner.setEnabled(false);

        Mahasiswa mahasiswa = (Mahasiswa) getIntent().getSerializableExtra("data");

        JurusanAdapter myAdapter = new JurusanAdapter(this, listJurusan);
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Jurusan jurusan = (Jurusan) parent.getItemAtPosition(position);
                String jurusanName = jurusan.getJurusanNama();
                jurusanId = jurusan.getJurusanId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(mahasiswa !=null){
            NamaMahasiswa.setText(mahasiswa.getNamaMahasiswa());
            Nim.setText(mahasiswa.getNim());
//            Jurusan.setText(mahasiswa.getJurusan());
            spinner.setSelection(mahasiswa.getJurusanId()-1);

        }

    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, RoomReadSingleActivity.class);
    }
}
