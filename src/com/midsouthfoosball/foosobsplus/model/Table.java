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
package com.midsouthfoosball.foosobsplus.model;

import java.io.IOException;

import main.OBSInterface;

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
		settings.saveToConfig();
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
    	try {
    		obsInterface.setContents(settings.getTournamentFileName(), getTournamentName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    private void writeEventName() {
		try {
    		obsInterface.setContents(settings.getEventFileName(), getEventName());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeTableName() {
		try {
    		obsInterface.setContents(settings.getTableFileName(), getTableName());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void writeAll() {
 		try {
    		obsInterface.setContents(settings.getTournamentFileName(), getTournamentName());
    		obsInterface.setContents(settings.getEventFileName(), getEventName());
     		obsInterface.setContents(settings.getTableFileName(), getTableName());
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
     }
  }
