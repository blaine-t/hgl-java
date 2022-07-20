package hgl;

import java.awt.Color;

import acm.graphics.*;

public class Test extends Positioning {

	private static final long serialVersionUID = -7175703548190493593L;

	private static final int REFRESH = 16;

	public void run() {

		setSize(1280,720); // Set default window to 720p

		// Declare required variables
		int oldWidth = 0;
		int oldHeight = 0;
		int width = getWidth();
		int height = getHeight();

		// PERCENTOBJREL TESTS

		// Create required objects and variables for percentObjRel() TEST 1
		double boxSize = 100;
		GRect testRect1 = new GRect(0,0,boxSize,boxSize);
		double percentX = 10;
		double percentY = 10;

		percentObjRel(testRect1,percentX,percentY,null,false);
		add(testRect1);
		println("POR TEST 1 COMPLETE");

		// Create required objects and variables for percentObjRel() TEST 2
		GRect testRect2 = new GRect(0,0,boxSize,boxSize);
		double percentX2 = 50;
		double percentY2 = 100;

		percentObjRel(testRect2,percentX2,percentY2,testRect1,true);
		add(testRect2);
		println("POR TEST 2 COMPLETE");

		// Create required objects and variables for percentObjRel() TEST 3
		GRect testRect3 = new GRect(0,0,boxSize,boxSize);
		double percentX3 = 40;
		double percentY3 = 40;

		percentObjRel(testRect3,percentX3,percentY3,testRect1,false);
		add(testRect3);
		println("POR TEST 3 COMPLETE");

		// RECTLABEL TESTS

		// Create required objects and variables for rectLabel() TEST 1
		GLabel testLabel1 = new GLabel("TEST1");
		GRect testRect4 = new GRect(0,0,0,0);
		double percentX4 = 90;
		double percentY4 = 10;
		int padding = 0;

		rectLabel(testLabel1,testRect4,percentX4,percentY4,padding,null);
		add(testLabel1);
		add(testRect4);
		println("RL TEST 1 COMPLETE");

		// Create required objects and variables for rectLabel() TEST 2
		GLabel testLabel2 = new GLabel("TEST2");
		GRect testRect5 = new GRect(0,0,0,0);
		double percentX5 = 50;
		double percentY5 = 150;
		int padding2 = 10;

		rectLabel(testLabel2,testRect5,percentX5,percentY5,padding2,testRect4);
		add(testLabel2);
		add(testRect5);
		println("RL TEST 2 COMPLETE");

		// PERCENTOBJSIZE TESTS

		// Create required objects and variables for percentObjSize() TEST 1
		GRect testRect6 = new GRect(400,0,0,0);
		double percentX6 = 9;
		double percentY6 = 16;

		percentObjSize(testRect6,percentX6,percentY6,null);
		add(testRect6);
		println("POS TEST 1 COMPLETE");

		// PERCENTLABEL TESTS

		// Create required objects and variables for percentLabel() TEST 1
		GLabel testLabel3 = new GLabel("TEST3",600,100);
		int scale = 32;

		percentLabel(testLabel3,scale);
		add(testLabel3);
		println("PL TEST 1 COMPLETE");

		// LABELCHUNK TESTS

		// Create required objects and variables for labelChunk() TEST 1
		GLabel testLabel4 = new GLabel("TEST4");
		GLabel testLabel5 = new GLabel("TEST5*");
		GLabel testLabel6 = new GLabel("TEST6**");
		GLabel[] testLabels1 = {testLabel4,testLabel5,testLabel6};
		GRect testRect7 = new GRect(100,400,0,0);
		double padding3 = 2;
		double padding4 = 0.5;

		percentObjRel(testLabel4, 0, 0, testRect7, true);
		percentObjRel(testLabel5, 0, 0, testLabel4, true);
		percentObjRel(testLabel6, 0, 0, testLabel5, true);
		labelChunk(testLabels1,testRect7,padding3,padding4);
		add(testLabel4);
		add(testLabel5);
		add(testLabel6);
		add(testRect7);
		println("LC TEST 1 COMPLETE");
		
		// PERCENTLINE TESTS
		
		// Create required objects and variables for percentLine() TEST 1
		GLine line1 = new GLine(0,0,0,0);
		double line1x1 = 0;
		double line1y1 = 50;
		double line1x2 = 100;
		double line1y2 = 90;
		
		percentLine(line1,line1x1,line1y1,line1x2,line1y2,null);
		line1.setColor(new Color(200,200,0,100));
		add(line1);
		println("PL TEST 1 COMPLETE");
		
		// THICKLINE TESTS
		
		// Create required objects and variables for thickLine() TEST 1
		double line2x1 = 10;
		double line2y1 = 90;
		double line2x2 = 80;
		double line2y2 = 80;
		int thickness = 50;
		GPolygon poly = thickLine(line2x1,line2y1,line2x2,line2y2,thickness,null);
		

		while(true) {
			// Update width and height of the canvas
			width = getWidth();
			height = getHeight();

			if (width != oldWidth || height != oldHeight) { // Only run updates when canvas changes size

				percentObjRel(testRect1,percentX,percentY,null,false);
				percentObjRel(testRect2,percentX2,percentY2,testRect1,true);
				percentObjRel(testRect3,percentX3,percentY3,testRect1,false);

				rectLabel(testLabel1,testRect4,percentX4,percentY4,padding,null);
				rectLabel(testLabel2,testRect5,percentX5,percentY5,padding2,testRect4);

				percentObjSize(testRect6,percentX6,percentY6,null);

				percentLabel(testLabel3,scale);
				
				percentObjRel(testLabel4, 0, 0, testRect7, true);
				percentObjRel(testLabel5, 0, 0, testLabel4, true);
				percentObjRel(testLabel6, 0, 0, testLabel5, true);
				labelChunk(testLabels1,testRect7,padding3,padding4);
				
				percentLine(line1,line1x1,line1y1,line1x2,line1y2,testRect6);
				
				remove(poly);
				poly = thickLine(line2x1,line2y1,line2x2,line2y2,thickness,testRect6);
				add(poly);
				
				// Write the new width and height to oldWidth and oldHeight
				oldWidth = width;
				oldHeight = height;
			}
			pause(REFRESH); // Wait to run loop again for given refresh rate
		}
	}
}
