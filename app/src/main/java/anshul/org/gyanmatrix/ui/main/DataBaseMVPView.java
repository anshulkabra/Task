package anshul.org.gyanmatrix.ui.main;

import java.util.List;

import anshul.org.gyanmatrix.data.model.RecordsEntity;
import anshul.org.gyanmatrix.ui.base.MvpView;

/**
 * Created by anshul on 12/3/17.
 */

public interface DataBaseMVPView extends MvpView {
    void showPlayers(List<RecordsEntity> players);
}
