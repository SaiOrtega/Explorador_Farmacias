package com.simonsoft.explorador_farmacias.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simonsoft.explorador_farmacias.Modelo.Farmacia;
import com.simonsoft.explorador_farmacias.R;

import java.util.List;

public class AdapterFarmacia extends RecyclerView.Adapter<AdapterFarmacia.ViewHolder> implements View.OnClickListener {
    private List<Farmacia> farmacias;
    private Context context;
    private LayoutInflater li;
    private View.OnClickListener listener;

    public AdapterFarmacia(List<Farmacia> farmacias,Context context, LayoutInflater inflater) {
      this.li = LayoutInflater.from(context);
      this.farmacias = farmacias;
    }

    @NonNull
    @Override
    public AdapterFarmacia.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = li.inflate(R.layout.lista_farmacias,parent,false);
       view.setOnClickListener(this);


        return new ViewHolder(view);
    }

    public void setOnClickLiseter(View.OnClickListener listener){
        this.listener = listener;


    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFarmacia.ViewHolder holder, int position) {
    String nombres = farmacias.get(position).getNombre();
    //String direccion = farmacias.get(position).getDireccion();
    String horario = farmacias.get(position).getHorario();
    //int imagen = farmacias.get(position).getImageId();

    holder.nombres.setText(nombres);
    //holder.direccion.setText(direccion);
    holder.horario.setText(horario);
    //holder.imagen.setImageResource(imagen);
    }

    @Override
    public int getItemCount() {
        return farmacias.size();
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombres,direccion,horario;
        //ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombres= itemView.findViewById(R.id.nombreFar);
            //direccion =itemView.findViewById(R.id.direccion);
            horario = itemView.findViewById(R.id.horario);
           // imagen = itemView.findViewById(R.id.imageFarmacia);

        }
    }
}
