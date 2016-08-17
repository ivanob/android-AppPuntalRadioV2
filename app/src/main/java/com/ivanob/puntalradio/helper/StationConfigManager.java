package com.ivanob.puntalradio.helper;

/**
 * Reads the .pls file to provide the configuration to connect to the
 * station
 * @author ivan
 *
 */
public class StationConfigManager {
	
	private static StationConfigManager instance = null;
	
	public static StationConfigManager getInstance() {
		if(instance == null) {
			instance = new StationConfigManager();
		}
		return instance;
	}
	
	private StationConfigManager(){}
	
	public String getStationURL(){
		return "http://5.39.76.68:8036/";
	}

}
