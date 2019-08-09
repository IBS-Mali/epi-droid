package ml.ibs.epi_droid_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class RegisterActivity extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("RegisterActivity");


    private Button saveSubmitButton;
    private Button saveButton;
    private Spinner villageSpinner;
    private EditText poidsField;
    private DatePicker registerDateField;
    private EditText nomField;
    private EditText prenomField;
    private EditText repondantPatientField;
    private DatePicker ddnField;
    private EditText SexeField;
    private Spinner ethnieSpinner;
    private Spinner etatCivilPatientSpinner;
    private Spinner niveauScolaireSpinner;
    private Spinner professionPrincipaleSpinner;
    private EditText coordonneesGPSField;
    private EditText perteConnaissanceField;
    private EditText perteUrineField;
    private EditText emissionBaveField;
    private EditText absenceContactField;
    private EditText secoussesAnormauxIncontrolablesField;
    private EditText apparitionBrutaleField;
    private EditText personneEtaitEpileptiqueField;
    private EditText sujetEpileptiqueField;
    private EditText ageDebutEpilepsieField;
    private EditText crise2DernieresAnneesField;
    private EditText typeEpilepsieField;
    private EditText nbCrisesEpilepsieField;
    private EditText priseMedicamentsModerneField;
    private EditText priseAntiepileptiquesModernesField;
    private EditText priseMedicamentsTraditiionnelField;
    private EditText antecedentsFamiliauxField;
    private EditText autresAntecedentsNeurologiquesFamiliauxField;
    private EditText quelsAntecedentsNeurologiquesFamiliauxField;
    private String sexePatient;
    private Boolean repondantIsPatient;
    private Boolean perteConnaissance;
    private Spinner nbPerteConnaissanceSpinner;
    private Spinner crisesGeneraliseeField;
    private Spinner crisesPartiellesField;
    private String villige_code;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("RegisterActivity");
        setContentView(R.layout.activity_register);
        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI RegisterActivity");
        nomField = findViewById(R.id.nomPatient);
        prenomField = findViewById(R.id.prenomPatient);
        saveButton = findViewById(R.id.saveButton);
        poidsField = findViewById(R.id.poidsField);
//        registerDateField = findViewById(R.id.registerDate);
//        repondantPatientField = findViewById(R.id.repondantPatient);
//        ddnField = findViewById(R.id.ddn);
//        SexeField = findViewById(R.id.Sexe);
//        ethnieField = findViewById(R.id.ethnie);
//        etatCivilPatientField = findViewById(R.id.etatCivilPatient);
//        niveauScolaireField = findViewById(R.id.niveauScolaire);
//        professionPrincipaleField = findViewById(R.id.professionPrincipale);
//        coordonneesGPSField = findViewById(R.id.coordonneesGPS);
//        perteConnaissanceField = findViewById(R.id.perteConnaissance);
//        perteUrineField = findViewById(R.id.perteUrine);
//        emissionBaveField = findViewById(R.id.emissionBave);
//        absenceContactField = findViewById(R.id.absenceContact);
//        secoussesAnormauxIncontrolablesField = findViewById(R.id.secoussesAnormauxIncontrolables);
//        apparitionBrutaleField = findViewById(R.id.apparitionBrutale);
//        personneEtaitEpileptiqueField = findViewById(R.id.personneEtaitEpileptique);
//        sujetEpileptiqueField = findViewById(R.id.sujetEpileptique);
//        ageDebutEpilepsieField = findViewById(R.id.ageDebutEpilepsie);
//        crise2DernieresAnneesField = findViewById(R.id.crise2DernieresAnnees);
//        crisesGeneraliseeField = findViewById(R.id.crisesGeneralisee);
//        crisesPartiellesField = findViewById(R.id.crisesPartielles);
//        nbCrisesEpilepsieField = findViewById(R.id.nbCrisesEpilepsie);
//        priseMedicamentsModerneField = findViewById(R.id.priseMedicamentsModerne);
//        priseAntiepileptiquesModernesField = findViewById(R.id.priseAntiepileptiquesModernes);
//        priseMedicamentsTraditiionnelField = findViewById(R.id.priseMedicamentsTraditiionnel);
//        antecedentsFamiliauxField = findViewById(R.id.antecedentsFamiliaux);
//        autresAntecedentsNeurologiquesFamiliauxField = findViewById(R.id.autresAntecedentsNeurologiquesFamiliaux);
//        quelsAntecedentsNeurologiquesFamiliauxField = findViewById(R.id.quelsAntecedentsNeurologiquesFamiliau);

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
        RadioGroup sexeRadioG = findViewById(R.id.sexeRadioG);
        sexeRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.masculin:
                    sexePatient  = "M";
                    break;
                case R.id.feminin:
                    sexePatient  = "F";
                    break;
            }
            Utils.toast(getBaseContext(), sexePatient);
            }
        });
        RadioGroup repondantRadioG = findViewById(R.id.radioRepondantG);
        repondantRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.repondantNon:
                    repondantIsPatient = false;
                    break;
                case R.id.repondantOUI:
                    repondantIsPatient  = true;
                    break;
            }
            }
        });

        List spinnerArray = new ArrayList();
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray entities = jsonObject.getJSONArray("entities");
            
            for(int i=0; i<entities.length(); i++) {
                JSONObject jb =(JSONObject) entities.get(i);
                String name = jb.getString("name");
                String code = jb.getString("code");
                String type = jb.getString("type");
                if (type.equals("vfq")){
                    spinnerArray.add(new StringWithTag(name, code));
                }
            }
        } catch (JSONException e) {
            Log.d(TAG, e.toString());
        }

        villageSpinner = findViewById(R.id.villageSpinner);

        ArrayAdapter<StringWithTag> spinnerAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_item, spinnerArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        villageSpinner.setAdapter(spinnerAdapter);
        villageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StringWithTag swt = (StringWithTag) parent.getItemAtPosition(position);
                String villige_code = (String) swt.tag;
                Log.d(TAG, villige_code);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ethnieSpinner = findViewById(R.id.ethSpinner);
        ArrayAdapter<String> ethAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, Constants.getETHNIE());
        ethnieSpinner.setAdapter(ethAdapter);

        etatCivilPatientSpinner  = findViewById(R.id.statutSpinner);
        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, Constants.getStatut());
        etatCivilPatientSpinner.setAdapter(sAdapter);

        niveauScolaireSpinner = findViewById(R.id.scolSpinner);
        ArrayAdapter<String> sAAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item,Constants.getScolarisation());
        niveauScolaireSpinner.setAdapter(sAAdapter);

        professionPrincipaleSpinner  = findViewById(R.id.profSpinner);
        ArrayAdapter<String> sPAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item,Constants.getProfession());
        professionPrincipaleSpinner.setAdapter(sPAdapter);

        nbPerteConnaissanceSpinner  = findViewById(R.id.santSpinner);
        ArrayAdapter<String> saPAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item,Constants.getSANTE());
        nbPerteConnaissanceSpinner.setAdapter(saPAdapter);
    }


    protected void storeReportData() {
        Log.d(TAG, "storeData");
        Utils.toast(this, Utils.stringFromField(nomField));
        RegisterData report = RegisterData.get();
        report.updateMetaData();
        report.nom = Utils.stringFromField(nomField);
        report.prenom = Utils.stringFromField(prenomField);
        report.village = villige_code;
        report.poids = Utils.floatFromField(poidsField);
        report.registerDate = Utils.getDateFromDatePicker(registerDateField);
//        report.nom = Utils.stringFromField(nomField);
//        report.prenom = Utils.stringFromField(prenomField);
//        report.repondantPatient = repondantIsPatient;
//        report.ddn = Utils.getDateFromDatePicker(ddnField);
//        report.Sexe = sexePatient;
//        report.ethnie = Utils.stringFromSpinner(ethnieSpinner);
//        report.etatCivilPatient = Utils.stringFromSpinner(etatCivilPatientSpinner);
//        report.niveauScolaire = Utils.stringFromSpinner(niveauScolaireSpinner);
//        report.professionPrincipale = Utils.stringFromSpinner(professionPrincipaleSpinner);
//        report.coordonneesGPS = Utils.stringFromField(coordonneesGPSField);
//        report.perteConnaissance = perteConnaissance;
//        report.perteUrine = Utils.stringFromField(perteUrineField);
//        report.emissionBave = Utils.stringFromField(emissionBaveField);
//        report.absenceContact = Utils.stringFromField(absenceContactField);
//        report.secoussesAnormauxIncontrolables = Utils.stringFromField(secoussesAnormauxIncontrolablesField);
//        report.apparitionBrutale = Utils.stringFromField(apparitionBrutaleField);
//        report.personneEtaitEpileptique = Utils.stringFromField(personneEtaitEpileptiqueField);
//        report.sujetEpileptique = Utils.stringFromField(sujetEpileptiqueField);
//        report.ageDebutEpilepsie = Utils.stringFromField(ageDebutEpilepsieField);
//        report.crise2DernieresAnnees = Utils.stringFromField(crise2DernieresAnneesField);
//          report.crisesGeneralisee = Utils.stringFromSpinner(crisesGeneraliseeField);
//          report.crisesPartielles = Utils.stringFromSpinner(crisesPartiellesField);
//        report.nbCrisesEpilepsie = Utils.stringFromField(nbCrisesEpilepsieField);
//        report.priseMedicamentsModerne = Utils.stringFromField(priseMedicamentsModerneField);
//        report.priseAntiepileptiquesModernes = Utils.stringFromField(priseAntiepileptiquesModernesField);
//        report.priseMedicamentsTraditiionnel = Utils.stringFromField(priseMedicamentsTraditiionnelField);
//        report.antecedentsFamiliaux = Utils.stringFromField(antecedentsFamiliauxField);
//        report.autresAntecedentsNeurologiquesFamiliaux = Utils.stringFromField(autresAntecedentsNeurologiquesFamiliauxField);
//        report.quelsAntecedentsNeurologiquesFamiliaux = Utils.stringFromField(quelsAntecedentsNeurologiquesFamiliauxField);
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

    public void getCoord(){}

    private static class StringWithTag {
        public String string;
        public Object tag;

        public StringWithTag(String string, Object tag) {
            this.string = string;
            this.tag = tag;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("data.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

}