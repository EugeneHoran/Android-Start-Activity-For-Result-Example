package com.eugene.testinstartactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_two);
        Button buttonTwo = (Button) findViewById(R.id.buttonTwo);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(MainActivity.ACTIVITY_TWO_RESULT, "Data From Activity Two Via Intent");
                setResult(RESULT_OK, data); // passing the RESULT_OK parameter
                finish();
            }
        });
        Button buttonSendDifferentResult = (Button) findViewById(R.id.buttonTwoDifferentResult);
        buttonSendDifferentResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(MainActivity.ACTIVITY_TWO_RESULT_DIFFERENT, "Different Data From Activity Two Via Intent");
                setResult(RESULT_OK, data); // passing the RESULT_OK parameter
                finish();
            }
        });
    }
}
