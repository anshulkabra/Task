package anshul.org.gyanmatrix.ui.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import anshul.org.gyanmatrix.R;
import anshul.org.gyanmatrix.data.local.DatabaseHelper;
import anshul.org.gyanmatrix.data.model.RecordsEntity;
import anshul.org.gyanmatrix.injection.ApplicationContext;
import anshul.org.gyanmatrix.ui.main.PlayerDetailsActivity;
import butterknife.Bind;
import butterknife.ButterKnife;


public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.Holder> implements View.OnClickListener {


    private List<RecordsEntity> mItems=new ArrayList<>();

    private DatabaseHelper sqliteHelper;

    private Context mContext;

    @Inject
    public PlayerListAdapter(@ApplicationContext Context context,DatabaseHelper databaseHelper) {
        sqliteHelper=databaseHelper;
        mContext=context;
    }


    public void setPlayers(List<RecordsEntity> items){
        this.mItems = items;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_list_row, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        RecordsEntity data = mItems.get(position);

        holder.playerName.setText(data.getName());
        holder.playerCountry.setText(data.getCountry());
        Picasso.with(mContext).load(data.getImage()).placeholder(R.drawable.icn_placeholder).error(R.drawable.icn_placeholder).into(holder.profleImage);

        holder.favouritePlayer.setOnClickListener(this);
        holder.favouritePlayer.setTag(position);
        holder.listLayer.setOnClickListener(this);
        holder.listLayer.setTag(position);

    }

    @Override
    public void onClick(View v) {
        int pos = 0;
        if (v.getTag() != null)
            pos = Integer.valueOf(String.valueOf(v.getTag()));

        switch (v.getId()) {
            case R.id.favouritePlayer:
                boolean success = sqliteHelper.insertIntoFavourite(mItems.get(pos));

                if (success) {
                    Toast.makeText(mContext, "Added to Favourite", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Player is alredy added to Favourite", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.listLayer:
                Intent bundle = new Intent(mContext, PlayerDetailsActivity.class);
                bundle.putExtra("Player", mItems.get(pos));
                mContext.startActivity(bundle);
                break;


        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        @Bind(R.id.playerName)
        TextView playerName;

        @Bind(R.id.playerCountry)
        TextView playerCountry;

        @Bind(R.id.favouritePlayer)
        TextView favouritePlayer;

        @Bind(R.id.profleImage)
        ImageView profleImage;


        @Bind(R.id.listLayer)
        RelativeLayout listLayer;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}














