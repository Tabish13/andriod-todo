package com.tabish.todo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class TodoRecyclerViewAdapter extends RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoListViewHolder>{

    private static final String TAG = "TodoRecyclerViewAdapter";
    private Context mContext;
    private List<Todo> mTodoList;

    public TodoRecyclerViewAdapter(Context context, List<Todo> todoList) {
        mContext = context;
        mTodoList = todoList;
    }

    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_single_card, parent, false);

        return new TodoListViewHolder(view);
    }

    /*
    * onBindViewhHolder is called when the recyler view need the new data set to be loaded in the view
    *
    * */
    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, final int position) {
        Todo todo = mTodoList.get(position);
        Log.d(TAG, "onBindViewHolder: " + todo.getMtitle() + ">>> Get position "+position);
        holder.grid_title.setText(todo.getMtitle());
        holder.grid_time.setText(new SimpleDateFormat("dd MMM yy").format(todo.getmCreatedTime()));

        // lister for the title/singletodo click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+String.valueOf(position));
                //Onclick of title do task here
                Intent addTodo = new Intent(mContext, AddTodo.class);
                Gson gson = new Gson();
                addTodo.putExtra("todoData", gson.toJson(mTodoList.get(position)));
                addTodo.putExtra("position", position);
                //(ArrayList<String>) getIntent().getSerializableExtra("mylist");
                mContext.startActivity(addTodo);
            }
        });
    }


    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: called ");
        return ((mTodoList != null) && (mTodoList.size() != 0) ? mTodoList.size() : 0);
    }

    /*
    * Function to notify view holder that new data has been added
    * */
    void loadNewData(List<Todo> newTodo) {
        mTodoList = newTodo;
        notifyDataSetChanged();
    }

    /*
    * View holder is used to add the data to the view which is added in the oncreateviewholder
    * using the inflate method to attach the recurring view in the Linear/Grid layout used while the recycler
    * intializing in main activity
    * */
    static class TodoListViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "TodoListViewHolder";
        TextView grid_title = null;
        TextView grid_time = null;

        public TodoListViewHolder(View itemView){
            super(itemView);
            Log.d(TAG, "TodoListViewHolder: Starts");
            this.grid_title = (TextView) itemView.findViewById(R.id.grid_title);
            this.grid_time = (TextView) itemView.findViewById(R.id.grid_time);
        }

    }

}
