package io.llamas.retobrounie;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.llamas.retobrounie.Adapters.CiudadesAdapter;
import io.llamas.retobrounie.CustomClasses.RalewayMediumEdit;
import io.llamas.retobrounie.CustomClasses.RalewaySemiBold;
import io.llamas.retobrounie.Data.CiudadReader;
import io.llamas.retobrounie.Data.PaisReader;
import io.llamas.retobrounie.Model.Ciudad;
import io.llamas.retobrounie.Model.Contract.CiudadEntry;

public class Ciudades extends Activity {

    EditText etCiudad, etURL, etLatitud, etLongitud;
    RecyclerView recyclerView;

    String pNombre;
    String pID;

    boolean enVentana = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudades);

        initViews();
        initActivity();
        initClickListeners();

    }

    /**
     * FUNCIONES INIT
     */

    public void initActivity() {

        pID = getIntent().getStringExtra("ID");
        pNombre = getIntent().getStringExtra("Nombre");

        TextView ttNombre = (RalewaySemiBold) findViewById(R.id.nombre);
        ttNombre.setText(pNombre);

        ArrayList<Ciudad> ciudades = getCiudades(pID);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CiudadesAdapter(this, ciudades));

    }

    public void initViews() {

        etCiudad = (RalewayMediumEdit) findViewById(R.id.etCiudad);
        etURL = (RalewayMediumEdit) findViewById(R.id.etURL);
        etLatitud = (RalewayMediumEdit) findViewById(R.id.etLatitud);
        etLongitud = (RalewayMediumEdit) findViewById(R.id.etLongitud);
        recyclerView = findViewById(R.id.recyclerView);

    }

    public void initClickListeners() {

        findViewById(R.id.regresar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.agregar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleVentana();
            }
        });

        findViewById(R.id.cerrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleVentana();
            }
        });

        findViewById(R.id.fondo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleVentana();
            }
        });

        findViewById(R.id.guardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tCiudad = etCiudad.getText().toString();
                String tURL = etURL.getText().toString();
                String tLatitud = etLatitud.getText().toString();
                String tLongitud = etLongitud.getText().toString();

                if (!tCiudad.isEmpty() && !tURL.isEmpty() && !tLatitud.isEmpty() && !tLongitud.isEmpty()) {

                    CiudadReader mDbHelper = new CiudadReader(Ciudades.this);
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();

                    createCiudadEntry(db, tCiudad, pID, pNombre, tURL, tLatitud, tLongitud);
                    recyclerView.setAdapter(new CiudadesAdapter(Ciudades.this, getCiudades(pID)));

                    toggleVentana();

                } else {
                    Toast.makeText(Ciudades.this, "Debes llenar todos los datos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    /**
     * FUNCIONES PRIMARIAS
     */

    private ArrayList<Ciudad> getCiudades(String pid) {

        ArrayList<Ciudad> ciudades = new ArrayList<>();
        CiudadReader mDbHelper = new CiudadReader(this);
        Cursor cursor = mDbHelper.getReadableDatabase().rawQuery("SELECT * FROM " + CiudadEntry.TABLE_NAME + " WHERE " + CiudadEntry.COLUMN_PAIS_ID + " = '" + pid + "'", null);

        try {

            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {

                ciudades.add(new Ciudad(
                        cursor.getInt(cursor.getColumnIndex(CiudadEntry._ID)),
                        cursor.getString(cursor.getColumnIndex(CiudadEntry.COLUMN_NOMBRE)),
                        cursor.getString(cursor.getColumnIndex(CiudadEntry.COLUMN_PAIS_ID)),
                        cursor.getString(cursor.getColumnIndex(CiudadEntry.COLUMN_PAIS)),
                        cursor.getString(cursor.getColumnIndex(CiudadEntry.COLUMN_IMAGEN)),
                        cursor.getString(cursor.getColumnIndex(CiudadEntry.COLUMN_LATITUD)),
                        cursor.getString(cursor.getColumnIndex(CiudadEntry.COLUMN_LONGITUD))
                ));
                cursor.moveToNext();

            }

        } finally {
            cursor.close();
        }

        return ciudades;
    }

    /**
     * FUNCIONES SECUNDARIAS
     */

    private void createCiudadEntry(SQLiteDatabase db, String nombre, String paisID, String pais, String imagen, String latitud, String longitud) {
        ContentValues values = new ContentValues();
        values.put(CiudadEntry.COLUMN_NOMBRE, nombre);
        values.put(CiudadEntry.COLUMN_PAIS_ID, paisID);
        values.put(CiudadEntry.COLUMN_PAIS, pais);
        values.put(CiudadEntry.COLUMN_IMAGEN, imagen);
        values.put(CiudadEntry.COLUMN_LATITUD, latitud);
        values.put(CiudadEntry.COLUMN_LONGITUD, longitud);
        db.insert(CiudadEntry.TABLE_NAME, null, values);
    }

    public void toggleVentana() {
        if (enVentana) {
            findViewById(R.id.ventana).setVisibility(View.GONE);
        } else {
            findViewById(R.id.ventana).setVisibility(View.VISIBLE);
        }
        enVentana = !enVentana;
    }

}
