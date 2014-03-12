
var exec = require("cordova/exec");

var ParseSocialPlugin = function () {
    this.name = "ParseSocialPlugin";
};

ParseSocialPlugin.prototype.init = function(appId, clientKey, success, error) {
  cordova.exec(success, error, 'ParseSocialPlugin', 'init', [appId, clientKey]);
};

ParseSocialPlugin.prototype.loginTwitter = function(success, error) {
  cordova.exec(success, error, 'ParseSocialPlugin', 'login', []);
};

ParseSocialPlugin.prototype.loginFacebook = function(success, error) {
  cordova.exec(success, error, 'ParseSocialPlugin', 'login', []);
};


module.exports = new ParseSocialPlugin();
