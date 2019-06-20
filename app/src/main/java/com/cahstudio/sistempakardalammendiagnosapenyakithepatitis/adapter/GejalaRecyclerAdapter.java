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
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin.TambahGejalaActivity;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.admin.GejalaPresenter;

import java.util.List;

public class GejalaRecyclerAdapter extends RecyclerView.Adapter<GejalaRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Gejala> gejalaList;
    private GejalaPresenter mPresenter;

    public GejalaRecyclerAdapter(Context context, List<Gejala> gejalaList, GejalaPresenter mPresenter) {
        this.context = context;
        this.gejalaList = gejalaList;
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public GejalaRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_gejala,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GejalaRecyclerAdapter.ViewHolder viewHolder, int i) {
        final Gejala gejala = gejalaList.get(i);

        viewHolder.tvKode.setText(gejala.getKodeGejala());
        viewHolder.tvGejala.setText(gejala.getGejala());
        viewHolder.tvBobot.setText("Bobot: "+gejala.getBobot());

        viewHolder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteGejala(gejala.getKodeGejala());
            }
        });

        viewHolder.ibtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TambahGejalaActivity.class);
                intent.putExtra("status","edit");
                intent.putExtra("kode_gejala",gejala.getKodeGejala());
                ((Activity) context).startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gejalaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKode,tvGejala,tvBobot;
        private ImageButton ibtnDelete,ibtnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ibtnEdit = itemView.findViewById(R.id.itemGejala_btnEdit);
            ibtnDelete = itemView.findViewById(R.id.itemGejala_btnDelete);
            tvKode = itemView.findViewById(R.id.itemGejala_tvKode);
            tvGejala = itemView.findViewById(R.id.itemGejala_tvNama);
            tvBobot = itemView.findViewById(R.id.itemGejala_tvBobot);
        }
    }
}
