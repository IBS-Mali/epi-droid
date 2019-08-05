package ml.ibs.epi_droid_app;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;

public class RegisterData extends BaseData {

    @Ignore
    private static final String TAG = Constants.getLogTag("RegisterData");

//    private String village;
//    private Float Poids;
//    private Date registerDate;
    private String nom;
    private String prenom;
//    private Boolean repondantPatient;
//    private Date ddn;
//    private String Sexe;
//    private String ethnie;
//    private String etatCivilPatient;
//    private String niveauScolaire;
//    private String professionPrincipale;
//    private String coordonneesGPS;
//    private Boolean perteConnaissance;
//    private Boolean perteUrine;
//    private Boolean emissionBave;
//    private String absenceContact;
//    private String secoussesAnormauxIncontrolables;
//    private String apparitionBrutale;
//    private String personneEtaitEpileptique;
//    private Boolean sujetEpileptique;
//    private Date ageDebutEpilepsie;
//    private Boolean crise2DernieresAnnees;
//    private String typeEpilepsie;
//    private String nbCrisesEpilepsie;
//    private String priseMedicamentsModerne;
//    private String priseAntiepileptiquesModernes;
//    private String priseMedicamentsTraditiionnel;
//    private String antecedentsFamiliaux;
//    private Boolean autresAntecedentsNeurologiquesFamiliaux;
//    private String quelsAntecedentsNeurologiquesFamiliaux;

//    private Date modifiedOn;

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

    //    public RegisterData(
//                        String village,
//                        Float Poids,
//                        Date registerDate,
//                        String nom,
//                        String prenom,
//                        Boolean repondantPatient,
//                        Date ddn,
//                        String Sexe,
//                        String ethnie,
//                        String etatCivilPatient,
//                        String niveauScolaire,
//                        String professionPrincipale,
//                        String coordonneesGPS,
//                        Boolean perteConnaissance,
//                        Boolean perteUrine,
//                        Boolean emissionBave,
//                        String absenceContact,
//                        String secoussesAnormauxIncontrolables,
//                        String apparitionBrutale,
//                        String personneEtaitEpileptique,
//                        Boolean sujetEpileptique,
//                        Date ageDebutEpilepsie,
//                        Boolean crise2DernieresAnnees,
//                        String typeEpilepsie,
//                        String nbCrisesEpilepsie,
//                        String priseMedicamentsModerne,
//                        String priseAntiepileptiquesModernes,
//                        String priseMedicamentsTraditiionnel,
//                        String antecedentsFamiliaux,
//                        Boolean autresAntecedentsNeurologiquesFamiliaux,
//                        String quelsAntecedentsNeurologiquesFamiliaux, Date modifiedOn) {
//
//        this.village = village;
//        this.Poids = Poids;
//        this.registerDate = registerDate;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.repondantPatient = repondantPatient;
//        this.ddn = ddn;
//        this.Sexe = Sexe;
//        this.ethnie = ethnie;
//        this.etatCivilPatient = etatCivilPatient;
//        this.niveauScolaire = niveauScolaire;
//        this.professionPrincipale = professionPrincipale;
//        this.coordonneesGPS = coordonneesGPS;
//        this.perteConnaissance = perteConnaissance;
//        this.perteUrine = perteUrine;
//        this.emissionBave = emissionBave;
//        this.absenceContact = absenceContact;
//        this.secoussesAnormauxIncontrolables = secoussesAnormauxIncontrolables;
//        this.apparitionBrutale = apparitionBrutale;
//        this.personneEtaitEpileptique = personneEtaitEpileptique;
//        this.sujetEpileptique = sujetEpileptique;
//        this.ageDebutEpilepsie = ageDebutEpilepsie;
//        this.crise2DernieresAnnees = crise2DernieresAnnees;
//        this.typeEpilepsie = typeEpilepsie;
//        this.nbCrisesEpilepsie = nbCrisesEpilepsie;
//        this.priseMedicamentsModerne = priseMedicamentsModerne;
//        this.priseAntiepileptiquesModernes = priseAntiepileptiquesModernes;
//        this.priseMedicamentsTraditiionnel = priseMedicamentsTraditiionnel;
//        this.antecedentsFamiliaux = antecedentsFamiliaux;
//        this.autresAntecedentsNeurologiquesFamiliaux = autresAntecedentsNeurologiquesFamiliaux;
//        this.quelsAntecedentsNeurologiquesFamiliaux = quelsAntecedentsNeurologiquesFamiliaux;
//        this.modifiedOn = modifiedOn;
//
//    }
//
//    public void setModifiedOn(Date date) {
//        this.modifiedOn = date;
//    }
//
//    public void setvillage(String village ){
//        this.village = village;
//    }
//
//    public void setPoids(Float Poids ){
//        this.Poids = Poids;
//    }
//
//    public void setregisterDate(Date registerDate ){
//        this.registerDate = registerDate;
//    }
//
    public void setNom(String nom ){
        this.nom = nom;
    }

    public void setPrenom(String prenom ){
        this.prenom = prenom;
    }
//
//    public void setrepondantPatient(Boolean repondantPatient ){
//        this.repondantPatient = repondantPatient;
//    }
//
//    public void setddn(Date ddn ){
//        this.ddn = ddn;
//    }
//
//    public void setSexe(String Sexe ){
//        this.Sexe = Sexe;
//    }
//
//    public void setethnie(String ethnie ){
//        this.ethnie = ethnie;
//    }
//
//    public void setetatCivilPatient(String etatCivilPatient ){
//        this.etatCivilPatient = etatCivilPatient;
//    }
//
//    public void setniveauScolaire(String niveauScolaire ){
//        this.niveauScolaire = niveauScolaire;
//    }
//
//    public void setprofessionPrincipale(String professionPrincipale ){
//        this.professionPrincipale = professionPrincipale;
//    }
//
//    public void setcoordonneesGPS(String coordonneesGPS ){
//        this.coordonneesGPS = coordonneesGPS;
//    }
//
//    public void setperteConnaissance(Boolean perteConnaissance ){
//        this.perteConnaissance = perteConnaissance;
//    }
//
//    public void setperteUrine(Boolean perteUrine ){
//        this.perteUrine = perteUrine;
//    }
//
//    public void setemissionBave(Boolean emissionBave ){
//        this.emissionBave = emissionBave;
//    }
//
//    public void setabsenceContact(String absenceContact ){
//        this.absenceContact = absenceContact;
//    }
//
//    public void setsecoussesAnormauxIncontrolables(String secoussesAnormauxIncontrolables ){
//        this.secoussesAnormauxIncontrolables = secoussesAnormauxIncontrolables;
//    }
//
//    public void setApparitionBrutale(String apparitionBrutale ){
//        this.apparitionBrutale = apparitionBrutale;
//    }
//
//    public void setpersonneEtaitEpileptique(String personneEtaitEpileptique ){
//        this.personneEtaitEpileptique = personneEtaitEpileptique;
//    }
//
//    public void setsujetEpileptique(Boolean sujetEpileptique ){
//        this.sujetEpileptique = sujetEpileptique;
//    }
//
//    public void setageDebutEpilepsie(Date ageDebutEpilepsie ){
//        this.ageDebutEpilepsie = ageDebutEpilepsie;
//    }
//
//    public void setcrise2DernieresAnnees(Boolean crise2DernieresAnnees ){
//        this.crise2DernieresAnnees = crise2DernieresAnnees;
//    }
//
//    public void settypeEpilepsie(String typeEpilepsie ){
//        this.typeEpilepsie = typeEpilepsie;
//    }
//
//    public void setnbCrisesEpilepsie(String nbCrisesEpilepsie ){
//        this.nbCrisesEpilepsie = nbCrisesEpilepsie;
//    }
//
//    public void setpriseMedicamentsModerne(String priseMedicamentsModerne ){
//        this.priseMedicamentsModerne = priseMedicamentsModerne;
//    }
//
//    public void setpriseAntiepileptiquesModernes(String priseAntiepileptiquesModernes ){
//        this.priseAntiepileptiquesModernes = priseAntiepileptiquesModernes;
//    }
//
//    public void setpriseMedicamentsTraditiionnel(String priseMedicamentsTraditiionnel ){
//        this.priseMedicamentsTraditiionnel = priseMedicamentsTraditiionnel;
//    }
//
//    public void setantecedentsFamiliaux(String antecedentsFamiliaux ){
//        this.antecedentsFamiliaux = antecedentsFamiliaux;
//    }
//
//    public void setautresAntecedentsNeurologiquesFamiliaux(Boolean autresAntecedentsNeurologiquesFamiliaux ){
//        this.autresAntecedentsNeurologiquesFamiliaux = autresAntecedentsNeurologiquesFamiliaux;
//    }
//
//    public void setquelsAntecedentsNeurologiquesFamiliaux(String quelsAntecedentsNeurologiquesFamiliaux ){
//        this.quelsAntecedentsNeurologiquesFamiliaux = quelsAntecedentsNeurologiquesFamiliaux;
//    }
//
//    public void setmodifiedOn(Date modifiedOn ){
//        this.modifiedOn = modifiedOn;
//    }
//    public Date getModifiedOn() {return this.modifiedOn; }

    public String buildSMSText() { return this.getNom() + Constants.sepaData + this.getPrenom(); }

    private String getNom() {
        return this.nom;
    }

    private String getPrenom() {
        return this.prenom;
    }

}