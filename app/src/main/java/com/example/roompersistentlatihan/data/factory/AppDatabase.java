package com.example.roompersistentlatihan.data.factory;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.roompersistentlatihan.data.JurusanDAO;
import com.example.roompersistentlatihan.data.MahasiswaDAO;
import com.example.roompersistentlatihan.model.Jurusan;
import com.example.roompersistentlatihan.model.Mahasiswa;

import java.util.concurrent.Executors;

@Database(entities = {Mahasiswa.class, Jurusan.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MahasiswaDAO mahasiswaDAO();
    public abstract JurusanDAO jurusanDAO();

    private static AppDatabase INSTANCE;

    public synchronized static AppDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static AppDatabase  buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "db_mahasiswa")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).jurusanDAO().insertAll(Jurusan.populateData());
                            }
                        });
                    }
                })
                .allowMainThreadQueries()
                .build();
    }

}
