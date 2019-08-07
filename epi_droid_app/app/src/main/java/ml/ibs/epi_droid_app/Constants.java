package ml.ibs.epi_droid_app;

import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class Constants {

    private final static String TAG = Constants.getLogTag("Constants");

    public static final int SMS_SUCCESS = 0;
    public static final int SMS_WARNING = 1;
    public static final int SMS_ERROR = 2;
    public static final int SMS_UNKNOWN = 3;

    public static final String server_number = "76433890";
    public static final String server_url = "http://epitraitement.ml";
    public static final String resource_url = "%1$s/resources";
    public static final String SPACER = " ";
    public static final String SUB_SPACER = "-";
    public static final String SHARP_SPACER = "#";
    public static final int MIN_CHARS_PASSWORD = 4;
    public static final int MIN_CHARS_USERNAME = 3;
    public static final String SMS_FORMAT = "%1$s %2$s %3$s %4$s";

    public static final String SMS_KEYWORD = "epi";

    public static final String SMS_SENT_INTENT = "com.ibs.epi_droid_app.SMS_SENT";
    public static final String SMS_DELIVERED_INTENT = "com.ibs.epi_droid_app.SMS_DELIVERED";

    public static final int NB_SECONDS_WAIT_FOR_REPLY = 150;
    public static final String SMS_CHANGE_PASSWRD = "passwd %1$s %2$s %3$s";

    public static final int RESULT_SETTINGS = 1;
    public static final String DATE_SPACER = "/";

    public static final String databaseName = "epidata.db";
    public static final char sepaData = '-';


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
    static String[] ethnie = new String[] { E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12 };

    public static final String[] getETHNIE() {
        return ethnie;
    }


    static String S1 = "Marié";
    static String S2 = "Vit avec parent(s)";
    static String S3 = "Concubinage";
    static String S4 = "Vit seul(e)";
    static String S5 = "Autre";
    static String STATUT = "statut";
    static String[] statut = new String[] { S1, S2, S3, S4, S5 };

    public static final String[] getStatut() {
        return statut;
    }


    static String Sc1 = "Primaire 1";
    static String Sc2 = "Primaire 2";
    static String Sc3 = "Primaire 3";
    static String Sc4 = "Primaire 4";
    static String Sc5 = "Primaire 5";
    static String Sc6 ="Primaire 6";
    static String Sc7 ="Secondaire 7";
    static String Sc8 = "Secondaire 8";
    static String Sc9 = "Secondaire 9";
    static String Sc10 = "Supérieure";
    static String Sc11 = "Aucun";

    static String[] scolarisation = new String[] { Sc1, Sc2, Sc3, Sc4, Sc5,Sc6,Sc7,Sc8,Sc9,Sc10,Sc11 };

    public static final String[] getScolarisation(){ return scolarisation; }



    static String PF1 = "Agriculteur";
    static String PF2 = "Eleveur";
    static String PF3 = "Pêcheur";
    static String PF4 = "Salarié ou fonctionnaire";
    static String PF5 = "Artisan ou commerçant";
    static String PF6 = "Etudiant";
    static String PF7 = "Travail à domicile";
    static String PF8 = "Profession libérale";
    static String PF9 = "Autre";
    static String[] profession = new String[] { PF1, PF2, PF3, PF4, PF5,PF6,PF7,PF8,PF9 };


    public static final String[] getProfession(){ return profession; }



    static String ST1 = "1foix";
    static String ST2 = "2fois";
    static String ST3 = "PLUS-FOIS ";
    static String ST4 = "Jamais";
    static String[] SANTE = new String[] { ST1, ST2, ST3, ST4 };


    public static final String[] getSANTE(){ return SANTE; }



    public static final String getLogTag(String activity) {
    	return String.format("EPI-Log-%s", activity);
    }

    public static int getSMSStatus(String message) {
        // for status, we need [:]
        if (!message.contains("[") || !message.contains("]") || !message.contains(":")) {
            return SMS_UNKNOWN;
        }

        int semiColon = message.indexOf(":");
        int endingBracket = message.indexOf("]");
        if (semiColon < 0 || endingBracket < 0) {
            return SMS_UNKNOWN;
        }
        String statusText = message.substring(semiColon + 1, endingBracket);
        Log.d(TAG, "Status: " + statusText);

        if (statusText.equals("OK")) {
            return SMS_SUCCESS;
        }

        if (statusText.equals("/!\\")) {
            return SMS_WARNING;
        }

        if (statusText.equals("ECHEC")) {
            return SMS_ERROR;
        }

        return SMS_UNKNOWN;
    }

    public static int getColorForStatus(int status) {
        switch (status) {
            case SMS_SUCCESS:
                return Color.rgb(71, 166, 42); // green
            case SMS_WARNING:
                return Color.rgb(208, 113, 42); // orange
            case SMS_ERROR:
                return Color.RED;
            case SMS_UNKNOWN:
            default:
                return -1;
        }
    }
   public static String getCompleteStatus(Boolean status) {
       if (status) {
           return "X";
       } else {
           return "  ";
       }
   }

    public static void updateButtonCompletion(Button button, boolean is_complete) {
        int color = getColorForStatus((is_complete) ? SMS_SUCCESS : SMS_UNKNOWN);
        if (color != -1) {
            button.setTextColor(color);
        }
    }

    public static String buildCompleteSMSText(String smsKeyword, String username,
                                              String password, String smsData) {
        return String.format(
                SMS_FORMAT, smsKeyword, username, password, smsData);
    }

    public static String stringFromFloat(float data) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(2);
        return nf.format(data);
    }

    public static String stringFromInteger(int data) {
        return String.valueOf(data);
    }

    public static int integerFromReport(int data) {
        if(data < 0) {
            data = 0;
        }
        return data;
    }

    public static float floatFromReport(float data) {
        if(data < 0) {
            data = (float) 0.00;
        }
        return data;
    }

    public static String stringFromReport(int data){
        return stringFromInteger(integerFromReport(data));
    }

    public static String stringFromReport(float data){
        return stringFromFloat(floatFromReport(data));
    }

    public static CompoundButton.OnCheckedChangeListener getResetTextViewCheckListener(final TextView textView) {
        Log.d(TAG, "recorded check listener");
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(TAG, "CompoundButton");
                textView.setError(null);
            }
        };
    }
}
