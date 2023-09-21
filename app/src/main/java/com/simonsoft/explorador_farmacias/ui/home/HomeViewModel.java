package com.simonsoft.explorador_farmacias.ui.home;

import android.app.Application;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.simonsoft.explorador_farmacias.Adaptador.AdapterFarmacia;
import com.simonsoft.explorador_farmacias.Modelo.Farmacia;
import com.simonsoft.explorador_farmacias.R;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Farmacia>> listaMutable;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }
    public LiveData<List<Farmacia>> getListaMUtable(){
        if(listaMutable == null){
            listaMutable = new MutableLiveData<>();
        }
        return listaMutable;
    }

    public void armarLista(){

        List<Farmacia> listaFarmacias = new ArrayList<>();
        listaFarmacias.add(new Farmacia("Farmacia Quintana","pringles 1080","08:00", R.drawable.quintana));
        listaFarmacias.add(new Farmacia("Farmacity","junin 456","08:00", R.drawable.farmacity));
        listaFarmacias.add(new Farmacia("Los Alamos","Rivadavia 657","08:00", R.drawable.farmacity));
        listaMutable.setValue(listaFarmacias);
    }

}