package com.example.roompersistentlatihan.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.roompersistentlatihan.model.Jurusan;

import java.util.List;

@Dao
public interface JurusanDAO {

    @Insert
    void insertAll(Jurusan... jurusans);

    @Query("SELECT * FROM tb_jurusan")
    List<Jurusan> getAllJurusan();
}
