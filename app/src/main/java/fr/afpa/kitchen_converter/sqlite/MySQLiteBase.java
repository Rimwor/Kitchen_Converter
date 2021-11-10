package fr.afpa.kitchen_converter.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MySQLiteBase extends SQLiteOpenHelper {

    private static final String TAG = "MySQLiteBase";

    public static final String DATABASE_NAME = "kitchen_converter.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_UNIT = "unit_table";
    public static final String COL_UNIT = "unit";
    public static final String COL_CONVERSION_AMOUNT = "conversion_amount";

    // Request LDD ( Create, Drop, ... )
    public static final String CREATE_TABLE_CURRENCY =
            "CREATE TABLE " + TABLE_UNIT + "("
                    + COL_UNIT + " TEXT PRIMARY KEY,"
                    + COL_CONVERSION_AMOUNT + " REAL);";

    public static final String DROP_TABLE_CURRENCY = "DROP TABLE IF EXISTS " + TABLE_UNIT + ";";

    public static final String INSERT_KILOGRAM =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('Kilogram',1000);";
    public static final String INSERT_GRAM =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('Gram',1);";
    public static final String INSERT_CENTIGRAM =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('Centigram',0.01);";
    public static final String INSERT_DECAGRAM =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('Decagram',10);";
    public static final String INSERT_HECTOGRAM =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('Hectogram',100);";
    public static final String INSERT_LITRE =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('Litre',1000);";
    public static final String INSERT_MILLILITRE =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('Millilitre',1);";
    public static final String INSERT_CENTILITRE =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('Centilitre',0.01);";
    public static final String INSERT_GLASS =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('1 Glass',250);";
    public static final String INSERT_34GLASS =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('3/4 Glass',180);";
    public static final String INSERT_23GLASS =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('2/3 Glass',160);";
    public static final String INSERT_12GLASS =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('1/2 Glass',125);";
    public static final String INSERT_13GLASS =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('1/3 Glass',80);";
    public static final String INSERT_14GLASS =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('1/4 Glass',60);";
    public static final String INSERT_16GLASS =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('1/6 Glass',40);";
    public static final String INSERT_18GLASS =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('1/8 Glass',30);";
    public static final String INSERT_116GLASS =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('1/16 Glass',15);";
    public static final String INSERT_TEASPOON =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('1 Tea Spoon',5);";
    public static final String INSERT_TABLESPOON =
            "INSERT INTO " + TABLE_UNIT
                    + "(" + COL_UNIT
                    + "," + COL_CONVERSION_AMOUNT + ") VALUES ('1 Table Spoon',15);";

    public MySQLiteBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.i(TAG, "ConvertSQLite");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate() Creation DB");

        // TABLE CURRENCY creation
        db.execSQL(CREATE_TABLE_CURRENCY);

        // SET BASE
        Log.i(TAG,"Setting table");
        db.execSQL(INSERT_KILOGRAM);
        db.execSQL(INSERT_GRAM);
        db.execSQL(INSERT_CENTIGRAM);

        db.execSQL(INSERT_DECAGRAM);
        db.execSQL(INSERT_HECTOGRAM);

        db.execSQL(INSERT_LITRE);
        db.execSQL(INSERT_MILLILITRE);
        db.execSQL(INSERT_CENTILITRE);

        db.execSQL(INSERT_GLASS);
        db.execSQL(INSERT_34GLASS);
        db.execSQL(INSERT_23GLASS);
        db.execSQL(INSERT_12GLASS);
        db.execSQL(INSERT_13GLASS);
        db.execSQL(INSERT_14GLASS);
        db.execSQL(INSERT_16GLASS);
        db.execSQL(INSERT_18GLASS);
        db.execSQL(INSERT_116GLASS);

        db.execSQL(INSERT_TEASPOON);
        db.execSQL(INSERT_TABLESPOON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading DB from version " + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        db.execSQL(DROP_TABLE_CURRENCY);
        onCreate(db);
    }

}
