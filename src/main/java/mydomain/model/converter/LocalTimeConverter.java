package mydomain.model.converter;

import javax.jdo.AttributeConverter;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class LocalTimeConverter implements AttributeConverter<String, Long> {
    @Override
    public Long convertToDatastore(String value) {
        LocalTime localTime = LocalTime.parse(value);
        return localTime.getLong(ChronoField.NANO_OF_DAY);
    }

    @Override
    public String convertToAttribute(Long value) {
        LocalTime localTime = LocalTime.ofNanoOfDay(value);
        return localTime.toString();
    }
}
