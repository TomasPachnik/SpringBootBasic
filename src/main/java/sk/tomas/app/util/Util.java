package sk.tomas.app.util;

import org.apache.commons.codec.binary.Base64;

import static org.apache.commons.codec.binary.StringUtils.getBytesUtf8;
import static org.apache.commons.codec.binary.StringUtils.newStringUtf8;

/**
 * Created by tomas on 09.01.2017.
 */
public class Util {

    public static String base64decode(String endoded) {
        return newStringUtf8(Base64.decodeBase64(endoded));
    }

    public static String base64encode(String decoded) {
        return Base64.encodeBase64String(getBytesUtf8(decoded));
    }

}
