package com.example.roompersistentlatihan.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tb_jurusan")
public class Jurusan {

    @PrimaryKey(autoGenerate = true)
    public int jurusanId;

    @ColumnInfo(name = "jurusanNama")
    public String jurusanNama;

    public Jurusan(String jurusanNama) {
        this.jurusanNama = jurusanNama;
    }

    public int getJurusanId() {
        return jurusanId;
    }

    public void setJurusanId(int jurusanId) {
        this.jurusanId = jurusanId;
    }

    public String getJurusanNama() {
        return jurusanNama;
    }

    public void setJurusanNama(String jurusanNama) {
        this.jurusanNama = jurusanNama;
    }

    public static Jurusan[] populateData() {
        return new Jurusan[] {
                new Jurusan("Teknologi Informasi"),
                new Jurusan("Teknik Elektro"),
                new Jurusan("Teknik Sipil"),
                new Jurusan("Teknik Mesin"),
                new Jurusan("Teknik Arsitektur")

        };
    }
}
