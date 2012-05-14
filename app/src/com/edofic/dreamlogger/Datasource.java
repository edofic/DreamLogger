package com.edofic.dreamlogger;

import android.content.Context;

/**
 * User: andraz
 * Date: 5/14/12
 * Time: 5:59 PM
 */
public class Datasource extends com.edofic.yodalib.database.Datasource<Dream> {
    public Datasource(Context context) {
        super(context, Dream.class);
    }
}
