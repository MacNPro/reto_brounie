package io.llamas.retobrounie.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import io.llamas.retobrounie.Model.Contract.CiudadEntry;

/**
 * Created by MacNPro on 12/2/17.
 */

public class CiudadReader extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CiudadReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CiudadEntry.TABLE_NAME + " (" +
                    CiudadEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CiudadEntry.COLUMN_NOMBRE + " TEXT," +
                    CiudadEntry.COLUMN_PAIS_ID + " TEXT," +
                    CiudadEntry.COLUMN_PAIS + " TEXT," +
                    CiudadEntry.COLUMN_IMAGEN + " TEXT," +
                    CiudadEntry.COLUMN_LATITUD + " TEXT," +
                    CiudadEntry.COLUMN_LONGITUD + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CiudadEntry.TABLE_NAME;

    public CiudadReader(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
