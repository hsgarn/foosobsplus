/**
Copyright 2020, 2021, 2022 Hugh Garner
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
package com.midsouthfoosball.foosobsplus.view;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class AboutPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String versionNumber = "1.087"; //$NON-NLS-1$

	public AboutPanel(JFrame aboutFrame) {
		setLayout(new MigLayout("", "[127.00][50.00:87.00,grow,left]", "[][][][][grow][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		JLabel lblProgram = new JLabel(Messages.getString("AboutPanel.Program")); //$NON-NLS-1$
		add(lblProgram, "cell 0 0,alignx right"); //$NON-NLS-1$
		
		JLabel lblProgramValue = new JLabel("FoosOBSPlus"); //$NON-NLS-1$
		add(lblProgramValue, "cell 1 0"); //$NON-NLS-1$
		
		JLabel lblVersion = new JLabel(Messages.getString("AboutPanel.Version")); //$NON-NLS-1$
		add(lblVersion, "cell 0 1,alignx right"); //$NON-NLS-1$
		
		JLabel lblVersionValue = new JLabel(versionNumber);
		add(lblVersionValue, "cell 1 1"); //$NON-NLS-1$
		
		JLabel lblReleaseDate = new JLabel(Messages.getString("AboutPanel.ReleaseDate")); //$NON-NLS-1$
		add(lblReleaseDate, "cell 0 2,alignx right"); //$NON-NLS-1$
		
		JLabel lblReleaseDateValue = new JLabel("06/26/2022");
		add(lblReleaseDateValue, "cell 1 2"); //$NON-NLS-1$
		
		JLabel lblAuthor = new JLabel(Messages.getString("AboutPanel.Author")); //$NON-NLS-1$
		add(lblAuthor, "cell 0 3,alignx right"); //$NON-NLS-1$
		
		JLabel lblAuthorValue = new JLabel("Hugh Garner"); //$NON-NLS-1$
		add(lblAuthorValue, "cell 1 3"); //$NON-NLS-1$
		
		JLabel lblCopyright = new JLabel(Messages.getString("AboutPanel.Copyright")); //$NON-NLS-1$
		add(lblCopyright, "cell 0 4,alignx right"); //$NON-NLS-1$
		
		JLabel lblCopyrightValue = new JLabel("2020, 2021, 2022 Hugh Garner"); //$NON-NLS-1$
		add(lblCopyrightValue, "cell 1 4"); //$NON-NLS-1$
		
		JLabel lblLicense = new JLabel(Messages.getString("AboutPanel.License")); //$NON-NLS-1$
		add(lblLicense, "cell 0 5,alignx right"); //$NON-NLS-1$
		
		JTextArea txtrPermissionIsHereby = new JTextArea();
		txtrPermissionIsHereby.setText(Messages.getString("AboutPanel.LicenseText")); //$NON-NLS-1$
		add(txtrPermissionIsHereby, "cell 1 5,grow"); //$NON-NLS-1$

		ImageIcon img = new ImageIcon(this.getClass().getResource("FoosBallTableAnimation.gif"));; //$NON-NLS-1$
		img.setImage(img.getImage().getScaledInstance(100, 100,  Image.SCALE_DEFAULT));
		Icon imgIcon = img;
		JLabel lblIcon = new JLabel(imgIcon);
		add(lblIcon, "cell 1 6,alignx center"); //$NON-NLS-1$

	}

}
