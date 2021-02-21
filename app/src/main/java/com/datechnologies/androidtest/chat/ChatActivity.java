package com.datechnologies.androidtest.chat;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.POJOs.ChatLogMessageModel;

import java.util.List;

/**
 * Screen that displays a list of chats from a chat log.
 */
public class ChatActivity extends AppCompatActivity {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private ChatAdapter chatAdapter;
    private ChatViewModel chatViewModel;

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context) {
        Intent starter = new Intent(context, ChatActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        instantiateRecyclerView();
        /*
            Followed a MVVM / separation of concerns style. So we initialize our
            ViewModel here and then in the following function call we set up our observer.
            All data operations are handled in the Repository. When the data changes, we can
            update our recycler view.
         */
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        setObserver();
        //fetchData();

        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.

        // TODO: Retrieve the chat data from http://dev.rapptrlabs.com/Tests/scripts/chat_log.php
        // TODO: Parse this chat data from JSON into ChatLogMessageModel and display it.
    }

    private void instantiateRecyclerView() {
        RecyclerView chatRecyclerView = findViewById(R.id.recyclerView);
        chatAdapter = new ChatAdapter();
        chatRecyclerView.setAdapter(chatAdapter);
        chatRecyclerView.setHasFixedSize(true);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void setObserver() {
        chatViewModel.getChatData().observe(this, new Observer<List<ChatLogMessageModel>>() {
            @Override
            public void onChanged(List<ChatLogMessageModel> chatLogMessageModels) {
                Log.d("onChanged", "onChangedCalled");
                chatAdapter.setChatLogMessageModelList(chatLogMessageModels);
            }
        });
    }

    /*private void fetchData() {
        Log.d("fetch", "getting data");
        chatViewModel.getData();
    }

    private void updateRecyclerView() {
        Log.d("updaterecycler", "called");
        // Does not load image unless the protocol is https vs. being http
        // So we loop through our fetched data and change this before we set it as our list
        // Compared to doing it on onBindViewHolder which seems wasteful
        chatAdapter.setChatLogMessageModelList(chatData);
    }*/

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
