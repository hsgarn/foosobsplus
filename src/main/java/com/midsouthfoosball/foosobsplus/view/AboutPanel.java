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
package com.midsouthfoosball.foosobsplus.view;

import com.midsouthfoosball.foosobsplus.model.Settings;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class AboutPanel extends JPanel {
	private static final long serialVersionUID 	= 1L;
	private static final String VERSIONNUMBER 	= "2.058"; //$NON-NLS-1$
	private static final String RELEASEDATE 	= "04/06/2025"; //$NON-NLS-1$
	private static final String AUTHOR          = "Hugh Garner"; //$NON-NLS-1$
	private static final String COPYRIGHT 		= "2020-2025 Hugh Garner"; //$NON-NLS-1$
	public AboutPanel(JFrame aboutFrame) {
		setLayout(new MigLayout("", "[127.00][50.00:87.00,grow,left]", "[][][][][grow][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JLabel lblProgram = new JLabel(Messages.getString("AboutPanel.Program")); //$NON-NLS-1$
		add(lblProgram, "cell 0 0,alignx right"); //$NON-NLS-1$
		JLabel lblProgramValue = new JLabel("FoosOBSPlus"); //$NON-NLS-1$
		add(lblProgramValue, "cell 1 0"); //$NON-NLS-1$
		JLabel lblVersion = new JLabel(Messages.getString("AboutPanel.Version")); //$NON-NLS-1$
		add(lblVersion, "cell 0 1,alignx right"); //$NON-NLS-1$
		JLabel lblVersionValue = new JLabel(VERSIONNUMBER);
		add(lblVersionValue, "cell 1 1"); //$NON-NLS-1$
		JLabel lblReleaseDate = new JLabel(Messages.getString("AboutPanel.ReleaseDate")); //$NON-NLS-1$
		add(lblReleaseDate, "cell 0 2,alignx right"); //$NON-NLS-1$
		JLabel lblReleaseDateValue = new JLabel(RELEASEDATE);
		add(lblReleaseDateValue, "cell 1 2"); //$NON-NLS-1$
		JLabel lblAuthor = new JLabel(Messages.getString("AboutPanel.Author")); //$NON-NLS-1$
		add(lblAuthor, "cell 0 3,alignx right"); //$NON-NLS-1$
		JLabel lblAuthorValue = new JLabel(AUTHOR); //$NON-NLS-1$
		add(lblAuthorValue, "cell 1 3"); //$NON-NLS-1$
		JLabel lblCopyright = new JLabel(Messages.getString("AboutPanel.Copyright")); //$NON-NLS-1$
		add(lblCopyright, "cell 0 4,alignx right"); //$NON-NLS-1$
		JLabel lblCopyrightValue = new JLabel(COPYRIGHT); //$NON-NLS-1$
		add(lblCopyrightValue, "cell 1 4"); //$NON-NLS-1$
		JLabel lblLicense = new JLabel(Messages.getString("AboutPanel.License")); //$NON-NLS-1$
		add(lblLicense, "cell 0 5,alignx right"); //$NON-NLS-1$
		JTextArea txtrPermissionIsHereby = new JTextArea();
		txtrPermissionIsHereby.setText(Messages.getString("AboutPanel.LicenseText")); //$NON-NLS-1$
		add(txtrPermissionIsHereby, "cell 1 5,grow"); //$NON-NLS-1$
		ImageIcon img = new ImageIcon(this.getClass().getResource(Settings.getControlParameter("IconImgPath"))); //$NON-NLS-1$
		img.setImage(img.getImage().getScaledInstance(100, 100,  Image.SCALE_DEFAULT));
		Icon imgIcon = img;
		JLabel lblIcon = new JLabel(imgIcon);
   		lblIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
				try {
                    URI logoLinkURI = new URI(Settings.getControlParameter("LogoLink"));
    				java.awt.Desktop.getDesktop().browse(logoLinkURI);
				} catch (IOException | URISyntaxException ex) {
                    Logger.getLogger(AboutPanel.class.getName()).log(Level.SEVERE, null, ex);
 				}
			}
		});
        add(lblIcon, "cell 1 6,alignx left"); //$NON-NLS-1$
		ImageIcon logo = new ImageIcon(this.getClass().getResource(Settings.getControlParameter("LogoImgPath"))); //$NON-NLS-1$
		logo.setImage(logo.getImage().getScaledInstance(100, 100,  Image.SCALE_DEFAULT));
		Icon imgIcon2 = logo;
        JLabel lblLogo = new JLabel(imgIcon2);
		lblLogo.setBackground(Color.BLACK);
        lblLogo.setOpaque(true);
   		lblLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
				try {
                    URI logoLinkURI = new URI(Settings.getControlParameter("LogoLink"));
    				java.awt.Desktop.getDesktop().browse(logoLinkURI);
				} catch (IOException | URISyntaxException ex) {
                    Logger.getLogger(AboutPanel.class.getName()).log(Level.SEVERE, null, ex);
 				}
			}
		});
		add(lblLogo, "cell 1 6, alignx right"); //$NON-NLS-1$
	}
}
