package com.tabish.todo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class TodoRecyclerViewAdapter extends RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoListViewHolder> {

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

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, int position) {
        Todo todo = mTodoList.get(position);
        Log.d(TAG, "onBindViewHolder: " + todo.getMtitle() + ">>> Get position "+position);
        holder.grid_title.setText(todo.getMtitle());
        holder.grid_time.setText(todo.getMtime());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: called");
        return ((mTodoList != null) && (mTodoList.size() != 0) ? mTodoList.size() : 0);
    }

    void loadNewData(List<Todo> newTodo) {
        mTodoList = newTodo;
        notifyDataSetChanged();
    }

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
