package com.gwtfb.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class MainWindowViewController extends Composite {
	
	private HorizontalPanel outer = new HorizontalPanel ();
	
	public MainWindowViewController () {

		outer.getElement().setId ( "MainWindowViewController" );
		outer.setSpacing(10);
		outer.add ( new HTML ( "This demo uses Facebook Connect. Please click to login " ) );
		outer.add ( new HTML ( "<fb:login-button autologoutlink='true'/> " ) );
		
		initWidget ( outer );
	}

}
