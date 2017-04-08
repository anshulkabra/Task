package anshul.org.gyanmatrix.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import anshul.org.gyanmatrix.data.remote.RestServices;
import anshul.org.gyanmatrix.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;


/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    RestServices provideRibotsService() {
        return RestServices.Creator.newRestService();
    }

}
