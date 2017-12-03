package io.llamas.retobrounie;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import io.llamas.retobrounie.Adapters.PaisesAdapter;
import io.llamas.retobrounie.Data.PaisReader;
import io.llamas.retobrounie.Model.Contract.PaisEntry;
import io.llamas.retobrounie.Model.Pais;

public class Inicio extends Activity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        initViews();
        initActivity();
        initClickListeners();

    }

    /**
     * FUNCIONES INIT
     */

    public void initActivity() {

        ArrayList<Pais> paises = getPaises();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PaisesAdapter(this, paises));

    }

    public void initViews() {

        recyclerView = findViewById(R.id.recyclerView);

    }

    public void initClickListeners() {

    }

    /**
     * FUNCIONES PRIMARIAS
     */

    private ArrayList<Pais> getPaises() {

        ArrayList<Pais> paises = new ArrayList<>();
        PaisReader mDbHelper = new PaisReader(this);
        Cursor cursor = mDbHelper.getReadableDatabase().rawQuery("SELECT * FROM " + PaisEntry.TABLE_NAME, null);

        try {

            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {

                paises.add(new Pais(
                        cursor.getString(cursor.getColumnIndex(PaisEntry._ID)),
                        cursor.getString(cursor.getColumnIndex(PaisEntry.COLUMN_NOMBRE)),
                        cursor.getString(cursor.getColumnIndex(PaisEntry.COLUMN_CAPITAL)),
                        cursor.getString(cursor.getColumnIndex(PaisEntry.COLUMN_IMAGEN))
                ));
                cursor.moveToNext();

            }

        } finally {
            cursor.close();
        }

        return paises;
    }

    /**
     * FUNCIONES SECUNDARIAS
     */

}
