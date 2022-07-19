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
	 * @param left If you want the text to be aligned left
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
	
	public final void labelChunk(GObject[] objs, GRect gbox) {
		int height = 0;
		double width = 0;
		for (int i = 0; objs.length > i; i++) {
			if (width < objs[i].getWidth()) {
				width = objs[i].getWidth()+objs[i].getWidth()/6;
			}
		}
		for (int i = 0; objs.length > i; i++) {
			height += objs[i].getHeight();
		}
		gbox.setSize(width, height);
	}
	
	public final void percentLine(GLine line, double x1, double y1, double x2, double y2) {
		// Get canvas size
		final int width = getWidth();
		final int height = getHeight();
		
		// Make all of the variables actual percents
		x1 /= 100;
		y1 /= 100;
		x2 /= 100;
		y2 /= 100;
		
		// Make the percents into actual points
		x1 *= width;
		y1 *= height;
		x2 *= width;
		y2 *= height;
		
		// Set lines values
		line.setStartPoint(x1, y1);
		line.setEndPoint(x2, y2);
		
	}
	
	public final GPolygon thickLine(double x1, double y1, double x2, double y2, int thickness) {
		// Get canvas size
		final int width = getWidth();
		final int height = getHeight();
		
		// Make all of the variables actual percents
		x1 /= 100;
		y1 /= 100;
		x2 /= 100;
		y2 /= 100;
		// Make the percents into actual points
		x1 *= width;
		y1 *= height;
		x2 *= width;
		y2 *= height;
		
		// Setup integers for thickness scaling
		double scaleX = width*thickness/1920;
		double scaleY = height*thickness/1080;
		
		// Scale the thicknesss
		double scaledThickness;
		
		if (scaleX > scaleY ) {
			scaledThickness = scaleY;
		}
		else { 
			scaledThickness = scaleX;
		}
		
		// Calculate the vertices
		final double halvedThickness = scaledThickness/2;
		
		final double posX1Final = x1 + halvedThickness;
		final double posY1Final = y1 - halvedThickness;
		final double posX2Final = x1 - halvedThickness;
		final double posY2Final = y1 + halvedThickness;
		final double posX3Final = x2 - halvedThickness;
		final double posY3Final = y2 + halvedThickness;
		final double posX4Final = x2 + halvedThickness;
		final double posY4Final = y2 - halvedThickness;
		
		// Set vertex values
		GPoint point1 = new GPoint(posX1Final,posY1Final);
		GPoint point2 = new GPoint(posX2Final,posY2Final);
		GPoint point3 = new GPoint(posX3Final,posY3Final);
		GPoint point4 = new GPoint(posX4Final,posY4Final);
		
		// Makes the thick line and fills it
		GPoint[] points = {point1,point2,point3,point4};
		GPolygon poly = new GPolygon(points);
		return (poly);
	}
}