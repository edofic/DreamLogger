/*
 * Copyright 2012 Andraz Bajt
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.edofic.dreamlogger;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.text.ParseException;

/**
 * User: andraz
 * Date: 5/14/12
 * Time: 10:47 AM
 */
public class Util {
    private Util() {
    }

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
        Toast.makeText(context, R.string.wtf, Toast.LENGTH_LONG).show();
    }
}
