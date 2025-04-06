/**
Copyright © 2020-2025 Hugh Garner
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

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GameTableModel extends AbstractTableModel {
	private static final String OFF = "0";
	static int defaultMaxGameCount = 5;
	static String defaultCutthroatMode = OFF;
	private String[] columnNames;
	private Object[][] data;
	public GameTableModel() {
		this(defaultMaxGameCount,defaultCutthroatMode);
	}
	public GameTableModel(int maxGameCount, String cutthroatMode) {
		this.columnNames = new String[maxGameCount+1];
		this.data = new Object[5][maxGameCount+1];
		this.columnNames[0] = "Game:";
		this.data[0][0] = (Object) "Game:";
		this.data[1][0] = (Object) "Team 1:";
		this.data[2][0] = (Object) "Team 2:";
		if (cutthroatMode.equals(OFF)) {
			this.data[3][0] = (Object) "Time:";
			for(int i=1;i <= maxGameCount;i++) {
				this.columnNames[i] = Integer.toString(i);
				this.data[0][i] = (Object) Integer.toString(i);
				this.data[1][i] = (Object) "0";
				this.data[2][i] = (Object) "0";
				this.data[3][i] = (Object) "00:00:00";
			}
		} else {
			this.data[3][0] = (Object) "Team 3:";
			this.data[4][0] = (Object) "Time:";
			for(int i=1;i <= maxGameCount;i++) {
				this.columnNames[i] = Integer.toString(i);
				this.data[0][i] = (Object) Integer.toString(i);
				this.data[1][i] = (Object) "0";
				this.data[2][i] = (Object) "0";
				this.data[3][i] = (Object) "0";
				this.data[4][i] = (Object) "00:00:00";
			}
		}
	}
        @Override
    public int getColumnCount() {
        return columnNames.length;
    }

        @Override
    public int getRowCount() {
        return data.length;
    }

        @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

        @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

        @Override
    public Class<? extends Object> getColumnClass(int c) {
         return getValueAt(0, c).getClass();
    }

        @Override
    public boolean isCellEditable(int row, int col) {
            return col <= 0;
    }
        @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
