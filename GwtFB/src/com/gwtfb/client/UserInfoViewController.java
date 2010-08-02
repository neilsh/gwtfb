package com.gwtfb.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
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

		outer.add ( new HTML ( "<fb:login-button autologoutlink='true'/>" ) );
		outer.add ( new HTML ( "<p/>" ) );
		
		// Display info about current user
		class MeCallback extends Callback<JavaScriptObject> {
			public void onSuccess ( JavaScriptObject response ) {
				renderMe ( response );
			}
		}
		fbCore.api ( "/me" , new MeCallback () );
		
		// Display Friends Size
		class FriendsCallback extends Callback<JavaScriptObject> {
			public void onSuccess ( JavaScriptObject response ) {
				renderFriends ( response );
			}
		}
		fbCore.api ( "/me/friends", new FriendsCallback () );
	
		// Display posts
		class PostsCallback extends Callback<JavaScriptObject> {
			public void onSuccess ( JavaScriptObject response ) {
				JSOModel model = response.cast ();
				JsArray array = model.getArray("data");
				outer.add ( new HTML ( "Posts " + array.length() ) );
				
			}
		}

	
		fbCore.api ( "/f8/posts",  new PostsCallback () );
		
		initWidget ( outer );
		fbXfbml.parse();
	}

	
	private void renderMe ( JavaScriptObject response ) {
		JSOModel jso = response.cast();
		outer.add( new HTML ( "You are logged in as " + jso.get ( "name" ) ) );
	}
	
	public void renderFriends ( JavaScriptObject response ) {
		JSOModel jso = response.cast ();
		
		JsArray array = jso.getArray("data");
		outer.add ( new HTML ( "You've got friends: " + (array != null ? array.length() : 0 ) ) );


	}
}
