package io.llamas.retobrounie.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.llamas.retobrounie.Ciudades;
import io.llamas.retobrounie.CustomClasses.RalewaySemiBold;
import io.llamas.retobrounie.Model.Ciudad;
import io.llamas.retobrounie.Perfil;
import io.llamas.retobrounie.R;

public class CiudadesAdapter extends RecyclerView.Adapter<CiudadesAdapter.ViewHolder> {

    private List<Ciudad> ciudades;
    private Context context;
    private double density;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen;
        TextView titulo, subtitulo;
        View view;

        ViewHolder(View v) {
            super(v);

            view = v.findViewById(R.id.view);
            imagen = v.findViewById(R.id.imagen);
            titulo = (RalewaySemiBold) v.findViewById(R.id.titulo);
            subtitulo = (RalewaySemiBold) v.findViewById(R.id.subtitulo);

        }

    }

    public CiudadesAdapter(Context context, List<Ciudad> ciudades) {
        this.ciudades = ciudades;
        this.context = context;
        this.density = context.getResources().getDisplayMetrics().density;
    }

    @Override
    public CiudadesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_tarjeta, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Ciudad c = ciudades.get(position);

        Glide.with(context).load(c.getImagen()).error(R.drawable.error).into(holder.imagen);

        holder.titulo.setText(c.getNombre());
        holder.subtitulo.setText(c.getLatitude() + ", " + c.getLongitude());

        setMargin(holder.view, position);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Perfil.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ciudades.size();
    }

    void print(String text) {
        Log.d(this.getClass().getSimpleName(), text);
    }

    private void setMargin(View view, int position) {

        int space6 = (int) Math.round(6 * density);
        int space12 = (int) Math.round(12 * density);

        boolean esPrimero = position == 0;
        boolean esUltimo = position == ciudades.size() - 1;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        if (esPrimero) {
            params.setMargins(space12, space12, space6, space6);
            view.setLayoutParams(params);
        } else if (esUltimo) {
            params.setMargins(space12, space6, space12, space12);
            view.setLayoutParams(params);
        } else {
            params.setMargins(space12, space6, space12, space6);
            view.setLayoutParams(params);
        }
    }

}


