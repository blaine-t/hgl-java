package hgl;

import acm.graphics.*;
import acm.program.*;

/**
 * @authors G2-Games & blaine-t 
 *
 */
public class Positioning extends GraphicsProgram {
	private static final long serialVersionUID = 3206404114531284252L;

	/**
	 * 
	 * Positions an object relative to another object (or the canvas) in percentile scaling
	 * 
	 * @usage percentObjRel(obj, percentX, percentY, relObj);
	 * @param obj The object that is being percentile scaled.
	 * @param percentX The location in percentile scaling from 0 to 100 of the object on the x cord.
	 * @param percentY The location in percentile scaling from 0 to 100 of the object on the y cord.
	 * @param relObj The object that the scaling should be relative to (Use null for canvas).
	 */
	public final void percentObjRel(GObject obj, double percentX, double percentY, GObject relObj, boolean left) {

		// Defines a bunch of variables, also sets defaults for many of them in case of nulls
		double posYFinal;
		double posXFinal;
		double relObjX = 0;
		double relObjY = 0;
		double relObjWidth = getWidth();
		double relObjHeight = getHeight();

		// Makes the percent an actual percent (ex: 50 becomes 0.5)
		double percentXFinal = (percentX / 100);
		double percentYFinal = (percentY / 100);

		// Gets the coordinates of the center of the object
		double objCenterX = (obj.getWidth() * 0.5);
		double objCenterY = (obj.getHeight() * 0.5);

		// Catches null values to prevent issues
		if (relObj != null) {
			relObjX = relObj.getX();
			relObjY = relObj.getY();
			relObjWidth = relObj.getWidth();
			relObjHeight = relObj.getHeight();
		}

		// These set the X and Y positions of the defined object relative to the parent object, in percent
		if (left != true) {
			posXFinal = (relObjWidth * percentXFinal) - objCenterX + relObjX;
			posYFinal = (relObjHeight * percentYFinal) - objCenterY + relObjY;
		} else {
			posXFinal = (relObjWidth * percentXFinal) + relObjX;
			posYFinal = (relObjHeight * percentYFinal) + relObjY;
		}

		// Check if the object is a GLabel, and if so translate it up by its height to avoid positioning errors
		if (obj.getClass().equals(GLabel.class)) {
			obj.setLocation(posXFinal, posYFinal+(obj.getHeight()*0.8));
		} else {
			obj.setLocation(posXFinal, posYFinal);
		}
	}

	/**
	 * 
	 * Makes a label in a GObject
	 * 
	 * @usage rectLabel(labelObj, boxObj, posX, posY, padding, relObj);
	 * @param labelObj The label that will be put in the box.
	 * @param boxObj The object that the label should be inside.
	 * @param percentX The location in percentile scaling from 0 to 100 of the object on the x cord.
	 * @param percentY The location in percentile scaling from 0 to 100 of the object on the y cord.
	 * @param padding Amount of pixel separation between the box and the text
	 * @param relObj The object that the boxObj should be relative to (Use null for canvas).
	 */
	public final void rectLabel(GLabel labelObj, GRect boxObj, double percentX, double percentY, int padding, GObject relObj) {
		// Checks if padding is set to 0, and if so it sets to the default padding value
		if (padding == 0) {
			padding = 5;
		}

		percentObjRel(boxObj, percentX, percentY, relObj, false);
		boxObj.setSize(labelObj.getWidth()+(padding), labelObj.getHeight()+(padding));
		percentObjRel(labelObj, 50, 50, boxObj, false);
	}

	/**
	 * 
	 * Changes the size of a GObject based on percent size.
	 * 
	 * @usage rectLabel(obj, percentX, percentY);
	 * @param obj The object that will be scaled
	 * @param PercentX The size in percentile scaling from 0 to 100 of the object on the x cord.
	 * @param percentY The size in percentile scaling from 0 to 100 of the object on the y cord.
	 */
	public final void percentObjSize(GObject obj, double percentX, double percentY) {
		// Makes the percent an actual percent (ex: 50 becomes 0.5)
		final double percentXFinal = (percentX / 100);
		final double percentYFinal = (percentY / 100);

		// Gets the coordinates of the canvas
		final int width = getWidth();
		final int height = getHeight();

		// Scales the objects size
		final double objWidth = width*percentXFinal;
		final double objHeight = height*percentYFinal;

		((GResizable) obj).setSize(objWidth,objHeight);
	}

	public final void percentLabel(GLabel labelObj, String font, int scale) {

		// Gets the coordinates of the canvas
		final int width = getWidth();
		final int height = getHeight();

		int scaleX = width*scale/1920;
		int scaleY = height*scale/1080;

		if (scaleX > scaleY ) {
			if (scaleY < 1) {
				scaleY = 1;
			}
			labelObj.setFont(font + "-" + scaleY);
		}
		else { 
			if (scaleX < 1) {
				scaleX = 1;
			}
			labelObj.setFont(font + "-" + scaleX);
		}
	}
}