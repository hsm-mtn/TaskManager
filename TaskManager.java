import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Task{
    private String title;
    private String description;
    private String dueDate;
    private boolean isComplete;

    public Task(String title, String description, String dueDate, boolean isComplete) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
      
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

}



public class TaskManager {

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        loadTasks(tasks);
        while (true) {
            System.out.println("Welcome to task manager how can we help you today:");
            System.out.println("1. add a task");
            System.out.println("2. view one/all tasks");
            System.out.println("3. mark task as cempleted");
            System.out.println("4. delete a task");
            System.out.println("5. exit");
            System.out.println("please choose an option");
            int choice = sc.nextInt();
            sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("you have choose to add a task");
                        System.out.println("please add the info :");
                        System.out.println("the title of the task");
                        String getTitle = sc.nextLine();
                        System.out.println("description");
                        String getDescription = sc.nextLine();
                        System.out.println("due date");
                        String getDueDate = sc.nextLine();
                        tasks.add(new Task(getTitle, getDescription, getDueDate, false));
                        break;
                    case 2:
                        System.out.println("choose your option :");
                        System.out.println("1. view all option");
                        System.out.println("2. view one specific option");
                        int choice2 =sc.nextInt();
                        sc.nextLine();
                        if(choice2 == 1){
                            viewAllTasks(tasks);
                            }

                            else if(choice2 == 2){
                                viewSpecificTask(tasks, sc);
                            }
                                else {
                                    System.out.println("invalide choice");
                                }
                    break;

                    case 3:
                                System.out.println("which task you want to mark as competed");
                                viewAllTasks(tasks);
                                int taskNumber = sc.nextInt();
                                sc.nextLine();
                                if(taskNumber <= 0 || taskNumber > tasks.size()){
                                    System.out.println("invalide choice");
                                }
                                else{
                                    tasks.get(taskNumber - 1).setComplete(true);
                                }
                    break;

                    case 4:
                                System.out.println("choose a task to delete:");
                                viewAllTasks(tasks);
                                int taskNumber2 = sc.nextInt();
                                sc.nextLine();
                                if(taskNumber2 <= 0 || taskNumber2 > tasks.size()){
                                    System.out.println("invalide choice");
                                }
                                else{
                                    tasks.remove(taskNumber2 - 1);
                                }
                    break;

                    case 5:
                                saveTasks(tasks);
                                System.out.println("exiting .....");
                                System.out.println("thank you and goodbye");
                                return;
                
                    default:
                                System.out.println("Invalide choice !");
                        break;
                }
            
        }

    }

    static void viewAllTasks(ArrayList<Task> tasks){
        if(tasks.isEmpty()){
            System.out.println("no task s found");
        }
        else{
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println((i + 1) + ". " + task.getTitle() + " - " + task.getDescription() + " (Due: " + task.getDueDate() + ") [" + (task.isComplete() ? "Completed" : "Pending") + "]");
            }
        }
    }

    static void viewSpecificTask(ArrayList<Task> tasks, Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found!");
        } else {
            System.out.print("Enter task number to view: ");
            int taskNumber = scanner.nextInt();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task task = tasks.get(taskNumber - 1);
                System.out.println("\nTask Details:");
                System.out.println("Title: " + task.getTitle());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Due Date: " + task.getDueDate());
                System.out.println("Status: " + (task.isComplete() ? "Completed" : "Pending"));
            } else {
                System.out.println("Invalid task number!");
            }
        }
    }

    static void saveTasks(ArrayList<Task> tasks){
        try (FileWriter writer = new FileWriter("tasks.txt")) {
            for (Task task : tasks) {
                writer.write(task.getTitle() + "," + task.getDescription() + "," + task.getDueDate() + "," + task.isComplete() + "\n");
            }
        } catch (IOException e) {
                System.out.println("Error saving tasks!");
            }
        }
    
    static void loadTasks(ArrayList<Task> tasks) {
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Task task = new Task(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]));
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("No tasks found or error loading tasks.");
        }
        }


        }
    
