package sk.tomas.app.mapper;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
public class UuidStringConverter extends BidirectionalConverter<UUID, String> {

    @Override
    public String convertTo(UUID source, Type<String> destinationType, MappingContext mappingContext) {
        return source.toString();
    }

    @Override
    public UUID convertFrom(String source, Type<UUID> destinationType, MappingContext mappingContext) {
        return UUID.fromString(source);
    }
}
