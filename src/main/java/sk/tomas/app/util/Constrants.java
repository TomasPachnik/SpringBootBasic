package sk.tomas.app.util;

/**
 * Created by tomas on 09.01.2017.
 */
public class Constrants {

    public static final String BASE_PATH = "/app";
    public static final String AUTHORIZE_ENDPOINT = BASE_PATH + "/authenticate"; //adresa, na ktorej pocuva authorize endpoint
    public static final String TOKEN_INFO_ENDPOINT = BASE_PATH + "/tokeninfo"; //adresa, na ktorej sa ziskaju z tokenu informacie o tokene
    public static final long VALIDITY = 1800000L; //30 min

}
