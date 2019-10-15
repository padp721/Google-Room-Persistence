package com.example.roompersistentlatihan;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roompersistentlatihan.data.factory.AppDatabase;
import com.example.roompersistentlatihan.model.Mahasiswa;

public class RoomCreateActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "db_mahasiswa").build();

        final EditText NamaMahasiswa    = findViewById(R.id.namamahasiswa);
        final EditText Nim              = findViewById(R.id.nim);
        final EditText Jurusan          = findViewById(R.id.jurusan);
        Button btSubmit                 = findViewById(R.id.bt_submit);

        final Mahasiswa mahasiswa = (Mahasiswa) getIntent().getSerializableExtra("data");

        if(mahasiswa !=null){
            NamaMahasiswa.setText(mahasiswa.getNamaMahasiswa());
            Nim.setText(mahasiswa.getNim());
            Jurusan.setText(mahasiswa.getJurusan());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mahasiswa.setNamaMahasiswa(NamaMahasiswa.getText().toString());
                    mahasiswa.setNim(Nim.getText().toString());
                    mahasiswa.setJurusan(Jurusan.getText().toString());

                    updateMahasiswa(mahasiswa);
                }
            });
        }else{
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Mahasiswa b = new Mahasiswa();
                    b.setJurusan(Jurusan.getText().toString());
                    b.setNim(Nim.getText().toString());
                    b.setNamaMahasiswa(NamaMahasiswa.getText().toString());
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
