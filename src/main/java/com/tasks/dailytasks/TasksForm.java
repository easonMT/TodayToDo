package com.tasks.dailytasks;

import java.util.List;

public class TasksForm {
    
    private List<Long> selectedTasks;

    public List<Long> getSelectedTasks() {
        return selectedTasks;
    }

    public void setSelectedTasks(List<Long> selectedTasks) {
        this.selectedTasks = selectedTasks;
    }

}
