package demo.userboard.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Converter
public class PasswordConverter implements AttributeConverter<String, String> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String convertToDatabaseColumn(String attribute) {

        return passwordEncoder.encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
