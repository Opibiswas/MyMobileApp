package com.example.mymobileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class BlogPostActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<BlogPost, BlogPostViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_post);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Photos");

        FirebaseRecyclerOptions<BlogPost> options =
                new FirebaseRecyclerOptions.Builder<BlogPost>()
                        .setQuery(databaseReference, BlogPost.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<BlogPost, BlogPostViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BlogPostViewHolder holder, int position, @NonNull BlogPost model) {
                holder.setCaption(model.getCaption());
                holder.setImageUrl(model.getPhotoUrl());
                holder.setLikeCount(model.getLikes());
                holder.setDislikeCount(model.getDislikes());
                holder.bind(getRef(position).getKey());
            }

            @NonNull
            @Override
            public BlogPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog_post, parent, false);
                return new BlogPostViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public static class BlogPostViewHolder extends RecyclerView.ViewHolder {
        View mView;

        // Declare a boolean variable to track whether the user has already liked or disliked the post
        boolean hasInteracted = false;

        public BlogPostViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setCaption(String caption) {
            TextView textViewCaption = mView.findViewById(R.id.textViewCaption);
            textViewCaption.setText(caption);
        }

        public void setImageUrl(String imageUrl) {
            ImageView imageView = mView.findViewById(R.id.imageView);
            Glide.with(mView).load(imageUrl).into(imageView);
        }

        public void setLikeCount(long likeCount) {
            TextView textViewLikes = mView.findViewById(R.id.textViewLikes);
            textViewLikes.setText(String.valueOf(likeCount));
        }

        public void setDislikeCount(long dislikeCount) {
            TextView textViewDislikes = mView.findViewById(R.id.textViewDislikes);
            textViewDislikes.setText(String.valueOf(dislikeCount));
        }

        public void bind(String postId) {
            // Get references to like and dislike buttons
            ImageButton likeButton = mView.findViewById(R.id.likeButton);
            ImageButton dislikeButton = mView.findViewById(R.id.dislikeButton);

            // Set click listeners for like and dislike buttons
            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!hasInteracted) {
                        likePost(postId);
                        hasInteracted = true; // Update the interaction status
                    } else {
                        Toast.makeText(itemView.getContext(), "You have already interacted with this post", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            dislikeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!hasInteracted) {
                        dislikePost(postId);
                        hasInteracted = true; // Update the interaction status
                    } else {
                        Toast.makeText(itemView.getContext(), "You have already interacted with this post", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        // Method to handle like button click
        private void likePost(String postId) {
            DatabaseReference postRef = FirebaseDatabase.getInstance().getReference().child("Photos").child(postId);
            postRef.child("likes").setValue(ServerValue.increment(1)); // Increment likes by 1
            postRef.child("dislikes").setValue(0); // Reset dislikes to 0 for this post
            Toast.makeText(itemView.getContext(), "Liked", Toast.LENGTH_SHORT).show();
        }

        // Method to handle dislike button click
        private void dislikePost(String postId) {
            DatabaseReference postRef = FirebaseDatabase.getInstance().getReference().child("Photos").child(postId);
            postRef.child("dislikes").setValue(ServerValue.increment(1)); // Increment dislikes by 1
            postRef.child("likes").setValue(0); // Reset likes to 0 for this post
            Toast.makeText(itemView.getContext(), "Disliked", Toast.LENGTH_SHORT).show();
        }

    }
}
