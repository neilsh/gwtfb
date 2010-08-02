package com.gwtfb.sdk;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtfb.client.Callback;
import com.gwtfb.client.JSOModel;


/**
 * Class that wraps facebook Javascript SDK
 * 
 * @author ola
 */
public class FBCore {

	/**
	 * Wrapper method
	 */
	public native void api ( String method, AsyncCallback<JavaScriptObject> callback ) /*-{
		var app=this;
		$wnd.FB.api (method, function(response){
			app.@com.gwtfb.sdk.FBCore::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback,response);
		});
	}-*/;
	
	/**
	 * Wrapper method
	 */
	public void api ( String method, JavaScriptObject params, JavaScriptObject resopnse ) {
	}

	/**
	 * Wrapper method
	 */
	public native void getLoginStatus ( Callback<JavaScriptObject> callback ) /*-{
		var app=this;
		$wnd.FB.getLoginStatus(function(response) {
    		app.@com.gwtfb.sdk.FBCore::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback,response);
		});
		
	}-*/;

	/**
	 * Wrapper method
	 */
	public native JavaScriptObject getSession () /*-{
		return $wnd.FB.getSession();
	}-*/;

	/**
	 * Wrapper method
	 * @see http://developers.facebook.com/docs/reference/javascript/FB.init
	 */
	public native void init ( String appId, boolean status, boolean cookie, boolean xfbml ) /*-{
		$wnd.FB.init({
			'appId': appId, 
			'status': status,
			'cookie': cookie,
			'xfbml' : xfbml
		});
	}-*/;
	
	
	/**
	 * Wrapper method
	 * @see http://developers.facebook.com/docs/reference/javascript/FB.login
	 */
	public native void login ( AsyncCallback<JavaScriptObject> callback ) /*-{
		$wnd.FB.login (function(response){
    		app.@com.gwtfb.sdk.FBCore::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback,response);
		});
	}-*/;
	
	/**
	 * Wrapper method
	 * @see http://developers.facebook.com/docs/reference/javascript/FB.logout
	 */
	public native void logout ( AsyncCallback<JavaScriptObject> callback ) /*-{
		$wnd.FB.logout(function(response){
    		app.@com.gwtfb.sdk.FBCore::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback,response);
		});
	}-*/;
	
	/**
	 * Wrapper method
	 * @see http://developers.facebook.com/docs/reference/javascript/FB.ui
	 */
	public native void ui ( JavaScriptObject params, AsyncCallback<JavaScriptObject> callback ) /*-{
		var app=this;
		$wnd.FB.ui(params,function(response){
    		app.@com.gwtfb.sdk.FBCore::callbackSuccess(Lcom/google/gwt/user/client/rpc/AsyncCallback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback,response);
		});
	}-*/;
	
	/*
     * Called when method succeeded.
     */
    protected void callbackSuccess(AsyncCallback<JavaScriptObject> callback, JavaScriptObject obj) {
        callback.onSuccess ( obj );
    }

}
