package testLayer.dbLayer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dbLayer.DBPostalCode;
import dbLayer.interfaceLayer.IFDBPostalcode;

/**
 * Class for TestDBPostalCode
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class TestDBPostalCode {
	private IFDBPostalcode dbPost;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dbPost = new DBPostalCode();
	}
	
	@Test
	public void test() {
		//dbPost.insertPostalCode(8000, "Hej V");
		

	}
	
}
