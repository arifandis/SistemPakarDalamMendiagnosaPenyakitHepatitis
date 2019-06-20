package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.contract.admin;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Admin;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

public interface AdminLoginContract {
    interface View{
        void showLoading();
        void hideLoading();
        void getResponse(Responses responses);
    }

    interface Presenter{
        void doLogin(Admin admin);
    }
}
