package ml.ibs.epi_droid_app;

import android.util.Log;

import com.orm.dsl.Ignore;

import java.util.Date;

public class SuiviData extends BaseData {

    @Ignore
    private static final String TAG = Constants.getLogTag("SuiviData");


    Date visite_date = null;
    String idPatient = "";
    String observance = "";
    int effets_indesirable = -1;
    String lesquelles = "";
    int crise = -1;
    String frenquence = "";
    String intensite = "";
    Boolean isSend = false;
    Boolean isComplet = false;

    public SuiviData() {}


    public static SuiviData get() {
        SuiviData report = getUniqueRecord(SuiviData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new SuiviData();

            Log.d(TAG, "NEW : " + report.toString());
            report.safeSave();

            Log.d(TAG, "safeSave : " + report.toString());
        } else {
            Log.d(TAG, "Record exist in Database.");
        }
        return report;
    }

    protected void resetReportData() {
        SuiviData report = SuiviData.get();
        report.isComplet = true;
        report.safeSave();
    }
    public String buildSMSText() {
        return "sv" + Constants.sepaData +
                Utils.dateTostrDate(this.visite_date) + Constants.sepaData +
                this.idPatient + Constants.sepaData +
                this.observance + Constants.sepaData +
                this.effets_indesirable + Constants.sepaData +
                this.lesquelles + Constants.sepaData +
                this.crise + Constants.sepaData +
                this.frenquence + Constants.sepaData +
                this.intensite;
    }
}