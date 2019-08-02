package ml.ibs.epi_droid_app;


import android.app.AlertDialog;
import android.content.DialogInterface;

import static java.lang.String.format;


public class Popups {

    public static class displayVersionPopup extends Popups {
        public displayVersionPopup(EPIHome snisiHome) {
            super();
            AlertDialog.Builder versionBuilder = new AlertDialog.Builder(snisiHome);
            versionBuilder.setTitle(R.string.app_name);
            String versionName = BuildConfig.VERSION_NAME;
            String msg_version = format("Version %s \n\nEn cas de problème contactez KABA ASSA.", versionName);
            versionBuilder.setMessage(msg_version);
            versionBuilder.setIcon(R.mipmap.ic_launcher);
            versionBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                        }
                    });
            // Remember, create doesn't show the dialog
            AlertDialog helpDialog = versionBuilder.create();
            helpDialog.show();
        }
    }



}
