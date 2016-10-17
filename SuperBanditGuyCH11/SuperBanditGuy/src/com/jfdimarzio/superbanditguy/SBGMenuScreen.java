package com.jfdimarzio.superbanditguy;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;



public class SBGMenuScreen extends Activity{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

	
	 ImageButton start = (ImageButton)findViewById(R.id.btnStart);
     ImageButton exit = (ImageButton)findViewById(R.id.btnExit);
     
     start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				/** Start Game!!!! */
				Intent game = new Intent(getApplicationContext(),SBGGameMain.class);
				SBGMenuScreen.this.startActivity(game);

			}
     	
     });
     
     exit.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View v) {
				//boolean clean = false;
				//clean = engine.onExit(v);	
				//if (clean)
				//{
					int pid= android.os.Process.myPid();
					android.os.Process.killProcess(pid);
				//}
			}
     	});
	}
}
