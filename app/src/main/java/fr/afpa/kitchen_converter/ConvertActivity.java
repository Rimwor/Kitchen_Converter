package fr.afpa.kitchen_converter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.afpa.kitchen_converter.operations.Convert;

public class ConvertActivity extends AppCompatActivity {

    // DECLARATIONS of the variables ================================= //
    private final static String TAG = "ConvertActivity";                 // TAG : onActivityCheckupSpy

    private ArrayList<String> unitsList;

    private Spinner spinner_from, spinner_to;                       // SPINNERS (Activity : Table)
    private EditText enter_an_amount;                               // EDITTEXT (Activity : Table)

    // onCREATE ====================================================== //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert);

        Intent iTable = getIntent();

        chargeUnit();
        chargeSpinner(R.id.spinner_from);
        chargeSpinner(R.id.spinner_to);
    }

    // FUNCTIONS ===================================================== //

    /**
     * SPINNER CHARGE
     * @param idSpinner
     */
    private void chargeSpinner(int idSpinner) {
        // GET A SPINNER BY ID
        Spinner spinner = (Spinner) findViewById(idSpinner);

        // ADAPTER CREATION
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, this.unitsList);
        // ASSIGN THE ADAPTER TO THE SPINNER
        spinner.setAdapter(adapter);
    }

    /**
     * UNIT CHARGE
     */
    private void chargeUnit() {
        Map<String, Double> conversionTable =  Convert.getConversionTable(this);
        this.unitsList = new ArrayList<String>(conversionTable.keySet());

        // ADD AN EMPTY KEY
        this.unitsList.add("");

        // ORDER THE TABLE
        Collections.sort(this.unitsList);
    }

    /**
     * Check fields, convert the starting unit and display the amount of the ending unit
     * @param v
     */
    public void do_convert(View v) {
        Log.i(TAG, "do_convert");

        // GET WIDGETS FROM THE VIEW
        Spinner spInitUnit = (Spinner) findViewById(R.id.spinner_from);
        String strInitUnit = (String) spInitUnit.getSelectedItem();
        Spinner spFinalUnit = (Spinner) findViewById(R.id.spinner_to);
        String strFinalUnit = (String) spFinalUnit.getSelectedItem();
        EditText eAmount = (EditText) findViewById(R.id.enter_an_amount);
        String strAmount = eAmount.getText().toString();

        // PUTS A CONTROL ON THE VALUES

        // TEST if the initial unit is EMPTY
        if (strInitUnit.equals("")) {
            Toast.makeText(getBaseContext(),R.string.err_init_unit,Toast.LENGTH_SHORT).show();

        // TEST if final unit is EMPTY
        } else if (strFinalUnit.equals("")) {
            Toast.makeText(getBaseContext(),R.string.err_final_unit,Toast.LENGTH_SHORT).show();

        // TEST if units are different
        } else if (strInitUnit.equals(strFinalUnit)) {
            Toast.makeText(getBaseContext(),R.string.err_double,Toast.LENGTH_SHORT).show();

        // TEST if there are only numbers (and no letters)
        } else if (strAmount.equals("") || strAmount.matches("[^0-9]")) {
            Toast.makeText(getBaseContext(),R.string.err_amount,Toast.LENGTH_SHORT).show();

        } else {

            // ALL OK
            try {

                double dblAmount = Double.valueOf(strAmount);

                // START A CONVERSION
                double dblAmountArr = Convert.convertir(strInitUnit, strFinalUnit, dblAmount);

                // SHOW THE CONVERSION RESULT
                String msg = dblAmount + " " + strInitUnit + " = " + dblAmountArr
                        + " " + strFinalUnit;

                // Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                TextView tResult = (TextView) findViewById(R.id.result);
                tResult.setText(msg);

            } catch (NumberFormatException e) {

                // The Amount error
                Toast.makeText(getBaseContext(),R.string.err_amount,Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * BACK button
     * @param view
     */
    public void back(View view) {
        Log.i(TAG, "back");
        ConvertActivity.this.finish();
    }

}
