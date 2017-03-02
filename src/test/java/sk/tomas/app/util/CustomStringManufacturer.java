package sk.tomas.app.util;

import sk.tomas.app.iam.model.input.IdentityInput;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.typeManufacturers.StringTypeManufacturerImpl;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Tomas Pachnik on 02-Mar-17.
 */
public class CustomStringManufacturer extends StringTypeManufacturerImpl {

    @Override
    public String getType(DataProviderStrategy strategy,
                          AttributeMetadata attributeMetadata,
                          Map<String, Type> genericTypesArgumentsMap) {

        if (IdentityInput.class.isAssignableFrom(attributeMetadata.getPojoClass())) {

            if ("email".equals(attributeMetadata.getAttributeName())) {
                return "asd@asd.sk";
            }
        }
        return super.getType(strategy, attributeMetadata, genericTypesArgumentsMap);
    }

}
