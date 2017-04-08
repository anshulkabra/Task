package anshul.org.gyanmatrix.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import anshul.org.gyanmatrix.R;
import anshul.org.gyanmatrix.data.local.DatabaseHelper;
import anshul.org.gyanmatrix.data.model.RecordsEntity;
import anshul.org.gyanmatrix.ui.base.BaseActivity;
import anshul.org.gyanmatrix.ui.controller.PlayerListAdapter;
import anshul.org.gyanmatrix.ui.presenter.DataBasePresenter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavouritePlayersActivity extends BaseActivity implements DataBaseMVPView  {

    @Inject
    PlayerListAdapter playerListAdapter;
    @Inject
    DataBasePresenter mDataBasePresenter;

    @Inject
    DatabaseHelper sqliteHelper;

    @Bind(R.id.playerListView)
    RecyclerView playerList;

    @Bind(R.id.actionTitle)
    TextView actionTitle;

    @Bind(R.id.noResultFound)
    TextView noResultFound;

    @Bind(R.id.backButton)
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_favourite_list);
        ButterKnife.bind(this);
        mDataBasePresenter.attachView(this);
        playerList.setLayoutManager(new LinearLayoutManager(this));
        actionTitle.setText(R.string.favourite_player);
        playerList.setAdapter(playerListAdapter);
        mDataBasePresenter.loadPlayers();
    }


    @OnClick(R.id.backButton)
    void setBackButton() {
        onBackPressed();
    }

    @Override
    public void showPlayers(List<RecordsEntity> playerList) {
        if(playerList.isEmpty()){
            noResultFound.setVisibility(View.VISIBLE);
            return;
        }
        playerListAdapter.setPlayers(playerList);
        playerListAdapter.notifyDataSetChanged();
    }
}
