package anshul.org.gyanmatrix.injection.component;


import anshul.org.gyanmatrix.ui.main.FavouritePlayersActivity;
import anshul.org.gyanmatrix.ui.main.MainActivity;
import anshul.org.gyanmatrix.injection.PerActivity;
import anshul.org.gyanmatrix.injection.module.ActivityModule;
import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(FavouritePlayersActivity favouritePlayersActivity);

}
