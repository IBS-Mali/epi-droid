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

        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI Register");

        nomField = (EditText) findViewById(R.id.nomPatient);
        prenomField = (EditText) findViewById(R.id.prenomPatient);
        saveSubmitButton = findViewById(R.id.saveSubmitButton);


        // setup invalid inputs checks
        setupInvalidInputChecks();

        final CheckedFormActivity activity = this;
        saveSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ensure data is OK
                if (!checkInputsAndCoherence()) { return; }


                checkAndSave();
                // transmit SMS
                requestPasswordAndTransmitSMS(activity, "EPI",
                        Constants.SMS_KEYWORD, buildSMSText());
            }
        });
    }


    private void checkAndSave() {
        /*
            Definition des contrôles.
        */
        storeReportData();
    }

    protected void storeReportData() {
        Log.d(TAG, "storeData");
        Utils.toast(this, Utils.stringFromField(nomField));
        RegisterData register =  new RegisterData(
                Utils.stringFromField(nomField),
                Utils.stringFromField(prenomField)
                );
        register.save();
//        finish();
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreData");
    }

    protected void setupInvalidInputChecks() {}

    protected boolean ensureDataCoherence(){return true;}


    protected String buildSMSText() {
        RegisterData report = RegisterData.get();
        return report.buildSMSText();
    }
}