/**
Copyright © 2026 Hugh Garner
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
package com.midsouthfoosball.foosobsplus.api;

import java.util.Map;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.Main;

import io.javalin.http.Context;

public class TableControllerAPI {
	private static final Logger logger = LoggerFactory.getLogger(TableControllerAPI.class);

	public void selectTable(Context ctx) {
		try {
			TableSelectRequest request = ctx.bodyAsClass(TableSelectRequest.class);

			// Validate request
			request.validate();

			String action = request.actionOrDefault();
			// Filled in on the EDT: [error message], [resulting table number], [resulting table name]
			final String[] error = new String[1];
			final int[] resultNumber = new int[1];
			final String[] resultName = new String[1];

			// Switch tables on EDT (Swing Event Dispatch Thread) and wait for result
			try {
				SwingUtilities.invokeAndWait(() -> {
					if (action.equals("next")) {
						Main.selectNextTable();
					} else {
						int tableNumber = request.tableNumber();
						if (tableNumber > Main.getTableCount()) {
							error[0] = "Invalid table number: " + tableNumber + ". Only " + Main.getTableCount() + " table(s) configured.";
							return;
						}
						Main.selectTableByNumber(tableNumber);
					}
					resultNumber[0] = Main.getActiveTableNumber();
					resultName[0] = Main.getActiveTableName();
				});
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				ctx.status(500).json(new APIResponse(false, "Table selection interrupted"));
				logger.error("API: Table selection interrupted", e);
				return;
			} catch (java.lang.reflect.InvocationTargetException e) {
				ctx.status(500).json(new APIResponse(false, "Error selecting table"));
				logger.error("API: Error selecting table on EDT", e);
				return;
			}

			if (error[0] != null) {
				ctx.status(400).json(new APIResponse(false, error[0]));
				logger.warn("API: Table selection failed: {}", error[0]);
				return;
			}

			Map<String, Object> data = Map.of("tableNumber", resultNumber[0], "tableName", resultName[0]);
			ctx.status(200).json(new APIResponse(true, "Table " + resultNumber[0] + " selected", data));
			logger.info("API: Selected table {} ({}) via action '{}'", resultNumber[0], resultName[0], action);

		} catch (TableSelectRequest.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: Table selection validation failed: {}", e.getMessage());

		} catch (Exception e) {
			ctx.status(500).json(new APIResponse(false, "Internal server error"));
			logger.error("API: Error selecting table", e);
		}
	}
}
