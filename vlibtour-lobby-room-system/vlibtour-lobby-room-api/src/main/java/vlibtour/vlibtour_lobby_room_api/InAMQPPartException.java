package vlibtour.vlibtour_lobby_room_api;

/**
 * The exception thrown in case of problem in the use of AMQP entities.
 * 
 * @author Denis Conan
 */
public class InAMQPPartException extends Exception {

	/**
	 * the version number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * creates an exception object with a message.
	 * 
	 * @param message
	 *            the message.
	 */
	public InAMQPPartException(final String message) {
		super(message);
	}
}
