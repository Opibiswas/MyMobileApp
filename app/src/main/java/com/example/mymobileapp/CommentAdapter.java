package com.example.mymobileapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<RatingDetails> commentList;

    public CommentAdapter(List<RatingDetails> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RatingDetails ratingDetails = commentList.get(position);
        holder.bind(ratingDetails);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userIdTextView;
        private TextView ratingTextView;
        private TextView commentTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userIdTextView = itemView.findViewById(R.id.textViewUserId);
            ratingTextView = itemView.findViewById(R.id.textViewRating);
            commentTextView = itemView.findViewById(R.id.textViewComment);
        }

        public void bind(RatingDetails ratingDetails) {
            userIdTextView.setText("User ID: " + ratingDetails.getUserId());
            ratingTextView.setText("Rating: " + ratingDetails.getRating());
            commentTextView.setText("Comment: " + ratingDetails.getComment());
        }
    }
}
