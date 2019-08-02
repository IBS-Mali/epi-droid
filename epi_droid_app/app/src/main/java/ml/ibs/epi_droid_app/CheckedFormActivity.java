package ml.ibs.epi_droid_app;

import android.app.Activity;
import android.content.Context;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import java.util.Map;

public class CheckedFormActivity extends Activity implements SMSUpdater {

    private final static String TAG = Constants.getLogTag("CheckedFormActivity");


    /* Username & Password for transmission */
    protected String username = "-";
    protected String password = "-";


    /* Keep an internal state of input validation for each fields */
	protected LinkedHashMap<Integer, Boolean> checkedFields = new LinkedHashMap<Integer, Boolean>();

	protected void updateFieldCheckedStatus(EditText editText) {
		updateFieldCheckedStatus(editText, false);
	}

	protected void updateFieldCheckedStatus(EditText editText, Boolean status) {
		checkedFields.put(editText.getId(), status);
	}

    /* Username & Password accessors */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	/* Abstract methods */
	protected void setupInvalidInputChecks() {}
	protected boolean ensureDataCoherence() { return false; }
	protected String buildSMSText() { return ""; }
	protected void storeReportData() {}
	protected void restoreReportData() {}
    protected void resetReportData() {
        Log.i(TAG, "resetReportData orig");
    }

	/* Visual feedback for invalid and incorect data */
	protected void addErrorToField(EditText editText, String message) {
		editText.setError(message);
		// editText.requestFocus();
	}

	protected boolean doCheckAndProceed(boolean test, String error_msg, EditText editText) {
		if (test) {
            addErrorToField(editText, error_msg);
			return false;
		} else {
			addErrorToField(editText, null);
		}
		return true;
	}

	/* General checking methods */
	protected boolean ensureValidInputs(boolean focusOnFailing) {
    	for (Map.Entry<Integer, Boolean> entry : checkedFields.entrySet()) {
 			if (!entry.getValue()) {
 				if (focusOnFailing) {
 					EditText field = (EditText) findViewById(entry.getKey());
                    field.setText(field.getText());
 					field.requestFocus();
 				}
 				return false;
 			}
		}
        return true;
    }

    protected void removeFocusFromFields() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    protected boolean checkInputsAndCoherence() {
        // remove focus so to remove
        removeFocusFromFields();

    	if (!ensureValidInputs(true)) {
    		Log.d(TAG, "Invalid inputs");
    		return false;
    	}
    	if (!ensureDataCoherence()) {
    		Log.d(TAG, "Not coherent inputs");
    		return false;
    	}
    	Log.i(TAG, "data looks good");
    	return true;
    }

	/* Input Validation Checks (standalone functions) */
	protected boolean assertNotEmpty(EditText editText) {
		boolean test = (editText.getText().toString().trim().length() == 0);
		String error_msg = String.format(getString(R.string.error_field_empty));
		return doCheckAndProceed(test, error_msg, editText);
	}

	protected boolean assertAtLeastThisLong(EditText editText, int min_chars) {
		boolean test = (editText.getText().toString().trim().length() < min_chars);
		String error_msg = String.format(getString(R.string.error_field_min_chars),
                String.valueOf(min_chars));
		return doCheckAndProceed(test, error_msg, editText);
	}

    protected boolean assertPasswordAlike(EditText editText) {
        String text = stringFromField(editText);
        boolean test = (text.contains(" ") || text.length() < Constants.MIN_CHARS_PASSWORD);
        String error_msg = String.format(getString(R.string.error_field_nopasswd),
                String.valueOf(Constants.MIN_CHARS_PASSWORD));
        return doCheckAndProceed(test, error_msg, editText);
    }

    protected boolean assertUsernameAlike(EditText editText) {
        String text = stringFromField(editText);
        boolean test = (text.contains(" ") || text.length() < Constants.MIN_CHARS_USERNAME);
        String error_msg = String.format(getString(R.string.error_field_nopasswd),
                String.valueOf(Constants.MIN_CHARS_USERNAME));
        return doCheckAndProceed(test, error_msg, editText);
    }

	protected boolean assertPositiveInteger(EditText editText) {
		boolean test = (integerFromField(editText, -1) < 0);
		String error_msg = String.format(
			getString(R.string.error_field_positive_integer));
		return doCheckAndProceed(test, error_msg, editText);
	}

	protected boolean assertPositiveFloat(EditText editText) {
		boolean test = (floatFromField(editText, -1) < 0);
		String error_msg = String.format(
			getString(R.string.error_field_positive_integer));
		return doCheckAndProceed(test, error_msg, editText);
	}

	/* Input Validation Checks (EventListener creators) */
	protected void setAssertNotEmpty(final EditText editText) {
    	updateFieldCheckedStatus(editText, false);
    	editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            	updateFieldCheckedStatus(editText, assertNotEmpty(editText));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    protected void setAssertAtLeastThisLong(final EditText editText, final int min_chars) {

    	updateFieldCheckedStatus(editText, false);
    	editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            	updateFieldCheckedStatus(editText, assertAtLeastThisLong(editText, min_chars));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    protected void setAssertPositiveInteger(final EditText editText) {
    	updateFieldCheckedStatus(editText, false);
    	editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            	updateFieldCheckedStatus(editText, assertPositiveInteger(editText));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    protected void setAssertPositiveFloat(final EditText editText) {

    	updateFieldCheckedStatus(editText, false);
    	editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            	updateFieldCheckedStatus(editText, assertPositiveFloat(editText));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    /* Bundled data-ok callbacks */
    protected void checkAndFinish() {
		if (!checkInputsAndCoherence()) { return; }
		finish();
	}

	protected void checkAndLogSMS() {
		if (!checkInputsAndCoherence()) { return; }

		String sms_text = buildSMSText();
        Log.d(TAG, sms_text);

		finish();
	}


    /* Input CleanUp/convertions */
    protected String stringFromField(EditText editText) {
        return editText.getText().toString().trim();
    }

    protected int integerFromField(EditText editText) {
        return integerFromField(editText, -1);
    }
    protected int integerFromField(EditText editText, int fallback) {
        String text = stringFromField(editText);
        if (text.length() > 0) {
            try {
                return Integer.parseInt(text);
            } catch (Exception e){
                Log.d(TAG, e.toString());
            }
        }
        return fallback;
    }

    protected float floatFromField(EditText editText) {
        return floatFromField(editText, -1);
    }
    protected float floatFromField(EditText editText, int fallback) {
        String text = stringFromField(editText);
        if (text.length() > 0) {
            return Float.parseFloat(text);
        }
        return fallback;
    }
    protected void setTextOnField(EditText editText, Object value) {
        String default_str = "";
        String value_str;
        try {
            value_str = String.valueOf(value);
        } catch (Exception e) {
            value_str = default_str;
        }
        //Log.d(TAG, value_str);
        editText.setText(value_str);
    }

    @Override
    public void gotSms(ArrayList<SmsMessage> messages) {

    }

    @Override
    public void gotSMSStatusUpdate(int status, String message) {

    }
}
