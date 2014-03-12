//
//  ParseSocialPlugin.java
//  Copyright (c) 2014 Alex Wasner
//

package com.alexwasner.parsesocialplugin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseTwitterUtils;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;

public class ParseSocialPlugin extends CordovaPlugin {

  public static final String ACTION_INIT = "init";
  
  public static final String ACTION_LOGIN_TWITTER = "loginTwitter";

  public static final String ACTION_LOGIN_FACEBOOK = "loginFacebook";

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if(ACTION_INIT.equals(action)){
      this.initialize(callbackContext, args);
      return true;
    }
    else if (ACTION_LOGIN_TWITTER.equals(action)){
      this.loginTwitter(callbackContext, args);
      return true;
    }
    else if (ACTION_LOGIN_FACEBOOK.equals(action)){
      this.loginFacebook(callbackContext, args);
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

  private void loginTwitter(final CallbackContext callbackContext, final JSONArray args) {
    cordova.getActivity().runOnUiThread(new Runnable() {
    public void run() {
    String consumerKey = "Y0urTw1tt3rC0nsum3rK3y";
    String consumerSecret ="Y0urTw1tt3rC0nsum3r53cr3t";
    ParseTwitterUtils.initialize(consumerKey,consumerSecret);
    ParseTwitterUtils.logIn(cordova.getActivity(), new LogInCallback() {
    @Override
    public void done(ParseUser user, ParseException err) {
        if (user == null) {
          Log.d("AppName", "The user cancelled the Twitter login.");
        } else if (user.isNew()) {
          Log.d("AppName", "User signed up and logged in through Twitter.");
        } else {
          Log.d("AppName", "User logged in through Twitter.");
            }
            Gson gson = new Gson();
            String json = gson.toJson(user); 
            callbackContext.success(json);
        }
      });
      }
    });
  }

  private void loginFacebook(final CallbackContext callbackContext, final JSONArray args) {
    cordova.getActivity().runOnUiThread(new Runnable() {
    public void run() {
      ParseFacebookUtils.initialize("YOUR FACEBOOK APP ID");
      ParseFacebookUtils.logIn(cordova.getActivity(), new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException err) {
        if (user == null) {
          Log.d("AppName", "Uh oh. The user cancelled the Facebook login.");
        } else if (user.isNew()) {
          Log.d("AppName", "User signed up and logged in through Facebook!");
        } else {
          Log.d("AppName", "User logged in through Facebook!");
            }
            Gson gson = new Gson();
            String json = gson.toJson(user); 
            callbackContext.success(json);
          }
        });
      }
    });
  }
  
}