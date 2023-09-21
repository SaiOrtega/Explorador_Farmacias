package com.simonsoft.explorador_farmacias.ui.gallery;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class GalleryViewModel extends AndroidViewModel {
    private Context context;
    private FusedLocationProviderClient fused;
    private MutableLiveData<MapaActual> mMapa;
    private static final LatLng SANLUIS=new LatLng(-33.280576,-66.332482);
    private static final LatLng QUINTANA=new LatLng(-33.29935,-66.34739);
    private static final LatLng LOSALAMOS=new LatLng(-33.30266,-66.33618);
    private static final LatLng FARMACITY=new LatLng(-33.30372,-66.33564);


    private LatLng localizacion;

    public GalleryViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        fused = LocationServices.getFusedLocationProviderClient(context);
    }

    public LiveData<MapaActual> getMMapa(){
        if(mMapa==null){
            mMapa=new MutableLiveData<>();
        }
        return mMapa;
    }


    public void obtenerUltimaUbicacion() {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Task<Location> tarea = fused.getLastLocation();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            tarea.addOnSuccessListener(getApplication().getMainExecutor(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                  if(location !=null ){
                      localizacion = new LatLng(location.getLatitude(), location.getLongitude());

                      obtenerMapa();
                      Log.d("Salida", "pasó");
                  }
                }
            });
        }else{

            localizacion=new LatLng(-33.280576,-66.332482);
            obtenerMapa();
        }


    }

    public class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            if(localizacion != null) {
                googleMap.addMarker(new MarkerOptions().position(localizacion).title("San Luis"));
                googleMap.addMarker(new MarkerOptions().position(QUINTANA).title("FARMACIA QUINTANA"));
                googleMap.addMarker(new MarkerOptions().position(LOSALAMOS).title("FARMACIA LOS ALAMOS"));
                googleMap.addMarker(new MarkerOptions().position(FARMACITY).title("FARMACITY"));

                CameraPosition camPos = new CameraPosition.Builder()
                        .target(localizacion)
                        .zoom(19)
                        .bearing(45)
                        .tilt(70)
                        .build();
                CameraUpdate update = CameraUpdateFactory.newCameraPosition(camPos);
                googleMap.animateCamera(update);
            }else{
                Toast.makeText(context, "ubicacion nula", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void obtenerMapa(){
        MapaActual ma=new MapaActual();
        mMapa.setValue(ma);
    }
}











