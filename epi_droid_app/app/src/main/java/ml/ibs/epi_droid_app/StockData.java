package ml.ibs.epi_droid_app;

import android.util.Log;

import com.orm.dsl.Ignore;

import java.util.Date;

public class StockData extends BaseData {

    @Ignore
    private static final String TAG = Constants.getLogTag("StockData");

    Date visite_date = null;
    String idPatient;
    String observance;
    int effets_indesirable;
    String lesquelles;
    int crise;
    String frenquence;
    String intensite_mensuelle;


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
        return "sv" + Constants.sepaData +
                this.visite_date + Constants.sepaData +
                this.idPatient + Constants.sepaData +
                this.observance + Constants.sepaData +
                this.effets_indesirable + Constants.sepaData +
                this.lesquelles + Constants.sepaData +
                this.crise + Constants.sepaData +
                this.frenquence + Constants.sepaData +
                this.intensite_mensuelle + Constants.sepaData;
    }
}