package com.gwtfb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.gwtfb.sdk.FBCore;
import com.gwtfb.sdk.FBEvent;
import com.gwtfb.sdk.FBXfbml;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtFB implements EntryPoint {

	// public final String APPID = "1d81c942b38e2e6b3fc35a147d371ab3";
	public final String APPID = "0d51db8fd8b95ef0c2337ccbdc00d736";
	private SimplePanel mainViewWrapper = new SimplePanel ();
	
	private FBCore fbCore = GWT.create(FBCore.class);
	private FBEvent fbEvent = GWT.create(FBEvent.class);
	private FBXfbml fbXfbml = GWT.create(FBXfbml.class);
	
	private boolean status = true;
	private boolean xfbml = true;
	private boolean cookie = true;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
	
		fbCore.init(APPID, status, cookie, xfbml);
		
		RootPanel root = RootPanel.get();
		root.add ( new TopMenu () );
		root.add ( mainViewWrapper );
	
		// Callback used when session status is changed
		class SessionChangeCallback extends Callback<JavaScriptObject> {
			public void onSuccess ( JavaScriptObject response ) {
				JSOModel obj = response.cast();
				
				GWT.log ( "sessionChange");
				
				if ( obj.hasKey("session") ) {
					renderWhenLoggedIn();
				} else {
					renderWhenNotLoggedIn();
				}
			}
		}
		// Get notified when user session is changed
		SessionChangeCallback sessionChangeCallback = new SessionChangeCallback ();
		fbEvent.subscribe("auth.sessionChange",sessionChangeCallback);
		
		// Callback used when checking login status
		class LoginStatusCallback extends Callback<JavaScriptObject> {
			public void onSuccess ( JavaScriptObject response ) {
				renderApp(response);
			}
		}
		LoginStatusCallback loginStatusCallback = new LoginStatusCallback ();
		fbCore.getLoginStatus( loginStatusCallback );
		
		
	}
	
	private void renderApp ( JavaScriptObject response ) {
		JSOModel obj = response.cast();
		if ( obj.hasKey("session") ) {
			renderWhenLoggedIn();
		} else {
			renderWhenNotLoggedIn ();
		}
		
	}
	
	private void renderWhenLoggedIn () {
		mainViewWrapper.setWidget ( new UserInfoViewController ( fbCore, fbXfbml ) );
	}
	
	private void renderWhenNotLoggedIn () {
		mainViewWrapper.setWidget ( new MainWindowViewController () );
		fbXfbml.parse();
	}
	
	private native void reload () /*-{
		$wnd.document.location.reload(true);
		
	}-*/;
}