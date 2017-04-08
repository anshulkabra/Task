package anshul.org.gyanmatrix.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import anshul.org.gyanmatrix.R;
import anshul.org.gyanmatrix.data.model.RecordsEntity;
import anshul.org.gyanmatrix.ui.base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PlayerDetailsActivity extends BaseActivity {

    @Bind(R.id.playerName)
    TextView playerName;

    @Bind(R.id.totalRun)
    TextView totalRun;

    @Bind(R.id.totalMatches)
    TextView totalMatches;

    @Bind(R.id.countryName)
    TextView countryName;

    @Bind(R.id.playerDescription)
    TextView playerDescription;

    @Bind(R.id.posterImage)
    ImageView posterImage;

    @Bind(R.id.actionTitle)
    TextView actionTitle;

    @Bind(R.id.backButton)
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
        ButterKnife.bind(this);

        actionTitle.setText(R.string.player_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            RecordsEntity recordsEntity = (RecordsEntity) extras.getSerializable("Player");
            Picasso.with(getApplicationContext()).load(recordsEntity.getImage()).into(posterImage);
            playerName.setText(recordsEntity.getName());
            totalRun.setText(recordsEntity.getTotal_score());
            totalMatches.setText(recordsEntity.getMatches_played());
            countryName.setText(recordsEntity.getCountry());
            playerDescription.setText(recordsEntity.getDescription());
        }
        else{
            popToast("Unable to open player details");
            finish();
        }

    }

   @OnClick(R.id.backButton)
    void setBackButton() {
        onBackPressed();
    }


}
