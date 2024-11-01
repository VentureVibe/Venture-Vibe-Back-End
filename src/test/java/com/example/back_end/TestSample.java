// WarehouseProductivity.java
public class WarehouseProductivity {
    private static final String APP_NAME = "Warehouse Productivity Monitoring";
    private static final String VERSION = "1.0.0";
    private static double productivityScore = 0.0;

    public static void main(String[] args) {
        System.out.println("App Name: " + APP_NAME);
        System.out.println("Version: " + VERSION);
    }
}

public class WarehouseProductivity {
    private static final String APP_NAME = "Warehouse Productivity Monitoring";
    private static final String VERSION = "1.0.0";
    private static double productivityScore = 0.0;

    public static void main(String[] args) {
        System.out.println("App Name: " + APP_NAME);
        System.out.println("Version: " + VERSION);
    }
}

// Worker class
class Worker {
    private int id;
    private String name;
    private int tasksCompleted;
    private int totalTasks;

    public Worker(int id, String name, int tasksCompleted, int totalTasks) {
        this.id = id;
        this.name = name;
        this.tasksCompleted = tasksCompleted;
        this.totalTasks = totalTasks;
    }

    public double calculateEfficiency() {
        return (double) tasksCompleted / totalTasks * 100;
    }

    public String toString() {
        return String.format("Worker{id=%d, name='%s', efficiency=%.2f%%}", id, name, calculateEfficiency());
    }
}
