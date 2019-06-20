package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import java.util.List;

public interface PengetahuanContract {
    interface View{
        void showLoading();
        void hideLoading();
        void getResponse(Responses responses);
        void getPengetahuanList(List<Pengetahuan> pengetahuanList);
    }

    interface Presenter{
        void doGetPengetahuanList();
        void deletePengetahuan(int id);

    }
}
