/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {
        console.log("receivedEvent");
    }
};

app.initialize();

// Login Test Code goes here.
var AuthChallengeHandler;
var isChallenged = false;
var AuthSecurityCheckName = "SecurityTest";

function initSecurityModule() {
	console.log("initMobileBankSecurityModule");
	AuthChallengeHandler = WL.Client.createSecurityCheckChallengeHandler(AuthSecurityCheckName);

	AuthChallengeHandler.handleChallenge = function(response) {
		console.log("handleChallenge");
        console.log(response);
        isChallenged = true;
        $.mobile.navigate("#loginPage");
	};

	AuthChallengeHandler.handleSuccess = function(response) {
		console.log("handleSuccess");
        console.log(response);
        $.mobile.navigate("#mainPage");
        $("#logoutBtn").show();
	};

	AuthChallengeHandler.handleFailure = function(response) {
		console.log("handleFailure");
		console.log(response);
	};
};

function wlCommonInit(){
    console.log("wlCommonInit");
    initSecurityModule();
    initAllPages();
    $("#logoutBtn").hide();
}

function initAllPages(){
    /* MainPage */
    $("#callAdapterBtn").on("click", function(){
        console.log("Call Adapter");
        var request = new WLResourceRequest('/adapters/ResourceAdapter/protectedResource', WLResourceRequest.POST);
        request.setHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
        request.setTimeout(100000);
        request.send().then(
            function(response){
                console.log(response.responseJSON.data);
                alert(response.responseJSON.data);
            },
            function(response){
                console.log(response);
                alert(JSON.stringify(response));
            }
        );
    });

    $("#logoutBtn").on("click", function(){
        WLAuthorizationManager.logout(AuthSecurityCheckName).then(
            function(response) {
                WL.Client.reloadApp();
            },
            function(response) {
                WL.Client.reloadApp();
            }
        );
    });

    /* LoginPage */
    $("#loginBtn").on("click", function(r){
        var account = $("#account").val();
        var pwd = $("#pwd").val();
        var loginParmas = {
            "acc" : $("#acc").val(),
            "pwd" : $("#pwd").val()
        }
        if(isChallenged) {
            AuthChallengeHandler.submitChallengeAnswer(loginParmas);
        } else {
            WLAuthorizationManager.login(AuthSecurityCheckName,loginParmas).then(
                function (response) {
                    console.log("LoginSuccess!");
                    console.log(response);
                },
                function (response) {
                    console.log("login fail!");
                    console.log(response);
                }
            );
        }
    });
    
}

