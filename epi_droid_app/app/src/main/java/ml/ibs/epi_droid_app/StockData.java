package ml.ibs.epi_droid_app;

import android.util.Log;

import com.orm.dsl.Ignore;

import java.util.Date;

public class StockData extends BaseData {

    @Ignore
    private static final String TAG = Constants.getLogTag("StockData");

    Date send_date = null;
    int pheno_quantite_recue  = -1;
    int pheno_quantite_utilisee = -1;
    int pheno_quantite_perdue  = -1;
    int pheno_quantite_restante = -1;
    int carba_quantite_recue  = -1;
    int carba_quantite_utilisee = -1;
    int carba_quantite_perdue  = -1;
    int carba_quantite_restante = -1;
    int sodi_quantite_recue  = -1;
    int sodi_quantite_utilisee = -1;
    int sodi_quantite_perdue  = -1;
    int sodi_quantite_restante = -1;
    Boolean isSend = false;
    Boolean isSave = false;

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
        return  Constants.keyStock + Constants.sepaData +
                this.pheno_quantite_recue  + Constants.sepaData +
                this.pheno_quantite_utilisee + Constants.sepaData +
                this.pheno_quantite_perdue  + Constants.sepaData +
                this.pheno_quantite_restante + Constants.sepaData +
                this.carba_quantite_recue  + Constants.sepaData +
                this.carba_quantite_utilisee + Constants.sepaData +
                this.carba_quantite_perdue  + Constants.sepaData +
                this.carba_quantite_restante + Constants.sepaData +
                this.sodi_quantite_recue  + Constants.sepaData +
                this.sodi_quantite_utilisee + Constants.sepaData +
                this.sodi_quantite_perdue  + Constants.sepaData +
                this.sodi_quantite_restante;
    }
}