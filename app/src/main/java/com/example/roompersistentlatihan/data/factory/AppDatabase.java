package com.example.roompersistentlatihan.data.factory;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.roompersistentlatihan.data.MahasiswaDAO;
import com.example.roompersistentlatihan.model.Mahasiswa;

@Database(entities = {Mahasiswa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MahasiswaDAO mahasiswaDAO();

}
