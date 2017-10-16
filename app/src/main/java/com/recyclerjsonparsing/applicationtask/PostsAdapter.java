package com.recyclerjsonparsing.applicationtask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;

    public List<Object> items;
    private final int POST = 0, COMMENT = 1, USER = 2, TODO = 3, PHOTO = 4;

    public PostsAdapter(Context context, List<Object> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {

        if (items.get(position) instanceof Post) {
            return POST;
        }
        if (items.get(position) instanceof Comment) {
            return COMMENT;
        }
        if (items.get(position) instanceof User) {
            return USER;
        }
        if (items.get(position) instanceof Todo) {
            return  TODO;
        }
        if (items.get(position) instanceof Photo) {
            return  PHOTO;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        switch (viewType) {
            case POST:
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
                vh = new PostViewHolder(v1);
                break;
            case COMMENT:
                View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
                vh = new CommentViewHolder(v2);
                break;
            case USER:
                View v3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
                vh = new UserViewHolder(v3);
                break;
            case PHOTO:
                View v4 = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
                vh = new PhotoViewHolder(v4);
                break;
            case TODO:
                View v5 = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
                vh = new TodoViewHolder(v5);
                break;
            default:
                View v6 = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
                vh = new PostViewHolder(v6);
                break;

        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (this.getItemViewType(position)) {
            case POST:
                PostViewHolder postViewHolder = (PostViewHolder) holder;
                Post post = (Post) items.get(position);
                postViewHolder.user_id.setText("userId: " + String.valueOf(post.getUserId()));
                postViewHolder.id.setText("id: " + String.valueOf(post.getId()));
                postViewHolder.title.setText("title: " + post.getTitle());
                postViewHolder.body.setText("body: " + post.getBody());
                break;
            case COMMENT:
                Comment comment = (Comment) items.get(position);
                CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
                commentViewHolder.post_id.setText("postId: " + String.valueOf(comment.getPostId()));
                commentViewHolder.id.setText("id: " + String.valueOf(comment.getId()));
                commentViewHolder.name.setText("name: " + comment.getName());
                commentViewHolder.email.setText("email: " + comment.getEmail());
                commentViewHolder.body.setText("body: " + comment.getBody());
                break;
            case USER:
                User user = (User) items.get(position);
                UserViewHolder userViewHolder = (UserViewHolder) holder;
                userViewHolder.id.setText("id: " + String.valueOf(user.getId()));
                userViewHolder.name.setText("name: " + (user.getName()));
                userViewHolder.user_name.setText("username: " + user.getUsername());
                userViewHolder.email.setText("email: " + user.getEmail());
                break;
            case PHOTO:
                Photo photo = (Photo) items.get(position);
                PhotoViewHolder photoViewHolder = (PhotoViewHolder) holder;
                Picasso.with(context).load("http://placehold.it/150/24f355.png").into((photoViewHolder).imageView);
                break;
            case TODO:
                Todo todo = (Todo) items.get(position);
                TodoViewHolder todoViewHolder = (TodoViewHolder) holder;
                todoViewHolder.user_id.setText("userId: " + String.valueOf(todo.getUserId()));
                todoViewHolder.id.setText("id: " + String.valueOf(todo.getId()));
                todoViewHolder.title.setText("title: " + todo.getTitle());
                todoViewHolder.completed.setText("completed: " + String.valueOf(todo.isCompleted()));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        TextView user_id;
        TextView id;
        TextView title;
        TextView body;

        public PostViewHolder(View itemView) {
            super(itemView);
            user_id = (TextView) itemView.findViewById(R.id.user_id);
            id = (TextView) itemView.findViewById(R.id.id);
            title = (TextView) itemView.findViewById(R.id.title);
            body = (TextView) itemView.findViewById(R.id.body);
        }
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView post_id;
        TextView id;
        TextView name;
        TextView email;
        TextView body;

        public CommentViewHolder(View itemView) {
            super(itemView);
            post_id = (TextView) itemView.findViewById(R.id.post_id);
            id = (TextView) itemView.findViewById(R.id.id);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            body = (TextView) itemView.findViewById(R.id.body);
        }
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView user_name;
        TextView email;

        public UserViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            name = (TextView) itemView.findViewById(R.id.name);
            user_name = (TextView) itemView.findViewById(R.id.user_name);
            email = (TextView) itemView.findViewById(R.id.email);
        }
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView user_id;
        TextView id;
        TextView title;
        TextView completed;

        public TodoViewHolder(View itemView) {
            super(itemView);
            user_id = (TextView) itemView.findViewById(R.id.user_id);
            id = (TextView) itemView.findViewById(R.id.id);
            title = (TextView) itemView.findViewById(R.id.title);
            completed = (TextView) itemView.findViewById(R.id.completed);
        }
    }
}



