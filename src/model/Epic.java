package model;

import java.util.ArrayList;

import service.TaskManager;

public class Epic extends Task {

    TaskManager manager;

    private final ArrayList<Integer> subtaskIds;

    public Epic(String title, String description, Status status) {
        super(title, description, status);
        this.subtaskIds = new ArrayList<>();
    }

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }


    public boolean isDone(Integer id) {
        return manager.getSubtaskById(id).getStatus() == Status.DONE;
    }

    @Override
    public String toString() {
        return "model.Epic{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", Id=" + getId() +
                ", status=" + getStatus() +
                ", subtasksCount=" + subtaskIds.size() +
                '}';
    }

}
