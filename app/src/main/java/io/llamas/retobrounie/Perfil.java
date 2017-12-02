package io.llamas.retobrounie;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import java.util.ArrayList;

import io.llamas.retobrounie.Adapters.PaisesAdapter;
import io.llamas.retobrounie.Model.Pais;

public class Perfil extends Activity {

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

    }

    public void initViews() {

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
