package hgl;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.graphics.GResizable;

import acm.program.GraphicsProgram;

/**
 * @authors G2-Games & blaine-t 
 *
 */
public class Positioning extends GraphicsProgram {
	
	private static final long serialVersionUID = -1765118902853006428L;

	
	/**
	 * 
	 * Positions an object relative to another object (or the canvas) in percentile scaling.
	 * 
	 * @usage percentObjRel(obj, percentX, percentY, relObj, left);
	 * 
	 * @param obj The object that is being percentile scaled.
	 * @param percentX The location in percentile scaling from 0 to 100 of the object on the x cord.
	 * @param percentY The location in percentile scaling from 0 to 100 of the object on the y cord.
	 * @param relObj The object that the scaling should be relative to (Use null for canvas).
	 * @param left If you want the object to be aligned left.
	 * 
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
		final double percentXFinal = (percentX / 100);
		final double percentYFinal = (percentY / 100);

		// Gets the coordinates of the center of the object
		final double objCenterX = (obj.getWidth() / 2);
		final double objCenterY = (obj.getHeight() / 2);

		// If user passes through something other than null use relative object for relativity  
		if (relObj != null) {
			relObjX = relObj.getX();
			relObjY = relObj.getY();
			relObjWidth = relObj.getWidth();
			relObjHeight = relObj.getHeight();
		}

		// These set the X and Y positions of the defined object relative to the parent object (left if left is true, otherwise center), in percent
		if (left) {
			posXFinal = (relObjWidth * percentXFinal) + relObjX;
			posYFinal = (relObjHeight * percentYFinal) + relObjY;
		} 
		else {
			posXFinal = (relObjWidth * percentXFinal) - objCenterX + relObjX;
			posYFinal = (relObjHeight * percentYFinal) - objCenterY + relObjY;
		}

		// Check if the object is a GLabel, and if so translate it up by its height to avoid positioning errors
		if (obj.getClass().equals(GLabel.class)) {
			obj.setLocation(posXFinal, posYFinal+(obj.getHeight()*0.8));
		} 
		else {
			obj.setLocation(posXFinal, posYFinal);
		}
	}

	
	/**
	 * 
	 * Makes a label in a GObject.
	 * 
	 * @usage rectLabel(labelObj, boxObj, percentX, percentY, padding, relObj);
	 * 
	 * @param labelObj The label that will be put in the box.
	 * @param boxObj The object that the label should be inside.
	 * @param percentX The location in percentile scaling from 0 to 100 of the box object on the x cord.
	 * @param percentY The location in percentile scaling from 0 to 100 of the box object on the y cord.
	 * @param padding Amount of pixel separation between the box and the text.
	 * @param relObj The object that the boxObj should be relative to (Use null for canvas).
	 * 
	 */
	public final void rectLabel(GLabel labelObj, GRect boxObj, double percentX, double percentY, int padding, GObject relObj) {
		
		// Checks if padding is set to 0, and if so it sets to the default padding value
		if (padding == 0) {
			padding = 5;
		}
		
		boxObj.setSize(labelObj.getWidth()+(padding), labelObj.getHeight()+(padding)); // Sets the size of the box
		percentObjRel(boxObj, percentX, percentY, relObj, false); // Sets the location of the box
		percentObjRel(labelObj, 50, 50, boxObj, false); // Sets the location of the label in the center of the box
	}

	
	/**
	 * 
	 * Changes the size of a GResizable based on percent size.
	 * 
	 * @usage percentObjSize(obj, percentX, percentY);
	 * 
	 * @param obj The resizable object that will be scaled.
	 * @param percentX The size in percentile scaling from 0 to 100 of the object on the x cord.
	 * @param percentY The size in percentile scaling from 0 to 100 of the object on the y cord.
	 * 
	 */
	public final void percentObjSize(GResizable obj, double percentX, double percentY) {
		
		// Makes the percent an actual percent (ex: 50 becomes 0.5)
		final double percentXFinal = (percentX / 100);
		final double percentYFinal = (percentY / 100);

		// Gets the coordinates of the canvas
		final int width = getWidth();
		final int height = getHeight();

		// Scales the objects size
		final double objWidth = width*percentXFinal;
		final double objHeight = height*percentYFinal;

		obj.setSize(objWidth,objHeight);
	}

	
	/**
	 * 
	 * Changes the size of text based on resolution of window.
	 * 
	 * @usage percentLabel(labelObj,scale);
	 * 
	 * @param labelObj The label object that will be scaled.
	 * @param scale The font size that the label will be at 1080p (will be scaled).
	 * 
	 */
	public final void percentLabel(GLabel labelObj, int scale) {

		// Gets the coordinates of the canvas
		final int width = getWidth();
		final int height = getHeight();

		// Gets the font size based on size of canvas relative to 1080p
		int scaleX = width*scale/1920;
		int scaleY = height*scale/1080;

		// Checks to see if X or Y is smaller and uses that value for the scaling
		if (scaleX > scaleY ) {
			if (scaleY < 1) { // If window is too small and will cause 0 or negative number force to 1 to fix errors
				scaleY = 1;
			}
			labelObj.setFont("*-" + scaleY); // Sets the font size of labelObj, maintaining current font
		}
		else { 
			if (scaleX < 1) { // If window is too small and will cause 0 or negative number force to 1 to fix errors
				scaleX = 1;
			}
			labelObj.setFont("*-" + scaleX); // Sets the font size of labelObj, maintaining current font
		}
	}
	
	
	/**
	 * 
	 * Creates a chunk of text out of an array of labels.
	 * 
	 * @usage labelChunk(labels,gbox);
	 * 
	 * @param labels The labels list which contains label object that will be chunked together
	 * @param gbox The box in which the labels will be
	 * @param xPadding The amount of padding for the chunk on the x.
	 * @param yPadding The amount of padding for the chunk on the y.
	 * 
	 */
	public final void labelChunk(GLabel[] labels, GRect gbox, double xPadding, double yPadding) {
		
		// Declares chunk defaults
		double height = 0;
		double width = 0;
		
		// Go through the list of labels and set the width of the box to the size of largest label + padding
		for (int i = 0; labels.length > i; i++) {
			if (width < labels[i].getWidth()) {
				width = labels[i].getWidth()+labels[i].getWidth()/xPadding;
			}
		}
		
		// Go through the list of labels and add up the heights to get the height of the box
		for (int i = 0; labels.length > i; i++) {
			height += labels[i].getHeight();
		}
		height += height/yPadding;
		
		gbox.setSize(width, height);
	}
	
	
	/**
	 * 
	 * Changes the size of a GLine based on percent size.
	 * 
	 * @usage percentLine(line, x1, y1, x2, y2);
	 * 
	 * @param line The line that will be scaled.
	 * @param x1 The location in percentile scaling from 0 to 100 of the line start on the x cord.
	 * @param y1 The location in percentile scaling from 0 to 100 of the line start on the y cord.
	 * @param x2 The location in percentile scaling from 0 to 100 of the line end on the x cord.
	 * @param y2 The location in percentile scaling from 0 to 100 of the line end on the x cord.
	 * 
	 */
	public final void percentLine(GLine line, double x1, double y1, double x2, double y2) {
		// Get canvas size
		final int width = getWidth();
		final int height = getHeight();
		
		// Make all of the variables actual percents (ex: 50 becomes 0.5)
		x1 /= 100;
		y1 /= 100;
		x2 /= 100;
		y2 /= 100;
		
		// Make the percents into actual points (ex: 0.5 with a 1000x1000 canvas becomes 500)
		x1 *= width;
		y1 *= height;
		x2 *= width;
		y2 *= height;
		
		// Set line values
		line.setStartPoint(x1, y1);
		line.setEndPoint(x2, y2);
		
	}
	
	
	/**
	 * 
	 * Creates a thick line given start and end points (in percentile) and thickness.
	 * 
	 * @usage thickLine(x1, y1, x2, y2,thickness);
	 * 
	 * @param x1 The location in percentile scaling from 0 to 100 of the line start on the x cord.
	 * @param y1 The location in percentile scaling from 0 to 100 of the line start on the y cord.
	 * @param x2 The location in percentile scaling from 0 to 100 of the line end on the x cord.
	 * @param y2 The location in percentile scaling from 0 to 100 of the line end on the x cord.
	 * @param thickness The thickness in pixels that the line will be at 1080p (will be scaled).
	 * 
	 * @return poly Returns the thick line (polygon) to be used/drew in the main program.
	 * 
	 */
	public final GPolygon thickLine(double x1, double y1, double x2, double y2, int thickness) {
		// Get canvas size
		final int width = getWidth();
		final int height = getHeight();
		
		// Make all of the variables actual percents (ex: 50 becomes 0.5)
		x1 /= 100;
		y1 /= 100;
		x2 /= 100;
		y2 /= 100;
		
		// Make the percents into actual points (ex: 0.5 with a 1000x1000 canvas becomes 500)
		x1 *= width;
		y1 *= height;
		x2 *= width;
		y2 *= height;
		
		// Setup scale for thickness scaling
		double scaleX = width*thickness/1920;
		double scaleY = height*thickness/1080;
		
		// Scale the thickness
		double scaledThickness;
		
		// Base scaling off of the smaller value
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
		poly.setFilled(true);

		return (poly);
	}
}