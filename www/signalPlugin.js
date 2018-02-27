var exec = require('cordova/exec');
var myAPI = {}
myAPI.coolMethod = function(arg0, success, error) {
    exec(success, error, "signalPlugin", "coolMethod", [arg0]);
};
//信号方法
myAPI.signal = function(arg0, success, error) {
    exec(success, error, "signalPlugin", "signal", [arg0]);
};
myAPI.getServerCellInfo = function(arg0, success, error) {
    exec(success, error, "signalPlugin", "getServerCellInfo", [arg0]);
};
myAPI.signalStrength = function(arg0, success, error) {
    exec(success, error, "signalPlugin", "signalStrength", [arg0]);
};
myAPI.getPhone = function(arg0, success, error) {
    exec(success, error, "signalPlugin", "getPhone", [arg0]);
};
module.exports = myAPI;
