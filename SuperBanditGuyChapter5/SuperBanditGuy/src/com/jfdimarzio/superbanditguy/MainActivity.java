package com.jfdimarzio.superbanditguy;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;


public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SBGVars.display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		
        setContentView(R.layout.activity_main);
        SBGVars.context = this;
        new Handler().postDelayed(new Thread() {
    		@Override
    		public void run() {
    			setContentView(R.layout.main_menu);
               Intent mainMenu = new Intent(MainActivity.this, SBGMenuScreen.class);
               MainActivity.this.startActivity(mainMenu);
    		   MainActivity.this.finish();
               overridePendingTransition(R.layout.fadein,R.layout.fadeout);
    		}
    	}, SBGVars.GAME_THREAD_DELAY);
	}

}
