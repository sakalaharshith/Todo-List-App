package com.example.todolist;

public class information {

    String Task;
    String timings;
    information(String t,String time)
    {
        Task=t;
        timings=time;
    }

    String getTask()
    {
        return Task;
    }
    String getTime()
    {
        return timings;
    }
}
