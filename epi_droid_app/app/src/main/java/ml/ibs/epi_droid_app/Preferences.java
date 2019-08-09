package ml.ibs.epi_droid_app;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.orm.SugarContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class Preferences extends PreferenceActivity {

    private static final String TAG = Constants.getLogTag("Preferences");
    private File currentDB;
    private String packageName;
    private String databaseName;
    private Spinner dristrictSpinner;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        super.onCreate(savedInstanceState);

//        setTitle(String.format("%1$s/%2$s", getString(R.string.app_name), getString(R.string.menu_settings)));
        databaseName = Constants.databaseName;
        currentDB = getApplicationContext().getDatabasePath(databaseName);
        packageName = getPackageName();

        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("passwork", "");
        editor.apply();
        addPreferencesFromResource(R.xml.epi_preferences);


        ListPreference entities = (ListPreference) findPreference("entities");

        final Preference restBtt = (Preference)findPreference("restDB");
        restBtt.setSummary(String.format(getString(R.string.summary_reset_db), 3));

        restBtt.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            int counter = 3;

            @Override
            public boolean onPreferenceClick(Preference preference) {
                counter--;
                restBtt.setSummary(String.format(getString(R.string.summary_reset_db), counter));
                if (counter == 1) {
                    restBtt.setTitle(getString(R.string.rest_confermed));
                }
                if (counter == 0) {
                    counter = 3;
                    restDatabase(Constants.databaseName);
                }
                return false;
            }
        });

    }


    private void restDatabase(String databaseName) {

    }
}
