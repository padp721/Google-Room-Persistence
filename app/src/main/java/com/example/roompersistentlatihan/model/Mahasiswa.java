package com.example.roompersistentlatihan.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tb_mahasiswa")
public class Mahasiswa implements Serializable{

    @PrimaryKey(autoGenerate = true)
    public int mahasiswaId;

    @ColumnInfo(name = "nama_mahasiswa")
    public String namaMahasiswa;

    @ColumnInfo(name = "nim")
    public String nim;

    @ColumnInfo(name = "jurusan")
    public String jurusan;

    public int getMahasiswaId() {
        return mahasiswaId;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public String getNim() {
        return nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setMahasiswaId(int mahasiswaId) {
        this.mahasiswaId = mahasiswaId;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}
