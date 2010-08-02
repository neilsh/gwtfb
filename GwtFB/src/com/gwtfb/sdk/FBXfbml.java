package com.gwtfb.sdk;

/**
 * Class that wraps xfbml methods 
 * @author ola
 * @see http://developers.facebook.com/docs/reference/javascript/#xfbml-methods
 */
public class FBXfbml {

	/**
	 * Wrapper method
	 * @see http://developers.facebook.com/docs/reference/javascript/FB.XFBML.parse
	 */
	public native void parse () /*-{
		$wnd.FB.XFBML.parse ();
	}-*/;
	
}
