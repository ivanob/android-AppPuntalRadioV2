package com.ivanob.puntalradio.helper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ivanob.puntalradio.R;
import com.ivanob.puntalradio.model.RadioProgram;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;

public class RadioProgrammingManager {

	private static RadioProgrammingManager instance = null;
	private LinkedList<RadioProgram> listPrograms;
	private Resources res;
	
	public int getNumPrograms(){
		return listPrograms.size();
	}
	
	public RadioProgram getProgram(int pos){
		return listPrograms.get(pos);
	}
	
	public static RadioProgrammingManager getInstance(Resources res) {
		if(instance == null) {
			instance = new RadioProgrammingManager(res);
		}
		return instance;
	}
	
	private RadioProgrammingManager(Resources res){
		this.res = res;
		listPrograms = new LinkedList<RadioProgram>();
		readXMLProgramming();
	}

	private void readXMLProgramming() {
		try {
			XmlResourceParser xpp=res.getXml(R.xml.programacion);
		    
		    parsePrograms(xpp);

	    } catch (Exception e) {
			e.printStackTrace();
	    }
	}

	private LinkedList<RadioProgram> parsePrograms(XmlResourceParser xpp) throws XmlPullParserException, IOException {
		int eventType = xpp.getEventType();
		String text=null;
		RadioProgram program = new RadioProgram();
		while(eventType != XmlPullParser.END_DOCUMENT){
			String tagname = xpp.getName();
			switch(eventType){	
				case XmlPullParser.START_TAG:
					if(tagname.equalsIgnoreCase("programa")){
						program = new RadioProgram();
						String progName=xpp.getAttributeValue(null, "nombre");
						program.setNombre(progName);
					}
					break;
				case XmlPullParser.TEXT:
                    text = xpp.getText();
                    break;
				case XmlPullParser.END_TAG:
					if (tagname.equalsIgnoreCase("programa")) {
						listPrograms.add(program);
                    }else if (tagname.equalsIgnoreCase("fichero")) {
                        String s = text;
                    	program.setFicheroDesc(s);
                    }else if (tagname.equalsIgnoreCase("podcast")) {
                        String s = text;
                    	program.addMediaURL("podcast", s);
                    }else if (tagname.equalsIgnoreCase("facebook")) {
                    	String s = text;
                    	program.addMediaURL("facebook", s);
                    }else if (tagname.equalsIgnoreCase("blog")) {
                    	String s = text;
                    	program.addMediaURL("blog", s);
                    }  
					break;
					
			}
			eventType = xpp.next();
		}
		return null;
	}
	
	public void loadProgramDetails(RadioProgram prog, Context ctx) throws XmlPullParserException, IOException{
		if(prog.getDescripcion()!=null){ //The info is already loaded
			return;
		}
		String nombreProg = prog.getFicheroDesc();
		String nombreSinExtension = nombreProg.substring(0, nombreProg.lastIndexOf('.'));
		int idProg = getResId(nombreSinExtension, "xml", ctx);
		XmlResourceParser xpp=res.getXml(idProg);
		int eventType = xpp.getEventType();
		String text=null;
		while(eventType != XmlPullParser.END_DOCUMENT){
			String tagname = xpp.getName();
			switch(eventType){	
				case XmlPullParser.START_TAG:
					if(tagname.equalsIgnoreCase("programa")){
					}
					break;
				case XmlPullParser.TEXT:
                    text = xpp.getText();
                    break;
				case XmlPullParser.END_TAG:
					if (tagname.equalsIgnoreCase("programa")) {
                    }else if (tagname.equalsIgnoreCase("nombre")) {
                        String s = text;
                    	prog.setNombreLargo(s);
                    }else if (tagname.equalsIgnoreCase("logo")) {
                    	String s = text;
                    	s = s.substring(0, s.lastIndexOf('.'));
                    	int idLogo = getResId(s, "drawable", ctx);
                    	prog.setIdLogo(idLogo);
                    }else if (tagname.equalsIgnoreCase("descripcion")) {
                    	String s = text;
                    	s = s.replaceAll("[\n\r]", "");
                    	s = s.trim().replaceAll(" +", " ");
                    	prog.setDescripcion(s);
                    }else if (tagname.equalsIgnoreCase("horario")) {
                    	String s = text;
                    	s = s.replaceAll("[\n\r]", "");
                    	s = s.replaceAll("\r", "");
                    	prog.setHorario(s);
                    }  
					break;
					
			}
			eventType = xpp.next();
		}
	}
	
	public static int getResId(String variableName, String type, Context ctx) {
		return ctx.getResources().getIdentifier(variableName, type, ctx.getPackageName());
	}
}
