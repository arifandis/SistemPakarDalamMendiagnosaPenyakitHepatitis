package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengguna;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import java.util.List;

public interface DiagnosaContract {
    interface View{
        void showLoading();
        void hideLoading();
        void retrieveGejalaList(List<Gejala> gejalaList);
        void retrievePengetahuanList(List<Pengetahuan> pengetahuanList);
        void retrievePenyakitList(List<Penyakit> penyakitList);
        void getResponse(Responses responses);
    }

    interface Presenter{
        void doRetrieveGejalaList();
        void doRetrievePengetahuanList(String gejalas);
        void doRetrievePenyakitList();
        void doSendDataPengguna(Pengguna pengguna);
    }
}
