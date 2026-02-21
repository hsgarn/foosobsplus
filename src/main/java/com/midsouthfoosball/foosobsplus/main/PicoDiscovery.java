/**
Copyright © 2025-2026 Hugh Garner
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
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.controller.AutoScoreManager;
import com.midsouthfoosball.foosobsplus.view.Messages;

public class PicoDiscovery {
	private static final Logger logger = LoggerFactory.getLogger(AutoScoreManager.class);
	private static final String ON = "1"; //$NON-NLS-1$
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Messages.getString("Main.DateTimePattern")); //$NON-NLS-1$
    public static String listenForPico(int port, int timeoutMs, Consumer<String> statusCallback) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setBroadcast(true);
        socket.setSoTimeout(timeoutMs);

        try {
            byte[] sendData = "DISCOVER_PICO".getBytes(StandardCharsets.UTF_8);
            DatagramPacket sendPacket = new DatagramPacket(
                sendData,
                sendData.length,
                InetAddress.getByName("255.255.255.255"),
                port
            );

            for (int i = 0; i < 5; i++) {
                String attemptMsg = "Broadcasting discovery request (attempt " + (i+1) + ")..."; //$NON-NLS-1$ //$NON-NLS-2$
                logger.info(attemptMsg);
                if (statusCallback != null) statusCallback.accept(attemptMsg);
                socket.send(sendPacket);

                try {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket);

                    String msg = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
                    logger.info("Received: " + msg); //$NON-NLS-1$
                    if (statusCallback != null) statusCallback.accept("Received: " + msg); //$NON-NLS-1$

                    if (msg.startsWith("Table")) { //$NON-NLS-1$
                        return msg;
                    }
                } catch (SocketTimeoutException e) {
                    String retryMsg = "No response, retrying (attempt " + (i+1) + ")..."; //$NON-NLS-1$ //$NON-NLS-2$
                    logger.info(retryMsg);
                    if (statusCallback != null) statusCallback.accept(retryMsg);
                }
            }
        } finally {
            socket.close();
        }

        return null;
    }
}

