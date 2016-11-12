package com.adfin.numobile.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.adfin.numobile.App;

/**
 * Created by aldieemaulana on 10/01/2016
 */
public class RalewayTextViewRegular extends TextView {
    public RalewayTextViewRegular(Context context) {
        this(context, null);
    }

    public RalewayTextViewRegular(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RalewayTextViewRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(App.ralewayRegular);
    }

}
