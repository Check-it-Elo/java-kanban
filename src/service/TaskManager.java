package service;

import model.Task;
import model.Subtask;
import model.Epic;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    // Методы для управления задачами
    public void addTask(Task task);

    public void deleteTaskByid(int id);

    public Task getTaskByid(int id);

    public ArrayList<Task> getAllTasks();

    public void deleteAllTasks();

    public void updateTask(Task task);

    // Методы для управления подзадачами
    public void addSubtask(Subtask subtask);

    public void deleteSubtaskByid(int id);

    public Subtask getSubtaskByid(int id);

    public ArrayList<Subtask> getAllSubtasks();

    public void deleteAllSubtasks();

    public void updateSubtask(Subtask subtask);

    // Методы для управления эпиками
    public void addEpic(Epic epic);

    public void deleteEpicByid(int id);

    public Epic getEpicByid(int id);

    public ArrayList<Epic> getAllEpics();

    public void deleteAllEpics();

    public void updateEpic(Epic epic);

    // Методы для обновления статусов
    public void updateEpicStatus(int epicid);

    public ArrayList<Integer> getSubtaskidsByEpicid(int id);

    Task getTask(int id);

    Subtask getSubtask(int id);

    Epic getEpic(int id);

    List<Task> getHistory();
}
