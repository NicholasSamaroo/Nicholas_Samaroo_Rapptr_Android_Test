package com.datechnologies.androidtest.chat;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.datechnologies.androidtest.POJOs.ChatLogMessageModel;
import com.datechnologies.androidtest.Repository;

import java.util.List;

public class ChatViewModel extends AndroidViewModel {
    private final Repository repository;
    private LiveData<List<ChatLogMessageModel>> chatData;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        //chatData = repository.getChatBubbleData();
    }

    /*public void getData() {
        repository.retrieveChatData();
    }*/

    public LiveData<List<ChatLogMessageModel>> getChatData() {
       if(chatData == null) {
           // We only fetch the data once by checking if chatData is null
           // In this scenario, the data is the same so we do not want to be re-fetching it.
           Log.d("getChatData", "getChatData called");
           chatData = repository.getChatBubbleData();
           repository.retrieveChatData();
       }
       return chatData;
    }
}
