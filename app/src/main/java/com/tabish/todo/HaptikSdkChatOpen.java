package com.tabish.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import ai.haptik.android.sdk.HaptikLib;
import ai.haptik.android.sdk.InitData;
import ai.haptik.android.sdk.Router;
import ai.haptik.android.sdk.SignUpData;
import ai.haptik.android.sdk.picassohelper.PicassoApiFactory;

public class HaptikSdkChatOpen {
    private static final String TAG = "HAPTIKSDKOPENCHAT";

    public void initializeChat(Context haptikSdkChatOpen, Application application){
       Log.d(TAG, "onCreate: Start");
       HaptikLib.setRunEnvironment(HaptikLib.RUN_ENVIRONMENT_STAGING);

       InitData initData = new InitData.Builder(application)
               .baseUrl("https://staging.hellohaptik.com")
               .debugEnabled(true)
               .notificationSound(Notification.DEFAULT_SOUND)
               .imageLoadingService(PicassoApiFactory.getPicassoApi())
               .build();

       if(!HaptikLib.isInitialized()){
           //This mean HaptikLib is not initialised. Please initialise the HaptikLib here
           HaptikLib.init(initData);
       }

       if (!HaptikLib.isUserLoggedIn()) {
           SignUpData signUpData = new SignUpData
                   .Builder(SignUpData.AUTH_TYPE_BASIC)
                   .build();

           Router.signUpAndLaunchChannel(haptikSdkChatOpen, signUpData, "testleadchannel", "HOMESCREEN");
       } else {
           Router.launchChannel(haptikSdkChatOpen, "testleadchannel", "HOMESCREEN");
       }

       //Router.launchChannel(HaptikSdkChatOpen.this, "testbusinesstestchannel", "HOMESCREEN");
       Log.d(TAG, "onCreate: END");
   }
}
