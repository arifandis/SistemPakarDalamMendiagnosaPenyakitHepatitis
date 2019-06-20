package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengguna;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import java.util.List;

public interface DetailRiwayatContract {
    interface View{
        void showLoading();
        void hideLoading();
        void getPengguna(Pengguna pengguna);
        void getGejalaList(List<Gejala> gejalaList);
        void getResponse(Responses responses);
        void getPengetahuanByGejala(List<Pengetahuan> pengetahuanList);
        void getPenyakitList(List<Penyakit> penyakitList);
    }

    interface Presenter{
        void doGetPenggunaById(int id);
        void doGetGejalaList();
        void doGetPenyakitList();
        void doGetPengetahuanByGejala(String kodeGejalas);
    }
}
