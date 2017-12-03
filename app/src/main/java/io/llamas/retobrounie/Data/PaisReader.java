package io.llamas.retobrounie.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import io.llamas.retobrounie.Model.Contract.PaisEntry;

/**
 * Created by MacNPro on 12/2/17.
 */

public class PaisReader extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DataReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PaisEntry.TABLE_NAME + " (" +
                    PaisEntry._ID + " TEXT PRIMARY KEY," +
                    PaisEntry.COLUMN_NOMBRE + " TEXT," +
                    PaisEntry.COLUMN_CAPITAL + " TEXT," +
                    PaisEntry.COLUMN_IMAGEN + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PaisEntry.TABLE_NAME;

    public PaisReader(Context context) {
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
