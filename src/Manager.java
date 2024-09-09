import java.util.ArrayList;
import java.util.HashMap;


public class Manager {

    private HashMap<Integer, Task> tasks;
    protected HashMap<Integer, Subtask> subtasks;
    private HashMap<Integer, Epic> epics;

    protected static int counter = 0;

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
        // subtasks.put(subtask.getID(), subtask);
        Integer epic = subtask.getLinkEpic();
        if (epic != null) {
            subtasks.put(epic, subtask);
            //updateEpicStatus(epic.getID());
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
        for (Epic epic : epics.values()) {
            epic.setStatus(Status.DONE);
        }
        subtasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
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
        if (epics.containsValue(task)) {
            System.err.println("Ошибка");
        } else {
            tasks.put(task.getID(), task);
        }
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getID(), subtask);
        Integer subtaskID = subtask.getLinkEpic();
        if (subtaskID != null) {
            ArrayList<Subtask> epicSubtasks = new ArrayList<>(subtasks.values());
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

    //Удаление по id
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteSubtaskById(int id) {
        for (Epic epic : epics.values()) {
            ArrayList<Integer> epicSubtasksIDs = epic.getSubtaskIDs();

            for (int i = 0; i < epicSubtasksIDs.size(); i++) {
                Integer subtask = epicSubtasksIDs.get(i);

                if (subtask == id) {
                    subtasks.remove(i);
                    break;
                }
            }
        }
    }

    public void deleteEpicById(int id) {
        Epic epic = getEpicById(id);
        if (epic != null) {
            for (Integer subtask : epic.getSubtaskIDs()) {
                deleteSubtaskById(subtask);
            }
            epics.remove(id);
        }
    }


    //ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ

    // Получение списка всех подзадач определённого эпика
    public ArrayList<Integer> getSubtaskIDsByEpicId(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            return epic.getSubtaskIDs();
        }
        return new ArrayList<>();
    }

    public void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            ArrayList<Integer> epicSubtasks = epic.getSubtaskIDs();
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

}

