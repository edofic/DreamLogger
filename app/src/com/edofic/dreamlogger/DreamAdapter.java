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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * User: andraz
 * Date: 5/13/12
 * Time: 3:04 PM
 */
public class DreamAdapter extends BaseAdapter {
    private Context mContext;
    private Database.Dreams datasource;
    private List<Dream> dreams;

    public DreamAdapter(Context context) {
        mContext = context;
        datasource = ((DreamApp) context.getApplicationContext()).getDb().getDreams();
        update();
    }

    public void update() {
        dreams = datasource.getAll();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dreams.size();
    }

    @Override
    public Dream getItem(int position) {
        return dreams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convert, ViewGroup parrent) {
        View v = convert;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listitem_dream, null);
        }

        Dream dream = getItem(position);
        if (dream != null) {
            TextView tv;
            tv = (TextView) v.findViewById(R.id.name);
            tv.setText(dream.getName());
            tv = (TextView) v.findViewById(R.id.date);
            tv.setText(Util.getFormattedDate(mContext, dream.getDate()));
        }
        return v;
    }
}
