package com.tabish.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class AddTodo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        Boolean doPositionExist = getIntent().hasExtra("position");
        Log.d("ADDTODO", "onCreate: "+String.valueOf(doPositionExist));
        String singleTodoData = getIntent().getStringExtra("todoData");
        if(singleTodoData != null){
            Gson gson = new Gson();
            Todo todoData = gson.fromJson(singleTodoData, Todo.class);
            Log.d("AddTodo", "onCreate: "+todoData.getMtitle());
            // Log.d("AddTodo", "onCreate: "+todoData.getTodoDetails(0).getTitle());
        }


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }
}
