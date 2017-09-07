package calaru.model.conv;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class BooleanAttributeConverter implements AttributeConverter<Boolean, String> {
	private final String TRUE = "S";
	private final String FALSE = "N";
	@Override
	public String convertToDatabaseColumn(Boolean b) {
		return (b == null ? FALSE : (b ? TRUE : FALSE));
	}

	@Override
	public Boolean convertToEntityAttribute(String strBool) {
		return TRUE.equalsIgnoreCase(strBool);
	}
}
