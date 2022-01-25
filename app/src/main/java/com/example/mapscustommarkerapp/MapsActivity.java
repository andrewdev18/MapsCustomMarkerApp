package com.example.mapscustommarkerapp;

import androidx.fragment.app.FragmentActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapscustommarkerapp.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng facEmpresariales = new LatLng(-1.012186, -79.470121);
        LatLng facCiencias = new LatLng(-1.012584, -79.470551);
        LatLng posgrado = new LatLng(-1.012265, -79.468842);
        LatLng agropecuaria = new LatLng(-1.080689,-79.501287);

        mMap.addMarker(new MarkerOptions().position(facCiencias).title("Facultad de Ciencias de la Ingeniería").snippet("Washington Chiriboga;Campus Central;facultadci@uteq.edu.ec;@drawable/fci"));
        mMap.addMarker(new MarkerOptions().position(facEmpresariales).title("Facultad de Ciencias Empresariales").snippet("Sin información;Campus Central;facultadce@uteq.edu.ec;@drawable/fce"));
        mMap.addMarker(new MarkerOptions().position(posgrado).title("Unidad de Posgrado").snippet("Sin información;Campus Central;posgrado@uteq.edu.ec;@drawable/posgrado"));
        mMap.addMarker(new MarkerOptions().position(agropecuaria).title("Facultad de Ciencias Agropecuarias").snippet("Sin información;Finca Experimental La María;decanato_agropecuarias@uteq.edu.ec;@drawable/uteq"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(facCiencias));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));


        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return customInfoWindow(marker);
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
    }

    private View customInfoWindow(Marker marker){
        View row = getLayoutInflater().inflate(R.layout.marker_detail, null);

        String dataSource = marker.getSnippet();
        String[] info = new String[4];
        info = dataSource.split(";");


        TextView facultad = (TextView) row.findViewById(R.id.facultad);
        TextView decano = (TextView) row.findViewById(R.id.decano);
        TextView ubicacion = (TextView) row.findViewById(R.id.ubicacion);
        TextView email = (TextView) row.findViewById(R.id.email);
        ImageView image = (ImageView) row.findViewById(R.id.markerImage);

        facultad.setText(marker.getTitle());
        decano.setText("Decano: " + info[0]);
        ubicacion.setText("Ubicación: " + info[1]);
        email.setText("Email: " + info[2]);

        int imgSourceIndex = getResources().getIdentifier(info[3],null,getPackageName());
        Drawable resource = getResources().getDrawable(imgSourceIndex, getTheme());
        image.setImageDrawable(resource);

        return row;
    }
}