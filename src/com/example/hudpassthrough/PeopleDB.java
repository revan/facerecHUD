package com.example.hudpassthrough;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.Environment;
import android.util.JsonReader;
import android.view.InputEvent;

public class PeopleDB {
	public JSONArray people;
	
	public PeopleDB(){
		
		try {
			people = new JSONArray(convertStreamToString(
							new FileInputStream(Environment.getExternalStorageDirectory()+"/people.db")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String find(int id, String field){
		for(int i=0; i<people.length(); i++){
			try {
				JSONObject person = people.getJSONObject(i);
				System.out.println(person.toString());
				if(((Integer)person.get("id")).intValue()!=id) //not necessary if things are sequential...
					continue;
				return person.getJSONObject("data").getString(field);
					
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	// http://senior.ceng.metu.edu.tr/2009/praeda/2009/01/11/a-simple-restful-client-at-android/
	private static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
