package app.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Config {
	
	public static final String panRegex = "[0-9]{16}";
	public static Map<String, String> statusCodes = new LinkedHashMap<String, String>();
	static {
		statusCodes.put("00", "TRANSACTION_COMPLETED");
		statusCodes.put("01", "CARD_AUTHENTICATION_FAILED");
		statusCodes.put("02", "CARD_INSUFFICIENT_FUNDS");
		statusCodes.put("03", "NO_ISSUER");
		statusCodes.put("04", "REQUEST_FORMAT_ERROR");
		statusCodes.put("05", "SERVER_ERROR");
	};	
	public static final String timestampFormat = "dd.MM.yyyy HH:mm:ss";
	public static final String cardExpirationDateRegex = "[0-9]{1,2}/[0-9]{2}";
	public static final String cardExpirationDateFormat = "MM/yy";
	public static final String issuerBankCode = "111111";
}
