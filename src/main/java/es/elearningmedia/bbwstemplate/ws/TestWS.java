package es.elearningmedia.bbwstemplate.ws;

import blackboard.platform.ws.VersionVO;
import blackboard.platform.ws.anns.AuthenticatedMethod;

public interface TestWS {

	/**
	 * Returns the current version of this web service on the server
	 * 
	 * @param unused
	 *            - this is an optional parameter put here to make the
	 *            generation of .net client applications from the wsdl 'cleaner'
	 *            (0-argument methods do not generate clean stubs and are much
	 *            harder to have the same method name across multiple Web
	 *            Services in the same .net client)
	 * @since 1
	 */
	public VersionVO getServerVersion(VersionVO unused);

	/**
	 * sets the client version to version 1 and returns an appropriate session.
	 * With each release of this web service implement a new
	 * initializeVersionXXX method
	 * 
	 * @param ignore
	 *            - this is an optional parameter put here to make the
	 *            generation of .net client applications from the wsdl 'cleaner'
	 *            (0-argument methods do not generate clean stubs and are much
	 *            harder to have the same method name across multiple Web
	 *            Services in the same .net client)
	 * @return true to indicate that the session has been initialized for the
	 *         util ws
	 * @since 1
	 */
	public boolean initializeTestWS(boolean ignore);

	/**
	 * @return feedback about the result of the operation.
	 * @since 1
	 */
	@AuthenticatedMethod(entitlements = {})
	public String getUserName(String uuid);

}
