package com.example.android.sunshine.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by MY on 2017-01-17.
 */

public class LocationEditTextPreference extends EditTextPreference {
    private static final int DEFAULT_MINIMUM_LOCATION_LENGTH = 3;
    private int minLen;

    public LocationEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LocationEditTextPreference, 0, 0);

        try {
            minLen = typedArray.getInteger(R.styleable.LocationEditTextPreference_minLength, DEFAULT_MINIMUM_LOCATION_LENGTH);
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    public void showDialog(Bundle bundle) {
        super.showDialog(bundle);

        EditText editText = getEditText();
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Dialog dialog = getDialog();
                if(dialog instanceof AlertDialog) {
                    AlertDialog alertDialog = (AlertDialog) dialog;
                    Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);

                    if(editable.length() < minLen) {
                        positiveButton.setEnabled(false);
                    } else {
                        positiveButton.setEnabled(true);
                    }
                }
            }
        });
    }
}