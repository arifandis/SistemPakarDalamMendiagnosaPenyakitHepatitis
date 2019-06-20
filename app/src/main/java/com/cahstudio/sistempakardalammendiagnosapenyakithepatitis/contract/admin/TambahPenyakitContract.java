package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

public interface TambahPenyakitContract {
    interface View{
        void showLoading();
        void hideLoading();
        void getResponse(Responses responses);
        void getPenyakit(Penyakit penyakit);
    }

    interface Presenter{
        void doAddPenyakit(Penyakit penyakit);
        void doUpdatePenyakit(Penyakit penyakit);
        void doGetPenyakit(String idPenyakit);
    }
}
