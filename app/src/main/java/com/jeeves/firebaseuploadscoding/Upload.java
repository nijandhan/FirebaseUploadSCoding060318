package com.jeeves.firebaseuploadscoding;

/**
 * Created by JEEVES on 24-Feb-18.
 */

//import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/23/2017.
 */
//@IgnoreExtraProperties
public class Upload{
    public static final int TYPE_NONE = -1;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_VIDEO = 2;

    public String name;
    public String url;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String name, String url) {
        this.name = name;
        this.url= url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getType(){
        if(url == null) {
            return TYPE_NONE;
        }
        if(url.contains(".mp4")){
            return TYPE_VIDEO;
        }
        else{
            return TYPE_IMAGE;
        }
    }
}