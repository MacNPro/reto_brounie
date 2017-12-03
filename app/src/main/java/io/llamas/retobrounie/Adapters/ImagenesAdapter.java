package io.llamas.retobrounie.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import io.llamas.retobrounie.Model.Ciudad;
import io.llamas.retobrounie.R;

public class ImagenesAdapter extends RecyclerView.Adapter<ImagenesAdapter.ViewHolder> {

    private List<byte[]> imagenes;
    private Context context;
    private double density;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen;

        ViewHolder(View v) {
            super(v);

            imagen = (ImageView) v.findViewById(R.id.imagen);

        }

    }

    public ImagenesAdapter(Context context, List<byte[]> imagenes) {
        this.imagenes = imagenes;
        this.context = context;
        this.density = context.getResources().getDisplayMetrics().density;
    }

    @Override
    public ImagenesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_imagen, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final byte[] img = imagenes.get(position);

        Glide.with(context).load(img).asBitmap().error(R.drawable.error).into(holder.imagen);
        setMargin(holder.imagen, position);

    }

    @Override
    public int getItemCount() {
        return imagenes.size();
    }

    void print(String text) {
        Log.d(this.getClass().getSimpleName(), text);
    }

    private void setMargin(View view, int position) {

        int space6 = (int) Math.round(6 * density);
        int space12 = (int) Math.round(12 * density);

        boolean esPrimero = position == 0;
        boolean esSegundo = position == 1;
        boolean esUltimo = position == imagenes.size() - 1;
        boolean esPenultimo = position == imagenes.size() - 2;
        boolean esIzquierda = position % 2 == 0;
        boolean esDerecha = position % 2 == 1;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (int) Math.round(180 * density)
        );

        if (esPenultimo && esIzquierda) {
            params.setMargins(space12, space6, space6, space12);
            view.setLayoutParams(params);
        } else if (esUltimo && esIzquierda) {
            params.setMargins(space12, space6, space6, space12);
            view.setLayoutParams(params);
        } else if (esUltimo && esDerecha) {
            params.setMargins(space6, space6, space12, space12);
            view.setLayoutParams(params);
        } else if (esPrimero) {
            params.setMargins(space12, space12, space6, space6);
            view.setLayoutParams(params);
        } else if (esSegundo) {
            params.setMargins(space6, space12, space12, space6);
            view.setLayoutParams(params);
        } else if (esIzquierda) {
            params.setMargins(space12, space6, space6, space6);
            view.setLayoutParams(params);
        } else if (esDerecha) {
            params.setMargins(space6, space6, space12, space6);
            view.setLayoutParams(params);
        }
    }

}


