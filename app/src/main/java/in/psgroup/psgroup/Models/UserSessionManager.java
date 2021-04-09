package in.psgroup.psgroup.Models;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import in.psgroup.psgroup.LoginActivity;

public class UserSessionManager  {
    SharedPreferences sharedPreferences;

    // Editor reference for Shared preferences
    SharedPreferences.Editor editor,editor1;

    //context
    Context context;

    //shared pref mode
    int PRIVATE_MODE = 0;

    //Shared Pref File name
    private static final String PREFER_NAME = "PsgroupPref";

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String KEY_email = "email";
    public static final String KEY_mobile = "mobile";
    public static final String KEY_accessToken = "access_token";
    public static final String KEY_customertype = "customertype";
    public static final String KEY_image = "image";
    public static final String KEY_loyaltycode = "mobile";
    public static final String KEY_GUEST = "isguest";
    public static final String KEY_ProfileImage = "profile_pic";




    //constructor with context parameter
    public UserSessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }
    public void createguest(boolean isguest){
        editor.putBoolean(KEY_GUEST, isguest);
        editor.commit();
    }

    public boolean checkguest(){
        return sharedPreferences.getBoolean(KEY_GUEST, false);
    }


    //Create login session
    public void createUserLoginSession(String accessToken,String name,String email_id,String mobile,String image,String loyaltycode,String customertype){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);
        // Storing name in pref
        editor.putString(KEY_NAME, name);
        // Storing email in pref
        editor.putString(KEY_accessToken,accessToken);
        editor.putString(KEY_email,email_id);
        editor.putString(KEY_mobile,mobile);
        editor.putString(KEY_image,image);
        editor.putString(KEY_customertype,customertype);
        editor.putString(KEY_loyaltycode,loyaltycode);


        // commit changes
        editor.commit();
    }

    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, LoginActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);

            return true;
        }
        return false;
    }


    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));

        //accessToken
        user.put(KEY_accessToken, sharedPreferences.getString(KEY_accessToken, null));
        //session_name
        user.put(KEY_email,sharedPreferences.getString(KEY_email,null));
        user.put(KEY_mobile,sharedPreferences.getString(KEY_mobile,null));

        user.put(KEY_image,sharedPreferences.getString(KEY_image,null));
        user.put(KEY_loyaltycode,sharedPreferences.getString(KEY_loyaltycode,null));
        user.put(KEY_customertype,sharedPreferences.getString(KEY_customertype,null));


        //mobile
        //subscribe
        // return user
        return user;
    }

    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(context, LoginActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }
    public void clearSession(){
        editor.clear();
        editor.commit();
    }

    public void updateProfilePic(String image){
        editor.putString(KEY_ProfileImage, image);
        editor.commit();
    }


    public boolean isUserLoggedIn(){
        return sharedPreferences.getBoolean(IS_USER_LOGIN, false);
    }
}
