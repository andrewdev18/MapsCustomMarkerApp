package com.example.mapscustommarkerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    Context context;

    public CustomInfoWindowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View row = LayoutInflater.from(context).inflate(R.layout.marker_detail, null);

        String dataSource = marker.getSnippet();
        String[] info = new String[3];
        info = dataSource.split(";");


        TextView facultad = (TextView) row.findViewById(R.id.facultad);
        TextView decano = (TextView) row.findViewById(R.id.decano);
        TextView ubicacion = (TextView) row.findViewById(R.id.ubicacion);
        TextView email = (TextView) row.findViewById(R.id.email);

        facultad.setText(marker.getTitle());
        decano.setText("Decano: " + info[0]);
        ubicacion.setText("Ubicación: " + info[1]);
        email.setText("Email: " + info[2]);

        return row;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
