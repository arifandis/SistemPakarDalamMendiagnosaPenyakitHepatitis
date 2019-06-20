package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import java.util.List;

public interface PenyakitContract {
    interface View{
        void showLoading();
        void hideLoading();
        void getPenyakitList(List<Penyakit> penyakitList);
        void getResponse(Responses responses);
    }

    interface Presenter{
        void doGetPenyakitList();
        void deletePenyakit(String id);
    }
}
