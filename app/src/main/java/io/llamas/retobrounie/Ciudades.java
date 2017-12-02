package io.llamas.retobrounie;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import io.llamas.retobrounie.Adapters.CiudadesAdapter;
import io.llamas.retobrounie.Adapters.PaisesAdapter;
import io.llamas.retobrounie.CustomClasses.RalewaySemiBold;
import io.llamas.retobrounie.Model.Ciudad;
import io.llamas.retobrounie.Model.Pais;

public class Ciudades extends Activity {

    RecyclerView recyclerView;

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

        TextView ttNombre = (RalewaySemiBold) findViewById(R.id.nombre);
        ttNombre.setText(getIntent().getStringExtra("Nombre"));

        ArrayList<Ciudad> ciudades = new ArrayList<>();

        ciudades.add(new Ciudad("BERLIN", "Berlin", "DEU", "Alemania", "http://www.telegraph.co.uk/content/dam/Travel/Cruise/river-spree-berlin-xlarge.jpg", "52.513200", "13.413721"));

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CiudadesAdapter(this, ciudades));

    }

    public void initViews() {

        recyclerView = findViewById(R.id.recyclerView);

    }

    public void initClickListeners() {

        findViewById(R.id.regresar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * FUNCIONES PRIMARIAS
     */

    /**
     * FUNCIONES SECUNDARIAS
     */
}
