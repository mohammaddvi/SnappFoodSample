package com.test.mohammaddvi.snappfood.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.mohammaddvi.snappfood.Fragments.InfoFragment;
import com.test.mohammaddvi.snappfood.Model.Comment;
import com.test.mohammaddvi.snappfood.R;

import java.util.ArrayList;

import static com.test.mohammaddvi.snappfood.Adapter.SectionListDataAdapter.decodeSampledBitmapFromResource;

public class RecyclerViewCommentAdapter extends RecyclerView.Adapter<RecyclerViewCommentAdapter.SingleItemComment> {
    private ArrayList<Comment> comments;
    private Context context;

    public RecyclerViewCommentAdapter(ArrayList<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewCommentAdapter.SingleItemComment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, null);
        return new RecyclerViewCommentAdapter.SingleItemComment(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewCommentAdapter.SingleItemComment holder, int position) {
        Comment comment = comments.get(position);
        holder.username.setText(comment.getName());
        holder.date.setText(comment.getDate());
        holder.commenttext.setText(comment.getComment());
        holder.feelingemoji.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(),
                context.getResources().getIdentifier(comment.getFeelingemoji(),
                        "drawable", context.getPackageName()),
                50, 50));
    }



    @Override
    public int getItemCount() {
        return comments.size();
    }


    public class SingleItemComment extends RecyclerView.ViewHolder {
        TextView username;
        TextView date;
        ImageView feelingemoji;
        TextView commenttext;

        public SingleItemComment(View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.username);
            this.date = itemView.findViewById(R.id.date);
            this.feelingemoji = itemView.findViewById(R.id.feelingemoji);
            this.commenttext = itemView.findViewById(R.id.commenttext);
        }
    }
}
