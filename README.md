## Parse.com Social Plugin for Apache Cordova

Cordova Plugin to bridge the android facebook and twitter authentication via Parse API. Developed for Apache Cordova CLI >= 3.0.0. 

This plugin uses [GSON](https://code.google.com/p/google-gson/) from Google and [Parse's](http://www.parse.com) [Android API](https://parse.com/apps/quickstart#social/mobile/android/native/existing), [Facebook Utils](https://parse.com/docs/android/api/com/parse/ParseFacebookUtils.html) and [Twitter Utils](https://parse.com/docs/android/api/com/parse/ParseTwitterUtils.html).
I have already included and referenced these files, but be sure to check out their documentation for further expansion of this plugin.


## Install

```
cordova plugin add https://github.com/alexwasner/cdvparsesocialplugin.git
```

Add your Twitter and Facebook API Keys to the JAVA files.

## Make call from JavaScript

In this example, you will need to replace PARSE_API_ID and PARSE_API_KEY with your consumer id and key from Parse.com

```
var promise = new Promise();
if(window.parsesocial){
  window.parsesocial.init(PARSE_API_ID,PARSE_API_KEY,function(){
    window.parsesocial.loginTwitter(function(user){
      var newUser = JSON.parse(user);
      promise.resolve();
    }.bind(this),
    function(){
      promise.reject();
    }.bind(this));
  }.bind(this),
  function(e){
    promise.reject();
  }.bind(this));
}
else{
  promise.reject();
}
return promise;
```
###Functions
window.parsesocial.init(ParseApiId, ParseApiKey, successCallback, errorCallback);
window.parsesocial.loginTwitter(successCallback, errorCallback);
window.parsesocial.loginFacebook(successCallback, errorCallback);

### JSON Sample data
```
{
    "authData": {
        "nameValuePairs": {
            "twitter": {
                "nameValuePairs": {
                    "id": "1234567",
                    "consumer_secret": "c0nsum3r_53cr37",
                    "auth_token": "My_4u7h_T0k3n",
                    "screen_name": "TwitterScreenName",
                    "consumer_key": "7w1773r_3rk3y",
                    "auth_token_secret": "7w1773r_4u7h_53cr37"
                }
            }
        }
    },
    "sessionToken": "My535510NT0k3n",
    "readOnlyLinkedServiceNames": ["twitter"],
    "linkedServiceNames": ["twitter"],
    "isNew": false,
    "isLazy": false,
    "isCurrentUser": true,
    "className": "_User",
    "createdAt": "Feb 25, 2014 3:37:23 AM",
    "dataAvailability": {
        "updatedAt": true,
        "username": true,
        "createdAt": true,
        "objectId": true
    },
    "updatedAt": "Feb 25, 2014 3:37:23 AM",
    "estimatedData": {
        "username": "MyUserName"
    },
    "taskQueue": {
        "lock": {
            "sync": {
                "state": 0
            }
        }
    },
    "hashedObjects": {},
    "mutex": {},
    "objectId": "1a2b3c4d",
    "operationSetQueue": [{}],
    "saveEvent": {
        "callbacks": []
    },
    "serverData": {
        "username": "MyUserName"
    },
    "hasBeenFetched": true,
    "dirty": false
}
```

## Usage

You **do not** need to reference any JavaScript, the Cordova plugin architecture will add a socialmessage object to your root automatically when you build.

Ensure you use the plugin after your deviceready event has been fired.

## Platforms

Currently supporting Android - check back soon for iOS support.

## License

[MIT License](http://ilee.mit-license.org)