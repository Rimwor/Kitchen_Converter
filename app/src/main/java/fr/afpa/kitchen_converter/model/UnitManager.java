package fr.afpa.kitchen_converter.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import fr.afpa.kitchen_converter.sqlite.MySQLiteBase;

public class UnitManager {
    private final static String TAG = "UnitManager";

    /**
     * Get Currency Table register
     * @param context
     * @return Map
     */
    public static Map<String, Double> getAll (Context context) {
        Log.i(TAG, "getAll");
        Map<String, Double> tCurrency = new HashMap<String, Double>();

        // Open DB connection
        MySQLiteBase conn = new MySQLiteBase(context, MySQLiteBase.DATABASE_NAME, null, MySQLiteBase.DATABASE_VERSION);

        //Calling DB with getWritableDatabase() to create DB
        // We can modify the call with getReadableDatabase()
        SQLiteDatabase db = conn.getWritableDatabase();

        // Get currencies list
        String sql = "SELECT " + MySQLiteBase.COL_UNIT + " as_id,"
                + MySQLiteBase.COL_CONVERSION_AMOUNT + " FROM "
                + MySQLiteBase.TABLE_UNIT;

        // Start the request
        Cursor c = db.rawQuery(sql, null);

        //Create a Money Table
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    //String currency = c.getString(c.getColumnIndex(MySQLiteBase.COL_CURRENCY));
                    String currency = c.getString(0);

                    double conversion_amount = c.getDouble(c.getColumnIndex(MySQLiteBase.COL_CONVERSION_AMOUNT));
                    tCurrency.put(currency, conversion_amount);

                    Log.e("DEBUG", "my :" + currency + " / amount " + conversion_amount);
                } while (c.moveToNext());
            }
        }
        db.close();

        return tCurrency;
    }
}
