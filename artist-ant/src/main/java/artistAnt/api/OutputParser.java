package artistAnt.api;


/**
 * Parses a model to some format, which will be parametrized.
 * @author calcacuervo
 *
 * @param <T> The result output.
 * @param <V> The type of {@link HistoryService} to get the information from. 
 */
public interface OutputParser<T, V> {

	/**
	 * Parses a given {@link HistoryService} to some format.
	 * @param history the history service. Must not be null. 
	 * @return the produced output.
	 */
	public T generateOutput(HistoryService<V> history);
}
