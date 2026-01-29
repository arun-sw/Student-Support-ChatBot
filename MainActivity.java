package com.example.studentsupportchartbot;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText etMessage;
    ImageButton btnSend;

    List<ChatResponse> chatList;
    ChatAdapter adapter;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.chatRecyclerView);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        chatList = new ArrayList<>();
        adapter = new ChatAdapter(chatList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        apiService = ApiClient.getClient().create(ApiService.class);

        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().trim();

            if (TextUtils.isEmpty(msg)) {
                etMessage.setError("Enter message");
                return;
            }

            // Add user message
            chatList.add(new ChatResponse(msg, true));
            adapter.notifyItemInserted(chatList.size() - 1);

            etMessage.setText("");

            // Call backend
            getBotReply(msg);
        });
    }

    private void getBotReply(String question) {

        Map<String, String> body = new HashMap<>();
        body.put("query", question);

        apiService.sendMessage(body).enqueue(new Callback<Map<String, String>>() {

            @Override
            public void onResponse(Call<Map<String, String>> call,
                                   Response<Map<String, String>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    String reply = response.body().get("answer");

                    chatList.add(new ChatResponse(reply, false));
                    adapter.notifyItemInserted(chatList.size() - 1);
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                chatList.add(new ChatResponse("❌ Server not reachable", false));
                adapter.notifyItemInserted(chatList.size() - 1);
            }
        });
    }
}
