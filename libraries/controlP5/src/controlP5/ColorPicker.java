package controlP5;

/**
 * controlP5 is a processing gui library.
 * 
 * 2006-2012 by Andreas Schlegel
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version. This library is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * @author Andreas Schlegel (http://www.sojamo.de)
 * @modified 02/29/2012
 * @version 0.7.1
 * 
 */

import processing.core.PApplet;

/**
 * A simple color picker using sliders to adjust RGBA values.
 * 
 * @example controllers/ControlP5colorPicker
 */
public class ColorPicker extends ControlGroup<ColorPicker> {

	protected Slider sliderRed;
	protected Slider sliderGreen;
	protected Slider sliderBlue;
	protected Slider sliderAlpha;
	protected ControlWindowCanvas currentColor;

	protected ColorPicker(ControlP5 theControlP5, ControllerGroup<?> theParent, String theName, int theX, int theY, int theWidth, int theHeight) {
		super(theControlP5, theParent, theName, theX, theY, theWidth, theHeight);
		isBarVisible = false;
		isCollapse = false;
		_myArrayValue = new float[] { 255, 255, 255, 255 };

		currentColor = addCanvas(new ColorField());

		sliderRed = cp5.addSlider(theName + "-red", 0, 255, 0, 0, theWidth, 10);
		cp5.removeProperty(sliderRed);
		sliderRed.setId(0);
		sliderRed.setBroadcast(false);
		sliderRed.addListener(this);
		sliderRed.moveTo(this);
		sliderRed.setMoveable(false);
		sliderRed.setColorBackground(0xff660000);
		sliderRed.setColorForeground(0xffaa0000);
		sliderRed.setColorActive(0xffff0000);
		sliderRed.getCaptionLabel().setVisible(false);
		sliderRed.setDecimalPrecision(0);
		sliderRed.setValue(255);
		
		sliderGreen = cp5.addSlider(theName + "-green", 0, 255, 0, 11, theWidth, 10);
		cp5.removeProperty(sliderGreen);
		sliderGreen.setId(1);
		sliderGreen.setBroadcast(false);
		sliderGreen.addListener(this);
		sliderGreen.moveTo(this);
		sliderGreen.setMoveable(false);
		sliderGreen.setColorBackground(0xff006600);
		sliderGreen.setColorForeground(0xff00aa00);
		sliderGreen.setColorActive(0xff00ff00);
		sliderGreen.getCaptionLabel().setVisible(false);
		sliderGreen.setDecimalPrecision(0);
		sliderGreen.setValue(255); 
		
		sliderBlue = cp5.addSlider(theName + "-blue", 0, 255, 0, 22, theWidth, 10);
		cp5.removeProperty(sliderBlue);
		sliderBlue.setId(2);
		sliderBlue.setBroadcast(false);
		sliderBlue.addListener(this);
		sliderBlue.moveTo(this);
		sliderBlue.setMoveable(false);
		sliderBlue.setColorBackground(0xff000066);
		sliderBlue.setColorForeground(0xff0000aa);
		sliderBlue.setColorActive(0xff0000ff);
		sliderBlue.getCaptionLabel().setVisible(false);
		sliderBlue.setDecimalPrecision(0);
		sliderBlue.setValue(255);
		
		sliderAlpha = cp5.addSlider(theName + "-alpha", 0, 255, 0, 33, theWidth, 10);
		cp5.removeProperty(sliderAlpha);
		sliderAlpha.setId(3);
		sliderAlpha.setBroadcast(false);
		sliderAlpha.addListener(this);
		
		sliderAlpha.moveTo(this);
		sliderAlpha.setMoveable(false);
		sliderAlpha.setColorBackground(0xff666666);
		sliderAlpha.setColorForeground(0xffaaaaaa);
		sliderAlpha.setColorActive(0xffffffff);
		sliderAlpha.getCaptionLabel().setVisible(false);
		sliderAlpha.setDecimalPrecision(0);
		sliderAlpha.getValueLabel().setColor(0xff000000);
		sliderAlpha.setValue(255);
		
	}

	/**
	 * @exclude {@inheritDoc}
	 */
	@Override
	@ControlP5.Invisible
	public void controlEvent(ControlEvent theEvent) {
		_myArrayValue[theEvent.getId()] = theEvent.getValue();
	}

	/**
	 * Requires an array of size 4 for RGBA
	 * 
	 * @return ColorPicker
	 */
	@Override
	public ColorPicker setArrayValue(float[] theArray) {
		sliderRed.setValue(theArray[0]);
		sliderGreen.setValue(theArray[1]);
		sliderBlue.setValue(theArray[2]);
		sliderAlpha.setValue(theArray[3]);
		_myArrayValue = theArray;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ColorPicker setColorValue(int theColor) {
		setArrayValue(new float[] { theColor >> 16 & 0xff, theColor >> 8 & 0xff, theColor >> 0 & 0xff, theColor >> 24 & 0xff });
		return this;
	}

	public int getColorValue() {
		int cc = 0xffffffff;
		return cc & (int) (_myArrayValue[3]) << 24 | (int) (_myArrayValue[0]) << 16 | (int) (_myArrayValue[1]) << 8 | (int) (_myArrayValue[2]) << 0;
	}

	private class ColorField extends ControlWindowCanvas {
		public void draw(PApplet theApplet) {
			theApplet.fill(_myArrayValue[0], _myArrayValue[1], _myArrayValue[2], _myArrayValue[3]);
			theApplet.rect(0, 44, getWidth(), 15);
		}
	}
	
	/**
	 * @exclude
	 * {@inheritDoc}
	 */
	@Override
	public String getInfo() {
		return "type:\tColorPicker\n" + super.toString();
	}
}

// some inspiration
// http://www.nbdtech.com/blog/archive/2008/04/27/Calculating-the-Perceived-Brightness-of-a-Color.aspx
// http://alienryderflex.com/hsp.html