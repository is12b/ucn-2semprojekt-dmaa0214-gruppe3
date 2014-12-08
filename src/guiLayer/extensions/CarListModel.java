package guiLayer.extensions;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import modelLayer.Car;

/**
 * Class for CarListModel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CarListModel extends AbstractListModel<Car> {

	private static final long serialVersionUID = 1L;
	private ArrayList<Car> cars;
	
	public CarListModel(ArrayList<Car> cars){
		this.cars = cars; 
	}
		
	public int getSize() {
		return cars.size();
	}
	public Car getElementAt(int index) {
		return cars.get(index);
	}
	
	public void refresh(ArrayList<Car> cars) {
		this.cars = cars;
	}
}

