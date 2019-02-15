package org.sea9.mobile.fcm;

import org.junit.Test;
import static org.junit.Assert.*;

public class FcmTesterTest {
	@Test
	public void testInitTester() {
		FcmTester objUnderTest = new FcmTester();
		if (objUnderTest.init()) objUnderTest.sendMessage();
		assertNotNull("Response", objUnderTest);
	}
}