/**
Copyright 2020, 2021 Hugh Garner
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
package com.midsouthfoosball.foosobsplus.model;

import java.io.IOException;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;

import net.twasi.obsremotejava.OBSRemoteController;

public class Table {
	
	private String tournamentName;
	private String eventName;
	private String tableName;
	private String side1Color;
	private String side2Color;
	private OBSInterface obsInterface;
	private Settings settings;
	
	public Table(OBSInterface obsInterface, Settings settings) {
		this.obsInterface = obsInterface;
		this.settings = settings;
	}
	
	public String getTournamentName() {
		return tournamentName;
	}
	public String getEventName() {
		return eventName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
		writeTournamentName();
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
		writeEventName();
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
		writeTableName();
		settings.setTableName(tableName);
		try {
		settings.saveControlConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getSide1Color() {
		return side1Color;
	}

	public void setSide1Color(String side1Color) {
		this.side1Color = side1Color;
	}

	public String getSide2Color() {
		return side2Color;
	}

	public void setSide2Color(String side2Color) {
		this.side2Color = side2Color;
	}

	public void clearAll() {
		tournamentName = "";
		eventName = "";
		tableName = "";
	}
	private void writeTournamentName() {
   		writeData(settings.getTournamentFileName(), settings.getTournamentSource(), getTournamentName());
	}
    private void writeEventName() {
    	writeData(settings.getEventFileName(), settings.getEventSource(), getEventName());
    }
    private void writeTableName() {
   		writeData(settings.getTableFileName(), settings.getTableSource(), getTableName());
    }
    public void writeAll() {
    	writeTournamentName();
    	writeEventName();
    	writeTableName();
     }
	private void writeData(String filename, String source, String data) {
    	OBS obs = OBS.getInstance();
    	OBSRemoteController obsController = obs.getController();
    	if (obsController == null || !obs.getConnected()) {
		   	try {
		    		obsInterface.setContents(filename, data);
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
		} else {
	   		obsController.setTextGDIPlusProperties(source, data, false, response -> {
	   			if(settings.getShowParsed())
	   				System.out.println("Table class: Source: [" + source + "] Text: [" + data + "] " + response + "]");
	   			});
		}
	}

  }
