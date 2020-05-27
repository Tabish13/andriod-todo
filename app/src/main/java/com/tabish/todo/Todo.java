package com.tabish.todo;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

public class Todo {
    private String mtitle;
    private Date mUpdatedtime;
    private Date mCreatedTime;
    private ArrayList<Task> mTodoDetails;

    public Todo(String title, ArrayList<Task> todoDetails){
        mtitle = title;
        mUpdatedtime = new Date();
        mCreatedTime = new Date();
        mTodoDetails = todoDetails;

    }

    public String getMtitle() {
        return mtitle;
    }



    public Date getmUpdatedtime() {
        return mUpdatedtime;
    }

    public void setmUpdatedtime(Date newUpdatedDate) {
         mUpdatedtime = newUpdatedDate;
    }

    public Date getmCreatedTime(){
        return mCreatedTime;
    }

    public Task getTodoDetails(int position) {
        return mTodoDetails.get(position);
    }

    public void addTodoDetails(Task todoDetails) {
        mTodoDetails.add(todoDetails);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "mtitle='" + mtitle + '\'' +
                ", mCreatedTime='" + mCreatedTime + '\'' +
                ", mUpdatedtime='" + mUpdatedtime + '\'' +
                ", mtodoDetails='" + mTodoDetails + '\'' +
                '}';
    }

    public static class Task{
        private String title;
        private TodoType type;
        private Status status;
        private Date modifiedDate;
        private Date createdDate;

        enum Status{
            DONE,
            PENDING
        }
        enum TodoType{
            CHECKBOX,
            STRING
        }

        public Task(String title, Status status,TodoType type) {
            this.title = title;
            this.type = type;
            this.modifiedDate = new Date();
            this.createdDate = new Date();
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public TodoType getType() {
            return type;
        }

        public void setType(TodoType type) {
            this.type = type;
        }

        public Date getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(Date modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public Date getCreatedDate() {
            return createdDate;
        }
    }
}


