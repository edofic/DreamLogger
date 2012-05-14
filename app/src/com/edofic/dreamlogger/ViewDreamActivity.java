package com.edofic.dreamlogger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import roboguice.activity.RoboActivity;
import roboguice.event.Observes;
import roboguice.inject.InjectView;

/**
 * User: andraz
 * Date: 5/13/12
 * Time: 11:29 PM
 */
public class ViewDreamActivity extends RoboActivity {
    public static final String EDIT_MODE = "editMode";
    public static final String LOAD_CURRENT = "loadCurrent";
    public static final String EXIT_ON_END = "exitOnEnd";
    private boolean editMode = false;
    private Dream dream;

    @InjectView(R.id.name)      private EditText name;
    @InjectView(R.id.date)      private TextView date;
    @InjectView(R.id.desc)      private EditText desc;
    @InjectView(R.id.buttons)   private LinearLayout buttons;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_dream);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dream.setName(name.getText().toString());
        dream.setDescription(desc.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Intent intent = getIntent();
        editMode = intent.getBooleanExtra(EDIT_MODE, editMode);
        intent.removeExtra(EDIT_MODE);
        intent.removeExtra(EDIT_MODE);
        if(intent.getBooleanExtra(LOAD_CURRENT, false)) {
            intent.removeExtra(LOAD_CURRENT);
            if(Dream.current==null) {         //create new
                editMode=true;
                dream = new Dream();
                dream.setDate(System.currentTimeMillis()); //defaults to creation date
            } else {
                dream = Dream.current.clone();
            }
        }

        loadData();
        enforceEdit();
    }

    private void loadData() {
        name.setText(dream.getName());
        date.setText(Util.getFormattedDate(this, dream.getDate()));
        desc.setText(dream.getDescription());
    }

    private void storeData() {
        dream.setName(name.getText().toString());
        dream.setDescription(desc.getText().toString());
    }

    private void enforceEdit() {
        name.setEnabled(editMode);
        desc.setEnabled(editMode);
        buttons.setVisibility(editMode ? View.VISIBLE : View.GONE);
    }

    private void finishEditing() {
        if(getIntent().getBooleanExtra(EXIT_ON_END, false)) {
            finish();
            return;
        }
        editMode=false;
        enforceEdit();
    }

    public void setDate(View sender) {
        //TODO
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_LONG).show();
    }

    public void save(View sender) {
        storeData();
        Datasource datasource = new Datasource(this);
        datasource.insertSingle(dream);
        Dream.current = dream;
        finishEditing();
    }

    public void cancel(View sender) {
        finishEditing();
        loadData();
    }

    //options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.prefs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuEdit:
                editMode=true;
                enforceEdit();
                return true;
        }
        //if we come here, we didn't handle the request
        return false;
    }
}