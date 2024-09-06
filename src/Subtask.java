public class Subtask extends Task {

    final private Epic linkEpic;

    public Subtask(String title, String description, Status status, Epic linkEpic) {
        super(title, description, status);
        this.linkEpic = linkEpic;
    }


    public Epic getLinkEpic() {
        return linkEpic;
    }


    @Override
    public String toString() {
        return "Subtask{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", ID=" + getID() +
                ", status=" + getStatus() +
                ", linkEpic=" + (linkEpic != null ? linkEpic.getID() : "null") +
                '}';
    }








}