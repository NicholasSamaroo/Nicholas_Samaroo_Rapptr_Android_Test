package com.datechnologies.androidtest;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.datechnologies.androidtest.POJOs.ChatLogMessageModel;
import com.datechnologies.androidtest.POJOs.Data;
import com.datechnologies.androidtest.POJOs.Post;
import com.datechnologies.androidtest.api.ChatAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private ChatAPI chatAPI;
    private MutableLiveData<List<ChatLogMessageModel>> chatBubbleData;
    private MutableLiveData<Post> postMutableLiveData;

    public Repository() {
        chatBubbleData = new MutableLiveData<>();
        postMutableLiveData = new MutableLiveData<>();
        chatAPI = RetrofitInstance.getRetrofitInstance().create(ChatAPI.class);
    }

    public void retrieveChatData() {
        chatAPI.getChatBubbles().enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.isSuccessful() && response.body() != null) {
                    chatBubbleData.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    public void retrievePost(String email, String password) {
        Log.d("repository", "Hello from retrievePost");
        chatAPI.post(email, password).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful() && response.body() != null) {
                    postMutableLiveData.setValue(response.body());
                } else {
                    Log.d("Unsuccessful", response.message());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    public LiveData<List<ChatLogMessageModel>> getChatBubbleData() {
        return chatBubbleData;
    }

    public LiveData<Post> getRemotePostData() {
        return postMutableLiveData;
    }
}
