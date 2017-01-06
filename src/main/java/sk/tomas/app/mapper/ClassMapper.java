package sk.tomas.app.mapper;

import ma.glasnost.orika.MapperFactory;
import sk.tomas.app.model.Identity;
import sk.tomas.app.orm.IdentityNode;

/**
 * Created by tomas on 06.01.2017.
 */
public class ClassMapper {

    public static void mapClass(MapperFactory mapperFactory) {
        mapperFactory.classMap(Identity.class, IdentityNode.class)
                .field("password", "encodedPassword")
                .byDefault()
                .register();
    }

}
