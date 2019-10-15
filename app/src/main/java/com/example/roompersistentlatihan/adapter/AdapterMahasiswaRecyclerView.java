package com.example.roompersistentlatihan.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.roompersistentlatihan.R;
import com.example.roompersistentlatihan.RoomCreateActivity;
import com.example.roompersistentlatihan.RoomReadSingleActivity;
import com.example.roompersistentlatihan.data.factory.AppDatabase;
import com.example.roompersistentlatihan.model.Mahasiswa;

public class AdapterMahasiswaRecyclerView extends RecyclerView.Adapter<AdapterMahasiswaRecyclerView.ViewHolder> {

    private ArrayList<Mahasiswa> daftarMahasiswa;
    private Context context;
    private AppDatabase db;

    public AdapterMahasiswaRecyclerView(ArrayList<Mahasiswa> mahasiswas, Context ctx){

        daftarMahasiswa = mahasiswas;
        context = ctx;

        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "db_mahasiswa").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_namamahasiswa);
            cvMain = v.findViewById(R.id.cv_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = daftarMahasiswa.get(position).getNamaMahasiswa();

        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Mahasiswa mahasiswa = db.mahasiswaDAO().selectMahasiswaDetail(daftarMahasiswa.get(position).getMahasiswaId());
                context.startActivity(RoomReadSingleActivity.getActIntent((Activity) context).putExtra("data", mahasiswa));
            }
        });
        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.view_dialog);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = dialog.findViewById(R.id.bt_edit_data);
                Button delButton = dialog.findViewById(R.id.bt_delete_data);

                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onEditMahasiswa(position);
                            }
                        }
                );

                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onDeteleMahasiswa(position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    private void onDeteleMahasiswa(int position){
        db.mahasiswaDAO().deleteMahasiswa(daftarMahasiswa.get(position));
        daftarMahasiswa.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarMahasiswa.size());
    }

    private void onEditMahasiswa(int position){
        context.startActivity(RoomCreateActivity.getActIntent((Activity) context).putExtra("data", daftarMahasiswa.get(position)));
    }

    @Override
    public int getItemCount() {

        return daftarMahasiswa.size();
    }
}
