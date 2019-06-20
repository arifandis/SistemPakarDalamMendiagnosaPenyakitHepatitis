package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Global {
    public static String PREF_NAME = "SISTEMPAKAR";
    private static String ID = "id";
    private static String CURRENT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    private static String NEW_DATE_FORMAT1 = "dd-MM-yyyy hh:mm";

    public static ProgressDialog setupProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Harap tunggu sebentar ...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        return progressDialog;
    }

    public static AlertDialog.Builder setupPopupDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);

        return builder;
    }

    public static String dateFormat1(String strDate) throws ParseException {
        DateFormat currentDateFormat = new SimpleDateFormat(CURRENT_DATE_FORMAT,new Locale(ID));
        DateFormat newDateFormat = new SimpleDateFormat(NEW_DATE_FORMAT1,new Locale(ID));
        Date date = currentDateFormat.parse(strDate);

        return newDateFormat.format(date);
    }

}
