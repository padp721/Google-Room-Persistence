package com.example.roompersistentlatihan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.roompersistentlatihan.R;
import com.example.roompersistentlatihan.model.Jurusan;

import java.util.List;

public class JurusanAdapter extends BaseAdapter implements SpinnerAdapter {

    private LayoutInflater mInflater;
    private List<Jurusan> jurusanList;

    public JurusanAdapter(Context context, List<Jurusan> jurusanList) {
        mInflater = LayoutInflater.from(context);
        this.jurusanList = jurusanList;
    }

    @Override
    public int getCount() {
        return jurusanList.size();
    }

    @Override
    public Object getItem(int position) {
        return jurusanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return jurusanList.get(position).getJurusanId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.spinner_item, parent, false);
        }
        Jurusan item = (Jurusan) getItem(position);
        TextView textView = view.findViewById(R.id.spinner_item);
        textView.setText(item.getJurusanNama());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.spinner_item, parent, false);
        }
        Jurusan item = (Jurusan) getItem(position);
        TextView textView = view.findViewById(R.id.spinner_item);
        textView.setText(item.getJurusanNama());
        return view;


    }
}
