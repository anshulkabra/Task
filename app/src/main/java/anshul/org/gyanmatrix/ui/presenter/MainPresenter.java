package anshul.org.gyanmatrix.ui.presenter;

import javax.inject.Inject;

import anshul.org.gyanmatrix.data.DataManager;
import anshul.org.gyanmatrix.data.model.ApiHitCount;
import anshul.org.gyanmatrix.data.model.ItemsEntity;
import anshul.org.gyanmatrix.injection.ConfigPersistent;
import anshul.org.gyanmatrix.ui.base.BasePresenter;
import anshul.org.gyanmatrix.ui.main.MainMvpView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;


@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadPlayer() {
        checkViewAttached();
        mSubscription = mDataManager.getPlayers("json", "list_player")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ItemsEntity>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the players.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(ItemsEntity itemsEntity) {

                        getMvpView().showPlayers(itemsEntity.getRecords());

                    }
                });
    }

    public void getApiHit() {
        checkViewAttached();
        mSubscription = mDataManager.getApiHit("json", "api_hits")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ApiHitCount>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the players.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(ApiHitCount apiHitCount) {

                        getMvpView().showApiHitCount(apiHitCount);

                    }
                });
    }
}
