package com.tasks.dailytasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* 
 * POST - Create
 * GET - Read
 * PUT - Update
 * DELETE - Delete
 */

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    
    @GetMapping("/")
    public String welcomePage() {
        return "welcome";
    }

    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/add")
    public String addNewTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add";
    }

    @PostMapping("/add")
    public String addNewTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/update")
    public String updateTaskForm(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("selectedTask", new Task());
        return "update";
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String updateSelectedTask(@ModelAttribute Task selectedTask) {
        Long taskId = selectedTask.getId();
        Task updateTask = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task with id: " + taskId + " not found"));

        updateTask.setName(selectedTask.getName());
        updateTask.setTime(selectedTask.getTime());
        updateTask.setStatus(selectedTask.getStatus());  

        taskRepository.save(updateTask);

        return "redirect:/tasks";
    }

    @GetMapping("/delete")
    public String deleteTaskForm(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskForm", new TasksForm());
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteSelectedTask(@ModelAttribute TasksForm tasksForm) {
        List<Long> selectedTasks = tasksForm.getSelectedTasks();
        for (Long taskId : selectedTasks) {
            taskRepository.deleteById(taskId);
        }
        return "redirect:/tasks";
    }

    
}
