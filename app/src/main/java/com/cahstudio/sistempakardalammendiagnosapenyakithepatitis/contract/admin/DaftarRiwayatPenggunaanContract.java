package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengguna;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import java.util.List;

public interface DaftarRiwayatPenggunaanContract {
    interface View{
        void showLoading();
        void hideLoading();
        void getResponse(Responses responses);
        void getDaftarRiwayat(List<Pengguna> penggunaList);
    }

    interface Presenter{
        void doGetDaftarRiwayat();
        void doDeleteDaftarRiwayat(int id);
    }
}
