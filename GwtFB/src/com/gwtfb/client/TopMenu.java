package com.gwtfb.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Display Top Menu
 * @author ola
 */
public class TopMenu extends Composite {

	private HorizontalPanel outer = new HorizontalPanel ();

	public TopMenu () {
		
		outer.add ( new HTML ( "<h1> GwtFB </h1>" ) );
		initWidget ( outer );
	}
}
