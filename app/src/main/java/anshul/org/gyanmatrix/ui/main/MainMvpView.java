package anshul.org.gyanmatrix.ui.main;

import java.util.List;

import anshul.org.gyanmatrix.data.model.ApiHitCount;
import anshul.org.gyanmatrix.data.model.RecordsEntity;
import anshul.org.gyanmatrix.ui.base.MvpView;


public interface MainMvpView extends MvpView {

    void showPlayers(List<RecordsEntity> ribots);

    void showError();

    void showApiHitCount(ApiHitCount apiHitCount);
}
