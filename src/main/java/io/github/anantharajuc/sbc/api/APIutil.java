package io.github.anantharajuc.sbc.api;

/**
 * Class that implements the API utility methods.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 * @since 23/05/2021
 */
public class APIutil 
{
	private APIutil() 
	{
		
	}
	
	/**
	* Field to represent API Name on the requests/responses header
	*/
	public static final String HEADER_API_NAME = "api-name";
	
	/**
	* Field to represent API version on the requests/responses header
	*/
	public static final String HEADER_API_VERSION = "api-version";
}
