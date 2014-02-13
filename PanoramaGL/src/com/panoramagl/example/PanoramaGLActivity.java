package com.panoramagl.example;

import com.panoramagl.PLImage;
import com.panoramagl.PLSpherical2Panorama;
import com.panoramagl.PLView;
import com.panoramagl.example.R;
import com.panoramagl.utils.PLUtils;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class PanoramaGLActivity extends PLView
{
	/**init methods*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//Load panorama
		PLSpherical2Panorama panorama = new PLSpherical2Panorama();
		panorama.getCamera().lookAt(30.0f, 90.0f);
        panorama.setImage(new PLImage(PLUtils.getBitmap(this, R.raw.spherical_pano), false));
        this.setPanorama(panorama);
	}
	
	/**
     * This event is fired when root content view is created
     * @param contentView current root content view
     * @return root content view that Activity will use
     */
	@Override
	protected View onContentViewCreated(View contentView)
	{
		//Load layout
		ViewGroup mainView = (ViewGroup)this.getLayoutInflater().inflate(R.layout.activity_main, null);
		//Add 360 view
    	mainView.addView(contentView, 0);
    	//Return root content view
		return super.onContentViewCreated(mainView);
	}
}