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
    Boolean sujet_epileptique = false;
    Date age_debut_epilepsie = null;
    Boolean crise_2_dernieres_annees = false;
    String crises_generalisee = "";
    String crises_partielles = "";
    String nbCrises_epilepsie = "";
    String nb_crises_epilepsie_d = "";
    String nb_crises_epilepsie_m = "";
    String nb_crises_epilepsie_y = "";
    String prise_medicaments_moderne = "";
    String prise_antiepileptiques_modernes = "";
    String prise_medicaments_traditionnel = "";
    String antecedents_familiaux = "";
    Boolean autres_antecedents_nfami = false;
    String quels_antecedents_neurologiques_familiaux = "";
    Boolean isSend = false;
    Boolean isComplet = false;


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

//    public RegisterData(
//            Date register_date,
//            String village,
//            String nom,
//            String prenom,
//            Float poids,
//            Boolean repondant_patient,
//            Date ddn,
//            String sexe,
//            String ethnie,
//            String etat_civil_patient,
//            String niveau_scolaire,
//            String profession_principale,
//            String coordonnees_gps,
//            Boolean perte_connaissance,
//            Boolean perte_urine,
//            Boolean emission_bave,
//            String absence_contact,
//            String secousses_anormaux_incontrolables,
//            String apparition_brutale,
//            String personne_etait_epileptique,
//            Boolean sujet_epileptique,
//            Date age_debut_epilepsie,
//            Boolean crise_2_dernieres_annees,
//            String crises_generalisee,
//            String crises_partielles,
//            String nbCrises_epilepsie,
//            String nb_crises_epilepsie_d,
//            String nb_crises_epilepsie_m,
//            String nb_crises_epilepsie_y,
//            String prise_medicaments_moderne,
//            String prise_antiepileptiques_modernes,
//            String prise_medicaments_traditionnel,
//            String antecedents_familiaux,
//            Boolean autres_antecedents_nfami,
//            String quels_antecedents_neurologiques_familiaux,
//            Boolean isSend,
//            Boolean isComplet
//            ) {
//        this.register_date = register_date;
//        this.village = village;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.poids = poids;
//        this.repondant_patient = repondant_patient;
//        this.ddn = ddn;
//        this.Sexe = Sexe;
//        this.ethnie = ethnie;
//        this.etat_civil_patient = etat_civil_patient;
//        this.niveau_scolaire = niveau_scolaire;
//        this.profession_principale = profession_principale;
//        this.coordonnees_gps = coordonnees_gps;
//        this.perte_connaissance = perte_connaissance;
//        this.perte_urine = perte_urine;
//        this.emission_bave = emission_bave;
//        this.absence_contact = absence_contact;
//        this.secousses_anormaux_incontrolables = secousses_anormaux_incontrolables;
//        this.apparition_brutale = apparition_brutale;
//        this.personne_etait_epileptique = personne_etait_epileptique;
//        this.sujet_epileptique = sujet_epileptique;
//        this.age_debut_epilepsie = age_debut_epilepsie;
//        this.crise_2_dernieres_annees = crise_2_dernieres_annees;
//        this.crises_generalisee = crises_generalisee;
//        this.crises_partielles = crises_partielles;
//        this.nbCrises_epilepsie = nbCrises_epilepsie;
//        this.nb_crises_epilepsie_d = nb_crises_epilepsie_d;
//        this.nb_crises_epilepsie_m = nb_crises_epilepsie_m;
//        this.nb_crises_epilepsie_y = nb_crises_epilepsie_y;
//        this.prise_medicaments_moderne = prise_medicaments_moderne;
//        this.prise_antiepileptiques_modernes = prise_antiepileptiques_modernes;
//        this.prise_medicaments_traditionnel = prise_medicaments_traditionnel;
//        this.antecedents_familiaux = antecedents_familiaux;
//        this.autres_antecedents_nfami = autres_antecedents_nfami;
//        this.quels_antecedents_neurologiques_familiaux = quels_antecedents_neurologiques_familiaux;
//        this.isSend = isSend;
//        this.isComplet = isComplet;
//    }

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
                this.age_debut_epilepsie  + Constants.sepaData +
                this.crise_2_dernieres_annees  + Constants.sepaData +
                this.crises_generalisee  + Constants.sepaData +
                this.crises_partielles  + Constants.sepaData +
                this.nbCrises_epilepsie  + Constants.sepaData +
                this.nb_crises_epilepsie_d  + Constants.sepaData +
                this.nb_crises_epilepsie_m  + Constants.sepaData +
                this.nb_crises_epilepsie_y  + Constants.sepaData +
                this.prise_medicaments_moderne  + Constants.sepaData +
                this.prise_antiepileptiques_modernes  + Constants.sepaData +
                this.prise_medicaments_traditionnel  + Constants.sepaData +
                this.antecedents_familiaux  + Constants.sepaData +
                this.autres_antecedents_nfami  + Constants.sepaData +
                this.quels_antecedents_neurologiques_familiaux  + Constants.sepaData +
                this.isSend  + Constants.sepaData +
                this.isComplet  + Constants.sepaData;

    }
}