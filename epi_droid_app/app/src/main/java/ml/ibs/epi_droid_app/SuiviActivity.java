package ml.ibs.epi_droid_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class SuiviActivity extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("SuiviActivity");

    private DatePicker dateVisiteField;
    private EditText idPatientField;
    private EditText nbPlaquetteField;
    private EditText poidsField;
    private RadioGroup effetsRadioG;
    private EditText lesEffetsField;
    private RadioGroup criseRadioG;
    private EditText frequenceField;
    private EditText intensiteField;
    private Button saveButton;
    private Button saveSubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.title_activity_suivi);
        setContentView(R.layout.activity_suivi);
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
        setupInvalidInputChecks();
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

        final SuiviData report = SuiviData.get();
        if (report.isSave){
            requestForResumeReport(this, "Suivi patient");
        }

    }

    protected void setupUI() {
        Log.d(TAG, "setupUI SuiviActivity");
        idPatientField = findViewById(R.id.idPatient);
        dateVisiteField = findViewById(R.id.dateVisite);
        nbPlaquetteField = findViewById(R.id.nbPlaquette);
        poidsField = findViewById(R.id.poidsField);
        effetsRadioG = findViewById(R.id.effetsRadioG);
        lesEffetsField = findViewById(R.id.lesEffets);
        criseRadioG = findViewById(R.id.criseRadioG);
        frequenceField= findViewById(R.id.frequence);
        intensiteField= findViewById(R.id.intensite);
        saveButton= findViewById(R.id.saveButton);
        saveSubmitButton= findViewById(R.id.saveSubmitButton);


    }

    protected void storeReportData() {
        Log.d(TAG, "storeData");

        SuiviData report = SuiviData.get();
        report.updateMetaData();
        report.visite_date = Utils.getDateFromDatePicker(dateVisiteField);
        report.idPatient = stringFromField(idPatientField);
        report.observance = stringFromField(nbPlaquetteField);
        report.poids = floatFromField(poidsField);
        report.effets_indesirable = getIntOnRadioGroup(effetsRadioG);
        report.lesquelles = stringFromField(lesEffetsField);
        report.crise = getIntOnRadioGroup(criseRadioG);
        report.frenquence = stringFromField(frequenceField);
        report.intensite = stringFromField(intensiteField);
        report.isSave = true;
        report.safeSave();
        Utils.toast(this, "Sauvegardé avec succès");
    }
    protected void restoreReportData() {
        Log.d(TAG, "restoreData");

        SuiviData report = SuiviData.get();
        setDatetoDatePicker(dateVisiteField, report.visite_date);
        setTextOnField(idPatientField, report.idPatient);
        setTextOnField(nbPlaquetteField, report.observance);
        setTextOnField(poidsField, report.poids);
        if (report.effets_indesirable==1) {
            checkOnRadio(effetsRadioG, R.id.effetOui);
        } else {
            checkOnRadio(effetsRadioG, R.id.effetNon);
        }
        setTextOnField(lesEffetsField, report.lesquelles);
        if (report.effets_indesirable==1) {
            checkOnRadio(criseRadioG, R.id.criseOui);
        } else {
            checkOnRadio(criseRadioG, R.id.criseNon);
        }
        setTextOnField(frequenceField, report.frenquence);
        setTextOnField(intensiteField, report.intensite);
    }

    protected String buildSMSText() {
        SuiviData report = SuiviData.get();
        return report.buildSMSText();
    }

    protected boolean setupInvalidInputChecks() {
        return assertNotEmpty(idPatientField) &&
                assertNotEmpty(nbPlaquetteField);
    }

    protected boolean ensureDataCoherence(){return true;}

    public void hideGoneLY(View view) {
        hideVisible(R.id.frequenceLY, getIntOnRadioGroup(criseRadioG));
    }

    public void hideOrGoneEffets(View view) {
        hideVisible(R.id.effetsLY, getIntOnRadioGroup(effetsRadioG));
    }

}
