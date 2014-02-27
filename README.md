## Parse-Twitter Plugin for Apache Cordova

Cordova Plugin to bridge the android twitter authentication via Parse API. Developed for Apache Cordova CLI >= 3.0.0. 

## Install

```
cordova plugin add https://github.com/alexwasner/cdv-parsetwitter.git
```

## Make call from JavaScript

```
var promise = new Promise();
if(window.parsetwitter){
  window.parsetwitter.init(PARSE_API_ID,PARSE_API_KEY,function(){
    window.parsetwitter.login(function(user){
      var newUser = JSON.parse(user);
      console.log(user);
      console.log(newUser.sessionToken);
      console.log(newUser.isNew);
      console.log(newUser.authData.nameValuePairs.twitter.nameValuePairs.screen_name);
      console.log(newUser.objectId);
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