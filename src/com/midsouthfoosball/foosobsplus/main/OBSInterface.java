/**
Copyright 2020 Hugh Garner
Permission is hereby granted, free of charge, to any person obtaining a copy 
of this software and associated documentation files (the "Software"), to deal 
in the Software without restriction, including without limitation the rights 
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
copies of the Software, and to permit persons to whom the Software is 
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in 
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR 
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
OTHER DEALINGS IN THE SOFTWARE.  
**/
package com.midsouthfoosball.foosobsplus.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.midsouthfoosball.foosobsplus.model.OBS;
import com.midsouthfoosball.foosobsplus.model.Settings;

import io.obswebsocket.community.client.OBSRemoteController;

public class OBSInterface {
	
	private String txtFilePath="";
	private String tableName="";
	private String fileName;
	private String theFileName;
	private String separator = FileSystems.getDefault().getSeparator();
	
	public OBSInterface(Settings settings) {
		txtFilePath = settings.getDatapath();
		if(!Files.exists(Paths.get(txtFilePath))) {
			try {
				Files.createDirectory(Paths.get(txtFilePath));
			} catch (IOException e) {
				System.out.println("Could not create directory " + txtFilePath);
				System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
			}
		}
	}

	public void setFilePath(String filePath) {
		this.txtFilePath = filePath;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getFilePath() {return this.txtFilePath;}
	public String getTableName() {return this.tableName;}

	public void setContents(String whichFile, String theContents) throws IOException {
		if (tableName.isEmpty()) {
			fileName = whichFile;
		} else {
			fileName = tableName + "_" + whichFile;
		}
		String fileNamePlusPath = txtFilePath + separator + fileName;
		if(!Files.exists(Paths.get(fileNamePlusPath))) {
			try {
				Files.createFile(Paths.get(fileNamePlusPath));
			} catch (IOException e) {
				System.out.println("Could not create file " + fileNamePlusPath);
				System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
			}
		}
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileNamePlusPath))) {
			if(theContents==null) theContents="";
			writer.write(theContents);
		}
	}

	public String getContents(String whichFile) throws IOException {
		String theContents = "";
		if (tableName.isEmpty()) {
			theFileName = txtFilePath + separator + whichFile;
		} else {
			theFileName = txtFilePath + separator + tableName + "_" + whichFile;
		}
		if(!Files.exists(Paths.get(theFileName))) {
			theContents = "";
		} else {
			try(BufferedReader br = new BufferedReader(new FileReader(theFileName))) {
				theContents = br.readLine();
			}
		}
		return theContents;
	}
	
	public void writeData(String source, String data, String className, Boolean showParsed) {
		OBS obs = OBS.getInstance();
		OBSRemoteController obsRemoteController = obs.getController();
		String jsonString = "{'text':'" + data + "'}";
		JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonString);

		obsRemoteController.setInputSettings(source, jsonObject, true, response -> {
	   			if(showParsed)
	   				System.out.println(className + " class: Source: [" + source + "] Text: [" + data + "] " + response.getMessageData().getRequestStatus() + "]");
	   			});
	}
}
