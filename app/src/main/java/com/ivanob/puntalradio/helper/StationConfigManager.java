package com.ivanob.puntalradio.helper;

import com.ivanob.puntalradio.model.ConfigBean;

/**
 * Reads the .pls file to provide the configuration to connect to the
 * station
 * @author ivan
 *
 */
public class StationConfigManager {
	private static StationConfigManager instance = null;
	private String urlConnection = "";
	
	public static StationConfigManager getInstance(ConfigBean config) {
		if(instance == null) {
			instance = new StationConfigManager(config);
		}
		return instance;
	}
	
	private StationConfigManager(ConfigBean config){urlConnection = config.getUrlConnection();}
	
	public String getStationURL(){
		return urlConnection;
	}

}
