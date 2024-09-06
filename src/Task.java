public class Task {

    String title;
    String description;
    int ID;
    Status status;
    private static int counter = 0;


    public Task(String title, String description, Status status) {
        this.title = title;
        this.description = description;
        this.ID = counter++;;
        this.status = status;
    }




    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getID() {
        return ID;
    }

    public Status getStatus() {
        return status;
    }



    public void changeTaskToInProgress() {
        status = Status.IN_PROGRESS;
    }

    public void changeTaskToDone() {
        status = Status.DONE;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return ID == task.ID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(ID);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ID=" + ID +
                ", status=" + status +
                '}';
    }




    //Если нужно менять значения полей у задачи вручную.
    // хз нужно или нет

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
