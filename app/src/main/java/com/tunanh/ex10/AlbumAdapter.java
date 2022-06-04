package com.tunanh.ex10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private List<Album> albums;
    private Context context;

    public AlbumAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout,parent,false);
        return new AlbumAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {
        Album album = albums.get(position);

        if (album == null) {
            return;
        } else {
            String strId = String.valueOf(album.getId());
            holder.tvId.setText(strId);
            holder.tvTitle.setText(album.getTitle());
            Picasso.get().load(album.getAvt_url()).fit().into(holder.imgAlbum);
        }
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvId;
        private TextView tvTitle;
        private ImageView imgAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId= itemView.findViewById(R.id.tv_id);
            tvTitle= itemView.findViewById(R.id.tv_title);
            imgAlbum=itemView.findViewById(R.id.img_album);

        }
    }
}
