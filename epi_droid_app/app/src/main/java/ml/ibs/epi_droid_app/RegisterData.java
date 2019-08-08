package ml.ibs.epi_droid_app;

import android.util.Log;

import com.orm.dsl.Ignore;

import java.util.Date;

public class RegisterData extends BaseData {

    @Ignore
    private static final String TAG = Constants.getLogTag("RegisterData");

    String village = "";
    Float poids = Float.valueOf(-1);
    Date registerDate = null;
    String nom = "";
    String prenom = "";
    Boolean repondantPatient = false;
    Date ddn = null;
    String Sexe = "";
    String ethnie = "";
    String etatCivilPatient = "";
    String niveauScolaire = "";
    String professionPrincipale = "";
    String coordonneesGPS = "";
    Boolean perteConnaissance = false;
    Boolean perteUrine = false;
    Boolean emissionBave = false;
    String absenceContact = "";
    String secoussesAnormauxIncontrolables = "";
    String apparitionBrutale = "";
    String personneEtaitEpileptique = "";
    Boolean sujetEpileptique = false;
    Date ageDebutEpilepsie = null;
    Boolean crise2DernieresAnnees = false;
    String crisesGeneralisee = "";
    String crisesPartielles = "";
    String nbCrisesEpilepsie = "";
    String priseMedicamentsModerne = "";
    String priseAntiepileptiquesModernes = "";
    String priseMedicamentsTraditiionnel = "";
    String antecedentsFamiliaux = "";
    Boolean autresAntecedentsNeurologiquesFamiliaux = false;
    String quelsAntecedentsNeurologiquesFamiliaux = "";
    Boolean isSend = false;
    Boolean isComplet = false;


    public RegisterData() {}


    public RegisterData(
            String nom, String prenom){
        this.nom = nom;
        this.prenom = prenom;
    }

    public static RegisterData get() {
        RegisterData report = getUniqueRecord(RegisterData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new RegisterData();
            report.safeSave();
        } else {
            Log.d(TAG, "Record exist in Database.");
        }
        return report;
    }

    public RegisterData(
                    String village,
                    Float poids,
                    Date registerDate,
                    String nom,
                    String prenom,
                    Boolean repondantPatient,
                    Date ddn,
                    String Sexe,
                    String ethnie,
                    String etatCivilPatient,
                    String niveauScolaire,
                    String professionPrincipale,
                    String coordonneesGPS,
                    Boolean perteConnaissance,
                    Boolean perteUrine,
                    Boolean emissionBave,
                    String absenceContact,
                    String secoussesAnormauxIncontrolables,
                    String apparitionBrutale,
                    String personneEtaitEpileptique,
                    Boolean sujetEpileptique,
                    Date ageDebutEpilepsie,
                    Boolean crise2DernieresAnnees,
                    String crisesGeneralisee,
                    String crisesPartielles,
                    String nbCrisesEpilepsie,
                    String priseMedicamentsModerne,
                    String priseAntiepileptiquesModernes,
                    String priseMedicamentsTraditiionnel,
                    String antecedentsFamiliaux,
                    Boolean autresAntecedentsNeurologiquesFamiliaux,
                    String quelsAntecedentsNeurologiquesFamiliaux) {

        this.village = village;
        this.poids = poids;
        this.registerDate = registerDate;
        this.nom = nom;
        this.prenom = prenom;
        this.repondantPatient = repondantPatient;
        this.ddn = ddn;
        this.Sexe = Sexe;
        this.ethnie = ethnie;
        this.etatCivilPatient = etatCivilPatient;
        this.niveauScolaire = niveauScolaire;
        this.professionPrincipale = professionPrincipale;
        this.coordonneesGPS = coordonneesGPS;
        this.perteConnaissance = perteConnaissance;
        this.perteUrine = perteUrine;
        this.emissionBave = emissionBave;
        this.absenceContact = absenceContact;
        this.secoussesAnormauxIncontrolables = secoussesAnormauxIncontrolables;
        this.apparitionBrutale = apparitionBrutale;
        this.personneEtaitEpileptique = personneEtaitEpileptique;
        this.sujetEpileptique = sujetEpileptique;
        this.ageDebutEpilepsie = ageDebutEpilepsie;
        this.crise2DernieresAnnees = crise2DernieresAnnees;
        this.crisesPartielles = crisesGeneralisee;
        this.crisesPartielles = crisesPartielles;
        this.nbCrisesEpilepsie = nbCrisesEpilepsie;
        this.priseMedicamentsModerne = priseMedicamentsModerne;
        this.priseAntiepileptiquesModernes = priseAntiepileptiquesModernes;
        this.priseMedicamentsTraditiionnel = priseMedicamentsTraditiionnel;
        this.antecedentsFamiliaux = antecedentsFamiliaux;
        this.autresAntecedentsNeurologiquesFamiliaux = autresAntecedentsNeurologiquesFamiliaux;
        this.quelsAntecedentsNeurologiquesFamiliaux = quelsAntecedentsNeurologiquesFamiliaux;

    }

    public String buildSMSText() {
        return "reg" + Constants.sepaData +
                this.village + Constants.sepaData +
                this.poids + Constants.sepaData +
                this.registerDate + Constants.sepaData +
                this.nom + Constants.sepaData +
                this.prenom + Constants.sepaData +
                this.repondantPatient + Constants.sepaData +
                this.ddn + Constants.sepaData +
                this.Sexe + Constants.sepaData +
                this.ethnie + Constants.sepaData +
                this.etatCivilPatient + Constants.sepaData +
                this.niveauScolaire + Constants.sepaData +
                this.professionPrincipale + Constants.sepaData +
                this.coordonneesGPS + Constants.sepaData +
                this.perteConnaissance + Constants.sepaData +
                this.perteUrine + Constants.sepaData +
                this.emissionBave + Constants.sepaData +
                this.absenceContact + Constants.sepaData +
                this.secoussesAnormauxIncontrolables + Constants.sepaData +
                this.apparitionBrutale + Constants.sepaData +
                this.personneEtaitEpileptique + Constants.sepaData +
                this.sujetEpileptique + Constants.sepaData +
                this.ageDebutEpilepsie + Constants.sepaData +
                this.crise2DernieresAnnees + Constants.sepaData +
                this.crisesGeneralisee + Constants.sepaData +
                this.crisesPartielles + Constants.sepaData +
                this.nbCrisesEpilepsie + Constants.sepaData +
                this.priseMedicamentsModerne + Constants.sepaData +
                this.priseAntiepileptiquesModernes + Constants.sepaData +
                this.priseMedicamentsTraditiionnel + Constants.sepaData +
                this.antecedentsFamiliaux + Constants.sepaData +
                this.autresAntecedentsNeurologiquesFamiliaux + Constants.sepaData +
                this.quelsAntecedentsNeurologiquesFamiliaux + Constants.sepaData;

    }
}