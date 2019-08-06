package ml.ibs.epi_droid_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class Register extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("Register");

    static String E1 = "Bambara";
    static String E2 = "Fulfulde";
    static String E3 = "Songhoi";
    static String E4 = "Tamacheq";
    static String E5 = "Bomu";
    static String E6 = "Mamara";
    static String E7 = "Shennara";
    static String E8 = "Soninke";
    static String E9 = "Bozo";
    static String E10 = "Dogon";
    static String E11 = "Malinke";
    static String E12 = "Khasonke";
    static String ETHNIE = "ethnie";
    static String[] ethnie = new String[] { E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12 };
    private Spinner spinner;

    public static final String[] getETHNIE() {
        return ethnie;
    }


    static String S1 = "marié";
    static String S2 = "vit avec parent(s)";
    static String S3 = "concubinage";
    static String S4 = "vit seul(e)";
    static String S5 = "Autre";
    static String STATUT = "statut";
    static String[] statut = new String[] { S1, S2, S3, S4, S5 };
    private Spinner spinner1;

    public static final String[] getStatut() {
        return statut;
    }


    static String Sc1 = "Primaire1";
    static String Sc2 = "Primaire2";
    static String Sc3 = "Primaire3";
    static String Sc4 = "Primaire4";
    static String Sc5 = "Primaire5";
    static String Sc6="primaire6";
    static String Sc7="secondaire7";
    static String Sc8="secondaire8";
    static String Sc9="secondaire9";
    static String Sc10="Supérieure";
    static String Sc11 = "Aucun";
    static String SCOLARISATION = "scolarisation";
    static String[] scolarisation = new String[] { Sc1, Sc2, Sc3, Sc4, Sc5,Sc6,Sc7,Sc8,Sc9,Sc10,Sc11 };
    private Spinner spinners;

    public static final String[] getScolarisation(){ return scolarisation; }



    static String PF1 = "Agriculteur";
    static String PF2 = "Eleveur";
    static String PF3 = "Pêcheur";
    static String PF4 = "Salarié ou fonctionnaire";
    static String PF5 = "Artisan ou commerçant";
    static String PF6="Etudiant";
    static String PF7="Travail à domicile";
    static String PF8="Profession libérale";
    static String PF9="Autre";
    static String PROFESSION = "profession";
    static String[] profession = new String[] { PF1, PF2, PF3, PF4, PF5,PF6,PF7,PF8,PF9 };
    private Spinner spinnerA;

    public static final String[] getProfession(){ return profession; }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");
        setContentView(R.layout.register);
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI Register");

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> ethAdapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_spinner_item,getETHNIE());
        spinner.setAdapter(ethAdapter);


        spinner1  = findViewById(R.id.spinner1);
        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_spinner_item,getStatut());
        spinner1.setAdapter(sAdapter);

        spinners  = findViewById(R.id.spinners);
        ArrayAdapter<String> sAAdapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_spinner_item,getScolarisation());
        spinners.setAdapter(sAAdapter);


        spinnerA  = findViewById(R.id.spinnerA);
        ArrayAdapter<String> sPAdapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_spinner_item,getProfession());
        spinnerA.setAdapter(sPAdapter);
    }



    protected void setupInvalidInputChecks() {}

    protected boolean ensureDataCoherence(){return true;}

    public String buildSMSText() {return "";}
}