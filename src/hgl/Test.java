package hgl;

import acm.graphics.*;

public class Test extends Positioning {
	private static final long serialVersionUID = -5197257880108098224L;
	
	public void run() {
		//set parameters
		setSize(400,400);

		//set objects
		GRoundRect mainBox = new GRoundRect(50,50,100,100);
		GRoundRect labelRect1 = new GRoundRect(20,20,100,30);
		GLabel label1 = new GLabel("Hello World!=");
		GRect testRect2 = new GRect(100,1,50,50);
		GOval littleBoi = new GOval(10,10);
		G3DRect test3D = new G3DRect(50,50,50,50,true);

		
		//add objects
		add(mainBox);
		add(labelRect1);
		add(label1);
		add(testRect2);
		add(littleBoi);
		add(test3D);

		while (true) {
			percentObjSize(testRect2, 10, 10);
			percentObjSize(test3D, 10, 10);
			percentObjSize(littleBoi, 2, 2);
			percentLabel(label1,"",128);
			percentObjRel(mainBox, 50, 50, null);
			mainBox.setSize(getWidth()-20,getHeight()-20);
			rectLabel(label1, labelRect1, 50, 20, 0, mainBox);
			percentObjRel(testRect2, 50, 50, null);
			percentObjRel(littleBoi, 50, 50, testRect2);
			percentObjRel(test3D, 75, 75, testRect2);
			pause(50);
		}
	}
}
