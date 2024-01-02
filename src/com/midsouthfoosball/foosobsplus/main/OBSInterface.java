/**
Copyright 2020-2024 Hugh Garner
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.midsouthfoosball.foosobsplus.model.OBS;
import com.midsouthfoosball.foosobsplus.model.Settings;

import io.obswebsocket.community.client.OBSRemoteController;
import io.obswebsocket.community.client.message.response.inputs.GetInputSettingsResponse;

public class OBSInterface {
	private static Logger logger = LoggerFactory.getLogger(OBSInterface.class);
	public OBSInterface(Settings settings) {}
	public String getContents(String whichSource) {
		OBS obs = OBS.getInstance();
		OBSRemoteController obsRemoteController = obs.getController();
		if (!(obsRemoteController==null) && obs.getConnected()) {
			GetInputSettingsResponse getInputSettings = obsRemoteController.getInputSettings(whichSource, 500);
			return (getInputSettings != null && getInputSettings.isSuccessful()) 
					? getInputSettings.getInputSettings().get("text").getAsString()
					: "" ;
		}
		return "";
	}
	public void writeData(String source, String data, String className, Boolean showParsed) {
		if (source == null || source.isEmpty()) return;
		OBS obs = OBS.getInstance();
		OBSRemoteController obsRemoteController = obs.getController();
		if (obsRemoteController != null && obs.getConnected()) {
			if (showParsed) logger.info(className + " class: Write Source: [" + source + "] Text: [" + data + "]]");
			String jsonString = "{'text':'" + data + "'}";
			JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonString);
			obsRemoteController.setInputSettings(source, jsonObject, true, response -> {
		   		if(showParsed)
		   			logger.info(className + " class: Write response for Source: [" + source + "] Text: [" + data + "] " + response.getMessageData().getRequestStatus() + "]");
		   	});
		}
	}
}
