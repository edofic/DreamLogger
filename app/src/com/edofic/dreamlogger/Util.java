package com.edofic.dreamlogger;

import android.app.Application;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.Toast;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.text.ParseException;

/**
 * User: andraz
 * Date: 5/14/12
 * Time: 10:47 AM
 */
public class Util {
    private Util() {}

    private static java.text.DateFormat getDateFormat(Context context) {
        return DateFormat.getDateFormat(context);
    }

    public static String getFormattedDate(Context context, long date) {
        return getDateFormat(context).format(date);
    }

    public static long parseDate(Context context, String date) throws ParseException {
        return getDateFormat(context).parse(date).getTime();
    }

    public static void wtf(Context context) {
        Toast.makeText(context , R.string.wtf, Toast.LENGTH_LONG).show();
    }
}
