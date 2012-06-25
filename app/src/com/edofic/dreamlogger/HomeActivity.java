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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;


public class HomeActivity extends RoboActivity {
    @InjectView(R.id.list)
    private ListView lv;

    private DreamAdapter adapter = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setUpList();
    }

    private void setUpList() {
        adapter = new DreamAdapter(this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Dream.current = adapter.getItem(position);
                startActivity(
                        new Intent(getApplicationContext(), ViewDreamActivity.class)
                                .putExtra(ViewDreamActivity.LOAD_CURRENT, true)
                );
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.update();
    }

    public void addDream(View sender) {
        Dream.current = null;
        startActivity(
                new Intent(this, ViewDreamActivity.class)
                        .putExtra(ViewDreamActivity.EXIT_ON_END, true)
                        .putExtra(ViewDreamActivity.LOAD_CURRENT, true)
        );
    }
}
