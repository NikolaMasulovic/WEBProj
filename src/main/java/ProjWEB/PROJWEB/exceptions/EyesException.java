package ProjWEB.PROJWEB.exceptions;

public class EyesException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates an EyesException instance.
     * @param message A description of the error.
     */
    public EyesException(String message) {
        super(message);
    }

    /**
     * Creates an EyesException instance.
     * @param message A description of the error.
     * @param e The throwable this exception should wrap.
     */
    public EyesException(String message, Throwable e) {
        super(message, e);
        e.printStackTrace();
    }
}
