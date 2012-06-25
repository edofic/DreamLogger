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
import com.edofic.yodalib.database.Datasource;
import com.edofic.yodalib.database.Query;

import java.util.List;

/**
 * User: andraz
 * Date: 25.6.12
 * Time: 9:37
 */
public class Database extends com.edofic.yodalib.database.Database {
    public static final String NAME = "dreams";
    public static final int VERSION = 1;

    private Dreams dreams = new Dreams();

    public Database(Context context) {
        super(context, NAME, VERSION);

    }

    @TableDatasource(injectForType = Dream.class)
    private Datasource<Dream> dreamsTable;

    public Dreams getDreams() {
        return dreams;
    }

    public void close() {
        dreamsTable.close();
        dreamsTable = null;
        dreams = null;
    }

    public class Dreams {
        public long insert(Dream dream) {
            return dreamsTable.insert(dream);
        }

        @SuppressWarnings("unchecked")
        public List<Dream> get(String whereClause) {
            Query<Dream> query = new Query<Dream>(Dream.class);
            final String[] columnNames = dreamsTable.getMetaData().getColumnNames();
            final String orderBy = Dream.COLUMN_DATE + " DESC";
            return query.execute(dreamsTable, true, columnNames, whereClause, null, null, orderBy, null);
        }

        public List<Dream> getAll() {
            return get(null);
        }
    }
}
