package ml.ibs.epi_droid_app;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class RegisterActivity extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("RegisterActivity");


    private Button saveSubmitButton;
    private Button saveButton;
    private Spinner villageSpinner;
    private EditText poidsField;
    private DatePicker registerDateField;
    private EditText nomField;
    private EditText prenomField;
    private RadioGroup repondantPatientRGroup;
    private DatePicker ddnField;
    private RadioGroup sexeRGroup;
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
//    private int repondantIsPatient;
    private Boolean perteConnaissance;
    private Spinner perteConnaissanceSpinner;
    private String villige_code;
    private Spinner crisesGeneraliseeSpinner;
    private Spinner crisesPartiellesSpinner;
    private TextView dateField;
    private List spinnerArray;
    private List spinnerArrayCode;
    private ArrayList<String> ethniesCode;
    private ArrayList<String> ethniesValue;
    private ArrayList<String> etatCivilCode;
    private ArrayList<String> etatCivilValue;
    private ArrayList<String> scolarityCode;
    private ArrayList<String> scolarityValue;
    private ArrayList<String> professionCode;
    private ArrayList<String> professionValue;
    private ArrayList<String> pertesConnaissanceCode;
    private ArrayList<String> pertesConnaissanceValue;
    private ArrayList<String> crisesGeneraliseesCode;
    private ArrayList<String> crisesGeneraliseesValue;
    private ArrayList<String> crisesPartiellesCode;
    private ArrayList<String> crisesPartiellesValue;
    private ArrayList<String> oNNspMapCode;
    private ArrayList<String> oNNspMapValue;
    private ArrayList<String> priseMMCode;
    private ArrayList<String> priseMMValue;
    private ArrayList<String> antiEpiCode;
    private ArrayList<String> antiEpiValue;
    private Spinner oNNspMapSpinner;
    private Spinner secousMapSpinner;
    private Spinner appariMapSpinner;
    private Spinner priseMMSpinner;
    private Spinner etatMapSpinner;
    private Spinner antiEpiSpinner;
    private Spinner priseMTSpinner;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_register);
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
        registerDateField = findViewById(R.id.registerDate);
        repondantPatientRGroup = findViewById(R.id.radioRepondantG);
        ddnField = findViewById(R.id.dDN);
        sexeRGroup = findViewById(R.id.sexeRadioG);

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

        spinnerArray = new ArrayList();
        spinnerArrayCode = new ArrayList();
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
                    spinnerArrayCode.add(code);
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
                villige_code = (String) swt.tag;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


        HashMap<String, String> hmapEth = buildArray("ETHNIE");
        ethniesCode = new ArrayList<String>(hmapEth.keySet());
        ethniesValue = new ArrayList<String>(hmapEth.values());

        ethnieSpinner = findViewById(R.id.ethSpinner);
        ArrayAdapter<String> ethAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, ethniesValue);
        ethnieSpinner.setAdapter(ethAdapter);

        HashMap<String, String>  etatCivilMap = buildArray("ETAT_CIVIL");
        etatCivilCode = new ArrayList<String>(etatCivilMap.keySet());
        etatCivilValue = new ArrayList<String>(etatCivilMap.values());
        etatCivilPatientSpinner  = findViewById(R.id.statutSpinner);
        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, etatCivilValue);
        etatCivilPatientSpinner.setAdapter(sAdapter);

        HashMap<String, String>  scolarityMap = buildArray("N_SCOLAIRE");
        scolarityCode = new ArrayList<String>(scolarityMap.keySet());
        scolarityValue = new ArrayList<String>(scolarityMap.values());
        niveauScolaireSpinner = findViewById(R.id.scolSpinner);
        ArrayAdapter<String> sAAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, scolarityValue);
        niveauScolaireSpinner.setAdapter(sAAdapter);

        HashMap<String, String>  professionMap = buildArray("PROFESSIONS");
        professionCode = new ArrayList<String>(professionMap.keySet());
        professionValue = new ArrayList<String>(professionMap.values());
        professionPrincipaleSpinner  = findViewById(R.id.profSpinner);
        ArrayAdapter<String> sPAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, professionValue);
        professionPrincipaleSpinner.setAdapter(sPAdapter);

        HashMap<String, String>  pertesConnaissanceMap = buildArray("PERTE_CONNAISSANCE");
        pertesConnaissanceCode = new ArrayList<String>(pertesConnaissanceMap.keySet());
        pertesConnaissanceValue = new ArrayList<String>(pertesConnaissanceMap.values());
        perteConnaissanceSpinner  = findViewById(R.id.pertConnaissanceSpinner);
        ArrayAdapter<String> saPAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, pertesConnaissanceValue);
        perteConnaissanceSpinner.setAdapter(saPAdapter);

        HashMap<String, String>  crisesGeneraliseesMap = buildArray("CRISES_G");
        crisesGeneraliseesCode = new ArrayList<String>(crisesGeneraliseesMap.keySet());
        crisesGeneraliseesValue = new ArrayList<String>(crisesGeneraliseesMap.values());
        crisesGeneraliseeSpinner = findViewById(R.id.crisesGeneralisee);
        ArrayAdapter<String> CGPAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, crisesGeneraliseesValue);
        crisesGeneraliseeSpinner.setAdapter(CGPAdapter);


        HashMap<String, String>  crisesPartiellesMap = buildArray("CRISES_P");
        crisesPartiellesCode = new ArrayList<String>(crisesPartiellesMap.keySet());
        crisesPartiellesValue = new ArrayList<String>(crisesPartiellesMap.values());
        crisesPartiellesSpinner = findViewById(R.id.crisesPartielles);
        ArrayAdapter<String> cPPAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, crisesPartiellesValue);
        crisesPartiellesSpinner.setAdapter(cPPAdapter);

        HashMap<String, String>  oNNspMap = buildArray("O_N_NSP");
        oNNspMapCode = new ArrayList<String>(oNNspMap.keySet());
        oNNspMapValue = new ArrayList<String>(oNNspMap.values());

        oNNspMapSpinner = findViewById(R.id.absenceSpinner);
        ArrayAdapter<String> oNNspAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, oNNspMapValue);
        oNNspMapSpinner.setAdapter(oNNspAdapter);

        secousMapSpinner = findViewById(R.id.seousseSpinner);
        ArrayAdapter<String> seousseMapAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, oNNspMapValue);
        secousMapSpinner.setAdapter(seousseMapAdapter);

        appariMapSpinner = findViewById(R.id.apparitionSpinner);
        ArrayAdapter<String> appariAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, oNNspMapValue);
        appariMapSpinner.setAdapter(appariAdapter);

        etatMapSpinner = findViewById(R.id.etatEpiSpinner);
        ArrayAdapter<String> etatAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, oNNspMapValue);
        etatMapSpinner.setAdapter(etatAdapter);

        HashMap<String, String>  priseMMMap = buildArray("PRISE_MEDOC");
        priseMMCode = new ArrayList<String>(priseMMMap.keySet());
        priseMMValue = new ArrayList<String>(priseMMMap.values());
        priseMMSpinner = findViewById(R.id.priseMModerne);
        ArrayAdapter<String> priseMMdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, priseMMValue);
        priseMMSpinner.setAdapter(priseMMdapter);

        HashMap<String, String>  antiEpiMap = buildArray("ANT_EPI_M");
        antiEpiCode = new ArrayList<String>(antiEpiMap.keySet());
        antiEpiValue = new ArrayList<String>(antiEpiMap.values());
        antiEpiSpinner = findViewById(R.id.priseAntiEpiModerne);
        ArrayAdapter<String> antiEpidapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, antiEpiValue);
        antiEpiSpinner.setAdapter(antiEpidapter);

        priseMTSpinner = findViewById(R.id.priseTraditionnel);
        ArrayAdapter<String> priseMTdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, priseMMValue);
        priseMTSpinner.setAdapter(priseMTdapter);
        final RegisterData report = RegisterData.get();
        if (!report.isSend){
            restoreReportData();
        }
    }


    protected void storeReportData() {
        Log.d(TAG, "storeData");

        RegisterData report = RegisterData.get();
        report.updateMetaData();
        report.nom = Utils.stringFromField(nomField);
        report.prenom = Utils.stringFromField(prenomField);
        report.village = villige_code;
        report.poids = Utils.floatFromField(poidsField);
        report.register_date = Utils.getDateFromDatePicker(registerDateField);
        report.repondant_patient = getIntOnRadioGroup(repondantPatientRGroup);
        report.ddn = Utils.getDateFromDatePicker(ddnField);
        report.sexe = getIntOnRadioGroup(sexeRGroup);
        report.ethnie = Utils.stringFromSpinner(ethnieSpinner, ethniesCode);
        report.etat_civil_patient = Utils.stringFromSpinner(etatCivilPatientSpinner, etatCivilCode);
        report.niveau_scolaire = Utils.stringFromSpinner(niveauScolaireSpinner, scolarityCode);
        report.profession_principale = Utils.stringFromSpinner(professionPrincipaleSpinner, professionCode);

        report.safeSave();

//        Utils.toast(RegisterActivity.this, Utils.stringFromSpinner(ethnieSpinner, ethniesCode));
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreData");

        RegisterData report = RegisterData.get();
//      TODO restore spinner village
        setWithIndexOnSpinner(villageSpinner, spinnerArrayCode, report.village);
        setDatetoDatePicker(registerDateField, report.register_date);
        setTextOnField(nomField, report.nom);
        setTextOnField(prenomField, report.prenom);
        setTextOnField(poidsField, report.poids);
        setWithIndexOnSpinner(ethnieSpinner, ethniesCode, report.ethnie);
        setWithIndexOnSpinner(etatCivilPatientSpinner, etatCivilCode, report.etat_civil_patient);
        setWithIndexOnSpinner(niveauScolaireSpinner, scolarityCode, report.niveau_scolaire);
        setWithIndexOnSpinner(professionPrincipaleSpinner, professionCode, report.profession_principale);
//        checkOnRadio(repondantPatientRGroup, report.repondant_patient);
        if (report.repondant_patient==1) {
            repondantPatientRGroup.check(R.id.repondantOUI);
        } else {
            repondantPatientRGroup.check(R.id.repondantNon);
        }
        setDatetoDatePicker(ddnField, report.ddn);
//        checkOnRadio(sexeRGroup, report.sexe);
        if (report.sexe == 1) {
            sexeRGroup.check(R.id.masculin);
        } else {
            sexeRGroup.check(R.id.feminin);
        }

//        if (!report.ethnie.equals("")) {
//            int pos = new ArrayList<String>(Arrays.asList(Constants.getETHNIE())).indexOf(report.ethnie);
//            Log.d(TAG, "ethnie " + pos);
//            ethnieSpinner.setSelection(pos);
//        }
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

        public String getWithTag(Object tag) {
           return this.string;

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

    public HashMap<String, String> buildArray(String jkey) {

        HashMap<String, String> spinnerMap = new HashMap<String, String>();
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONObject arrayKey = jsonObject.getJSONObject(jkey);
            for(int i=0; i<arrayKey.names().length(); i++) {
                try {
                    Object jb = arrayKey.names().get(i);
                    String code = jb.toString();
                    spinnerMap.put(code, arrayKey.get(code).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
//            Log.d(TAG, "SP = " + keyList);
        } catch (JSONException e) {
            Log.d(TAG, e.toString());
        }

        return spinnerMap;

    }

}