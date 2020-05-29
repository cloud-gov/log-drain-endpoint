package org.cloudgov.logdrainendpoint;

public class SpammerMessageParser {
	
	public static LogMessage parse(String msg) {
		try {
			int lastDashIndex = msg.lastIndexOf('-');
			String appInstanceGuid = msg.substring(lastDashIndex - 29, lastDashIndex - 1);
			long number = -1;
			try {
				number = Long.valueOf(msg.substring(lastDashIndex + 1).trim());
			} catch (NumberFormatException ignore) {
				//System.err.println( "Error parsing message number: " + msg.substring(lastDashIndex).trim());
				return null;
			}
			return new LogMessage(number, appInstanceGuid);
		} catch(IndexOutOfBoundsException ignore) {
			//System.err.println( "Index out of bounds");
			return null;
		}
	}
}