package anshul.org.gyanmatrix.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import anshul.org.gyanmatrix.R;
import anshul.org.gyanmatrix.Sorting.ShortByCricketMatch;
import anshul.org.gyanmatrix.Sorting.ShortByFetchOrder;
import anshul.org.gyanmatrix.Sorting.ShortByRuns;
import anshul.org.gyanmatrix.data.model.ApiHitCount;
import anshul.org.gyanmatrix.data.model.RecordsEntity;
import anshul.org.gyanmatrix.ui.base.BaseActivity;
import anshul.org.gyanmatrix.ui.controller.PlayerListAdapter;
import anshul.org.gyanmatrix.ui.presenter.MainPresenter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements MainMvpView {

    private int shortFlag = 0;
    @Inject
    MainPresenter mMainPresenter;
    @Inject
    PlayerListAdapter playerListAdapter;

    @Bind(R.id.playerListView)
    RecyclerView playerListRecycleView;

    @Bind(R.id.showFavourite)
    TextView showFavourites;

    @Bind(R.id.quatoValue)
    TextView quatoValue;

    @Bind(R.id.searchView)
    EditText searchView;

    @Bind(R.id.sortByCricketPlayed)
    Button sortByCricketPlayed;

    @Bind(R.id.sortByRuns)
    Button sortByRuns;

    @Bind(R.id.pb_loading_indicator)
    ProgressBar pIndicator;

    @Bind(R.id.backButton)
    Button backButton;



    public ArrayList<RecordsEntity> recordsEntities=new ArrayList<>();
    public ArrayList<RecordsEntity> searchData=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        backButton.setVisibility(View.GONE);


        mMainPresenter.attachView(this);

        playerListRecycleView.setLayoutManager(new LinearLayoutManager(this));
        playerListRecycleView.setAdapter(playerListAdapter);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()){
                    playerListAdapter.setPlayers(recordsEntities);
                    playerListAdapter.notifyDataSetChanged();
                }
                else {
                    searchData.clear();
                    for(RecordsEntity d : recordsEntities){
                        if(d.getName().contains(s) || d.getCountry().contains(s)){
                            searchData.add(d);
                        }
                        //something here
                    }

                    playerListAdapter.setPlayers(searchData);
                    playerListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mMainPresenter.loadPlayer();
        mMainPresenter.getApiHit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    public void showPlayers(List<RecordsEntity> playerList) {
        recordsEntities.clear();
        recordsEntities.addAll(playerList);
        playerListAdapter.setPlayers(playerList);
        playerListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        popToast("Failed");
    }

    @Override
    public void showApiHitCount(ApiHitCount apiHitCount) {
        quatoValue.setText(apiHitCount.getApi_hits());
    }

    @OnClick(R.id.sortByCricketPlayed)
    void shortByVote() {
        if (shortFlag == 1) {
            shortFlag = 0;
            Collections.sort(recordsEntities,new ShortByFetchOrder());
        } else {
            shortFlag = 1;
            Collections.sort(recordsEntities,new ShortByCricketMatch());
        }
        updateUiOfShortFlag();
        playerListAdapter.setPlayers(recordsEntities);
        playerListAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.sortByRuns)
    void shorByCreation() {
        if (shortFlag == 2) {
            shortFlag = 0;
            Collections.sort(recordsEntities,new ShortByFetchOrder());
        } else {
            shortFlag = 2;
            Collections.sort(recordsEntities,new ShortByRuns());
        }
        updateUiOfShortFlag();
        playerListAdapter.setPlayers(recordsEntities);
        playerListAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.showFavourite)
    void openSaveHistory() {
        startActivity(new Intent(this, FavouritePlayersActivity.class));
    }


    public void updateUiOfShortFlag() {

        switch (shortFlag) {
            case 0:
                sortByCricketPlayed.setBackgroundResource(R.drawable.button_background_white);
                sortByRuns.setBackgroundResource(R.drawable.button_background_white);
                break;
            case 1:
                sortByCricketPlayed.setBackgroundResource(R.drawable.button_background_blue);
                sortByRuns.setBackgroundResource(R.drawable.button_background_white);
                break;
            case 2:
                sortByCricketPlayed.setBackgroundResource(R.drawable.button_background_white);
                sortByRuns.setBackgroundResource(R.drawable.button_background_blue);
                break;
        }
    }

}
