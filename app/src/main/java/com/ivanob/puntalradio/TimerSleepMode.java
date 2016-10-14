package com.ivanob.puntalradio;

import com.ivanob.puntalradio.MainActivity;

import java.util.TimerTask;

public class TimerSleepMode extends TimerTask {

	MainActivity main;
	
	public TimerSleepMode(MainActivity m){
		main = m;
	}
	
	public void run() {
		main.closeApp();
	}

}
