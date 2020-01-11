package it.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonFormatter {

	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	/**
	 * Re-format a JSON String.
	 *
	 * @param json JSON String to be formatted
	 * @return Re-formatted JSON String
	 */
	public static String format(String json) {
		try {
			return objectMapper.writeValueAsString(objectMapper.readTree(json));
		} catch (Exception e) {
			throw new RuntimeException(
					"Cannot format JSON string. Does it contain valid JSON? The content is:\n" + json, e);
		}
	}
}
