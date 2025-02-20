package main;

import java.util.ArrayList;

public class SetShape {
    public ArrayList<Shape> availSet;

    public SetShape(Shape shape) {
        this.availSet = new ArrayList<>();
        availSet.add(shape);
        availSet.add(shape.rotate());
        availSet.add(shape.rotate().rotate());
        availSet.add(shape.rotate().rotate().rotate());
        availSet.add(shape.flip());
        availSet.add(shape.flip().rotate());
        availSet.add(shape.flip().rotate().rotate());
        availSet.add(shape.flip().rotate().rotate().rotate());
    }
    
    public void printSet() {
    	System.out.println("Set : ");
    	for (Shape set: availSet) {
    		set.printShape();
    	}
    }
}