package sk.tomas.app.mapper;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import sk.tomas.app.model.Role;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by tomas on 06.01.2017.
 */
public class SetArrayConverter extends CustomConverter<Set<Role>, String[]> {
    @Override
    public String[] convert(Set<Role> source, Type<? extends String[]> destinationType, MappingContext mappingContext) {
        List<String> list = new LinkedList<>();
        for (Role role : source) {
            list.add(role.getName());
        }
        return list.toArray(new String[list.size()]);
    }
}
