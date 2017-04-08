package anshul.org.gyanmatrix.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import anshul.org.gyanmatrix.Utils.RxEventBus;
import anshul.org.gyanmatrix.data.DataManager;
import anshul.org.gyanmatrix.data.local.DatabaseHelper;
import anshul.org.gyanmatrix.data.remote.RestServices;
import anshul.org.gyanmatrix.injection.ApplicationContext;
import anshul.org.gyanmatrix.injection.module.ApplicationModule;
import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();
    RestServices ribotsService();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();

}
