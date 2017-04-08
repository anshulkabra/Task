package anshul.org.gyanmatrix.data;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;


import anshul.org.gyanmatrix.data.local.DatabaseHelper;
import anshul.org.gyanmatrix.data.model.ApiHitCount;
import anshul.org.gyanmatrix.data.model.ItemsEntity;
import anshul.org.gyanmatrix.data.model.RecordsEntity;
import anshul.org.gyanmatrix.data.remote.RestServices;
import rx.Observable;


@Singleton
public class DataManager {

    private final RestServices mRestServices;
    private final DatabaseHelper mDatabaseHelper;


    @Inject
    public DataManager(RestServices restServices,DatabaseHelper databaseHelper) {
        mRestServices = restServices;
        mDatabaseHelper = databaseHelper;
    }


    public Observable<ItemsEntity> getPlayers(String type, String query) {
        return mRestServices.fetchPlayers(type,query);
    }

    public Observable<ApiHitCount> getApiHit(String type, String query) {
        return mRestServices.getApiHit(type,query);
    }

    public Observable<ArrayList<RecordsEntity>> getPlayers() {
        return mDatabaseHelper.getUsers();
    }

}
