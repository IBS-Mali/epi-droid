package ml.ibs.epi_droid_app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("RegisterActivity");


    private Button saveSubmitButton;
    private Button saveButton;
    private Spinner ethSpinner;
    private Spinner statutSpinner;
    private Spinner scolSpinner;
    private Spinner profSpinner;
    private Spinner villageSpinner;
    private EditText PoidsField;
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
//        villageField = findViewById(R.id.village);
//        PoidsField = findViewById(R.id.Poids);
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
//        typeEpilepsieField = findViewById(R.id.typeEpilepsie);
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

        /*RegisterData report = RegisterData.get();
        if (!report.isSend){
            restoreReportData();
        }*/
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
        ethSpinner = findViewById(R.id.ethSpinner);
        ArrayAdapter<String> ethAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, Constants.getETHNIE());
        ethSpinner.setAdapter(ethAdapter);


        statutSpinner  = findViewById(R.id.statutSpinner);
        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, Constants.getStatut());
        statutSpinner.setAdapter(sAdapter);

        scolSpinner = findViewById(R.id.scolSpinner);
        ArrayAdapter<String> sAAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item,Constants.getScolarisation());
        scolSpinner.setAdapter(sAAdapter);


        profSpinner  = findViewById(R.id.profSpinner);
        ArrayAdapter<String> sPAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item,Constants.getProfession());
        profSpinner.setAdapter(sPAdapter);
    }


    protected void storeReportData() {
        Log.d(TAG, "storeData");
        Utils.toast(this, Utils.stringFromField(nomField));
        RegisterData report = RegisterData.get();
        report.updateMetaData();
        report.nom = Utils.stringFromField(nomField);
        report.prenom = Utils.stringFromField(prenomField);
        report.village = Utils.stringFromSpinner(villageSpinner);
        report.Poids = Utils.floatFromField(PoidsField);
        report.registerDate = Utils.getDateFromDatePicker(registerDateField);
        report.nom = Utils.stringFromField(nomField);
        report.prenom = Utils.stringFromField(prenomField);
        report.repondantPatient = repondantIsPatient;
        report.ddn = Utils.getDateFromDatePicker(ddnField);
        report.Sexe = sexePatient;
        report.ethnie = Utils.stringFromSpinner(ethnieSpinner);
        report.etatCivilPatient = Utils.stringFromSpinner(etatCivilPatientSpinner);
        report.niveauScolaire = Utils.stringFromSpinner(niveauScolaireSpinner);
        report.professionPrincipale = Utils.stringFromSpinner(professionPrincipaleSpinner);
//        report.coordonneesGPS = Utils.stringFromField(coordonneesGPSField);
        report.perteConnaissance = perteConnaissance;
//        report.perteUrine = Utils.stringFromField(perteUrineField);
//        report.emissionBave = Utils.stringFromField(emissionBaveField);
//        report.absenceContact = Utils.stringFromField(absenceContactField);
//        report.secoussesAnormauxIncontrolables = Utils.stringFromField(secoussesAnormauxIncontrolablesField);
//        report.apparitionBrutale = Utils.stringFromField(apparitionBrutaleField);
//        report.personneEtaitEpileptique = Utils.stringFromField(personneEtaitEpileptiqueField);
//        report.sujetEpileptique = Utils.stringFromField(sujetEpileptiqueField);
//        report.ageDebutEpilepsie = Utils.stringFromField(ageDebutEpilepsieField);
//        report.crise2DernieresAnnees = Utils.stringFromField(crise2DernieresAnneesField);
//        report.typeEpilepsie = Utils.stringFromField(typeEpilepsieField);
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
}