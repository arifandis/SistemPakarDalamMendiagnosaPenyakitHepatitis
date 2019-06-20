package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import java.util.List;

public interface TambahPengetahuanContract {
    interface View{
        void showLoading();
        void hideLoading();
        void getResponse(Responses responses);
        void getGejalaList(List<Gejala> gejalaList);
        void getPenyakitList(List<Penyakit> penyakitList);
        void getPengetahuan(Pengetahuan pengetahuan);

    }

    interface Presenter{
        void doGetGejalaList();
        void doGetPenyakitList();
        void doAddPengetahuan(Pengetahuan pengetahuan);
        void doUpdatePengetahuan(Pengetahuan pengetahuan);
        void doGetPengetahuanById(int id);
    }
}
