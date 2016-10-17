package com.jfdimarzio.superbanditguy;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;



public class SBGGameRenderer implements Renderer{

	//private SBGSplash splashImage = new SBGSplash();
	//private SBGBackground background1 = new SBGBackground();
	private Context context;
	private long loopStart = 0;
	private long loopEnd = 0;
	private long loopRunTime = 0 ;
	
	private float bgScroll1;
	
	private SuperBanditGuy goodguy = new SuperBanditGuy();
	private SBGTile tiles = new SBGTile();
	
	private SBGTextures textureloader;
	private int[] spriteSheets = new int[2];
	
	public SBGGameRenderer(Context appContext){
		context = appContext;
	}
	
	@Override
 	public void onDrawFrame(GL10 gl) {
		loopStart = System.currentTimeMillis();
		// TODO Auto-generated method stub
		try {
			if (loopRunTime < SBGVars.GAME_THREAD_FPS_SLEEP){
				Thread.sleep(SBGVars.GAME_THREAD_FPS_SLEEP - loopRunTime);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glPushMatrix();
	    gl.glScalef(1f, 1f, 1f);
	    gl.glTranslatef(0f, 0f, 0f);
    
		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glScalef(1.0f, -1.0f, 1.0f);
		
		//SBGVars.totalGameLoops +=1;
	    movePlayer(gl);
	    //drawtiles(gl);
 
	    gl.glPopMatrix();
	    gl.glLoadIdentity();
		
		gl.glEnable(GL10.GL_BLEND); 
	    gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
	    
	    loopEnd = System.currentTimeMillis();
	    loopRunTime = ((loopEnd - loopStart));
	}
	
	private void drawtiles(GL10 gl){
	
	int map[][] = {
			{1,1,1,1,1,1,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
	};
	

	float tileLocY = 0f;
	float tileLocX = 0f;
	for(int x=0; x<10; x++){
		for(int y=0; y<10; y++){
			

			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(.20f, .20f, 1f);
			gl.glTranslatef(tileLocY, tileLocX, 0f);
			
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();			
			
			switch(map[x][y]){
			case 1:
				gl.glTranslatef(.75f,.75f, 0f); 
				break;
			case 0:
				gl.glTranslatef(.75f,1f, 0f); 
				break;
			}
			tiles.draw(gl, spriteSheets, SBGVars.SBG_TILE_PTR);
			tileLocY += .50;
		}
	gl.glPopMatrix();
	gl.glLoadIdentity();
	tileLocY = 0f;
	tileLocX += .50;
	}
	

	
	}

	private void scrollBackground1(GL10 gl, int direction){
		if (bgScroll1 == Float.MAX_VALUE){
			bgScroll1 = 0f;
		}
		

	    gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glPushMatrix();
	    gl.glScalef(1f, 1f, 1f);
	    gl.glTranslatef(0f, 0f, 0f);
    
		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();
	    gl.glTranslatef(bgScroll1,0.0f, 0.0f); 

	   // background1.draw(gl);
	    gl.glPopMatrix();
	    switch(direction)
	    {
	    case SBGVars.PLAYER_MOVE_RIGHT:
	    	bgScroll1 +=  SBGVars.SCROLL_BACKGROUND_1;
	    	break;
	    case SBGVars.PLAYER_MOVE_LEFT:
	    	bgScroll1 -=  SBGVars.SCROLL_BACKGROUND_1;
	    	break;
	    }
	    gl.glLoadIdentity();


	}
	
	private void movePlayer(GL10 gl){
		//background1.draw(gl);
		if(!goodguy.isDead)
		{
			if(SBGVars.totalGameLoops > 15){
				SBGVars.PLAYER_RUN_SPEED += .05f;
			}
			switch(SBGVars.playeraction){
			case SBGVars.PLAYER_MOVE_RIGHT:
			
				SBGVars.currentstandingframe = SBGVars.STANDING_RIGHT;
				
				SBGVars.currentrunaniframe += .25f;
				if (SBGVars.currentrunaniframe > .75f)
				{
					SBGVars.currentrunaniframe = .0f;
				}
				
				if(SBGVars.playercurrentlocation >= 3f)
				{
					scrollBackground1(gl, SBGVars.playeraction);
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 1f);
					gl.glTranslatef(SBGVars.playercurrentlocation, .75f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					gl.glTranslatef(SBGVars.currentrunaniframe,.50f, 0.0f); 
					goodguy.draw(gl,spriteSheets,SBGVars.SBG_RUNNING_PTR);
					gl.glPopMatrix();
					gl.glLoadIdentity();	
					
				}else{
					SBGVars.playercurrentlocation += SBGVars.PLAYER_RUN_SPEED;
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 1f);
					gl.glTranslatef(SBGVars.playercurrentlocation, .75f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					gl.glTranslatef(SBGVars.currentrunaniframe,.50f, 0.0f); 
					goodguy.draw(gl,spriteSheets,SBGVars.SBG_RUNNING_PTR);
					gl.glPopMatrix();
					gl.glLoadIdentity();
					
				}
				
				break;
			case SBGVars.PLAYER_MOVE_LEFT:
				
				SBGVars.currentstandingframe = SBGVars.STANDING_LEFT;
				
				SBGVars.currentrunaniframe += .25f;
				if (SBGVars.currentrunaniframe > .75f)
				{
					SBGVars.currentrunaniframe = .0f;
				}
				
				if(SBGVars.playercurrentlocation <= 2.5f)
				{
					scrollBackground1(gl, SBGVars.playeraction);
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 1f);
					gl.glTranslatef(SBGVars.playercurrentlocation, .75f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					gl.glTranslatef(SBGVars.currentrunaniframe,.75f, 0.0f); 
					goodguy.draw(gl,spriteSheets,SBGVars.SBG_RUNNING_PTR);
					gl.glPopMatrix();
					gl.glLoadIdentity();	
					
				}else{
					SBGVars.playercurrentlocation -= SBGVars.PLAYER_RUN_SPEED;
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 1f);
					gl.glTranslatef(SBGVars.playercurrentlocation, .75f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					gl.glTranslatef(SBGVars.currentrunaniframe,.75f, 0.0f); 
					goodguy.draw(gl,spriteSheets,SBGVars.SBG_RUNNING_PTR);
					gl.glPopMatrix();
					gl.glLoadIdentity();
					
				}
				break;
				
			case SBGVars.PLAYER_STAND:
				SBGVars.totalGameLoops = 0;
				SBGVars.PLAYER_RUN_SPEED = .15f;
				gl.glMatrixMode(GL10.GL_MODELVIEW);
				gl.glLoadIdentity();
				gl.glPushMatrix();
				gl.glScalef(.15f, .15f, 1f);
				gl.glTranslatef(SBGVars.playercurrentlocation, .75f, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(SBGVars.currentstandingframe,.25f, 0.0f); 
				goodguy.draw(gl,spriteSheets,SBGVars.SBG_RUNNING_PTR);
				gl.glPopMatrix();
				gl.glLoadIdentity();
				break;
			}
		}
	}
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		gl.glViewport(0, 0, width,height);
		 
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		
		gl.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		
		textureloader = new SBGTextures(gl);
		spriteSheets = textureloader.loadTexture(gl, SBGVars.SBG_RUNNING_SSHEET, SBGVars.context, SBGVars.SBG_RUNNING_PTR);
		spriteSheets = textureloader.loadTexture(gl, SBGVars.SBG_TILE_SSHEET, SBGVars.context, SBGVars.SBG_TILE_PTR);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		//splashImage.loadTexture(gl, R.drawable.titlescreengl, context);
		
		//background1.loadTexture(gl, R.drawable.titlescreengl, context);
	} 
}