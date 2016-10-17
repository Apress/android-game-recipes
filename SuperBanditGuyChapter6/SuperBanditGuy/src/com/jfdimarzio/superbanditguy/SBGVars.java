package com.jfdimarzio.superbanditguy;

import android.content.Context;
import android.view.Display;

public class SBGVars {
	/*Constants that will be used in the game*/
	
	public static final int GAME_THREAD_DELAY = 4000;
	public static final int SBG_RUNNING_SSHEET = R.drawable.sbgrunning;
	public static final int SBG_TILE_SSHEET = R.drawable.tiles;
	public static final int SBG_RUNNING_PTR = 1;
	public static final int SBG_TILE_PTR = 2;
	public static final int PLAYER_MOVE_LEFT = 1;
	public static final int PLAYER_STAND = 0;
	public static final int PLAYER_MOVE_RIGHT = 2;
	public static final int GAME_THREAD_FPS_SLEEP = (1000/30);	
	public static float PLAYER_RUN_SPEED = .15f;
	public static final float STANDING_LEFT = .75f;
	public static final float STANDING_RIGHT = .0f;
	public static final int BACKGROUND_STARFIELD = R.drawable.starfield;
	public static float SCROLL_BACKGROUND_2  = .004f;
	public static float SCROLL_BACKGROUND_1  = .002f;
	public static int playeraction = 0;
	public static int totalGameLoops = 0;
	public static float playercurrentlocation = .75f;
	public static float currentrunaniframe = 0f;
	public static float currentstandingframe = 0f;
	public static Display display;
	public static Context context;
}
