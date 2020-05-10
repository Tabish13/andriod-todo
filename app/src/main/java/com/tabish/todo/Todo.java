package com.tabish.todo;

public class Todo {
    private String mtitle;
    private String mtime;

    public Todo(String title, String time){
        mtitle = title;
        mtime = time;
    }

    public String getMtitle() {
        return mtitle;
    }



    public String getMtime() {
        return mtime;
    }



    @Override
    public String toString() {
        return "Todo{" +
                "mtitle='" + mtitle + '\'' +
                ", mtime='" + mtime + '\'' +
                '}';
    }
}
