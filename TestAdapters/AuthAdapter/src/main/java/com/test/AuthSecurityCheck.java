/**
* Copyright 2016 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.test;

import com.ibm.json.java.JSON;
import com.ibm.json.java.JSONObject;
import com.ibm.mfp.security.checks.base.UserAuthenticationSecurityCheck;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;

import javax.servlet.http.HttpServletRequest;
import com.ibm.mfp.server.security.external.checks.AuthorizationResponse;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt.GetOptsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.util.logging.Logger;
import jdk.Exported;

public class AuthSecurityCheck extends UserAuthenticationSecurityCheck {

    static Logger logger = Logger.getLogger(AuthSecurityCheck.class.getName());

    private String userId, displayName;
    private JSONObject attrObject;

    /* 暫存錯誤或互動相關資訊*/
    private String errorMessage;

    @Override
    protected AuthenticatedUser createUser() {
        log("createUser, userId : "+userId+ ", displayName:"+displayName+", this.getName():"+this.getName());
        Map<String, Object> userAttrsMap = new HashMap<String, Object>();
        userAttrsMap.put("attributes",attrObject);
        return new AuthenticatedUser(userId, displayName, this.getName(), userAttrsMap);
    }

    public void log(String log) {
        logger.info(log);
    }

    @Override
    protected boolean validateCredentials(Map<String, Object> credentials) {
        if(credentials == null) {
            errorMessage = "no params"; 
            return false;
        } else {
            try {
                // large JSON String data
                attrObject = JSONObject.parse(largeJSONString);
                // small JSON String data
                // attrObject = JSONObject.parse(smallJSONString);
                return true;
            } catch(Exception e) {
                e.printStackTrace();
            }
            errorMessage = "parse error";
            return false;
        }
    }

    @Override
    protected Map<String, Object> createChallenge() {
        Map challenge = new HashMap();
        challenge.put("errorMessage",errorMessage);
        return challenge;
    }

    /**
     * Get the currently authenticated user
     * @return AuthenticatedUser
     */
    public AuthenticatedUser getUser(){
        return authorizationContext.getActiveUser();
    }

    public boolean isLoggedIn(){
        return this.getState().equals(STATE_SUCCESS);
    }

    private String smallJSONString = "{\"something\":[{\"data\":\"val 0\"},{\"data\":\"val 1\"}]}";

    private String largeJSONString = "{\"something\":[{\"data\":\"val 0\"},{\"data\":\"val 1\"},{\"data\":\"val 2\"},{\"data\":\"val 3\"},{\"data\":\"val 4\"},{\"data\":\"val 5\"},{\"data\":\"val 6\"},{\"data\":\"val 7\"},{\"data\":\"val 8\"},{\"data\":\"val 9\"},{\"data\":\"val 10\"},{\"data\":\"val 11\"},{\"data\":\"val 12\"},{\"data\":\"val 13\"},{\"data\":\"val 14\"},{\"data\":\"val 15\"},{\"data\":\"val 16\"},{\"data\":\"val 17\"},{\"data\":\"val 18\"},{\"data\":\"val 19\"},{\"data\":\"val 20\"},{\"data\":\"val 21\"},{\"data\":\"val 22\"},{\"data\":\"val 23\"},{\"data\":\"val 24\"},{\"data\":\"val 25\"},{\"data\":\"val 26\"},{\"data\":\"val 27\"},{\"data\":\"val 28\"},{\"data\":\"val 29\"},{\"data\":\"val 30\"},{\"data\":\"val 31\"},{\"data\":\"val 32\"},{\"data\":\"val 33\"},{\"data\":\"val 34\"},{\"data\":\"val 35\"},{\"data\":\"val 36\"},{\"data\":\"val 37\"},{\"data\":\"val 38\"},{\"data\":\"val 39\"},{\"data\":\"val 40\"},{\"data\":\"val 41\"},{\"data\":\"val 42\"},{\"data\":\"val 43\"},{\"data\":\"val 44\"},{\"data\":\"val 45\"},{\"data\":\"val 46\"},{\"data\":\"val 47\"},{\"data\":\"val 48\"},{\"data\":\"val 49\"},{\"data\":\"val 50\"},{\"data\":\"val 51\"},{\"data\":\"val 52\"},{\"data\":\"val 53\"},{\"data\":\"val 54\"},{\"data\":\"val 55\"},{\"data\":\"val 56\"},{\"data\":\"val 57\"},{\"data\":\"val 58\"},{\"data\":\"val 59\"},{\"data\":\"val 60\"},{\"data\":\"val 61\"},{\"data\":\"val 62\"},{\"data\":\"val 63\"},{\"data\":\"val 64\"},{\"data\":\"val 65\"},{\"data\":\"val 66\"},{\"data\":\"val 67\"},{\"data\":\"val 68\"},{\"data\":\"val 69\"},{\"data\":\"val 70\"},{\"data\":\"val 71\"},{\"data\":\"val 72\"},{\"data\":\"val 73\"},{\"data\":\"val 74\"},{\"data\":\"val 75\"},{\"data\":\"val 76\"},{\"data\":\"val 77\"},{\"data\":\"val 78\"},{\"data\":\"val 79\"},{\"data\":\"val 80\"},{\"data\":\"val 81\"},{\"data\":\"val 82\"},{\"data\":\"val 83\"},{\"data\":\"val 84\"},{\"data\":\"val 85\"},{\"data\":\"val 86\"},{\"data\":\"val 87\"},{\"data\":\"val 88\"},{\"data\":\"val 89\"},{\"data\":\"val 90\"},{\"data\":\"val 91\"},{\"data\":\"val 92\"},{\"data\":\"val 93\"},{\"data\":\"val 94\"},{\"data\":\"val 95\"},{\"data\":\"val 96\"},{\"data\":\"val 97\"},{\"data\":\"val 98\"},{\"data\":\"val 99\"},{\"data\":\"val 0\"},{\"data\":\"val 1\"},{\"data\":\"val 2\"},{\"data\":\"val 3\"},{\"data\":\"val 4\"},{\"data\":\"val 5\"},{\"data\":\"val 6\"},{\"data\":\"val 7\"},{\"data\":\"val 8\"},{\"data\":\"val 9\"},{\"data\":\"val 10\"},{\"data\":\"val 11\"},{\"data\":\"val 12\"},{\"data\":\"val 13\"},{\"data\":\"val 14\"},{\"data\":\"val 15\"},{\"data\":\"val 16\"},{\"data\":\"val 17\"},{\"data\":\"val 18\"},{\"data\":\"val 19\"},{\"data\":\"val 20\"},{\"data\":\"val 21\"},{\"data\":\"val 22\"},{\"data\":\"val 23\"},{\"data\":\"val 24\"},{\"data\":\"val 25\"},{\"data\":\"val 26\"},{\"data\":\"val 27\"},{\"data\":\"val 28\"},{\"data\":\"val 29\"},{\"data\":\"val 30\"},{\"data\":\"val 31\"},{\"data\":\"val 32\"},{\"data\":\"val 33\"},{\"data\":\"val 34\"},{\"data\":\"val 35\"},{\"data\":\"val 36\"},{\"data\":\"val 37\"},{\"data\":\"val 38\"},{\"data\":\"val 39\"},{\"data\":\"val 40\"},{\"data\":\"val 41\"},{\"data\":\"val 42\"},{\"data\":\"val 43\"},{\"data\":\"val 44\"},{\"data\":\"val 45\"},{\"data\":\"val 46\"},{\"data\":\"val 47\"},{\"data\":\"val 48\"},{\"data\":\"val 49\"},{\"data\":\"val 50\"},{\"data\":\"val 51\"},{\"data\":\"val 52\"},{\"data\":\"val 53\"},{\"data\":\"val 54\"},{\"data\":\"val 55\"},{\"data\":\"val 56\"},{\"data\":\"val 57\"},{\"data\":\"val 58\"},{\"data\":\"val 59\"},{\"data\":\"val 60\"},{\"data\":\"val 61\"},{\"data\":\"val 62\"},{\"data\":\"val 63\"},{\"data\":\"val 64\"},{\"data\":\"val 65\"},{\"data\":\"val 66\"},{\"data\":\"val 67\"},{\"data\":\"val 68\"},{\"data\":\"val 69\"},{\"data\":\"val 70\"},{\"data\":\"val 71\"},{\"data\":\"val 72\"},{\"data\":\"val 73\"},{\"data\":\"val 74\"},{\"data\":\"val 75\"},{\"data\":\"val 76\"},{\"data\":\"val 77\"},{\"data\":\"val 78\"},{\"data\":\"val 79\"},{\"data\":\"val 80\"},{\"data\":\"val 81\"},{\"data\":\"val 82\"},{\"data\":\"val 83\"},{\"data\":\"val 84\"},{\"data\":\"val 85\"},{\"data\":\"val 86\"},{\"data\":\"val 87\"},{\"data\":\"val 88\"},{\"data\":\"val 89\"},{\"data\":\"val 90\"},{\"data\":\"val 91\"},{\"data\":\"val 92\"},{\"data\":\"val 93\"},{\"data\":\"val 94\"},{\"data\":\"val 95\"},{\"data\":\"val 96\"},{\"data\":\"val 97\"},{\"data\":\"val 98\"},{\"data\":\"val 99\"},{\"data\":\"val 0\"},{\"data\":\"val 1\"},{\"data\":\"val 2\"},{\"data\":\"val 3\"},{\"data\":\"val 4\"},{\"data\":\"val 5\"},{\"data\":\"val 6\"},{\"data\":\"val 7\"},{\"data\":\"val 8\"},{\"data\":\"val 9\"},{\"data\":\"val 10\"},{\"data\":\"val 11\"},{\"data\":\"val 12\"},{\"data\":\"val 13\"},{\"data\":\"val 14\"},{\"data\":\"val 15\"},{\"data\":\"val 16\"},{\"data\":\"val 17\"},{\"data\":\"val 18\"},{\"data\":\"val 19\"},{\"data\":\"val 20\"},{\"data\":\"val 21\"},{\"data\":\"val 22\"},{\"data\":\"val 23\"},{\"data\":\"val 24\"},{\"data\":\"val 25\"},{\"data\":\"val 26\"},{\"data\":\"val 27\"},{\"data\":\"val 28\"},{\"data\":\"val 29\"},{\"data\":\"val 30\"},{\"data\":\"val 31\"},{\"data\":\"val 32\"},{\"data\":\"val 33\"},{\"data\":\"val 34\"},{\"data\":\"val 35\"},{\"data\":\"val 36\"},{\"data\":\"val 37\"},{\"data\":\"val 38\"},{\"data\":\"val 39\"},{\"data\":\"val 40\"},{\"data\":\"val 41\"},{\"data\":\"val 42\"},{\"data\":\"val 43\"},{\"data\":\"val 44\"},{\"data\":\"val 45\"},{\"data\":\"val 46\"},{\"data\":\"val 47\"},{\"data\":\"val 48\"},{\"data\":\"val 49\"},{\"data\":\"val 50\"},{\"data\":\"val 51\"},{\"data\":\"val 52\"},{\"data\":\"val 53\"},{\"data\":\"val 54\"},{\"data\":\"val 55\"},{\"data\":\"val 56\"},{\"data\":\"val 57\"},{\"data\":\"val 58\"},{\"data\":\"val 59\"},{\"data\":\"val 60\"},{\"data\":\"val 61\"},{\"data\":\"val 62\"},{\"data\":\"val 63\"},{\"data\":\"val 64\"},{\"data\":\"val 65\"},{\"data\":\"val 66\"},{\"data\":\"val 67\"},{\"data\":\"val 68\"},{\"data\":\"val 69\"},{\"data\":\"val 70\"},{\"data\":\"val 71\"},{\"data\":\"val 72\"},{\"data\":\"val 73\"},{\"data\":\"val 74\"},{\"data\":\"val 75\"},{\"data\":\"val 76\"},{\"data\":\"val 77\"},{\"data\":\"val 78\"},{\"data\":\"val 79\"},{\"data\":\"val 80\"},{\"data\":\"val 81\"},{\"data\":\"val 82\"},{\"data\":\"val 83\"},{\"data\":\"val 84\"},{\"data\":\"val 85\"},{\"data\":\"val 86\"},{\"data\":\"val 87\"},{\"data\":\"val 88\"},{\"data\":\"val 89\"},{\"data\":\"val 90\"},{\"data\":\"val 91\"},{\"data\":\"val 92\"},{\"data\":\"val 93\"},{\"data\":\"val 94\"},{\"data\":\"val 95\"},{\"data\":\"val 96\"},{\"data\":\"val 97\"},{\"data\":\"val 98\"},{\"data\":\"val 99\"},{\"data\":\"val 0\"},{\"data\":\"val 1\"},{\"data\":\"val 2\"},{\"data\":\"val 3\"},{\"data\":\"val 4\"},{\"data\":\"val 5\"},{\"data\":\"val 6\"},{\"data\":\"val 7\"},{\"data\":\"val 8\"},{\"data\":\"val 9\"},{\"data\":\"val 10\"},{\"data\":\"val 11\"},{\"data\":\"val 12\"},{\"data\":\"val 13\"},{\"data\":\"val 14\"},{\"data\":\"val 15\"},{\"data\":\"val 16\"},{\"data\":\"val 17\"},{\"data\":\"val 18\"},{\"data\":\"val 19\"},{\"data\":\"val 20\"},{\"data\":\"val 21\"},{\"data\":\"val 22\"},{\"data\":\"val 23\"},{\"data\":\"val 24\"},{\"data\":\"val 25\"},{\"data\":\"val 26\"},{\"data\":\"val 27\"},{\"data\":\"val 28\"},{\"data\":\"val 29\"},{\"data\":\"val 30\"},{\"data\":\"val 31\"},{\"data\":\"val 32\"},{\"data\":\"val 33\"},{\"data\":\"val 34\"},{\"data\":\"val 35\"},{\"data\":\"val 36\"},{\"data\":\"val 37\"},{\"data\":\"val 38\"},{\"data\":\"val 39\"},{\"data\":\"val 40\"},{\"data\":\"val 41\"},{\"data\":\"val 42\"},{\"data\":\"val 43\"},{\"data\":\"val 44\"},{\"data\":\"val 45\"},{\"data\":\"val 46\"},{\"data\":\"val 47\"},{\"data\":\"val 48\"},{\"data\":\"val 49\"},{\"data\":\"val 50\"},{\"data\":\"val 51\"},{\"data\":\"val 52\"},{\"data\":\"val 53\"},{\"data\":\"val 54\"},{\"data\":\"val 55\"},{\"data\":\"val 56\"},{\"data\":\"val 57\"},{\"data\":\"val 58\"},{\"data\":\"val 59\"},{\"data\":\"val 60\"},{\"data\":\"val 61\"},{\"data\":\"val 62\"},{\"data\":\"val 63\"},{\"data\":\"val 64\"},{\"data\":\"val 65\"},{\"data\":\"val 66\"},{\"data\":\"val 67\"},{\"data\":\"val 68\"},{\"data\":\"val 69\"},{\"data\":\"val 70\"},{\"data\":\"val 71\"},{\"data\":\"val 72\"},{\"data\":\"val 73\"},{\"data\":\"val 74\"},{\"data\":\"val 75\"},{\"data\":\"val 76\"},{\"data\":\"val 77\"},{\"data\":\"val 78\"},{\"data\":\"val 79\"},{\"data\":\"val 80\"},{\"data\":\"val 81\"},{\"data\":\"val 82\"},{\"data\":\"val 83\"},{\"data\":\"val 84\"},{\"data\":\"val 85\"},{\"data\":\"val 86\"},{\"data\":\"val 87\"},{\"data\":\"val 88\"},{\"data\":\"val 89\"},{\"data\":\"val 90\"},{\"data\":\"val 91\"},{\"data\":\"val 92\"},{\"data\":\"val 93\"},{\"data\":\"val 94\"},{\"data\":\"val 95\"},{\"data\":\"val 96\"},{\"data\":\"val 97\"},{\"data\":\"val 98\"},{\"data\":\"val 99\"},{\"data\":\"val 0\"},{\"data\":\"val 1\"},{\"data\":\"val 2\"},{\"data\":\"val 3\"},{\"data\":\"val 4\"},{\"data\":\"val 5\"},{\"data\":\"val 6\"},{\"data\":\"val 7\"},{\"data\":\"val 8\"},{\"data\":\"val 9\"},{\"data\":\"val 10\"},{\"data\":\"val 11\"},{\"data\":\"val 12\"},{\"data\":\"val 13\"},{\"data\":\"val 14\"},{\"data\":\"val 15\"},{\"data\":\"val 16\"},{\"data\":\"val 17\"},{\"data\":\"val 18\"},{\"data\":\"val 19\"},{\"data\":\"val 20\"},{\"data\":\"val 21\"},{\"data\":\"val 22\"},{\"data\":\"val 23\"},{\"data\":\"val 24\"},{\"data\":\"val 25\"},{\"data\":\"val 26\"},{\"data\":\"val 27\"},{\"data\":\"val 28\"},{\"data\":\"val 29\"},{\"data\":\"val 30\"},{\"data\":\"val 31\"},{\"data\":\"val 32\"},{\"data\":\"val 33\"},{\"data\":\"val 34\"},{\"data\":\"val 35\"},{\"data\":\"val 36\"},{\"data\":\"val 37\"},{\"data\":\"val 38\"},{\"data\":\"val 39\"},{\"data\":\"val 40\"},{\"data\":\"val 41\"},{\"data\":\"val 42\"},{\"data\":\"val 43\"},{\"data\":\"val 44\"},{\"data\":\"val 45\"},{\"data\":\"val 46\"},{\"data\":\"val 47\"},{\"data\":\"val 48\"},{\"data\":\"val 49\"},{\"data\":\"val 50\"},{\"data\":\"val 51\"},{\"data\":\"val 52\"},{\"data\":\"val 53\"},{\"data\":\"val 54\"},{\"data\":\"val 55\"},{\"data\":\"val 56\"},{\"data\":\"val 57\"},{\"data\":\"val 58\"},{\"data\":\"val 59\"},{\"data\":\"val 60\"},{\"data\":\"val 61\"},{\"data\":\"val 62\"},{\"data\":\"val 63\"},{\"data\":\"val 64\"},{\"data\":\"val 65\"},{\"data\":\"val 66\"},{\"data\":\"val 67\"},{\"data\":\"val 68\"},{\"data\":\"val 69\"},{\"data\":\"val 70\"},{\"data\":\"val 71\"},{\"data\":\"val 72\"},{\"data\":\"val 73\"},{\"data\":\"val 74\"},{\"data\":\"val 75\"},{\"data\":\"val 76\"},{\"data\":\"val 77\"},{\"data\":\"val 78\"},{\"data\":\"val 79\"},{\"data\":\"val 80\"},{\"data\":\"val 81\"},{\"data\":\"val 82\"},{\"data\":\"val 83\"},{\"data\":\"val 84\"},{\"data\":\"val 85\"},{\"data\":\"val 86\"},{\"data\":\"val 87\"},{\"data\":\"val 88\"},{\"data\":\"val 89\"},{\"data\":\"val 90\"},{\"data\":\"val 91\"},{\"data\":\"val 92\"},{\"data\":\"val 93\"},{\"data\":\"val 94\"},{\"data\":\"val 95\"},{\"data\":\"val 96\"},{\"data\":\"val 97\"},{\"data\":\"val 98\"},{\"data\":\"val 99\"},{\"data\":\"val 0\"},{\"data\":\"val 1\"},{\"data\":\"val 2\"},{\"data\":\"val 3\"},{\"data\":\"val 4\"},{\"data\":\"val 5\"},{\"data\":\"val 6\"},{\"data\":\"val 7\"},{\"data\":\"val 8\"},{\"data\":\"val 9\"},{\"data\":\"val 10\"},{\"data\":\"val 11\"},{\"data\":\"val 12\"},{\"data\":\"val 13\"},{\"data\":\"val 14\"},{\"data\":\"val 15\"},{\"data\":\"val 16\"},{\"data\":\"val 17\"},{\"data\":\"val 18\"},{\"data\":\"val 19\"},{\"data\":\"val 20\"},{\"data\":\"val 21\"},{\"data\":\"val 22\"},{\"data\":\"val 23\"},{\"data\":\"val 24\"},{\"data\":\"val 25\"},{\"data\":\"val 26\"},{\"data\":\"val 27\"},{\"data\":\"val 28\"},{\"data\":\"val 29\"},{\"data\":\"val 30\"},{\"data\":\"val 31\"},{\"data\":\"val 32\"},{\"data\":\"val 33\"},{\"data\":\"val 34\"},{\"data\":\"val 35\"},{\"data\":\"val 36\"},{\"data\":\"val 37\"},{\"data\":\"val 38\"},{\"data\":\"val 39\"},{\"data\":\"val 40\"},{\"data\":\"val 41\"},{\"data\":\"val 42\"},{\"data\":\"val 43\"},{\"data\":\"val 44\"},{\"data\":\"val 45\"},{\"data\":\"val 46\"},{\"data\":\"val 47\"},{\"data\":\"val 48\"},{\"data\":\"val 49\"},{\"data\":\"val 50\"},{\"data\":\"val 51\"},{\"data\":\"val 52\"},{\"data\":\"val 53\"},{\"data\":\"val 54\"},{\"data\":\"val 55\"},{\"data\":\"val 56\"},{\"data\":\"val 57\"},{\"data\":\"val 58\"},{\"data\":\"val 59\"},{\"data\":\"val 60\"},{\"data\":\"val 61\"},{\"data\":\"val 62\"},{\"data\":\"val 63\"},{\"data\":\"val 64\"},{\"data\":\"val 65\"},{\"data\":\"val 66\"},{\"data\":\"val 67\"},{\"data\":\"val 68\"},{\"data\":\"val 69\"},{\"data\":\"val 70\"},{\"data\":\"val 71\"},{\"data\":\"val 72\"},{\"data\":\"val 73\"},{\"data\":\"val 74\"},{\"data\":\"val 75\"},{\"data\":\"val 76\"},{\"data\":\"val 77\"},{\"data\":\"val 78\"},{\"data\":\"val 79\"},{\"data\":\"val 80\"},{\"data\":\"val 81\"},{\"data\":\"val 82\"},{\"data\":\"val 83\"},{\"data\":\"val 84\"},{\"data\":\"val 85\"},{\"data\":\"val 86\"},{\"data\":\"val 87\"},{\"data\":\"val 88\"},{\"data\":\"val 89\"},{\"data\":\"val 90\"},{\"data\":\"val 91\"},{\"data\":\"val 92\"},{\"data\":\"val 93\"},{\"data\":\"val 94\"},{\"data\":\"val 95\"},{\"data\":\"val 96\"},{\"data\":\"val 97\"},{\"data\":\"val 98\"},{\"data\":\"val 99\"}]}";
}
