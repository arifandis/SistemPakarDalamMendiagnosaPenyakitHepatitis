package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin.TambahPengetahuanActivity;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.PengetahuanPresenter;

import java.util.List;

public class PengetahuanRecyclerAdapter extends RecyclerView.Adapter<PengetahuanRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Pengetahuan> pengetahuanList;
    private PengetahuanPresenter mPresenter;

    public PengetahuanRecyclerAdapter(Context context, List<Pengetahuan> pengetahuanList, PengetahuanPresenter mPresenter) {
        this.context = context;
        this.pengetahuanList = pengetahuanList;
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pengetahuan,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Pengetahuan pengetahuan = pengetahuanList.get(i);

        viewHolder.tvNo.setText(String.valueOf(i+1));
        viewHolder.tvGejala.setText(pengetahuan.getKodeGejala());
        viewHolder.tvPenyakit.setText(pengetahuan.getIdPenyakit());
        viewHolder.tvBobot.setText("Bobot: "+pengetahuan.getBobot());

        viewHolder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deletePengetahuan(pengetahuan.getIdPengetahuan());
            }
        });

        viewHolder.ibtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TambahPengetahuanActivity.class);
                intent.putExtra("status","edit");
                intent.putExtra("id_pengetahuan",pengetahuan.getIdPengetahuan());
                ((Activity) context).startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pengetahuanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGejala,tvPenyakit,tvBobot,tvNo;
        ImageButton ibtnEdit,ibtnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNo = itemView.findViewById(R.id.itemPengetahuan_tvNo);
            tvGejala = itemView.findViewById(R.id.itemPengetahuan_tvGejala);
            tvPenyakit = itemView.findViewById(R.id.itemPengetahuan_tvPenyakit);
            tvBobot = itemView.findViewById(R.id.itemPengetahuan_tvBobot);
            ibtnDelete = itemView.findViewById(R.id.itemPengetahuan_btnDelete);
            ibtnEdit = itemView.findViewById(R.id.itemPengetahuan_btnEdit);
        }
    }
}
