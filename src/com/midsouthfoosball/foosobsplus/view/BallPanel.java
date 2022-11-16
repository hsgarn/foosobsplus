/**
Copyright 2021, 2022 Hugh Garner
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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class BallPanel extends JPanel {
	private Border innerBorder;
	private JToggleButton btnCueBall;
	private JToggleButton btnOneBall;
	private JToggleButton btnTwoBall;
	private JToggleButton btnThreeBall;
	private JToggleButton btnFourBall;
	private JToggleButton btnFiveBall;
	private JToggleButton btnSixBall;
	private JToggleButton btnSevenBall;
	private JToggleButton btnEightBall;
	private JToggleButton btnNineBall;
	private JToggleButton btnTenBall;
	private JToggleButton btnElevenBall;
	private JToggleButton btnTwelveBall;
	private JToggleButton btnThirteenBall;
	private JToggleButton btnFourteenBall;
	private JToggleButton btnFifteenBall;
	private JButton btnSyncOBS;
	private JButton btnResetNineBall;
	private JButton btnShowAllBalls;
	private JButton btnHideAllBalls;
	
	public BallPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 350;
		dim.height = 30;
		setPreferredSize(dim);

		innerBorder = BorderFactory.createTitledBorder("Ball Panel");
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		ImageIcon imgCueBall = new ImageIcon(this.getClass().getResource("CueBall.png"));; //$NON-NLS-1$
		imgCueBall.setImage(imgCueBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconCueBall = imgCueBall;
		ImageIcon imgCueXBall = new ImageIcon(this.getClass().getResource("CueXBall.png"));; //$NON-NLS-1$
		imgCueXBall.setImage(imgCueXBall.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		Icon imgIconCueXBall = imgCueXBall;
		btnCueBall = new JToggleButton(imgIconCueBall);
		btnCueBall.setSelectedIcon(imgIconCueXBall);
		btnCueBall.setRolloverIcon(btnCueBall.isSelected() ? btnCueBall.getSelectedIcon() : btnCueBall.getIcon());

		ImageIcon imgOneBall = new ImageIcon(this.getClass().getResource("OneBall.png"));; //$NON-NLS-1$
		imgOneBall.setImage(imgOneBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconOneBall = imgOneBall;
		ImageIcon imgOneXBall = new ImageIcon(this.getClass().getResource("OneXBall.png"));; //$NON-NLS-1$
		imgOneXBall.setImage(imgOneXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconOneXBall = imgOneXBall;
		btnOneBall = new JToggleButton(imgIconOneBall);
		btnOneBall.setSelectedIcon(imgIconOneXBall);
		btnOneBall.setRolloverIcon(btnOneBall.isSelected() ? btnOneBall.getSelectedIcon() : btnOneBall.getIcon());

		ImageIcon imgTwoBall = new ImageIcon(this.getClass().getResource("TwoBall.png"));; //$NON-NLS-1$
		imgTwoBall.setImage(imgTwoBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconTwoBall = imgTwoBall;
		ImageIcon imgTwoXBall = new ImageIcon(this.getClass().getResource("TwoXBall.png"));; //$NON-NLS-1$
		imgTwoXBall.setImage(imgTwoXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconTwoXBall = imgTwoXBall;
		btnTwoBall = new JToggleButton(imgIconTwoBall);
		btnTwoBall.setSelectedIcon(imgIconTwoXBall);
		btnTwoBall.setRolloverIcon(btnTwoBall.isSelected() ? btnTwoBall.getSelectedIcon() : btnTwoBall.getIcon());

		ImageIcon imgThreeBall = new ImageIcon(this.getClass().getResource("ThreeBall.png"));; //$NON-NLS-1$
		imgThreeBall.setImage(imgThreeBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconThreeBall = imgThreeBall;
		ImageIcon imgThreeXBall = new ImageIcon(this.getClass().getResource("ThreeXBall.png"));; //$NON-NLS-1$
		imgThreeXBall.setImage(imgThreeXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconThreeXBall = imgThreeXBall;
		btnThreeBall = new JToggleButton(imgIconThreeBall);
		btnThreeBall.setSelectedIcon(imgIconThreeXBall);
		btnThreeBall.setRolloverIcon(btnThreeBall.isSelected() ? btnThreeBall.getSelectedIcon() : btnThreeBall.getIcon());

		ImageIcon imgFourBall = new ImageIcon(this.getClass().getResource("FourBall.png"));; //$NON-NLS-1$
		imgFourBall.setImage(imgFourBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconFourBall = imgFourBall;
		ImageIcon imgFourXBall = new ImageIcon(this.getClass().getResource("FourXBall.png"));; //$NON-NLS-1$
		imgFourXBall.setImage(imgFourXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconFourXBall = imgFourXBall;
		btnFourBall = new JToggleButton(imgIconFourBall);
		btnFourBall.setSelectedIcon(imgIconFourXBall);
		btnFourBall.setRolloverIcon(btnFourBall.isSelected() ? btnFourBall.getSelectedIcon() : btnFourBall.getIcon());

		ImageIcon imgFiveBall = new ImageIcon(this.getClass().getResource("FiveBall.png"));; //$NON-NLS-1$
		imgFiveBall.setImage(imgFiveBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconFiveBall = imgFiveBall;
		ImageIcon imgFiveXBall = new ImageIcon(this.getClass().getResource("FiveXBall.png"));; //$NON-NLS-1$
		imgFiveXBall.setImage(imgFiveXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconFiveXBall = imgFiveXBall;
		btnFiveBall = new JToggleButton(imgIconFiveBall);
		btnFiveBall.setSelectedIcon(imgIconFiveXBall);
		btnFiveBall.setRolloverIcon(btnFiveBall.isSelected() ? btnFiveBall.getSelectedIcon() : btnFiveBall.getIcon());

		ImageIcon imgSixBall = new ImageIcon(this.getClass().getResource("SixBall.png"));; //$NON-NLS-1$
		imgSixBall.setImage(imgSixBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconSixBall = imgSixBall;
		ImageIcon imgSixXBall = new ImageIcon(this.getClass().getResource("SixXBall.png"));; //$NON-NLS-1$
		imgSixXBall.setImage(imgSixXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconSixXBall = imgSixXBall;
		btnSixBall = new JToggleButton(imgIconSixBall);
		btnSixBall.setSelectedIcon(imgIconSixXBall);
		btnSixBall.setRolloverIcon(btnSixBall.isSelected() ? btnSixBall.getSelectedIcon() : btnSixBall.getIcon());

		ImageIcon imgSevenBall = new ImageIcon(this.getClass().getResource("SevenBall.png"));; //$NON-NLS-1$
		imgSevenBall.setImage(imgSevenBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconSevenBall = imgSevenBall;
		ImageIcon imgSevenXBall = new ImageIcon(this.getClass().getResource("SevenXBall.png"));; //$NON-NLS-1$
		imgSevenXBall.setImage(imgSevenXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconSevenXBall = imgSevenXBall;
		btnSevenBall = new JToggleButton(imgIconSevenBall);
		btnSevenBall.setSelectedIcon(imgIconSevenXBall);
		btnSevenBall.setRolloverIcon(btnSevenBall.isSelected() ? btnSevenBall.getSelectedIcon() : btnSevenBall.getIcon());

		ImageIcon imgEightBall = new ImageIcon(this.getClass().getResource("EightBall.png"));; //$NON-NLS-1$
		imgEightBall.setImage(imgEightBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconEightBall = imgEightBall;
		ImageIcon imgEightXBall = new ImageIcon(this.getClass().getResource("EightXBall.png"));; //$NON-NLS-1$
		imgEightXBall.setImage(imgEightXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconEightXBall = imgEightXBall;
		btnEightBall = new JToggleButton(imgIconEightBall);
		btnEightBall.setSelectedIcon(imgIconEightXBall);
		btnEightBall.setRolloverIcon(btnEightBall.isSelected() ? btnEightBall.getSelectedIcon() : btnEightBall.getIcon());

		ImageIcon imgNineBall = new ImageIcon(this.getClass().getResource("NineBall.png"));; //$NON-NLS-1$
		imgNineBall.setImage(imgNineBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconNineBall = imgNineBall;
		ImageIcon imgNineXBall = new ImageIcon(this.getClass().getResource("NineXBall.png"));; //$NON-NLS-1$
		imgNineXBall.setImage(imgNineXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconNineXBall = imgNineXBall;
		btnNineBall = new JToggleButton(imgIconNineBall);
		btnNineBall.setSelectedIcon(imgIconNineXBall);
		btnNineBall.setRolloverIcon(btnNineBall.isSelected() ? btnNineBall.getSelectedIcon() : btnNineBall.getIcon());

		ImageIcon imgTenBall = new ImageIcon(this.getClass().getResource("TenBall.png"));; //$NON-NLS-1$
		imgTenBall.setImage(imgTenBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconTenBall = imgTenBall;
		ImageIcon imgTenXBall = new ImageIcon(this.getClass().getResource("TenXBall.png"));; //$NON-NLS-1$
		imgTenXBall.setImage(imgTenXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconTenXBall = imgTenXBall;
		btnTenBall = new JToggleButton(imgIconTenBall);
		btnTenBall.setSelectedIcon(imgIconTenXBall);
		btnTenBall.setRolloverIcon(btnTenBall.isSelected() ? btnTenBall.getSelectedIcon() : btnTenBall.getIcon());

		ImageIcon imgElevenBall = new ImageIcon(this.getClass().getResource("ElevenBall.png"));; //$NON-NLS-1$
		imgElevenBall.setImage(imgElevenBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconElevenBall = imgElevenBall;
		ImageIcon imgElevenXBall = new ImageIcon(this.getClass().getResource("ElevenXBall.png"));; //$NON-NLS-1$
		imgElevenXBall.setImage(imgElevenXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconElevenXBall = imgElevenXBall;
		btnElevenBall = new JToggleButton(imgIconElevenBall);
		btnElevenBall.setSelectedIcon(imgIconElevenXBall);
		btnElevenBall.setRolloverIcon(btnElevenBall.isSelected() ? btnElevenBall.getSelectedIcon() : btnElevenBall.getIcon());

		ImageIcon imgTwelveBall = new ImageIcon(this.getClass().getResource("TwelveBall.png"));; //$NON-NLS-1$
		imgTwelveBall.setImage(imgTwelveBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconTwelveBall = imgTwelveBall;
		ImageIcon imgTwelveXBall = new ImageIcon(this.getClass().getResource("TwelveXBall.png"));; //$NON-NLS-1$
		imgTwelveXBall.setImage(imgTwelveXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconTwelveXBall = imgTwelveXBall;
		btnTwelveBall = new JToggleButton(imgIconTwelveBall);
		btnTwelveBall.setSelectedIcon(imgIconTwelveXBall);
		btnTwelveBall.setRolloverIcon(btnTwelveBall.isSelected() ? btnTwelveBall.getSelectedIcon() : btnTwelveBall.getIcon());

		ImageIcon imgThirteenBall = new ImageIcon(this.getClass().getResource("ThirteenBall.png"));; //$NON-NLS-1$
		imgThirteenBall.setImage(imgThirteenBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconThriteenBall = imgThirteenBall;
		ImageIcon imgThirteenXBall = new ImageIcon(this.getClass().getResource("ThirteenXBall.png"));; //$NON-NLS-1$
		imgThirteenXBall.setImage(imgThirteenXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconThirteenXBall = imgThirteenXBall;
		btnThirteenBall = new JToggleButton(imgIconThriteenBall);
		btnThirteenBall.setSelectedIcon(imgIconThirteenXBall);
		btnThirteenBall.setRolloverIcon(btnThirteenBall.isSelected() ? btnThirteenBall.getSelectedIcon() : btnThirteenBall.getIcon());

		ImageIcon imgFourteenBall = new ImageIcon(this.getClass().getResource("FourteenBall.png"));; //$NON-NLS-1$
		imgFourteenBall.setImage(imgFourteenBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconFourteenBall = imgFourteenBall;
		ImageIcon imgFourteenXBall = new ImageIcon(this.getClass().getResource("FourteenXBall.png"));; //$NON-NLS-1$
		imgFourteenXBall.setImage(imgFourteenXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconFourteenXBall = imgFourteenXBall;
		btnFourteenBall = new JToggleButton(imgIconFourteenBall);
		btnFourteenBall.setSelectedIcon(imgIconFourteenXBall);
		btnFourteenBall.setRolloverIcon(btnFourteenBall.isSelected() ? btnFourteenBall.getSelectedIcon() : btnFourteenBall.getIcon());

		ImageIcon imgFifteenBall = new ImageIcon(this.getClass().getResource("FifteenBall.png"));; //$NON-NLS-1$
		imgFifteenBall.setImage(imgFifteenBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconFifteenBall = imgFifteenBall;
		ImageIcon imgFifteenXBall = new ImageIcon(this.getClass().getResource("FifteenXBall.png"));; //$NON-NLS-1$
		imgFifteenXBall.setImage(imgFifteenXBall.getImage().getScaledInstance(40, 40,  Image.SCALE_DEFAULT));
		Icon imgIconFifteenXBall = imgFifteenXBall;
		btnFifteenBall = new JToggleButton(imgIconFifteenBall);
		btnFifteenBall.setSelectedIcon(imgIconFifteenXBall);
		btnFifteenBall.setRolloverIcon(btnFifteenBall.isSelected() ? btnFifteenBall.getSelectedIcon() : btnFifteenBall.getIcon());

		btnSyncOBS = new JButton("Sync OBS");
		btnResetNineBall = new JButton("9 Ball");
		btnShowAllBalls = new JButton("Show All");
		btnHideAllBalls = new JButton("Hide All");
		
		layoutComponents();

	}
	
	private void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnCueBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 1;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnOneBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnTwoBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 3;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnThreeBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 4;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnFourBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 5;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnFiveBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 6;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSixBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 7;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSevenBall, gc);
		
		gc.gridy ++;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnEightBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 1;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnNineBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnTenBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 3;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnElevenBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 4;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnTwelveBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 5;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnThirteenBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 6;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnFourteenBall, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 7;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnFifteenBall, gc);
		
		gc.gridy ++;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridwidth=2;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSyncOBS, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.gridwidth=2;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnResetNineBall, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 4;
		gc.gridwidth = 2;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnShowAllBalls, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 6;
		gc.gridwidth = 2;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnHideAllBalls, gc);
}
	
	public boolean getCueBallSelectedState() {
		return btnCueBall.isSelected();
	}
	public boolean getOneBallSelectedState() {
		return btnOneBall.isSelected();
	}
	public boolean getTwoBallSelectedState() {
		return btnTwoBall.isSelected();
	}
	public boolean getThreeBallSelectedState() {
		return btnThreeBall.isSelected();
	}
	public boolean getFourBallSelectedState() {
		return btnFourBall.isSelected();
	}
	public boolean getFiveBallSelectedState() {
		return btnFiveBall.isSelected();
	}
	public boolean getSixBallSelectedState() {
		return btnSixBall.isSelected();
	}
	public boolean getSevenBallSelectedState() {
		return btnSevenBall.isSelected();
	}
	public boolean getEightBallSelectedState() {
		return btnEightBall.isSelected();
	}
	public boolean getNineBallSelectedState() {
		return btnNineBall.isSelected();
	}
	public boolean getTenBallSelectedState() {
		return btnTenBall.isSelected();
	}
	public boolean getElevenBallSelectedState() {
		return btnElevenBall.isSelected();
	}
	public boolean getTwelveBallSelectedState() {
		return btnTwelveBall.isSelected();
	}
	public boolean getThirteenBallSelectedState() {
		return btnThirteenBall.isSelected();
	}
	public boolean getFourteenBallSelectedState() {
		return btnFourteenBall.isSelected();
	}
	public boolean getFifteenBallSelectedState() {
		return btnFifteenBall.isSelected();
	}
	public boolean getBallSelectedState(String ball) {
		boolean state = false;
		switch (ball) {
		case "Cue": state = btnCueBall.isSelected();
			break;
		case "One": state = btnOneBall.isSelected();
			break;
		case "Two": state = btnTwoBall.isSelected();
			break;
		case "Three": state = btnThreeBall.isSelected();
			break;
		case "Four": state = btnFourBall.isSelected();
			break;
		case "Five": state = btnFiveBall.isSelected();
			break;
		case "Six": state = btnSixBall.isSelected();
			break;
		case "Seven": state = btnSevenBall.isSelected();
			break;
		case "Eight": state = btnEightBall.isSelected();
			break;
		case "Nine": state = btnNineBall.isSelected();
			break;
		case "Ten": state = btnTenBall.isSelected();
			break;
		case "Eleven": state = btnElevenBall.isSelected();
			break;
		case "Twelve": state = btnTwelveBall.isSelected();
			break;
		case "Thirteen": state = btnThirteenBall.isSelected();
			break;
		case "Fourteen": state = btnFourteenBall.isSelected();
			break;
		case "Fifteen": state = btnFifteenBall.isSelected();
			break;
		}
		return state;
	}
	public void setBallSelected(String ball, boolean selected) {
		switch (ball) {
			case "Cue": btnCueBall.setSelected(selected);
				break;
			case "One": btnOneBall.setSelected(selected);
				break;
			case "Two": btnTwoBall.setSelected(selected);
				break;
			case "Three": btnThreeBall.setSelected(selected);
				break;
			case "Four": btnFourBall.setSelected(selected);
				break;
			case "Five": btnFiveBall.setSelected(selected);
				break;
			case "Six": btnSixBall.setSelected(selected);
				break;
			case "Seven": btnSevenBall.setSelected(selected);
				break;
			case "Eight": btnEightBall.setSelected(selected);
				break;
			case "Nine": btnNineBall.setSelected(selected);
				break;
			case "Ten": btnTenBall.setSelected(selected);
				break;
			case "Eleven": btnElevenBall.setSelected(selected);
				break;
			case "Twelve": btnTwelveBall.setSelected(selected);
				break;
			case "Thirteen": btnThirteenBall.setSelected(selected);
				break;
			case "Fourteen": btnFourteenBall.setSelected(selected);
				break;
			case "Fifteen": btnFifteenBall.setSelected(selected);
				break;
		}
	}
	//////// Listeners \\\\\\\\\\
	public void addBtnCueBallListener(ActionListener listenForBtnCueBall) {
		btnCueBall.addActionListener(listenForBtnCueBall);
	}
	public void addBtnOneBallListener(ActionListener listenForBtnOneBall) {
		btnOneBall.addActionListener(listenForBtnOneBall);
	}
	public void addBtnTwoBallListener(ActionListener listenForBtnTwoBall) {
		btnTwoBall.addActionListener(listenForBtnTwoBall);
	}
	public void addBtnThreeBallListener(ActionListener listenForBtnThreeBall) {
		btnThreeBall.addActionListener(listenForBtnThreeBall);
	}
	public void addBtnFourBallListener(ActionListener listenForBtnFourBall) {
		btnFourBall.addActionListener(listenForBtnFourBall);
	}
	public void addBtnFiveBallListener(ActionListener listenForBtnFiveBall) {
		btnFiveBall.addActionListener(listenForBtnFiveBall);
	}
	public void addBtnSixBallListener(ActionListener listenForBtnSixBall) {
		btnSixBall.addActionListener(listenForBtnSixBall);
	}
	public void addBtnSevenBallListener(ActionListener listenForBtnSevenBall) {
		btnSevenBall.addActionListener(listenForBtnSevenBall);
	}
	public void addBtnEightBallListener(ActionListener listenForBtnEightBall) {
		btnEightBall.addActionListener(listenForBtnEightBall);
	}
	public void addBtnNineBallListener(ActionListener listenForBtnNineBall) {
		btnNineBall.addActionListener(listenForBtnNineBall);
	}
	public void addBtnTenBallListener(ActionListener listenForBtnTenBall) {
		btnTenBall.addActionListener(listenForBtnTenBall);
	}
	public void addBtnElevenBallListener(ActionListener listenForBtnElevenBall) {
		btnElevenBall.addActionListener(listenForBtnElevenBall);
	}
	public void addBtnTwelveBallListener(ActionListener listenForBtnTwelveBall) {
		btnTwelveBall.addActionListener(listenForBtnTwelveBall);
	}
	public void addBtnThirteenBallListener(ActionListener listenForBtnThirteenBall) {
		btnThirteenBall.addActionListener(listenForBtnThirteenBall);
	}
	public void addBtnFourteenBallListener(ActionListener listenForBtnFourteenBall) {
		btnFourteenBall.addActionListener(listenForBtnFourteenBall);
	}
	public void addBtnFifteenBallListener(ActionListener listenForBtnFifteenBall) {
		btnFifteenBall.addActionListener(listenForBtnFifteenBall);
	}
	public void addBtnSyncOBSListener(ActionListener listenForBtnSyncOBS) {
		btnSyncOBS.addActionListener(listenForBtnSyncOBS);
	}
	public void addBtnResetNineBallListener(ActionListener listenForBtnResetNineBall) {
		btnResetNineBall.addActionListener(listenForBtnResetNineBall);
	}
	public void addBtnShowAllBallsListener(ActionListener listenForBtnShowAllBalls) {
		btnShowAllBalls.addActionListener(listenForBtnShowAllBalls);
	}
	public void addBtnHideAllBallsListener(ActionListener listenForBtnHideAllBalls) {
		btnHideAllBalls.addActionListener(listenForBtnHideAllBalls);
	}
	
}
