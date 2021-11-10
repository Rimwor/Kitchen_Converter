package fr.afpa.kitchen_converter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import fr.afpa.kitchen_converter.model.UnitManager;

public class MainActivity extends AppCompatActivity {

    // DECLARATIONS of the variables ================================= //
    private final static String TAG = "MainActivity";                    // TAG : onActivityCheckupSpy

    // onCREATE ====================================================== //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

        UnitManager.getAll(this); // DATABASE test
    }

    // FUNCTIONS ===================================================== //
    /**
     * SHOW << a CONVERT TABLE View >>
     * (Activity : Table)
     * @param view
     */
    public void table(View view) {
        Intent iTable = new Intent(this, TableActivity.class);
        startActivity(iTable);
    }

    /**
     * SHOW << a CONVERT View >>
     * (Activity : Convert)
     * @param view
     */
    public void convert(View view) {
        Intent iConvert = new Intent(this, ConvertActivity.class);
        startActivity(iConvert);

    }

    /**
     * INFO button
     * @param view
     */
    public void info(View view) {
        Log.i(TAG, "info");

        // ALERT DIALOG BUILDER
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.info);

        // << QUIT >> BUTTON
        alertDialogBuilder.setNegativeButton(R.string.info_quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, R.string.info_quit_msg, Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /**
     * QUIT button
     * @param v
     */
    public void quit(View v) {
        Log.i(TAG, "quit");

        // ALERT DIALOG BUILDER
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.quit_question);

        // << YES >> BUTTON
        alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();
                System.exit(0);
            }
        });
        // << NO >> BUTTON
        alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, R.string.quit_no, Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

//        Toast.makeText(getBaseContext(), R.string.msg_quit, Toast.LENGTH_SHORT).show();
//        MainActivity.this.finish();
//        System.exit(0);
    }

}