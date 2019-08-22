package ml.ibs.epi_droid_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class StockActivity extends CheckedFormActivity {


    private final static String TAG = Constants.getLogTag("StockActivity");
    private DatePicker dateStockField;
    private EditText phenoQuantiteRecueField;
    private EditText phenoQuantiteUtiliseeField;
    private EditText phenoQuantitePerdueField;
    private EditText phenoQuantiteRestanteField;
    private EditText carbaQuantiteRecueField;
    private EditText carbaQuantiteUtiliseeField;
    private EditText carbaQuantitePerdueField;
    private EditText carbaQuantiteRestanteField;
    private EditText sodiQuantiteRecueField;
    private EditText sodiQuantiteUtiliseeField;
    private EditText sodiQuantitePerdueField;
    private EditText sodiQuantiteRestanteField;

    private Button saveButton;
    private Button saveSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_stock);
        setContentView(R.layout.activity_stock);
        setupUI();

        setupSMSReceiver();

        saveSubmitButton = findViewById(R.id.saveSubmitButton);
        // setup invalid inputs checks
        setupInvalidInputChecks();
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ensure data is OK
                storeReportData();
            }
        });
        // setup invalid inputs checks
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ensure data is OK
                storeReportData();
            }
        });

        final CheckedFormActivity activity = this;
        saveSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ensure data is OK
                if (!checkInputsAndCoherence()) { return; }
                if (!setupInvalidInputChecks()) { return; }
                storeReportData();
                // transmit SMS
                requestPasswordAndTransmitSMS(activity, "EPI",
                        Constants.SMS_KEYWORD, buildSMSText());
            }
        });

        final StockData report = StockData.get();

        if (report.isSave){
            requestForResumeReport(this, "Gestion des stock");
        }

    }

    protected void setupUI() {
        Log.d(TAG, "setupUI SuiviActivity");
        dateStockField = findViewById(R.id.dateStock);
        phenoQuantiteRecueField = findViewById(R.id.phenoQuantiteRecue);
        phenoQuantiteUtiliseeField = findViewById(R.id.phenoQuantiteUtilisee);
        phenoQuantitePerdueField = findViewById(R.id.phenoQuantitePerdue);
        phenoQuantiteRestanteField = findViewById(R.id.phenoQuantiteRestante);
        carbaQuantiteRecueField = findViewById(R.id.carbaQuantiteRecue);
        carbaQuantiteUtiliseeField = findViewById(R.id.carbaQuantiteUtilisee);
        carbaQuantitePerdueField = findViewById(R.id.carbaQuantitePerdue);
        carbaQuantiteRestanteField = findViewById(R.id.carbaQuantiteRestante);
        sodiQuantiteRecueField = findViewById(R.id.sodiQuantiteRecue);
        sodiQuantiteUtiliseeField = findViewById(R.id.sodiQuantiteUtilisee);
        sodiQuantitePerdueField = findViewById(R.id.sodiQuantitePerdue);
        sodiQuantiteRestanteField = findViewById(R.id.sodiQuantiteRestante);
        saveButton= findViewById(R.id.saveButton);
        saveSubmitButton= findViewById(R.id.saveSubmitButton);

    }

    protected void storeReportData() {
        Log.d(TAG, "storeData");

        StockData report = StockData.get();
        report.updateMetaData();
        report.send_date = Utils.getDateFromDatePicker(dateStockField);

        report.pheno_quantite_recue = integerFromField(phenoQuantiteRecueField);
        report.pheno_quantite_utilisee = integerFromField(phenoQuantiteUtiliseeField);
        report.pheno_quantite_perdue = integerFromField(phenoQuantitePerdueField);
        report.pheno_quantite_restante = integerFromField(phenoQuantiteRestanteField);
        report.carba_quantite_recue = integerFromField(carbaQuantiteRecueField);
        report.carba_quantite_utilisee = integerFromField(carbaQuantiteUtiliseeField);
        report.carba_quantite_perdue = integerFromField(carbaQuantitePerdueField);
        report.carba_quantite_restante = integerFromField(carbaQuantiteRestanteField);
        report.sodi_quantite_recue = integerFromField(sodiQuantiteRecueField);
        report.sodi_quantite_utilisee = integerFromField(sodiQuantiteUtiliseeField);
        report.sodi_quantite_perdue = integerFromField(sodiQuantitePerdueField);
        report.sodi_quantite_restante = integerFromField(sodiQuantiteRestanteField);
        report.isSave = true;
        report.safeSave();
        Utils.toast(this, "Sauvegardé avec succès");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreData");

        StockData report = StockData.get();
        setDatetoDatePicker(dateStockField, report.send_date);
        setTextOnField(phenoQuantiteRecueField, report.pheno_quantite_recue);
        setTextOnField(phenoQuantiteUtiliseeField, report.pheno_quantite_utilisee);
        setTextOnField(phenoQuantitePerdueField, report.pheno_quantite_perdue);
        setTextOnField(phenoQuantiteRestanteField, report.pheno_quantite_restante);
        setTextOnField(carbaQuantiteRecueField, report.carba_quantite_recue);
        setTextOnField(carbaQuantiteUtiliseeField, report.carba_quantite_utilisee);
        setTextOnField(carbaQuantitePerdueField, report.carba_quantite_perdue);
        setTextOnField(carbaQuantiteRestanteField, report.carba_quantite_restante);
        setTextOnField(sodiQuantiteRecueField, report.sodi_quantite_recue);
        setTextOnField(sodiQuantiteUtiliseeField, report.sodi_quantite_utilisee);
        setTextOnField(sodiQuantitePerdueField, report.sodi_quantite_perdue);
        setTextOnField(sodiQuantiteRestanteField, report.sodi_quantite_restante);
    }

    protected String buildSMSText() {
        StockData report = StockData.get();
        return report.buildSMSText();
    }

    protected boolean setupInvalidInputChecks() {
        return  assertNotEmpty(phenoQuantiteRecueField) &&
                assertNotEmpty(phenoQuantiteUtiliseeField) &&
                assertNotEmpty(phenoQuantitePerdueField) &&
                assertNotEmpty(phenoQuantiteRestanteField) &&
                assertNotEmpty(carbaQuantiteRecueField) &&
                assertNotEmpty(carbaQuantiteUtiliseeField) &&
                assertNotEmpty(carbaQuantitePerdueField) &&
                assertNotEmpty(carbaQuantiteRestanteField) &&
                assertNotEmpty(sodiQuantiteRecueField) &&
                assertNotEmpty(sodiQuantiteUtiliseeField) &&
                assertNotEmpty(sodiQuantitePerdueField) &&
                assertNotEmpty(sodiQuantiteRestanteField);
    }

    protected boolean ensureDataCoherence(){return true;}

}
