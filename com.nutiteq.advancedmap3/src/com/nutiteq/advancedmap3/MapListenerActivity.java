package com.nutiteq.advancedmap3;

import android.os.Bundle;

import com.nutiteq.advancedmap3.listener.MyMapEventListener;
import com.nutiteq.core.MapRange;
import com.nutiteq.datasources.LocalVectorDataSource;
import com.nutiteq.layers.VectorLayer;

/**
 * A sample displaying how to set custom MapListener
 * to detect clicks on map and on map vector elements.
 * Actual vector elements are added in Overlays2DActivity, as this class extends it.
 */
public class MapListenerActivity extends Overlays2DActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        // Overlays2DActivity adds 2D vector elements to map  
        super.onCreate(savedInstanceState);
        
        // 1. Initialize a local vector data source and layer for click Balloons
        LocalVectorDataSource vectorDataSource = new LocalVectorDataSource(baseProjection);
        // Initialize a vector layer with the previous data source
        VectorLayer vectorLayer = new VectorLayer(vectorDataSource);
        // Add the previous vector layer to the map
        mapView.getLayers().add(vectorLayer);
        // Set visible zoom range for the vector layer
        vectorLayer.setVisibleZoomRange(new MapRange(10, 24));
        
        // 2. Create and set a map event listener, 
        // it needs the data source for balloons
        mapView.setMapEventListener(new MyMapEventListener(mapView, vectorDataSource));
    }
    
    @Override
	public void onDestroy() {
    	mapView.setMapEventListener(null);
    	super.onDestroy();
    }
}
