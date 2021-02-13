package com.datechnologies.androidtest.login;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.datechnologies.androidtest.POJOs.Post;
import com.datechnologies.androidtest.Repository;

public class LoginViewModel extends AndroidViewModel {
    private final Repository repository;
    private LiveData<Post> postLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        postLiveData = repository.getRemotePostData();
    }

    public void getPostData(String email, String password) {
        Log.d("viewModel", "Hello from LoginViewModel");
        repository.retrievePost(email, password);

    }

    public LiveData<Post> getPostLiveData() {
        return postLiveData;
    }
}
