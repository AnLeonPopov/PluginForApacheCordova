module.exports = {
    runFormAndroid: function (successCallback, errorCallback, myData) {
        cordova.exec(successCallback, errorCallback, "MyPlugin", "runFormAndroid", [myData])
    }
}