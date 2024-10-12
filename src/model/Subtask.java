package model;

public class Subtask extends Task {

    private Integer epicId;

    public Subtask(String title, String description, Status status, Integer epicId) {
        super(title, description, status);
        this.epicId = epicId;
    }

    public Integer getLinkEpic() {
        return epicId;
    }


    @Override
    public String toString() {
        return "model.Subtask{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", Id=" + getId() +
                ", status=" + getStatus() +
                '}';
    }

}