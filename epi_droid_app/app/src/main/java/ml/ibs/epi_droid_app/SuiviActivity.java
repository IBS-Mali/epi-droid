package ml.ibs.epi_droid_app;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class SuiviActivity extends Activity {
    private final static String TAG = Constants.getLogTag("SuiviActivity");

    private EditText idPatientField;
    private EditText dateVisiteField;
    private TextView observationField;
    private EditText nombrePlaquetteField;
    private RadioGroup criseRadio;
    private TextView criseeText;
    private RadioButton buttonOui;
    private RadioButton buttonNon;
    private EditText frequenceField;
    private EditText intensiteField;
    private Button saveButton;
    private Button saveSubmitButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi);




    }

    protected void setupUI() {
        Log.d(TAG, "setupUI SuiviActivity");
        //idPatientField = findViewById(R.id.idPatient);
        //dateVisiteField = findViewById(R.id.dateVisite);
        //observationField= findViewById(R.id.observation);
        //nombrePlaquetteField= findViewById(R.id.nombrePlaquetteField);
        //criseRadio= findViewById(R.id.criseRadio);
        //buttonOuiButton= findViewById(R.id.buttonOui);
        //buttonNonButton= findViewById(R.id.buttonNon);
        //frequenceField= findViewById(R.id.frequence);
        //intensiteField= findViewById(R.id.intensite);
        //saveButton= findViewById(R.id.saveButton);
        //saveSubmitButton= findViewById(R.id.saveSubmitButton);



    }

}
