import java.util.ArrayList;

public class Epic extends Task{

    private ArrayList <Subtask> subtasks;


    public Epic(String title, String description, Status status) {
        super(title, description, status);
        this.subtasks = new ArrayList<>();
        }

    //добавить подзадачу
    /*
    public void addSubtask (Subtask subtask) {
        subtasks.add(subtask);
    }

     */

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }


    public boolean isDone() {
        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() != Status.DONE) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", ID=" + getID() +
                ", status=" + getStatus() +
                ", subtasksCount=" + subtasks.size() +
                '}';
    }





}
