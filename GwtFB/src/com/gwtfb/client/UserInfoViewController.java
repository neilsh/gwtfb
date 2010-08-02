package com.gwtfb.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtfb.sdk.FBCore;
import com.gwtfb.sdk.FBXfbml;

public class UserInfoViewController extends Composite {
	
	private VerticalPanel outer = new VerticalPanel ();
	private FBXfbml fbXfbml;
	
	public UserInfoViewController ( final FBCore fbCore, final FBXfbml fbXfbml ) {
		
		this.fbXfbml = fbXfbml;
		
		class MeCallback extends Callback<JavaScriptObject> {
			public void onSuccess ( JavaScriptObject response ) {
				renderMe ( response );
			}
		}
		fbCore.api ( "/me" , new MeCallback () );
		
		class FriendsCallback extends Callback<JavaScriptObject> {
			public void onSuccess ( JavaScriptObject response ) {
				renderFriends ( response );
			}
		}
		fbCore.api ( "/me/friends", new FriendsCallback () );
		


		initWidget ( outer );

	}

	
	private void renderMe ( JavaScriptObject response ) {
		JSOModel jso = response.cast();
		outer.add( new HTML ( "You are logged in as " + jso.get ( "name" ) ) );
	}
	
	public void renderFriends ( JavaScriptObject response ) {
		JSOModel jso = response.cast ();
		
		JsArray array = jso.getArray("data");
		outer.add ( new HTML ( "You got friends: " + (array != null ? array.length() : 0 ) ) );
		outer.add ( new HTML ( "<p/>" ) );

		outer.add ( new HTML ( "<fb:login-button autologoutlink='true'/>" ) );
		fbXfbml.parse();

	}
}
