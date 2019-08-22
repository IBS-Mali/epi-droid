package ml.ibs.epi_droid_app;

import android.util.Log;

import com.orm.dsl.Ignore;

import java.util.Date;

public class RegisterData extends BaseData {

    @Ignore
    private static final String TAG = Constants.getLogTag("RegisterData");

    Date register_date = null;
    String village = "";
    String nom = "";
    String prenom = "";
    Float poids = Float.valueOf(-1);
    int repondant_patient = -1;
    Date ddn = null;
    int sexe = -1;
    String ethnie = "";
    String etat_civil_patient = "";
    String niveau_scolaire = "";
    String profession_principale = "";
    String coordonnees_gps = "";
    String perte_connaissance = "";
    String absence_contact = "";
    String secousses_anormaux_incontrolables = "";
    String apparition_brutale = "";
    String personne_etait_epileptique = "";
    int sujet_epileptique = 0;
    int annee_debut_epilepsie = -1;
    int crise_2_dernieres_annees = -1;
    String crises_generalisee = "";
    String crises_partielles = "";
    String nbCrises_epilepsie = "";
    int nb_crises_epilepsie_d = -1;
    int nb_crises_epilepsie_m = -1;
    int nb_crises_epilepsie_y = -1;
    String prise_medicaments_modernes = "";
    String anti_epilepique_moderne = "";
    String prise_medicaments_traditionnels = "";
    int antecedents_familiaux = -1;
    int antecedents_neurologique = -1;
    String quels_antecedents_neurologiques_familiaux = "";
    int neuropaludisme = -1;
    int meningite = -1;
    int encephalite = -1;
    int accouchement_d = -1;
    int avc = -1;
    Boolean isSend = false;
    Boolean isSave = false;


    public RegisterData() {}


    public static RegisterData get() {
        RegisterData report = getUniqueRecord(RegisterData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new RegisterData();

            Log.d(TAG, "NEW : " + report.toString());
            report.safeSave();

            Log.d(TAG, "safeSave : " + report.toString());
        } else {
            Log.d(TAG, "Record exist in Database.");
        }
        return report;
    }


    public String buildSMSText() {
        return "reg" + Constants.sepaData +
                Utils.dateTostrDate(this.register_date)  + Constants.sepaData +
                this.village  + Constants.sepaData +
                this.nom  + Constants.sepaData +
                this.prenom  + Constants.sepaData +
                this.poids  + Constants.sepaData +
                this.repondant_patient  + Constants.sepaData +
                Utils.dateTostrDate(this.ddn)  + Constants.sepaData +
                Utils.getStringSexe(this.sexe)  + Constants.sepaData +
                this.ethnie  + Constants.sepaData +
                this.etat_civil_patient  + Constants.sepaData +
                this.niveau_scolaire  + Constants.sepaData +
                this.profession_principale  + Constants.sepaData +
                this.coordonnees_gps  + Constants.sepaData +
                this.perte_connaissance  + Constants.sepaData +
                this.absence_contact  + Constants.sepaData +
                this.secousses_anormaux_incontrolables  + Constants.sepaData +
                this.apparition_brutale  + Constants.sepaData +
                this.personne_etait_epileptique  + Constants.sepaData +
                this.sujet_epileptique  + Constants.sepaData +
                this.annee_debut_epilepsie  + Constants.sepaData +
                this.crise_2_dernieres_annees  + Constants.sepaData +
                this.crises_generalisee  + Constants.sepaData +
                this.crises_partielles  + Constants.sepaData +
                this.nbCrises_epilepsie  + Constants.sepaData +
                this.nb_crises_epilepsie_d  + Constants.sepaData +
                this.nb_crises_epilepsie_m  + Constants.sepaData +
                this.nb_crises_epilepsie_y  + Constants.sepaData +
                this.prise_medicaments_modernes  + Constants.sepaData +
                this.prise_medicaments_traditionnels  + Constants.sepaData +
                this.antecedents_familiaux  + Constants.sepaData +
                this.antecedents_neurologique  + Constants.sepaData +
                this.quels_antecedents_neurologiques_familiaux + Constants.sepaData +
                this.neuropaludisme + Constants.sepaData +
                this.meningite + Constants.sepaData +
                this.encephalite + Constants.sepaData +
                this.accouchement_d + Constants.sepaData +
                this.avc;

    }
}