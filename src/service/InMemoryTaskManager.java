package service;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    private HashMap<Integer, Task> tasks;
    protected HashMap<Integer, Subtask> subtasks;
    private HashMap<Integer, Epic> epics;

    private HistoryManager historyManager;

    public static int counter = 0;

    public InMemoryTaskManager() {
        this.tasks = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.historyManager = Managers.getDefaultHistory();
    }

    //Добавление
    @Override
    public void addTask(Task task) {
        tasks.put(task.getid(), task);
    }

    @Override
    public void addSubtask(Subtask subtask) {
        // subtasks.put(subtask.getid(), subtask);
        Integer epic = subtask.getLinkEpic();
        if (epic != null) {
            subtasks.put(subtask.getid(), subtask);
            //updateEpicStatus(epic.getid());
        }
    }

    @Override
    public void addEpic(Epic epic) {
        epics.put(epic.getid(), epic);
    }

    //Получение
    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    //Удаление
    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        for (Epic epic : epics.values()) {
            ArrayList<Integer> subtaskids = epic.getSubtaskids();

            for (Integer id : subtaskids) {
                subtasks.remove(id);
            }
            updateEpicStatus(epic.getid());
        }
        subtasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    //Получение по id
    @Override
    public Task getTaskByid(int id) {
        Task task = tasks.get(id);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public Subtask getSubtaskByid(int id) {
        Subtask subtask = subtasks.get(id);
        if (subtask != null) {
            historyManager.add(subtask);
        }
        return subtask;
    }

    @Override
    public Epic getEpicByid(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

    //Обновление
    @Override
    public void updateTask(Task task) {
        if (epics.containsValue(task)) {
            System.err.println("Ошибка");
        } else {
            tasks.put(task.getid(), task);
        }
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getid(), subtask);
        Integer subtaskid = subtask.getLinkEpic();
        if (subtaskid != null) {
            ArrayList<Subtask> epicSubtasks = new ArrayList<>(subtasks.values());
            for (int i = 0; i < epicSubtasks.size(); i++) {
                if (epicSubtasks.get(i).getid() == subtask.getid()) {
                    epicSubtasks.remove(i);
                    break;
                }
            }
            epicSubtasks.add(subtask);
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getid())) {
            Epic existingEpic = epics.get(epic.getid());

            existingEpic.setTitle(epic.getTitle());
            existingEpic.setDescription(epic.getDescription());

            epics.put(epic.getid(), existingEpic);
        }
    }

    //Удаление по id
    @Override
    public void deleteTaskByid(int id) {
        tasks.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void deleteSubtaskByid(int id) {
        for (Epic epic : epics.values()) {
            ArrayList<Integer> epicSubtasksids = epic.getSubtaskids();

            for (int i = 0; i < epicSubtasksids.size(); i++) {
                Integer subtask = epicSubtasksids.get(i);

                if (subtask.equals(id)) {
                    epicSubtasksids.remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public void deleteEpicByid(int id) {
        Epic epic = getEpicByid(id);
        if (epic != null) {
            for (Integer subtask : epic.getSubtaskids()) {
                deleteSubtaskByid(subtask);
            }
            epics.remove(id);
        }
    }

    //ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ

    // Получение списка всех подзадач определённого эпика
    @Override
    public ArrayList<Integer> getSubtaskidsByEpicid(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            return epic.getSubtaskids();
        }
        return new ArrayList<>();
    }

    @Override
    public void updateEpicStatus(int epicid) {
        Epic epic = epics.get(epicid);
        if (epic != null) {
            ArrayList<Integer> epicSubtasks = epic.getSubtaskids();
            if (epicSubtasks.isEmpty()) {
                epic.setStatus(Status.NEW);
            } else {
                boolean allDone = true;
                boolean anyInProgress = false;

                for (Integer subtask : epicSubtasks) {
                    if (subtasks.get(subtask).getStatus() == Status.NEW) {
                        allDone = false;
                    } else if (subtasks.get(subtask).getStatus() == Status.IN_PROGRESS) {
                        anyInProgress = true;
                    }
                }

                if (allDone) {
                    epic.setStatus(Status.DONE);
                } else if (anyInProgress) {
                    epic.setStatus(Status.IN_PROGRESS);
                } else {
                    epic.setStatus(Status.NEW);
                }
            }
        }
    }

    @Override
    public Task getTask(int id) {
        Task task = tasks.get(id);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public Subtask getSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        if (subtask != null) {
            historyManager.add(subtask);
        }
        return subtask;
    }


    @Override
    public Epic getEpic(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

//    private void addToHistory(Task task) {
//        if (history.size() >= HISTORY_LIMIT) {
//            history.remove(0);
//        }
//        history.add(task);
//    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

}
