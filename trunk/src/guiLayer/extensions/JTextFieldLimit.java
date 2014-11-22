package guiLayer.extensions;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends JTextField {
	private static final long serialVersionUID = 1L;
	private int limit;
	private boolean onlyInt;
	private boolean oneSpace;

	public JTextFieldLimit(int limit, boolean onlyInt, boolean oneSpace) {
		super();
		this.limit = limit;
		this.onlyInt = onlyInt;
		this.oneSpace = oneSpace;
		setDocument(createDefaultModel());
	}
	
	@Override
	protected PlainDocument createDefaultModel() {
		return new PlainDocument() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void insertString(int offset, String str, AttributeSet attr)
					throws BadLocationException {
				if (str == null)
					return;
				boolean go = true;
				if (onlyInt) {
					char[] chars = str.toCharArray();

					for (int i = 0; i < chars.length; i++) {
						try {
							Integer.parseInt(String.valueOf(chars[i]));
						} catch (NumberFormatException e) {
							go = false;
							break;
						}
					}
				} else {
					if (oneSpace) {
						if (str.equals(" ")) {
							return;
						}
					} else if (getLength() == 0 && str.equals(" ")) {
						return;
					} else if (str.equals(" ")) {
						if (getLength() > 0) {
							if (getText(0, getLength()).charAt(getLength() - 1) == ' ') {
								return;
							}
						}
					} else {

					}
				}

				if (go && ((getLength() + str.length()) <= limit)) {
					super.insertString(offset, str, attr);
				}
			}
		};
	}

	

}
