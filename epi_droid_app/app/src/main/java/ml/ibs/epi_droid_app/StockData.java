package ml.ibs.epi_droid_app;

import android.util.Log;

import com.orm.dsl.Ignore;

import java.util.Date;

public class StockData extends BaseData {

    @Ignore
    private static final String TAG = Constants.getLogTag("StockData");

    /*Date register_date = null;
    String village = "";
    String nom = "";
    String prenom = "";
    Float poids = Float.valueOf(-1);
    int repondant_patient = -1;
    Date ddn = null;
    int sexe = -1;*/
    public StockData() {}


    public static StockData get() {
        StockData report = getUniqueRecord(StockData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new StockData();

            Log.d(TAG, "NEW : " + report.toString());
            report.safeSave();

            Log.d(TAG, "safeSave : " + report.toString());
        } else {
            Log.d(TAG, "Record exist in Database.");
        }
        return report;
    }

    public String buildSMSText() {
        return "sv" + Constants.sepaData;
    }
}