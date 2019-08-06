package ml.ibs.epi_droid_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Register extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("Register");
    private EditText nomField;
    private EditText prenomField;
    private Button saveSubmitButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");
        setContentView(R.layout.register);

        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI Register");

        nomField = findViewById(R.id.nomPatient);
        prenomField = findViewById(R.id.prenomPatient);
        Button saveButton = findViewById(R.id.saveButton);
        saveSubmitButton = findViewById(R.id.saveSubmitButton);

        // setup invalid inputs checks
        setupInvalidInputChecks();

        RegisterData report = RegisterData.get();
        if (!report.isSend){
            restoreReportData();
        }
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
    }

    protected void storeReportData() {
        Log.d(TAG, "storeData");
        Utils.toast(this, Utils.stringFromField(nomField));
        RegisterData report = RegisterData.get();
        report.updateMetaData();
        report.nom = Utils.stringFromField(nomField);
        report.prenom = Utils.stringFromField(prenomField);
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreData");

        RegisterData report = RegisterData.get();
        setTextOnField(nomField, report.nom);
        setTextOnField(prenomField, report.prenom);

    }

    protected boolean setupInvalidInputChecks() {
        return assertNotEmpty(nomField) && assertNotEmpty(prenomField);
    }

    protected boolean ensureDataCoherence(){return true;}


    protected String buildSMSText() {
        RegisterData report = RegisterData.get();
        return report.buildSMSText();
    }
}