package me.jump360.chatui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import me.jump360.chatui.R;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {

    private List<Integer> emojiList;
    private Context context;

    public EmojiAdapter(List<Integer> emojiList, Context context) {
        this.emojiList = emojiList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.emoji_layout,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.imageViewEmoji.setImageResource(emojiList.get(i));
    }

    @Override
    public int getItemCount() {
        return emojiList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        ImageView imageViewEmoji;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewEmoji = itemView.findViewById(R.id.iv_emoji);

        }
    }
}
