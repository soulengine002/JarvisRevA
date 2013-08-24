package com.example.advancesplash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.view.Menu;
import android.os.*;
import android.graphics.drawable.AnimationDrawable;

public class SplashScreen extends Activity {

	private Thread mSplashThread;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // Splash screen view
	    setContentView(R.layout.splash);
	    
	    final SplashScreen sPlashScreen = this;
	    
	    // The Thread to wait for splash screen events
	    mSplashThread = new Thread(){
	    	@Override
	    	public void run(){
	    			try {
	    				synchronized (this){
	    					//wait given period of time or exit on touch
	    					wait(3000);
	    				}
	    			}catch(InterruptedException E){
	    			}
	    			finish();
	    			
	    			// Run next activity
	    			Intent intent = new Intent();
	    			intent.setClass(sPlashScreen, MainActivity.class);
	    			startActivity(intent);
	    			//stop(); 
	    	}
	    };
	    mSplashThread.start();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent evt)
	{
		if(evt.getAction() == MotionEvent.ACTION_DOWN)
		{
			synchronized(mSplashThread){
				mSplashThread.notifyAll();
			}
		}
		return true;
	}
}
