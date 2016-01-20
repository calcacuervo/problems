package artistAnt.exception;

/**
 * Exception that indicates there was a problem when configuring the ant, grid, colours.
 * @author calcacuervo
 *
 */
public class AntConfigurationParseException extends RuntimeException {
	/**
	 * Generated UID.
	 */
	private static final long serialVersionUID = -7861530255554044643L;

	public AntConfigurationParseException(String message) {
		super(message);
	}

	public AntConfigurationParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public AntConfigurationParseException(Throwable cause) {
		super(cause);
	}
}
