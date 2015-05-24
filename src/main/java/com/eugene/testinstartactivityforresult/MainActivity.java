package com.eugene.testinstartactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText editText;
    TextView requestResult, savedStates;

    static final int ACTIVITY_ONE_REQUEST = 1;  // The request code for ActivityOne
    static final int ACTIVITY_TWO_REQUEST = 2;  // The request code for ActivityTwo
    public final static String ACTIVITY_ONE_RESULT = "activity_one"; // Data Argument For Activity One
    public final static String ACTIVITY_TWO_RESULT = "activity_two";// Data Argument For Activity Two
    public final static String ACTIVITY_TWO_RESULT_DIFFERENT = "activity_two_different_result"; // Different Data Argument For Activity Two

    static final String SAVED_STATE = "saved_state"; // Save the states
    static final String SAVED_EDIT_TEXT = "text_within_EditText"; // Save the EditText
    private String savedState;
    private String savedEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            savedState = savedInstanceState.getString(SAVED_STATE, "not_saved");
            savedEditText = savedInstanceState.getString(SAVED_EDIT_TEXT, "");
        } else {
            savedState = "Not Saved";
            savedEditText = "";
        }

        editText = (EditText) findViewById(R.id.editText);
        requestResult = (TextView) findViewById(R.id.requestResult);
        savedStates = (TextView) findViewById(R.id.savedStates);
        Button buttonOne = (Button) findViewById(R.id.buttonOne);
        Button buttonTwo = (Button) findViewById(R.id.buttonTwo);

        // Used to start ActivityOne
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityOne = new Intent(MainActivity.this, ActivityOne.class);
                startActivityForResult(activityOne, ACTIVITY_ONE_REQUEST);
            }
        });

        // Used to start ActivityTwo
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityOne = new Intent(MainActivity.this, ActivityTwo.class);
                startActivityForResult(activityOne, ACTIVITY_TWO_REQUEST); //
            }
        });
        updateSavedItems();
    }

    // update widgets
    private void updateSavedItems() {
        savedStates.setText(savedState);
        editText.setText(savedEditText);
    }

    /**
     * Get the Results from the Other Activities
     *
     * @param requestCode In this case either 1 = ACTIVITY_ONE_REQUEST  or 2 = ACTIVITY_TWO_REQUEST
     * @param resultCode  Determines whether the request was successful.
     * @param data        The being sent from other activities via Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_ONE_REQUEST && resultCode == RESULT_OK) {
            if (data.hasExtra(ACTIVITY_ONE_RESULT)) {
                String result = data.getExtras().getString(ACTIVITY_ONE_RESULT);
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                requestResult.setText(result);
            }
        }
        if (requestCode == ACTIVITY_TWO_REQUEST && resultCode == RESULT_OK) {
            /**
             * Possibly of returning two different results from Activity Two
             */
            if (data.hasExtra(ACTIVITY_TWO_RESULT)) {
                String result = data.getExtras().getString(ACTIVITY_TWO_RESULT);
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                requestResult.setText(result);
            }
            if (data.hasExtra(ACTIVITY_TWO_RESULT_DIFFERENT)) {
                String result = data.getExtras().getString(ACTIVITY_TWO_RESULT_DIFFERENT);
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                requestResult.setText(result);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current state
        savedInstanceState.putString(SAVED_STATE, "Saved");
        savedInstanceState.putString(SAVED_EDIT_TEXT, editText.getText().toString());
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
}
