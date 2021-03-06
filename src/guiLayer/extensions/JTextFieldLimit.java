package guiLayer.extensions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import exceptions.SubmitException;

public class JTextFieldLimit extends JTextField {
	private static final long serialVersionUID = 1L;
	private int limit;
	private boolean onlyInt;

	/**
	 * Constructor for JTextFieldLimit objects.
	 *
	 */
	public JTextFieldLimit(int limit, boolean onlyInt) {
		super();
		this.limit = limit;
		this.onlyInt = onlyInt;
	}
	
	/**
	 * Constructor for JTextFieldLimit objects.
	 *
	 */
	public JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
		this.onlyInt = false;
	}
	
	@Override
	protected PlainDocument createDefaultModel() {
		PlainDocument doc = new PlainDocument() {

			private static final long serialVersionUID = 1L;

			@Override
			public void insertString(int offset, String str, AttributeSet attr)
					throws BadLocationException {
				boolean go = true;
				if (str == null) {
					go = false;
				}

				if (onlyInt) {
					char[] chars = str.toCharArray();

					for (int i = 0; i < chars.length; i++) {
						try {
							Integer.parseInt(String.valueOf(chars[i]));
						} catch (NumberFormatException e) {
							go = false;
						}
					}
				}

				if (go && ((getLength() + str.length()) <= limit)) {
					super.insertString(offset, str, attr);
				}
			}
		};
		return doc;
	}

	/**
	 * Method for get text as an integer
	 * @return the text as an integer or -1 if failed
	 */
	public int getValue() {
		int ret = -1;
		if (onlyInt) {
			try {
				ret = Integer.parseInt(getText());
			} catch (NumberFormatException e) {
				ret = -1;
			}
		}
		return ret;
	}
	
	/**
	 * Returns true if, and only if, length() is 0 on trimmed text.
	 * @return true if length() is 0, otherwise false
	 */
	public boolean isEmpty() {
		return getText().trim().isEmpty();
	}
	
	/**
	 * Returns a string, if the text is an email
	 * @return the email as a string
	 * @throws SubmitException if the text is not an email
	 */
	public String getEmail() throws SubmitException {
		String email = getText();
		if(!email.trim().isEmpty()) {
			Pattern pattern = Pattern.compile("^.+@.+\\..+$");
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches()) {
				throw new SubmitException("Emailen er ikke angivet i korrekt format", this);
			}
		}
		return email;
	}
}
