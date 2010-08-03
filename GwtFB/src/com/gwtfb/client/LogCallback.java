package com.gwtfb.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LogCallback implements AsyncCallback<JavaScriptObject> {

    public void onFailure(Throwable caught) {
        // TODO Auto-generated method stub

    }

    public void onSuccess(JavaScriptObject result) {
        GWT.log( "Result " + new JSONObject ( result ) );
    }

}
