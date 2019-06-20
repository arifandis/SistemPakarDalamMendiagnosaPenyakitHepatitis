package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.Global;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin.DetailRiwayatActivity;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengguna;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.DaftarRiwayatPenggunaanPresenter;

import java.text.ParseException;
import java.util.List;

public class DaftarRiwayatPenggunaanRecyclerAdapter extends RecyclerView.Adapter<DaftarRiwayatPenggunaanRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Pengguna> penggunaList;
    private DaftarRiwayatPenggunaanPresenter mPresenter;

    public DaftarRiwayatPenggunaanRecyclerAdapter(Context context, List<Pengguna> penggunaList, DaftarRiwayatPenggunaanPresenter mPresenter) {
        this.context = context;
        this.penggunaList = penggunaList;
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pengguna,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Pengguna pengguna = penggunaList.get(i);

        viewHolder.tvNo.setText(String.valueOf(i+1));
        viewHolder.tvNama.setText(pengguna.getNama());
        viewHolder.tvUsia.setText(pengguna.getUsia()+" tahun");
        try {
            viewHolder.tvTanggal.setText(Global.dateFormat1(pengguna.getTanggal())+" WIB");
        } catch (ParseException e) {
            e.printStackTrace();
            viewHolder.tvTanggal.setText("-");
        }

        viewHolder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.doDeleteDaftarRiwayat(pengguna.getId());
            }
        });

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailRiwayatActivity.class);
                intent.putExtra("id",pengguna.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return penggunaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNo,tvNama,tvUsia,tvTanggal;
        private ConstraintLayout parent;
        private ImageButton ibtnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ibtnDelete = itemView.findViewById(R.id.itemPengguna_btnDelete);
            parent = itemView.findViewById(R.id.itemPengguna_parent);
            tvNama = itemView.findViewById(R.id.itemPengguna_tvNama);
            tvNo = itemView.findViewById(R.id.itemPengguna_tvNo);
            tvUsia = itemView.findViewById(R.id.itemPengguna_tvUsia);
            tvTanggal = itemView.findViewById(R.id.itemPengguna_tvTanggal);
        }
    }
}
