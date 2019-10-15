package com.example.roompersistentlatihan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roompersistentlatihan.model.Mahasiswa;

public class RoomReadSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditText NamaMahasiswa = findViewById(R.id.namamahasiswa);
        EditText Nim = findViewById(R.id.nim);
        EditText Jurusan = findViewById(R.id.jurusan);
        Button btSubmit = findViewById(R.id.bt_submit);

        NamaMahasiswa.setEnabled(false);
        Nim.setEnabled(false);
        Jurusan.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        Mahasiswa mahasiswa = (Mahasiswa) getIntent().getSerializableExtra("data");
        if(mahasiswa !=null){
            NamaMahasiswa.setText(mahasiswa.getNamaMahasiswa());
            Nim.setText(mahasiswa.getNim());
            Jurusan.setText(mahasiswa.getJurusan());
        }

    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, RoomReadSingleActivity.class);
    }
}
