package com.example.roompersistentlatihan;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.roompersistentlatihan.adapter.AdapterMahasiswaRecyclerView;
import com.example.roompersistentlatihan.data.factory.AppDatabase;
import com.example.roompersistentlatihan.model.Mahasiswa;

public class RoomReadActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Mahasiswa> daftarMahasiswa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);


        daftarMahasiswa = new ArrayList<>();


        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "db_mahasiswa").allowMainThreadQueries().build();


        rvView = findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);


        daftarMahasiswa.addAll(Arrays.asList(db.mahasiswaDAO().selectAllMahasiswas()));


        adapter = new AdapterMahasiswaRecyclerView(daftarMahasiswa, this);
        rvView.setAdapter(adapter);
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, RoomReadActivity.class);
    }
}
