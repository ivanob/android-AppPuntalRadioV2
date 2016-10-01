package com.ivanob.puntalradio.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ivanob.puntalradio.R;
import com.ivanob.puntalradio.R.xml;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.JsonReader;
import android.util.JsonToken;

public class RadioProgrammingManager {

	private static RadioProgrammingManager instance = null;
	private List<RadioProgram> listPrograms;
	private Resources res;
	private Context ctx;
	
	public int getNumPrograms(){
		return listPrograms.size();
	}
	
	public RadioProgram getProgram(int pos){
		return listPrograms.get(pos);
	}
	
	public static RadioProgrammingManager getInstance(Context ctx) throws IOException {
		if(instance == null) {
			instance = new RadioProgrammingManager(ctx);
		}
		return instance;
	}
	
	private RadioProgrammingManager(Context ctx) throws IOException {
		this.ctx = ctx;
		RadioProgram r = new RadioProgram();
		InputStream is = ctx.getAssets().open("programas.json");
		try {
			readJsonStream(is);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String loadJSONFromAsset() {
		String json = null;
		try {
			InputStream is = ctx.getAssets().open("programas.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}


	public List<RadioProgram> readJsonStream(InputStream in) throws IOException, JSONException {
		listPrograms = new ArrayList<RadioProgram>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8);
		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null)
		{
			sb.append(line + "\n");
		}
		String result = sb.toString();
		JSONObject jObject = new JSONObject(result);
		JSONArray jArray = jObject.getJSONArray("programas");
		for (int i=0; i < jArray.length(); i++)
		{
			try {
				JSONObject oneObject = jArray.getJSONObject(i);
				// Pulling items from the array
				String nombre = oneObject.getString("nombre");
				String descripcion = oneObject.getString("descripcion");
				String strLogo = oneObject.getString("logo");
				strLogo = strLogo.substring(0, strLogo.lastIndexOf('.'));
				int logoID = getResId(strLogo, "drawable", ctx);
				RadioProgram prog = new RadioProgram();
				prog.setNombre(nombre);
				prog.setDescripcion(descripcion);
				prog.setIdLogo(logoID);
				listPrograms.add(prog);
			} catch (JSONException e) {
				// Oops
			}
		}
		return listPrograms;
	}

	private static int getResId(String variableName, String type, Context ctx) {
		return ctx.getResources().getIdentifier(variableName, type, ctx.getPackageName());
	}
}
