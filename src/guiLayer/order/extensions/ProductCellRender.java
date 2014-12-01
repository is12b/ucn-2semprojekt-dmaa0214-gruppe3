package guiLayer.order.extensions;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import modelLayer.Customer;
import modelLayer.Product;

public class ProductCellRender extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;
	private ProductListPanel productPanel;

	public ProductCellRender() {

	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list,
			Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Product p = (Product) value;

		productPanel = new ProductListPanel(p.getItemNumber(), p.getName(), p.getDescription(), p.getPrice(), p.getUnitType().getShortDescription(), !isSelected);

		return productPanel;
	}

}
