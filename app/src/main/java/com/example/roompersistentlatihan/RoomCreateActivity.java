package com.example.roompersistentlatihan;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.roompersistentlatihan.adapter.JurusanAdapter;
import com.example.roompersistentlatihan.data.factory.AppDatabase;
import com.example.roompersistentlatihan.model.Jurusan;
import com.example.roompersistentlatihan.model.Mahasiswa;

import java.util.List;

public class RoomCreateActivity extends AppCompatActivity {

    private AppDatabase db;
    public int jurusanId = 0 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "db_mahasiswa").allowMainThreadQueries().build();

        final EditText NamaMahasiswa    = findViewById(R.id.namamahasiswa);
        final EditText Nim              = findViewById(R.id.nim);
        final Spinner spinner           = findViewById(R.id.spinnerJurusan);
        Button btSubmit                 = findViewById(R.id.bt_submit);
        List<Jurusan> listJurusan       = db.jurusanDAO().getAllJurusan();

        final Mahasiswa mahasiswa = (Mahasiswa) getIntent().getSerializableExtra("data");

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
            spinner.setSelection(mahasiswa.getJurusanId()-1);
//            Jurusan.setText(mahasiswa.getJurusan());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mahasiswa.setNamaMahasiswa(NamaMahasiswa.getText().toString());
                    mahasiswa.setNim(Nim.getText().toString());
//                    mahasiswa.setJurusan(Jurusan.getText().toString());
                    mahasiswa.setJurusanId(jurusanId);

                    updateMahasiswa(mahasiswa);
                }
            });
        }else{
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Mahasiswa b = new Mahasiswa();
//                    b.setJurusan(Jurusan.getText().toString());
                    b.setNim(Nim.getText().toString());
                    b.setNamaMahasiswa(NamaMahasiswa.getText().toString());
                    b.setJurusanId(jurusanId);
                    insertData(b);
                }
            });
        }
    }

    private void updateMahasiswa(final Mahasiswa mahasiswa){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.mahasiswaDAO().updateMahasiswa(mahasiswa);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(RoomCreateActivity.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void insertData(final Mahasiswa mahasiswa){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.mahasiswaDAO().insertMahasiswa(mahasiswa);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(RoomCreateActivity.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, RoomCreateActivity.class);
    }
}
