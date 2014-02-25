
var exec = require("cordova/exec");

var ParseTwitter = function () {
    this.name = "ParseTwitter";
};

ParseTwitter.prototype.init = function(appId, clientKey, success, error) {
  cordova.exec(success, error, 'ParseTwitter', 'init', [appId, clientKey]);
};

ParseTwitter.prototype.login = function(success, error) {
  cordova.exec(success, error, 'ParseTwitter', 'login', []);
};


module.exports = new ParseTwitter();
