package sk.tomas.app.mapper;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import sk.tomas.app.model.Password;

/**
 * Created by tomas on 06.01.2017.
 */
public class PasswordStringConverter extends BidirectionalConverter<Password, String> {
    @Override
    public String convertTo(Password source, Type<String> destinationType, MappingContext mappingContext) {
        return source.getPassword();
    }

    @Override
    public Password convertFrom(String source, Type<Password> destinationType, MappingContext mappingContext) {
        return new Password(source);
    }
}
