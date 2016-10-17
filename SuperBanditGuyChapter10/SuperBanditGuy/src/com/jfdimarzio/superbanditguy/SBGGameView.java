package com.jfdimarzio.superbanditguy;

import com.jfdimarzio.superbanditguy.SBGGameRenderer;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class SBGGameView extends GLSurfaceView {
	
	public SBGGameView(Context context) {
		super(context);
		
		setRenderer(new SBGGameRenderer(context));
	}
	

}
