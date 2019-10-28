package com.example.roompersistentlatihan.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tb_mahasiswa", foreignKeys = {@ForeignKey(
        entity = Jurusan.class,
        parentColumns = "jurusanId",
        childColumns = "jurusanId"
)})

public class Mahasiswa implements Serializable{

    @PrimaryKey(autoGenerate = true)
    public int mahasiswaId;

    @ColumnInfo(name = "nama_mahasiswa")
    public String namaMahasiswa;

    @ColumnInfo(name = "nim")
    public String nim;

    @ColumnInfo(name = "jurusanId", index = true)
    public int jurusanId;

    public int getMahasiswaId() {
        return mahasiswaId;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public String getNim() {
        return nim;
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

    public int getJurusanId() {
        return jurusanId;
    }

    public void setJurusanId(int jurusanId) {
        this.jurusanId = jurusanId;
    }
}
