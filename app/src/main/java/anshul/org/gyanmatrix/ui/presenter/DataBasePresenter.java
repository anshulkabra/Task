package anshul.org.gyanmatrix.ui.presenter;

import java.util.ArrayList;

import javax.inject.Inject;

import anshul.org.gyanmatrix.data.DataManager;
import anshul.org.gyanmatrix.data.model.RecordsEntity;
import anshul.org.gyanmatrix.ui.base.BasePresenter;
import anshul.org.gyanmatrix.ui.main.DataBaseMVPView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by anshul on 12/3/17.
 */

public class DataBasePresenter extends BasePresenter<DataBaseMVPView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public DataBasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(DataBaseMVPView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
    }

    public void loadPlayers() {
        checkViewAttached();
        mSubscription = mDataManager.getPlayers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ArrayList<RecordsEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the players.");

                    }

                    @Override
                    public void onNext(ArrayList<RecordsEntity> recordsEntities) {
                        getMvpView().showPlayers(recordsEntities);
                    }


                });
    }


}
