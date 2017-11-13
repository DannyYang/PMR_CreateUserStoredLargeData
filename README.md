# PMR_CreateUserStoredLargeData
This project is only for PMR. It shows that stored large data to user attribute will get 500 error

![Image](https://github.com/DannyYang/PMR_CreateUserStoredLargeData/blob/master/img/500Error.png)

Steps : 
1. Build and Deploy Adapters to MFP server (AuthAdapter & ResourceAdapter in TestAdapters folder)
   >1. cd to AuthAdapter & ResourceAdapter
   >2. mfpdev adapter build
   >3. mfpdev adapter deploy
   
2. Modify MFP Server URL in config.xml then cordova prepare
   > <mfp:server runtime="mfp" url="http://{Your_Sever_Path}:9080" />

3. cd to PMR_LargeUser_Data and register APP to MFP Server
   >1. cd PMR_LargeUser_Data
   >2. mfpdev app register

4. Add "AuthSecurityCheckScope" that mapping "SecurityTest" (Android & iOS)

![Image](https://github.com/DannyYang/PMR_CreateUserStoredLargeData/blob/master/img/addScope.png)

5. run APP in simulator

6. if you use smaller JSON string object, it will be ok.

![Image](https://github.com/DannyYang/PMR_CreateUserStoredLargeData/blob/master/img/OKResult.png)
