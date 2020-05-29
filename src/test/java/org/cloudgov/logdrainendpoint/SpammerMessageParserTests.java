package org.cloudgov.logdrainendpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class SpammerMessageParserTests {
	
	@Test
	public void itParsesAValidMessage() throws Exception {
		String msg = "2020-05-28T08:26:44.17-0600 [APP/PROC/WEB/0] ERR 2020-05-28 14:26:44,175 4ed0e3ad-54ec-48b8-6500-7c38 - 107";
		LogMessage logMsg = SpammerMessageParser.parse(msg);
		assertNotNull(logMsg);
		assertEquals("4ed0e3ad-54ec-48b8-6500-7c38", logMsg.getAppInstanceGuid());
		assertEquals(107, logMsg.getMessageNumber());
	}

	@Test
	public void itParsesAValidCFMessage() throws Exception {
		String msg = "<11>1 2020-05-28T08:26:44.17-0600 [APP/PROC/WEB/0] ERR 2020-05-28 14:26:44,175 4ed0e3ad-54ec-48b8-6500-7c38 - 107";
		LogMessage logMsg = SpammerMessageParser.parse(msg);
		assertEquals("4ed0e3ad-54ec-48b8-6500-7c38", logMsg.getAppInstanceGuid());
		assertEquals(107, logMsg.getMessageNumber());
	}

	@Test
	public void itIgnoresAnInvalidMessage() throws Exception {
		String msg = "2020-05-28T08:26:44.17-0600 [APP/PROC/WEB/0] ERR 2020-05-28 14:26:44,175";
		LogMessage logMsg = SpammerMessageParser.parse(msg);
		assertNull(logMsg);
	}
	
}