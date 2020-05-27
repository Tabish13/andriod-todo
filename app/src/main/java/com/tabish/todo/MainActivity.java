package com.tabish.todo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String TODODATA = "TodoData";
    private TodoRecyclerViewAdapter mtodoRecyclerViewAdapter;
    private List<Todo> mTodoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        Log.d(TAG, "onCreate: created fab");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTodo = new Intent(getApplicationContext(), AddTodo.class);
                startActivity(addTodo);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.todo_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mTodoList = new ArrayList<Todo>();
        SharedPreferences sharedPrefDb = getApplicationContext().getSharedPreferences(TODODATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPrefDb.edit();

        String todoData = sharedPrefDb.getString(TODODATA, null);
        //Log.d(TAG, "onCreate: todoData "+todoData);
        // Using gson for storing as string and converting to java object
        Gson gson = new Gson();
        //Get shared preference data
        if (todoData != null) {
            Type type = new TypeToken<ArrayList<Todo>>() {}.getType();
            mTodoList = gson.fromJson(todoData, type);

        } else {
            //Populate the Data if no data for testing purpose REMOVE once done
            // Dummy data for testing purpose
            for (int i = 0; i < 100; i++) {
                //Put Shared preference data
                ArrayList<Todo.Task> todoTask = new ArrayList<Todo.Task>();
                todoTask.add(new Todo.Task("Title Todo 1", Todo.Task.Status.PENDING, Todo.Task.TodoType.CHECKBOX));
                todoTask.add(new Todo.Task("Title Todo 2", Todo.Task.Status.DONE, Todo.Task.TodoType.STRING));
                mTodoList.add(new Todo("Title" + String.valueOf(i), todoTask));
                String mTodoListString = gson.toJson(mTodoList);
                sharedPrefEditor.putString(TODODATA, mTodoListString);
                sharedPrefEditor.commit();
                //mTodoList.add(new Todo("hello" + String.valueOf(i), "12:12"));
                //mTodoList.add(new Todo("hello2" + String.valueOf(i), "12:13"));
            }
        }


        mtodoRecyclerViewAdapter = new TodoRecyclerViewAdapter(this, mTodoList);
        recyclerView.setAdapter(mtodoRecyclerViewAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent addTodo = new Intent(this, AddTodo.class);
            startActivity(addTodo);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
