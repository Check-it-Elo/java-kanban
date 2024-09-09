import java.util.ArrayList;

public class Epic extends Task{

    Manager manager;

    private final ArrayList <Integer> subtaskIDs;

    public Epic(String title, String description, Status status) {
        super(title, description, status);
        this.subtaskIDs = new ArrayList<>();
        }

    public ArrayList<Integer> getSubtaskIDs() {
        return subtaskIDs;
    }


    public boolean isDone(Integer id) {
        return manager.getSubtaskById(id).getStatus() == Status.DONE;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", ID=" + getID() +
                ", status=" + getStatus() +
                ", subtasksCount=" + subtaskIDs.size() +
                '}';
    }

}
