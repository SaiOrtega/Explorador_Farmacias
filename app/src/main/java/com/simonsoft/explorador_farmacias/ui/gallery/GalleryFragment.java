package com.simonsoft.explorador_farmacias.ui.gallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Gap;
import com.simonsoft.explorador_farmacias.R;
import com.simonsoft.explorador_farmacias.databinding.FragmentGalleryBinding;
import com.simonsoft.explorador_farmacias.ui.home.HomeViewModel;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
       // galleryViewModel.obtenerUltimaUbicacion();
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        galleryViewModel.getMMapa().observe(getViewLifecycleOwner(), new Observer<GalleryViewModel.MapaActual>() {
            @Override
            public void onChanged(GalleryViewModel.MapaActual mapaActual) {
                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

                mapFragment.getMapAsync(mapaActual);

            }
        });
        galleryViewModel.obtenerUltimaUbicacion();


    return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}