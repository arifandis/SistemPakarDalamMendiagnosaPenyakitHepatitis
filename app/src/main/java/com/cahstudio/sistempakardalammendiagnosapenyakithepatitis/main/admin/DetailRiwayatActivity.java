package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.main.admin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.R;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiInterface;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api.ApiService;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin.DetailRiwayatContract;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper.Global;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengguna;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.presenter.DetailRiwayatPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DetailRiwayatActivity extends AppCompatActivity implements DetailRiwayatContract.View {
    private TextView tvNama,tvUsia,tvJenisKelamin,tvPenyakitCF,tvKemungkinanCF,tvRumusCF,tvPenyakitDS,tvKemungkinanDS,tvRumusDS;
    private TableLayout tlGejala;

    private ProgressDialog progressDialog;
    private ApiInterface mApi;
    private DetailRiwayatPresenter mPresenter;
    private Pengguna mPengguna;

    private List<Penyakit> mPenyakitList = new ArrayList<>();
    private List<Gejala> mGejalaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Pengguna");

        tvNama = findViewById(R.id.detail_tvNama);
        tvUsia = findViewById(R.id.detail_tvUsia);
        tvJenisKelamin = findViewById(R.id.detail_tvKelamin);
        tlGejala = findViewById(R.id.layout_headerGejala);
        tvPenyakitCF = findViewById(R.id.detail_tvPenyakitCF);
        tvKemungkinanCF = findViewById(R.id.detail_tvKemungkinanCF);
        tvRumusCF = findViewById(R.id.detail_tvRumusCF);
        tvPenyakitDS = findViewById(R.id.detail_tvPenyakitDS);
        tvKemungkinanDS = findViewById(R.id.detail_tvKemungkinanDS);
        tvRumusDS = findViewById(R.id.detail_tvRumusDS);

        mApi = ApiService.getService().create(ApiInterface.class);
        mPresenter = new DetailRiwayatPresenter(this,mApi);
        progressDialog = Global.setupProgressDialog(this);

        if (getIntent() != null){
            int id = getIntent().getIntExtra("id",0);
            mPresenter.doGetPenggunaById(id);
        }
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void getPengguna(Pengguna pengguna) {
        mPengguna = pengguna;
        tvNama.setText(pengguna.getNama());
        tvUsia.setText(pengguna.getUsia());
        tvJenisKelamin.setText(pengguna.getJenisKelamin());
    }

    @Override
    public void getGejalaList(List<Gejala> gejalaList) {
        TableRow trGejala;
        String[] kodeGejalas = mPengguna.getGejala().split(",");
        for (int i=0; i<gejalaList.size();i++){
            for (String kodeGejala: kodeGejalas){
                if (kodeGejala.equals(gejalaList.get(i).getKodeGejala())){
                    mGejalaList.add(gejalaList.get(i));
                    trGejala = (TableRow) getLayoutInflater().inflate(R.layout.row_layout_gejala,null);
                    TextView tvNo = trGejala.findViewById(R.id.row_tvNo);
                    TextView tvGejala = trGejala.findViewById(R.id.row_tvNamaGejala);
                    tvNo.setText(String.valueOf(i+1));
                    tvGejala.setText(gejalaList.get(i).getGejala());
                    tlGejala.addView(trGejala);
                }
            }
        }

        mPresenter.doGetPengetahuanByGejala(mPengguna.getGejala());
    }

    @Override
    public void getResponse(Responses responses) {
        Toast.makeText(this, responses.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getPengetahuanByGejala(List<Pengetahuan> pengetahuanList) {
        for (int i = 0; i < mPenyakitList.size();i++){
            float count = 0;
            for (Pengetahuan pengetahuan: pengetahuanList){
                if (pengetahuan.getIdPenyakit().equals(mPenyakitList.get(i).getIdPenyakit())){
                    count+=Float.valueOf(pengetahuan.getBobot());
                    mPenyakitList.get(i).setTotalBobot(count);
                }
            }
        }

        //CF
        float bobotOld=0;
        String rumusCF = "";
        float valueCF = 0;
        for (int y=0;y<mPenyakitList.size();y++){
            int x = 1;
            String penyakit = mPenyakitList.get(y).getNamaPenyakit();
            rumusCF = rumusCF + "----- "+ penyakit.toUpperCase() +" -----\n";
            for (int i=0;i<pengetahuanList.size();i++){
                if (pengetahuanList.get(i).getIdPenyakit().equals(mPenyakitList.get(y).getIdPenyakit())){
                    if (x == 1){
                        bobotOld = Float.valueOf(pengetahuanList.get(i).getBobot());
                        x++;
                    }else if ( x == 2){
                        rumusCF =  rumusCF + "CFcombine CF[H,E]1,2 = "+bobotOld+" + "+pengetahuanList.get(i).getBobot()+" * (1-"+bobotOld+")\n";
                        bobotOld = countWithMethodCF(bobotOld,Float.valueOf(pengetahuanList.get(i).getBobot()));
                        rumusCF =  rumusCF + "CFcombine CF[H,E]1,2 = "+bobotOld+"\n\n";
                        x++;
                    }else{
                        rumusCF =  rumusCF + "CFcombine CF[H,E]old,"+x+" = "+bobotOld+" + "+pengetahuanList.get(i).getBobot()+" * (1-"+bobotOld+")\n";
                        bobotOld = countWithMethodCF(bobotOld,Float.valueOf(pengetahuanList.get(i).getBobot()));
                        rumusCF =  rumusCF + "CFcombine CF[H,E]old,"+x+" = "+bobotOld+"\n\n";
                        x++;
                    }
                }
            }
            valueCF = countFinalValueCF(bobotOld);
            mPenyakitList.get(y).setValueCF(valueCF);
            rumusCF = rumusCF + "CF[H,E]old * 100% = " + bobotOld + " * 100% = " + valueCF + "\n\n";
            valueCF = 0;
            bobotOld = 0;
        }

        //DS
        float m=0;
        float belief=0;
        float m2=0;
        float belief2=0;
        float valueDS = 0;
        String rumusDS = "";
        for (int l=0;l<mPenyakitList.size();l++){
            int j = 1;
            int k = 1;
            String penyakit = mPenyakitList.get(l).getNamaPenyakit();
            rumusDS = rumusDS + "----- "+ penyakit.toUpperCase() +" -----\n";
            for (int i=0;i<pengetahuanList.size();i++){
                if (pengetahuanList.get(i).getIdPenyakit().equals(mPenyakitList.get(l).getIdPenyakit())){
                    if (k == 1){
                        m = Float.valueOf(pengetahuanList.get(i).getBobot());
                        belief = countBelief(m);
                        rumusDS = rumusDS + "m"+(j)+" (G"+(k)+") = "+m+"\n";
                        rumusDS = rumusDS + "m"+(j)+"{θ}: 1-"+m+" = "+belief+"\n\n";
                        j++;
                    }else{
                        Log.d("mkedua",pengetahuanList.get(i).getBobot());
                        m2 = Float.parseFloat(pengetahuanList.get(i).getBobot());
                        belief2 = countBelief(m2);
                        rumusDS = rumusDS + "m"+(j)+" (G"+(k)+") = "+m2+"\n";
                        rumusDS = rumusDS + "m"+(j)+"{θ} = 1-"+m2+" = "+belief2+"\n\n";
                        j++;

                        rumusDS = rumusDS + "m"+(j)+" = ("+m+" * "+m2+") + ("+m+" * "+belief2+")/1-0\n";
                        m = countMCombine(m,m2,belief2);
                        rumusDS = rumusDS + "m"+(j)+" = "+m+"\n";
                        rumusDS = rumusDS + "m"+(j)+"{θ} = "+belief+" * "+belief2+"/1-0\n";
                        belief = countBeliefMCombine(belief,belief2);
                        rumusDS = rumusDS + "m"+(j)+"{θ} = "+belief+"\n\n";
                        j++;
                    }
                    k++;
                }
            }
            valueDS = countFinalValueDS(m,belief);
            mPenyakitList.get(l).setValueDS(valueDS);
            rumusDS = rumusDS + "Nilai Akhir = ("+m+"+"+belief+") * 100% = "+valueDS+"\n\n";
            valueDS = 0;
            m = 0;
            belief = 0;
        }

        Collections.sort(mPenyakitList, new Comparator<Penyakit>() {
            @Override
            public int compare(Penyakit o1, Penyakit o2) {
                return Float.compare(o2.getValueCF(), o1.getValueCF());
            }
        });

        tvRumusCF.setText(rumusCF);
        tvPenyakitCF.setText(mPenyakitList.get(0).getNamaPenyakit());
        tvKemungkinanCF.setText(mPenyakitList.get(0).getValueCF()+"%");

        Collections.sort(mPenyakitList, new Comparator<Penyakit>() {
            @Override
            public int compare(Penyakit o1, Penyakit o2) {
                return Float.compare(o2.getValueDS(), o1.getValueCF());
            }
        });

        tvRumusDS.setText(rumusDS);
        tvPenyakitDS.setText(mPenyakitList.get(0).getNamaPenyakit());
        tvKemungkinanDS.setText(mPenyakitList.get(0).getValueDS()+"%");
    }

    @Override
    public void getPenyakitList(List<Penyakit> penyakitList) {
        mPenyakitList.addAll(penyakitList);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    protected float countWithMethodCF(float bobotOld, float bobotNew){
        return bobotOld + (bobotNew*(1-bobotOld));
    }

    protected float countBelief(float bobot){
        return 1-bobot;
    }

    protected float countMCombine(float bobot1, float bobot2, float belief2){
        return ((bobot1*bobot2)+(bobot1*belief2))/(1-0);
    }

    protected float countBeliefMCombine(float belief1, float belief2){
        return (belief1*belief2)/(1-0);
    }

    protected float countFinalValueDS(float bobot, float belief){
        return (bobot+belief) * 100;
    }

    protected float countFinalValueCF(float bobot){
        return bobot * 100;
    }
}
