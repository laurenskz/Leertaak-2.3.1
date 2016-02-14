package multiformat;

/**
 * Created by Jules on 13/02/2016.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Error! Not a valid command.");
    }
}
