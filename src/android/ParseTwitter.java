//
//  ParseTwitter.java
//  Copyright (c) 2014 Alex Wasner
//

package com.alexwasner.parsetwitter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

import android.content.Context;
import android.util.Log;

public class ParseTwitter extends CordovaPlugin {

  public static final String ACTION_INIT = "init";
  
  public static final String ACTION_LOGIN = "login";

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if(ACTION_INIT.equals(action)){
      this.initialize(callbackContext, args);
      return true;
    }
    else if (ACTION_LOGIN.equals(action)){
      this.login(callbackContext, args);
      return true;
    }
    return false;
  }

  private void initialize(final CallbackContext callbackContext, final JSONArray args) {
      cordova.getThreadPool().execute(new Runnable() {
        public void run() {
              try {
                  String appId = args.getString(0);
                  String clientKey = args.getString(1);
                  Parse.initialize(cordova.getActivity(), appId, clientKey);
                  callbackContext.success();
              } catch (JSONException e) {
                  callbackContext.error("JSONException");
              }
          }
      });
  }

  private void login(final CallbackContext callbackContext, final JSONArray args) {
//    Context context=this.cordova.getActivity()
    cordova.getActivity().runOnUiThread(new Runnable() {
          public void run() {
        String consumerKey = "Y0urTw1tt3rC0nsum3rK3y";
        String consumerSecret ="Y0urTw1tt3rC0nsum3r53cr3t";
        ParseTwitterUtils.initialize(consumerKey,consumerSecret);
        ParseTwitterUtils.logIn(cordova.getActivity(), new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException err) {
            if (user == null) {
              Log.d("VapeSlate", "The user cancelled the Twitter login.");
            } else if (user.isNew()) {
              Log.d("VapeSlate", "User signed up and logged in through Twitter.");
            } else {
              Log.d("VapeSlate", "User logged in through Twitter.");
            }
            callbackContext.success();
        }
      });
          }
      });
  }
  
}