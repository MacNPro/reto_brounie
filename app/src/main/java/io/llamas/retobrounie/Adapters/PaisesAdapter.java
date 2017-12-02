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
import io.llamas.retobrounie.Model.Pais;
import io.llamas.retobrounie.R;

public class PaisesAdapter extends RecyclerView.Adapter<PaisesAdapter.ViewHolder> {

    private List<Pais> paises;
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

    public PaisesAdapter(Context context, List<Pais> paises) {
        this.paises = paises;
        this.context = context;
        this.density = context.getResources().getDisplayMetrics().density;
    }

    @Override
    public PaisesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_tarjeta, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pais p = paises.get(position);

        Glide.with(context).load(p.getImagen()).error(R.drawable.error).into(holder.imagen);

        holder.titulo.setText(p.getNombre());
        holder.subtitulo.setText(p.getCapital());

        setMargin(holder.view, position);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Ciudades.class);
                i.putExtra("ID", p.getId());
                i.putExtra("Nombre", p.getNombre());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return paises.size();
    }

    void print(String text) {
        Log.d(this.getClass().getSimpleName(), text);
    }

    private void setMargin(View view, int position) {

        int space6 = (int) Math.round(6 * density);
        int space12 = (int) Math.round(12 * density);

        boolean esPrimero = position == 0;
        boolean esUltimo = position == paises.size() - 1;

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


