/*
public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
    }
}

 */

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        //СОЗДАНИЕ
        Task task1 = new Task("Задача 1", "Описание для задачи 1", Status.NEW);
        Task task2 = new Task("Задача 2", "Описание для задачи 2", Status.NEW);
        manager.addTask(task1);
        manager.addTask(task2);

        Epic epic1 = new Epic("Эпик 1", "Описание для эпика 1", Status.NEW);
        manager.addEpic(epic1);
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание для подзадачи 1", Status.NEW, epic1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание для подзадачи 2", Status.NEW, epic1);
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        Epic epic2 = new Epic("Эпик 2", "Описание для эпика 2", Status.NEW);
        manager.addEpic(epic2);
        Subtask subtask3 = new Subtask("Подзадача 3", "Описание для подзадачи 3", Status.NEW, epic2);
        manager.addSubtask(subtask3);

        //ПЕЧАТЬ
        System.out.println("Все задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nВсе эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nВсе подзадачи:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        //ИЗМЕНЕНИЕ СТАСУСОВ
        task1.changeTaskToDone();
        subtask1.changeTaskToInProgress();
        subtask2.changeTaskToDone();

        manager.updateTask(task1);
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subtask2);

        manager.updateEpicStatus(epic1.getID());
        manager.updateEpicStatus(epic2.getID());

        System.out.println("\nПосле изменений статусов:");
        System.out.println(task1);
        System.out.println(subtask1);
        System.out.println(subtask2);
        System.out.println(epic1);
        System.out.println(epic2);

        //УДАЛЕНИЕ
        manager.deleteTaskById(task1.getID());
        manager.deleteEpicById(epic1.getID());

        System.out.println("\nВсе задачи после удаления задачи 1:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("\nВсе эпики после удаления эпика 1:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("\nВсе подзадачи:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }
    }
}