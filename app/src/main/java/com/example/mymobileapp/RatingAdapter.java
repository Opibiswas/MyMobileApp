package com.example.mymobileapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {

    private List<RatingDetails> ratingList;

    public RatingAdapter(List<RatingDetails> ratingList) {
        this.ratingList = ratingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {   //crate veiwholder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RatingDetails ratingDetails = ratingList.get(position);
        holder.bind(ratingDetails);
    }

    @Override
    public int getItemCount() {
        return ratingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userIdTextView;
        private TextView ratingTextView;
        private TextView commentTextView;

        public ViewHolder(@NonNull View itemView) {       //itemView contain the layout
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
