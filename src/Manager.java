import java.util.HashMap;
import java.util.ArrayList;


public class Manager {

    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Subtask> subtasks;
    private HashMap<Integer, Epic> epics;

    public Manager() {
        this.tasks = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.epics = new HashMap<>();
    }


    //Добавление
    public void addTask(Task task) {
        tasks.put(task.getID(), task);
    }

    public void addSubtask(Subtask subtask) {
        subtasks.put(subtask.getID(), subtask);
        Epic epic = subtask.getLinkEpic();
        if (epic != null) {
            epic.getSubtasks().add(subtask);
            updateEpicStatus(epic.getID());
        }
    }

    public void addEpic(Epic epic) {
        epics.put(epic.getID(), epic);
    }

    //Получение
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    //Удаление
    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
    }

    //Получение по id
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    //Обновление
    public void updateTask(Task task) {
        tasks.put(task.getID(), task);
    }

    public void updateSubtask(Subtask subtask) {
            subtasks.put(subtask.getID(), subtask);
            Epic epic = subtask.getLinkEpic();
            if (epic != null) {
                ArrayList<Subtask> epicSubtasks = epic.getSubtasks();
                for (int i = 0; i < epicSubtasks.size(); i++) {
                    if (epicSubtasks.get(i).getID() == subtask.getID()) {
                        epicSubtasks.remove(i);
                        break;
                    }
                }
                epicSubtasks.add(subtask);
            }
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getID(), epic);
    }

    //Удалениепо id
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteSubtaskById(int id) {
        subtasks.remove(id);

        for (Epic epic : epics.values()) {
            ArrayList<Subtask> epicSubtasks = epic.getSubtasks();

            for (int i = 0; i < epicSubtasks.size(); i++) {
                Subtask subtask = epicSubtasks.get(i);

                if (subtask.getID() == id) {
                    epicSubtasks.remove(i);
                    break;
                }
            }
        }
    }

    public void deleteEpicById(int id) {
        Epic epic = getEpicById(id);
        if (epic != null) {
            for (Subtask subtask : epic.getSubtasks()) {
                deleteSubtaskById(subtask.getID());
            }
            epics.remove(id);
        }
    }


    //ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ

    // Получение списка всех подзадач определённого эпика
    public ArrayList<Subtask> getSubtasksByEpicId(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            return epic.getSubtasks();
        }
        return new ArrayList<>();
    }

    public void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            ArrayList<Subtask> epicSubtasks = epic.getSubtasks();
            if (epicSubtasks.isEmpty()) {
                epic.setStatus(Status.NEW);
            } else {
                boolean allDone = true;
                boolean anyInProgress = false;

                for (Subtask subtask : epicSubtasks) {
                    if (subtask.getStatus() == Status.NEW) {
                        allDone = false;
                    } else if (subtask.getStatus() == Status.IN_PROGRESS) {
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















}

