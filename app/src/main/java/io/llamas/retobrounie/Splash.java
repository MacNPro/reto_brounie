package io.llamas.retobrounie;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import io.llamas.retobrounie.Data.CiudadReader;
import io.llamas.retobrounie.Data.PaisReader;
import io.llamas.retobrounie.Model.Contract.CiudadEntry;
import io.llamas.retobrounie.Model.Contract.PaisEntry;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        llenarTablaPaises();
        llenarTablaCiudades();

        startActivity(new Intent(this, Inicio.class));
        finish();

    }

    private void llenarTablaPaises() {

        PaisReader mDbHelper = new PaisReader(this);
        Cursor cursor = mDbHelper.getReadableDatabase().rawQuery("SELECT * FROM " + PaisEntry.TABLE_NAME, null);

        try {
            if (cursor.getCount() == 0) {

                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                createPaisEntry(db, "DEU", "Alemania", "Berlín", "http://www.telegraph.co.uk/content/dam/Travel/Cruise/river-spree-berlin-xlarge.jpg");
                createPaisEntry(db, "FRA", "Francia", "Paris", "https://www.st-christophers.co.uk/__data/assets/image/0005/441644/iStock-604371970.jpg");
                createPaisEntry(db, "MEX", "México", "Ciudad de México", "http://giratur.net/archiv/2014/05/metropolitan-cathedral-zocalo-mexico-city.jpg");
                createPaisEntry(db, "NZL", "Nueva Zelanda", "Wellington", "https://media.licdn.com/media/AAEAAQAAAAAAAAisAAAAJDAzNjY2ODMyLTI4MTYtNDM5NS05MGE5LTNkNDVlZDI2NzExZg.jpg");


            }
        } finally {
            cursor.close();
        }

    }

    private void llenarTablaCiudades() {

        CiudadReader mDbHelper = new CiudadReader(this);
        Cursor cursor = mDbHelper.getReadableDatabase().rawQuery("SELECT * FROM " + CiudadEntry.TABLE_NAME, null);

        try {
            if (cursor.getCount() == 0) {

                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                createCiudadEntry(db, "Berlín", "DEU", "Alemania", "http://www.telegraph.co.uk/content/dam/Travel/Cruise/river-spree-berlin-xlarge.jpg", "52.513200", "13.413721");
                createCiudadEntry(db, "Munich", "DEU", "Alemania", "http://res.muenchen-p.de/c_fit,h_650,w_980,fl_progressive,q_75/.imaging/stk/responsive/galleryLarge/dms/shutterstock/neues-rathaus-marienplatz/document/neues-rathaus-marienplatz.jpg", "48.133939", "11.584759");
                createCiudadEntry(db, "Frankfurt", "DEU", "Alemania", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Frankfurt_Skyline_%2816259801511%29.jpg/300px-Frankfurt_Skyline_%2816259801511%29.jpg", "50.109900", "8.677756");

                createCiudadEntry(db, "Paris", "FRA", "Francia", "https://en.parisinfo.com/var/otcp/sites/images/node_43/node_51/node_233/visuel-carrousel-dossier-ou-sortir-le-soir-a-paris-740x380-c-dr/16967596-1-fre-FR/Visuel-carrousel-dossier-Ou-sortir-le-soir-a-Paris-740x380-C-DR.jpg", "48.855311", "2.351273");
                createCiudadEntry(db, "Marseille", "FRA", "Francia", "https://www.letsflycheaper.com/images/travelguides/marseille06.jpg?x81763", "43.302386", "5.413100");
                createCiudadEntry(db, "Lyon", "FRA", "Francia", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Frankfurt_Skyline_%2816259801511%29.jpg/300px-Frankfurt_Skyline_%2816259801511%29.jpg", "45.754490", "4.838526");

                createCiudadEntry(db, "Ciudad de Mexico", "MEX", "México", "http://giratur.net/archiv/2014/05/metropolitan-cathedral-zocalo-mexico-city.jpg", "19.361346", "-99.149908");
                createCiudadEntry(db, "Querétaro", "MEX", "México", "http://amqueretaro.com/wp-content/uploads/2017/04/queretaro-capacitado-en-transparencia.jpg", "20.607303", "-100.406100");
                createCiudadEntry(db, "León", "MEX", "México", "https://portalexport.files.wordpress.com/2014/09/guanajuato_city_093950.jpg", "21.125742", "-101.671164");

                createCiudadEntry(db, "Wellington", "NZL", "Nueva Zelanda", "https://media.licdn.com/media/AAEAAQAAAAAAAAisAAAAJDAzNjY2ODMyLTI4MTYtNDM5NS05MGE5LTNkNDVlZDI2NzExZg.jpg", "-41.286546", "174.773436");
                createCiudadEntry(db, "Auckland", "NZL", "Nueva Zelanda", "http://thecityfix.com/files/2014/09/smart-city-bacelona-640x480.jpg", "-36.895570", "174.800921");
                createCiudadEntry(db, "Dunedin", "NZL", "Nueva Zelanda", "http://www.salvationarmy.org.nz/sites/default/files/styles/hero_image/public/city/image/20130709Dunedinareapic.jpg", "-45.887219", "170.501848");

            }
        } finally {
            cursor.close();
        }

    }

    private void createPaisEntry(SQLiteDatabase db, String id, String nombre, String capital, String imagen) {
        ContentValues values = new ContentValues();
        values.put(PaisEntry._ID, id);
        values.put(PaisEntry.COLUMN_NOMBRE, nombre);
        values.put(PaisEntry.COLUMN_CAPITAL, capital);
        values.put(PaisEntry.COLUMN_IMAGEN, imagen);
        db.insert(PaisEntry.TABLE_NAME, null, values);
    }

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

}
