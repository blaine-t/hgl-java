package hgl;

import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import acm.graphics.*;

public class Test extends Positioning {
	private static final long serialVersionUID = -5197257880108098224L;
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void run() {
		//add listeners
		addKeyListeners();
		addMouseListeners();
		
		//set parameters
		setSize(1280, 720);

		//set objects
		GRoundRect mainBox = new GRoundRect(50,50,100,100);
		mainBox.setColor(Color.white);
		GRoundRect labelRect1 = new GRoundRect(10,10,300,300);
		labelRect1.setColor(Color.white);
		setBackground(Color.black);
		GLabel label1 = new GLabel("Hello World!");
		GLabel label2 = new GLabel("Goodbye World!");
		GLabel label3 = new GLabel("World...?");
		label1.setColor(Color.white);
		label2.setColor(Color.white);
		label3.setColor(Color.white);
		GObject[] labels = {label1, label2, label3};
		
		//add objects
		add(mainBox);
		add(labelRect1);
		add(label1);
		add(label2);
		add(label3);

		while (true) {
			/*
			percentObjSize(testRect2, 10, 10);
			percentObjSize(test3D, 10, 10);
			percentObjSize(littleBoi, 2, 2);
			*/
			percentObjRel(mainBox, 50, 50, null, false);
			mainBox.setSize(getWidth()-20,getHeight()-20);
			percentObjRel(labelRect1, 2, 2, mainBox, true);
			percentObjRel(label1, 5, 0, labelRect1, true);
			percentLabel(label1,"Sans Serif",64);
			percentObjRel(label2, 0, 0, label1, true);
			percentLabel(label2,"Sans Serif",64);
			percentObjRel(label3, 0, 100, label1, true);
			percentLabel(label3,"Sans Serif",64);
			labelChunk(labels,labelRect1);
			
			pause(16);
		}
	}
}
