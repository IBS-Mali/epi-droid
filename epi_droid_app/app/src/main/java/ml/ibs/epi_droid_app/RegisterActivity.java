package ml.ibs.epi_droid_app;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
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
    private EditText poidsField;
    private DatePicker registerDateField;
    private EditText nomField;
    private EditText prenomField;
    private EditText startYearField;
    private RadioGroup repondantPatientRGroup;
    private DatePicker ddnField;
    private RadioGroup sexeRGroup;
    private RadioGroup sujetEpileptiqueG;
    private RadioGroup typeCrisesG;
    private RadioGroup anteNeurologiquesG;
    private Spinner ethnieSpinner;
    private Spinner etatCivilPatientSpinner;
    private Spinner niveauScolaireSpinner;
    private Spinner professionPrincipaleSpinner;
    private Spinner perteConnaissanceSpinner;
    private String villige_code;
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
    private ArrayList<String> antiEpiMCode;
    private ArrayList<String> antiEpiMValue;
    private Spinner villageSpinner;
    private Spinner oNNspMapSpinner;
    private Spinner secousMapSpinner;
    private Spinner appariMapSpinner;
    private Spinner priseMMSpinner;
    private Spinner etatMapSpinner;
    private Spinner antiEpiMSpinner;
    private Spinner priseMTSpinner;
    private Spinner crisesGeneraliseeSpinner;
    private Spinner crisesPartiellesSpinner;
    private EditText nbPerDayField;
    private EditText nbPerMonthField;
    private EditText nbPerYearField;
    private RadioGroup antecedentFlleG;
    private CheckBox neuropaludismeChecBox;
    private CheckBox meningiteChecBox;
    private CheckBox encephaliteChecBox;
    private CheckBox accouchementDChecBox;
    private CheckBox idencephalite;
    private CheckBox avcChecBox;


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
        startYearField = findViewById(R.id.startYear);
        saveSubmitButton = findViewById(R.id.saveSubmitButton);
        // setup invalid inputs checks
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ensure data is OK
                storeReportData();
                setupInvalidInputChecks();
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

        sujetEpileptiqueG = findViewById(R.id.sujetEpileptiqueG);
        typeCrisesG = findViewById(R.id.typeCrisesG);

        HashMap<String, String>  priseMMMap = buildArray("PRISE_MEDOC");
        priseMMCode = new ArrayList<String>(priseMMMap.keySet());
        priseMMValue = new ArrayList<String>(priseMMMap.values());
        priseMMSpinner = findViewById(R.id.priseMModerne);
        ArrayAdapter<String> priseMMdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, priseMMValue);
        priseMMSpinner.setAdapter(priseMMdapter);
        priseMMSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 String selectedItem = parent.getItemAtPosition(position).toString();
                 LinearLayout pMMLY = findViewById(R.id.antiEpiModerneLY);
                 if (selectedItem.equals("NON") || selectedItem.equals("Jamais")){
                     pMMLY.setVisibility(View.GONE);
                 } else {pMMLY.setVisibility(View.VISIBLE);}
             }
             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {}
         });

        HashMap<String, String>  antiEpiMap = buildArray("ANT_EPI_M");
        antiEpiMCode = new ArrayList<String>(antiEpiMap.keySet());
        antiEpiMValue = new ArrayList<String>(antiEpiMap.values());
        antiEpiMSpinner = findViewById(R.id.antiEpiModerne);
        ArrayAdapter<String> antiEpidapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, antiEpiMValue);
        antiEpiMSpinner.setAdapter(antiEpidapter);

        priseMTSpinner = findViewById(R.id.priseAntiEpiTraditionnel);
        ArrayAdapter<String> priseMTdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, priseMMValue);
        priseMTSpinner.setAdapter(priseMTdapter);

        nbPerDayField = findViewById(R.id.nbPerDay);
        nbPerMonthField = findViewById(R.id.nbPerMonth);
        nbPerYearField = findViewById(R.id.nbPerYear);
        antecedentFlleG = findViewById(R.id.antecedentFlleG);
        anteNeurologiquesG = findViewById(R.id.anteNeurologiquesG);
        neuropaludismeChecBox = findViewById(R.id.neuropaludisme);
        meningiteChecBox = findViewById(R.id.meningite);
        encephaliteChecBox = findViewById(R.id.encephalite);
        accouchementDChecBox = findViewById(R.id.accouchementD);
        avcChecBox = findViewById(R.id.avc);
        final RegisterData report = RegisterData.get();
        if (!report.isSend){
            restoreReportData();
        }
    }


    protected void storeReportData() {
        Log.d(TAG, "storeData");

        RegisterData report = RegisterData.get();
        report.updateMetaData();
        report.nom = stringFromField(nomField);
        report.prenom = stringFromField(prenomField);
        report.village = villige_code;
        report.poids = floatFromField(poidsField);
        report.register_date = Utils.getDateFromDatePicker(registerDateField);
        report.repondant_patient = getIntOnRadioGroup(repondantPatientRGroup);
        report.ddn = Utils.getDateFromDatePicker(ddnField);
        report.sexe = getIntOnRadioGroup(sexeRGroup);
        report.ethnie = stringFromSpinner(ethnieSpinner, ethniesCode);
        report.etat_civil_patient = stringFromSpinner(etatCivilPatientSpinner, etatCivilCode);
        report.niveau_scolaire = stringFromSpinner(niveauScolaireSpinner, scolarityCode);
        report.profession_principale = stringFromSpinner(professionPrincipaleSpinner, professionCode);
//        TODO GPS
        report.perte_connaissance = stringFromSpinner(perteConnaissanceSpinner, pertesConnaissanceCode);
        report.absence_contact = stringFromSpinner(oNNspMapSpinner, oNNspMapCode);
        report.secousses_anormaux_incontrolables = stringFromSpinner(secousMapSpinner, oNNspMapCode);
        report.apparition_brutale = stringFromSpinner(appariMapSpinner, oNNspMapCode);
        report.personne_etait_epileptique = stringFromSpinner(etatMapSpinner, oNNspMapCode);
        report.sujet_epileptique = getIntOnRadioGroup(sujetEpileptiqueG);
        report.annee_debut_epilepsie = integerFromField(startYearField);
        report.crise_2_dernieres_annees = getIntOnRadioGroup(typeCrisesG);
        report.crises_generalisee = stringFromSpinner(crisesGeneraliseeSpinner, crisesGeneraliseesCode);
        report.crises_partielles = stringFromSpinner(crisesPartiellesSpinner, crisesPartiellesCode);
        report.nb_crises_epilepsie_d = integerFromField(nbPerDayField);
        report.nb_crises_epilepsie_m = integerFromField(nbPerMonthField);
        report.nb_crises_epilepsie_y = integerFromField(nbPerYearField);
        report.prise_medicaments_modernes = stringFromSpinner(priseMMSpinner, priseMMCode);
        report.anti_epilepique_moderne = stringFromSpinner(antiEpiMSpinner, antiEpiMCode);
        report.prise_medicaments_traditionnels = stringFromSpinner(priseMTSpinner, priseMMCode);
        report.antecedents_familiaux = getIntOnRadioGroup(antecedentFlleG);
        report.antecedents_neurologique = getIntOnRadioGroup(anteNeurologiquesG);
        report.neuropaludisme = getIntCheckBox(neuropaludismeChecBox);
        report.meningite = getIntCheckBox(meningiteChecBox);
        report.encephalite = getIntCheckBox(encephaliteChecBox);
        report.accouchement_d = getIntCheckBox(accouchementDChecBox);
        report.avc = getIntCheckBox(avcChecBox);
        report.safeSave();

        Utils.toast(RegisterActivity.this, "Sauvegardé avec succès");
//        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreData");

        RegisterData report = RegisterData.get();
        setWithIndexOnSpinner(villageSpinner, spinnerArrayCode, report.village);
        setDatetoDatePicker(registerDateField, report.register_date);
        if (report.repondant_patient==1) {
            checkOnRadio(repondantPatientRGroup, R.id.repondantOUI);
        } else {
            checkOnRadio(repondantPatientRGroup, R.id.repondantNon);
        }
        setTextOnField(nomField, report.nom);
        setTextOnField(prenomField, report.prenom);
        setTextOnField(poidsField, report.poids);
        setDatetoDatePicker(ddnField, report.ddn);
        if (report.sexe == 0) {
            checkOnRadio(sexeRGroup, R.id.masculin);
        } else {
            checkOnRadio(sexeRGroup, R.id.feminin);
        }
        setWithIndexOnSpinner(ethnieSpinner, ethniesCode, report.ethnie);
        setWithIndexOnSpinner(etatCivilPatientSpinner, etatCivilCode, report.etat_civil_patient);
        setWithIndexOnSpinner(niveauScolaireSpinner, scolarityCode, report.niveau_scolaire);
        setWithIndexOnSpinner(professionPrincipaleSpinner, professionCode, report.profession_principale);
        setWithIndexOnSpinner(perteConnaissanceSpinner, pertesConnaissanceCode, report.perte_connaissance);
        setWithIndexOnSpinner(oNNspMapSpinner, oNNspMapCode, report.absence_contact);
        setWithIndexOnSpinner(secousMapSpinner, oNNspMapCode, report.secousses_anormaux_incontrolables);
        setWithIndexOnSpinner(appariMapSpinner, oNNspMapCode, report.apparition_brutale);
        setWithIndexOnSpinner(etatMapSpinner, oNNspMapCode, report.personne_etait_epileptique);
        if (report.sujet_epileptique == 0) {
            checkOnRadio(sujetEpileptiqueG, R.id.sujetEpilepNon);
        } else {
            checkOnRadio(sujetEpileptiqueG, R.id.sujetEpilepOui);
        }
        setTextOnField(startYearField, report.annee_debut_epilepsie);
        if (report.crise_2_dernieres_annees == 0) {
            checkOnRadio(typeCrisesG, R.id.typeCrisesNon);
        } else {
            checkOnRadio(typeCrisesG, R.id.typeCrisesOui);
        }
        setWithIndexOnSpinner(crisesGeneraliseeSpinner, crisesGeneraliseesCode, report.crises_generalisee);
        setWithIndexOnSpinner(crisesPartiellesSpinner, crisesPartiellesCode, report.crises_partielles);

        setTextOnField(nbPerDayField, report.nb_crises_epilepsie_d);
        setTextOnField(nbPerMonthField, report.nb_crises_epilepsie_m);
        setTextOnField(nbPerYearField, report.nb_crises_epilepsie_y);

        setWithIndexOnSpinner(priseMMSpinner, priseMMCode, report.prise_medicaments_modernes);
        setWithIndexOnSpinner(antiEpiMSpinner, antiEpiMCode, report.anti_epilepique_moderne);
        setWithIndexOnSpinner(priseMTSpinner, priseMMCode, report.prise_medicaments_traditionnels);
//      TODO ADD GET GPS
        if (report.antecedents_familiaux == 0) {
            checkOnRadio(antecedentFlleG, R.id.antecedentFlleNon);
        } else {
            checkOnRadio(antecedentFlleG, R.id.antecedentFlleOui);
        }
        if (report.antecedents_neurologique == 0) {
            checkOnRadio(anteNeurologiquesG, R.id.anteNeurologiqueNon);
        } else {
            checkOnRadio(anteNeurologiquesG, R.id.anteNeurologiqueOui);
        }
        setCheckOnBox(neuropaludismeChecBox, report.neuropaludisme);
        setCheckOnBox(meningiteChecBox, report.meningite);
        setCheckOnBox(encephaliteChecBox, report.encephalite);
        setCheckOnBox(accouchementDChecBox, report.accouchement_d);
        setCheckOnBox(avcChecBox, report.avc);
    }


    protected boolean setupInvalidInputChecks() {
        return assertNotEmpty(nomField) &&
                assertNotEmpty(prenomField) &&
                assertNotEmpty(poidsField);
    }

    protected boolean ensureDataCoherence(){return true;}

    protected String buildSMSText() {
        RegisterData report = RegisterData.get();
        return report.buildSMSText();
    }

    public void getCoord(){}

    public void hideVisible(View view) {
        hideVisible(R.id.startedYear, getIntOnRadioGroup(sujetEpileptiqueG));
    }

    public void hideVisibleCrisesL(View view) {
        hideVisible(R.id.typeCrisesL, getIntOnRadioGroup(typeCrisesG));
    }

    public void anteNeurologiques(View view) {
        hideVisible(R.id.anteNeurologiquesLY, getIntOnRadioGroup(anteNeurologiquesG));
    }


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