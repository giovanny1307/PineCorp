package headapp.digitalexperiences.com.headapp;


import android.content.Context;
import android.app.Application;

/**
 * Created by Giovanny on 9/1/2015.
 */
public class HeadAppApplication extends Application {



    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }



}
