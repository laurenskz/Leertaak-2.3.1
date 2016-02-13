package multiformat;

/**
 * Created by Laurens on 13-2-2016.
 */
public class NumberBaseException extends Exception {

    public static final String DEFAULT_ERROR_MESSAGE = "Het ingevoerde getal bevindt zich niet in het geselecteerde talstelsel";

    public NumberBaseException(Exception exception){
        super(exception);
    }

    public NumberBaseException(String message){
        super(message);
    }

    public NumberBaseException(){
        this(DEFAULT_ERROR_MESSAGE);
    }


}
