package guiLayer.product.models;

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
		
		this.map.put("V�lg", null);
		addElement("V�lg");
		for (UnitType ut : utList) {
			if(ut != null) {
				String key = ut.toString();
				this.map.put(key, ut);
				addElement(key);
			}
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
