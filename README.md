# PMR_CreateUserStoredLargeData
This project is only for PMR. It shows that stored large data to user attribute will get 500 error


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

5. run APP in simulator
