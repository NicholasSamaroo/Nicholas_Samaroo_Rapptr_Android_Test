package com.datechnologies.androidtest.chat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.datechnologies.androidtest.POJOs.ChatLogMessageModel;
import com.datechnologies.androidtest.Repository;

import java.util.List;

public class ChatViewModel extends AndroidViewModel {
    private final Repository repository;
    private LiveData<List<ChatLogMessageModel>> chatData;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        chatData = repository.getChatBubbleData();
    }

    public void getData() {
        repository.retrieveChatData();
    }

    public LiveData<List<ChatLogMessageModel>> getChatData() {
        return chatData;
    }
}
