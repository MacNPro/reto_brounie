package io.llamas.retobrounie;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import io.llamas.retobrounie.Adapters.PaisesAdapter;
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

        ArrayList<Pais> paises = new ArrayList<>();

        paises.add(new Pais("DEU", "Alemania", "Berlín", "http://www.telegraph.co.uk/content/dam/Travel/Cruise/river-spree-berlin-xlarge.jpg"));
        paises.add(new Pais("FRA", "Francia", "Paris", "https://en.parisinfo.com/var/otcp/sites/images/node_43/node_51/node_233/visuel-carrousel-dossier-ou-sortir-le-soir-a-paris-740x380-c-dr/16967596-1-fre-FR/Visuel-carrousel-dossier-Ou-sortir-le-soir-a-Paris-740x380-C-DR.jpg"));
        paises.add(new Pais("MEX", "México", "Ciudad de México", "http://giratur.net/archiv/2014/05/metropolitan-cathedral-zocalo-mexico-city.jpg"));
        paises.add(new Pais("NZL", "Nueva Zelanda", "Wellington", "https://media.licdn.com/media/AAEAAQAAAAAAAAisAAAAJDAzNjY2ODMyLTI4MTYtNDM5NS05MGE5LTNkNDVlZDI2NzExZg.jpg"));

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

    /**
     * FUNCIONES SECUNDARIAS
     */

}
