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

import android.text.format.DateFormat;
import com.edofic.yodalib.database.Column;
import com.edofic.yodalib.database.Table;

import java.util.Calendar;
import java.util.Locale;

/**
 * User: andraz
 * Date: 5/13/12
 * Time: 2:49 PM
 */
@Table(name = Dream.TABLE_NAME)
public class Dream implements Cloneable{
    public static Dream current;

    public static final String TABLE_NAME           = "dream";
    public static final String COLUMN_ID            = "id";
    public static final String COLUMN_NAME          ="name";
    public static final String COLUMN_DESCRIPTION   = "description";
    public static final String COLUMN_DATE          = "date";

    @Column(name = COLUMN_ID, position = 1, primaryKey = true, autoIncrement = true)
    private long id;
    @Column(name = COLUMN_NAME, position = 2)
    private String name;
    @Column(name = COLUMN_DESCRIPTION, position = 3)
    private String description ;
    @Column(name = COLUMN_DATE, position = 4)
    private long date;

    public Dream() { }

    public Dream(long id, String name, String description, long date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    protected Dream clone() {
        try {
            return (Dream)super.clone();
        } catch (CloneNotSupportedException e) {
            throw  new AssertionError("Clone not supported in a class that implements cloneable");
        }
    }
}
