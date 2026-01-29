package com.example.studentsupportchartbot;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/chat")
    Call<Map<String, String>> sendMessage(@Body Map<String, String> body);
}
