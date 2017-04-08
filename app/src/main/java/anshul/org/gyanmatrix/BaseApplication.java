package anshul.org.gyanmatrix;

import android.app.Application;
import android.content.Context;

import anshul.org.gyanmatrix.injection.component.ApplicationComponent;
import anshul.org.gyanmatrix.injection.component.DaggerApplicationComponent;
import anshul.org.gyanmatrix.injection.module.ApplicationModule;


public class BaseApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
