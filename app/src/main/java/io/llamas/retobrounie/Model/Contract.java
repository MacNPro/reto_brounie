package io.llamas.retobrounie.Model;

import android.provider.BaseColumns;

/**
 * Created by MacNPro on 12/2/17.
 */

public final class Contract {

    private Contract() {
    }

    public static class PaisEntry implements BaseColumns {
        public static final String TABLE_NAME = "paises";
        public static final String _ID = "_id";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_CAPITAL = "capital";
        public static final String COLUMN_IMAGEN = "imagen";
    }

    public static class CiudadEntry implements BaseColumns {
        public static final String TABLE_NAME = "ciudades";
        public static final String _ID = "_id";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_PAIS_ID = "pid";
        public static final String COLUMN_PAIS = "pais";
        public static final String COLUMN_IMAGEN = "imagen";
        public static final String COLUMN_LATITUD = "latitud";
        public static final String COLUMN_LONGITUD = "longitud";
    }

    public static class ImagenEntry implements BaseColumns {
        public static final String TABLE_NAME = "imagenes";
        public static final String _ID = "_id";
        public static final String COLUMN_CIUDAD_ID = "cid";
        public static final String COLUMN_DATA = "data";
    }

}
