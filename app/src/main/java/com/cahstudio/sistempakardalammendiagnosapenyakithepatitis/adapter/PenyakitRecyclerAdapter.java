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
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin.TambahPenyakitActivity;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.PenyakitPresenter;

import java.util.List;

public class PenyakitRecyclerAdapter extends RecyclerView.Adapter<PenyakitRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Penyakit> penyakitList;
    private PenyakitPresenter mPresenter;

    public PenyakitRecyclerAdapter(Context context, List<Penyakit> penyakitList, PenyakitPresenter mPresenter) {
        this.context = context;
        this.penyakitList = penyakitList;
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_penyakit,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Penyakit penyakit = penyakitList.get(i);

        viewHolder.tvId.setText(penyakit.getIdPenyakit());
        viewHolder.tvPenyakit.setText(penyakit.getNamaPenyakit());

        viewHolder.ibtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TambahPenyakitActivity.class);
                intent.putExtra("status","edit");
                intent.putExtra("id_penyakit",penyakit.getIdPenyakit());
                ((Activity) context).startActivityForResult(intent,1);
            }
        });

        viewHolder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deletePenyakit(penyakit.getIdPenyakit());
            }
        });
    }

    @Override
    public int getItemCount() {
        return penyakitList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId,tvPenyakit;
        ImageButton ibtnEdit,ibtnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.itemPenyakit_tvKode);
            tvPenyakit = itemView.findViewById(R.id.itemPenyakit_tvNama);
            ibtnDelete = itemView.findViewById(R.id.itemPenyakit_btnDelete);
            ibtnEdit = itemView.findViewById(R.id.itemPenyakit_btnEdit);
        }
    }
}
