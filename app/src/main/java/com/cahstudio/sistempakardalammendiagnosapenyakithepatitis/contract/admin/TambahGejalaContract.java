package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import java.util.List;

public interface TambahGejalaContract {
    interface View{
        void showLoading();
        void hideLoading();
        void getResponse(Responses responses);
        void getGejala(Gejala gejala);
    }

    interface Presenter{
        void doAddGejala(Gejala gejala);
        void doUpdateGejala(Gejala gejala);
        void doGetGejala(String kodeGejala);
    }
}
