package ml.ibs.epi_droid_app;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Utils extends AppCompatActivity {

    private static final String TAG = Constants.getLogTag("Utils");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static void motification(Activity activity, String title, String message) {
        new AlertDialog.Builder(activity).setTitle(title)
                .setMessage(message).show();
    }
    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    public static void toast (Context context , String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
        toast.show();
    }
    public static Date strDateToDate(String strDate) {

        Log.d(TAG, " StrDate" + strDate);
        Date date = new Date();
        try {
            date = dateFormat.parse(strDate.replace("T", " "));
        } catch (ParseException e) {
            Log.d(TAG, "ParseException" + e.toString());
        }
        return date;
    }
    public static String dateTostrDate(Date date) {
        String finalDate = dateFormat.format(date);
        return finalDate;
    }
    public static String getDefaultCurrencyFormat(float value) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
        nf.setGroupingUsed(true);
        nf.setMaximumFractionDigits(3);
        return nf.format(value);
    }
    public static void copyFile(Activity context, File from, File to) {
        try {
            FileChannel src = new FileInputStream(from).getChannel();
            FileChannel dst = new FileOutputStream(to).getChannel();
            dst.transferFrom(src, 0, src.size());
            src.close();
            dst.close();
        } catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    protected static float floatFromField(EditText editText) {
        return floatFromField(editText, -1);
    }
    protected static float floatFromField(EditText editText, int fallback) {
        String text = stringFromField(editText);
        if (text.length() > 0) {
            return Float.parseFloat(text);
        }
        return fallback;
    }
    protected static String stringFromField(EditText editText) {
        return editText.getText().toString().trim();
    }
    protected static String stringFromSpinner(Spinner spinner) {
        return spinner.getSelectedItem().toString();
    }
    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
