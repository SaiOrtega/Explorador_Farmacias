package com.simonsoft.explorador_farmacias.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simonsoft.explorador_farmacias.Adaptador.AdapterFarmacia;
import com.simonsoft.explorador_farmacias.Modelo.Farmacia;
import com.simonsoft.explorador_farmacias.R;
import com.simonsoft.explorador_farmacias.databinding.FragmentHomeBinding;



import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    AdapterFarmacia adapterFarmacia;
    RecyclerView recyclerView;

    private FragmentHomeBinding binding;
    ArrayList<Farmacia> listaFarmacias;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
         View view  = inflater.inflate(R.layout.fragment_home,container,false);
         recyclerView = view.findViewById(R.id.recycleVieFarmacia);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

       homeViewModel.getListaMUtable().observe(getViewLifecycleOwner(), new Observer<List<Farmacia>>() {
           @Override
           public void onChanged(List<Farmacia> farmacias) {
               recyclerView.findViewById(R.id.recycleVieFarmacia);

               AdapterFarmacia iad = new AdapterFarmacia(farmacias, requireContext(),getLayoutInflater());
               recyclerView.setAdapter(iad);
           }
       });

        homeViewModel.armarLista();


        return view;
    }



}