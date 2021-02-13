package com.datechnologies.androidtest.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.datechnologies.androidtest.POJOs.ChatLogMessageModel;
import com.datechnologies.androidtest.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A recycler view adapter used to display chat log messages in {@link ChatActivity}.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private List<ChatLogMessageModel> chatLogMessageModelList;

    //==============================================================================================
    // Constructor
    //==============================================================================================

    public ChatAdapter() {
        chatLogMessageModelList = new ArrayList<>();
    }

    //==============================================================================================
    // Class Instance Methods
    //==============================================================================================

    public void setChatLogMessageModelList(List<ChatLogMessageModel> chatLogMessageModelList) {
        this.chatLogMessageModelList = chatLogMessageModelList;
        notifyDataSetChanged();
    }

    //==============================================================================================
    // RecyclerView.Adapter Methods
    //==============================================================================================

    @NotNull
    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);

        // We want each view holder to take up an equal amount of space on the screen
        // So since there are four items, we set the height here to a 1/4 of the parents height
        itemView.getLayoutParams().height = parent.getHeight() / 4;
        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder viewHolder, int position) {
        ChatLogMessageModel chatLogMessageModel = chatLogMessageModelList.get(position);

        viewHolder.messageTextView.setText(chatLogMessageModel.getMessage());
        viewHolder.chatUserName.setText(chatLogMessageModel.getName());

        Picasso.get()
                .load(chatLogMessageModel.getAvatar_url())
                .into(viewHolder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        if(chatLogMessageModelList != null) {
            return chatLogMessageModelList.size();
        } else {
            return 0;
        }
    }

    //==============================================================================================
    // ChatViewHolder Class
    //==============================================================================================

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView messageTextView;
        TextView chatUserName;

        public ChatViewHolder(View view) {
            super(view);
            // ShapeableImageView IS - A ImageView, so we can set it equal to an ImageView
            avatarImageView = view.findViewById(R.id.avatarImageView);
            messageTextView = view.findViewById(R.id.messageTextView);
            chatUserName = view.findViewById(R.id.chatUserName);
        }
    }

}
