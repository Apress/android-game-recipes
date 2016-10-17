package com.jfdimarzio.superbanditguy;



import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class SBGGameMain extends Activity {
private GestureDetector gd;	
private SBGGameView gameView;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new SBGGameView(this);
        setContentView(gameView);
        gd = new GestureDetector(this,gestureListener);
    }
    @Override
    protected void onResume() {
       super.onResume();
       gameView.onResume();
    }

    @Override
    protected void onPause() {
       super.onPause();
       gameView.onPause();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	float x = event.getX();
        float y = event.getY();
        DisplayMetrics outMetrics = new DisplayMetrics();
        
        SBGVars.display.getMetrics(outMetrics);
        
        int height = outMetrics.heightPixels / 4;

        int playableArea = outMetrics.heightPixels - height;
        if (y > playableArea){
        	switch (event.getAction()){
        	case MotionEvent.ACTION_DOWN:
        		if(x < outMetrics.widthPixels / 2){
        			SBGVars.playeraction = SBGVars.PLAYER_MOVE_LEFT;
        		}else{
        			SBGVars.playeraction = SBGVars.PLAYER_MOVE_RIGHT;
        		}
        		break;
        	case MotionEvent.ACTION_UP:
        		SBGVars.playeraction = SBGVars.PLAYER_STAND;
        		break;
        	}
        }
        else {
        		return gd.onTouchEvent(event);
        }
        	
        
        return false;
    }
    
    GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener(){
    	@Override
    	public boolean onDown(MotionEvent arg0) {
    		// TODO Auto-generated method stub
    		return false;
    	}
    	
    	@Override
    	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
    			float velocityY) {
    		
    		float leftMotion = e1.getX() - e2.getX();
    		float upMotion = e1.getY() - e2.getY();
    		
    		float rightMotion = e2.getX() - e1.getX();
    		float downMotion = e2.getY() - e1.getY();
    		
    		if((leftMotion == Math.max(leftMotion, rightMotion)) && (leftMotion > Math.max(downMotion, upMotion)) )
    		{
    		}
    		
    		if((rightMotion == Math.max(leftMotion, rightMotion)) && rightMotion > Math.max(downMotion, upMotion) )
    		{
    		}
    		if((upMotion == Math.max(upMotion, downMotion)) && (upMotion > Math.max(leftMotion, rightMotion)) )
    		{
    		}
    		
    		if((downMotion == Math.max(upMotion, downMotion)) && (downMotion > Math.max(leftMotion, rightMotion)) )
    		{
    		}
    		return false;
    	}
    	@Override
    	public void onLongPress(MotionEvent e) {
    		// TODO Auto-generated method stub
    		
    	}
    	@Override
    	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
    			float distanceY) {
    		// TODO Auto-generated method stub
    		return false;
    	}
    	@Override
    	public void onShowPress(MotionEvent e) {
    		// TODO Auto-generated method stub
    		
    	}
    	@Override
    	public boolean onSingleTapUp(MotionEvent e) {
    		// TODO Auto-generated method stub
    		return false;
    	}

    	
    };
}
