package io.llamas.retobrounie;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.llamas.retobrounie.Adapters.ImagenesAdapter;
import io.llamas.retobrounie.CustomClasses.RalewaySemiBold;
import io.llamas.retobrounie.Data.ImagenReader;
import io.llamas.retobrounie.Model.Ciudad;
import io.llamas.retobrounie.Model.Contract.ImagenEntry;

public class Perfil extends Activity {

    RecyclerView recyclerView;

    TextView ttCiudad, ttPais;
    Ciudad ciudad;

    public static final int REQUEST_TAKE_PHOTO = 0;
    public static final int MEDIA_TYPE_IMAGE = 4;
    public static final int MEDIA_TYPE_VIDEO = 5;

    private Uri mMediaUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        initViews();
        initActivity();
        initClickListeners();

    }

    /**
     * FUNCIONES INIT
     */

    public void initActivity() {

        ciudad = (Ciudad) getIntent().getSerializableExtra("Ciudad");

        ttCiudad.setText(ciudad.getNombre());
        ttPais.setText(ciudad.getPais());

        Glide.with(this)
                .load(ciudad.getImagen())
                .error(R.drawable.error)
                .into((ImageView) findViewById(R.id.imagen));

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ImagenesAdapter(this, getFotos(ciudad.getId())));

    }

    public void initViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ttCiudad = (RalewaySemiBold) findViewById(R.id.ttCiudad);
        ttPais = (RalewaySemiBold) findViewById(R.id.ttPais);

    }

    public void initClickListeners() {

        findViewById(R.id.regresar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.abrirEnMaps).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + ciudad.getLatitude() + "," + ciudad.getLongitude() + "?z=0(" + ciudad.getNombre() + ")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        findViewById(R.id.agregarFoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMediaUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                if (mMediaUri == null) {
                    Toast.makeText(Perfil.this, "No se pudo accesar el almacenamiento externo", Toast.LENGTH_LONG).show();
                } else {
                    Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri);
                    startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
                }

            }
        });

    }

    /**
     * FUNCIONES PRIMARIAS
     */

    private ArrayList<byte[]> getFotos(int cid) {

        ArrayList<byte[]> imagenes = new ArrayList<>();
        ImagenReader mDbHelper = new ImagenReader(this);
        Cursor cursor = mDbHelper.getReadableDatabase().rawQuery("SELECT * FROM " + ImagenEntry.TABLE_NAME + " WHERE " + ImagenEntry.COLUMN_CIUDAD_ID + " = " + cid, null);

        try {

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {

                    imagenes.add(cursor.getBlob(cursor.getColumnIndex(ImagenEntry.COLUMN_DATA)));

                    if (i < cursor.getCount() - 1)
                        cursor.moveToNext();

                }
            }

        } finally {
            cursor.close();
        }

        return imagenes;
    }

    /**
     * FUNCIONES SECUNDARIAS
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {

                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mMediaUri);
                    byte[] bytes = getBytes(bitmap);

                    ImagenReader mDbHelper = new ImagenReader(Perfil.this);
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();

                    createImagenEntry(db, ciudad.getId(), bytes);
                    recyclerView.setAdapter(new ImagenesAdapter(this, getFotos(ciudad.getId())));


                } catch (IOException e) {

                }

            }
        } else if (resultCode != RESULT_CANCELED) {
            Toast.makeText(this, "Sorry, there was an error!", Toast.LENGTH_LONG).show();
        }
    }

    private void createImagenEntry(SQLiteDatabase db, int cid, byte[] bytes) {
        ContentValues values = new ContentValues();
        values.put(ImagenEntry._ID, System.currentTimeMillis());
        values.put(ImagenEntry.COLUMN_CIUDAD_ID, cid);
        values.put(ImagenEntry.COLUMN_DATA, bytes);
        db.insert(ImagenEntry.TABLE_NAME, null, values);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    private Uri getOutputMediaFileUri(int mediaType) {
        // check for external storage
        if (isExternalStorageAvailable()) {
            // get the URI

            // 1. Get the external storage directory
            File mediaStorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

            // 2. Create a unique file name
            String fileName = "";
            String fileType = "";
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            if (mediaType == MEDIA_TYPE_IMAGE) {
                fileName = "IMG_" + timeStamp;
                fileType = ".jpg";
            } else if (mediaType == MEDIA_TYPE_VIDEO) {
                fileName = "VID_" + timeStamp;
                fileType = ".mp4";
            } else {
                return null;
            }

            // 3. Create the file
            File mediaFile;
            try {
                mediaFile = File.createTempFile(fileName, fileType, mediaStorageDir);
                Log.i(this.getClass().getSimpleName(), "File: " + Uri.fromFile(mediaFile));

                // 4. Return the file's URI
                return Uri.fromFile(mediaFile);
            } catch (IOException e) {
                Log.e(this.getClass().getSimpleName(), "Error creating file: " +
                        mediaStorageDir.getAbsolutePath() + fileName + fileType);
            }
        }

        // something went wrong
        return null;

    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        } else {
            return false;
        }
    }

    private void print(String text) {
        Log.d(this.getClass().getSimpleName(), text);
    }

}
