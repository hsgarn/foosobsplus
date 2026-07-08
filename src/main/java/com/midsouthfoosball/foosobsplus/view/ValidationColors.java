/**
Copyright © 2021-2026 Hugh Garner
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Single source of truth for the colors used to flag OBS setup problems on the
 * Sources, Filters, and Stat Sources panels, plus a factory for the matching
 * legend so every window explains the colors the same way.
 */
public final class ValidationColors {
	private ValidationColors() {}
	public static final Color MISSING = new Color(255, 205, 205);
	public static final Color WRONG_TYPE = new Color(255, 224, 178);
	public static final Color DUPLICATE = new Color(255, 255, 153);

	/** Builds a compact horizontal legend: a swatch + label for each problem color. */
	public static JPanel buildLegend() {
		JPanel legend = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
		legend.setOpaque(false);
		legend.add(new JLabel(Messages.getString("Validate.Legend"))); //$NON-NLS-1$
		legend.add(swatch(MISSING, Messages.getString("Validate.LegendMissing"))); //$NON-NLS-1$
		legend.add(swatch(WRONG_TYPE, Messages.getString("Validate.LegendWrongType"))); //$NON-NLS-1$
		legend.add(swatch(DUPLICATE, Messages.getString("Validate.LegendDuplicate"))); //$NON-NLS-1$
		return legend;
	}

	private static JComponent swatch(Color color, String text) {
		JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
		item.setOpaque(false);
		JPanel box = new JPanel();
		box.setOpaque(true);
		box.setBackground(color);
		box.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		box.setPreferredSize(new Dimension(14, 14));
		item.add(box);
		item.add(new JLabel(text));
		return item;
	}
}
