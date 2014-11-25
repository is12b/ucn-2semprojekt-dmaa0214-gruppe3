package guiLayer.extensions;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;

import modelLayer.UnitType;

/**
 * Class for UnitTypeComboBoxModel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class UnitTypeComboBoxModel extends DefaultComboBoxModel<String> {

	private static final long serialVersionUID = 1L;
	private HashMap<String, UnitType> map;
	
	public UnitTypeComboBoxModel() {
		map = new HashMap<String, UnitType>();
	}
	
	public void update(ArrayList<UnitType> utList) {
		if (!map.isEmpty() || getSize() > 0) {
			map.clear();
			removeAllElements();
		}
		this.map.put("Vælg", null);
		for (UnitType ut : utList) {
			if(ut != null) {
				this.map.put(ut.toString(), ut);
			}
		}
		for (String s : map.keySet()) {
			addElement(s);
		}
	}
	
	public UnitType getSelectedUnitType() {
		String str = (String) getSelectedItem();
		return map.get(str);
	}
	
	public UnitType getUnitTypeAt(int index) {
		String str = getElementAt(index);
		return map.get(str);
	}

}
