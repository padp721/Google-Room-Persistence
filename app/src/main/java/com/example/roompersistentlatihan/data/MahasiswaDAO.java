package com.example.roompersistentlatihan.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.roompersistentlatihan.model.Mahasiswa;

@Dao
public interface MahasiswaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMahasiswa(Mahasiswa mahasiswa);

    @Update
    int updateMahasiswa(Mahasiswa mahasiswa);

    @Delete
    int deleteMahasiswa(Mahasiswa mahasiswa);

    @Query("SELECT * FROM tb_mahasiswa")
    Mahasiswa[] selectAllMahasiswas();

    @Query("SELECT * FROM tb_mahasiswa WHERE mahasiswaId = :id LIMIT 1")
    Mahasiswa selectMahasiswaDetail(int id);
}
