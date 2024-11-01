import java.util.ArrayList;
import java.util.List;

public class WarehouseProductivity {
    private static final String APP_NAME = "Warehouse Productivity Monitoring";
    private static final String VERSION = "1.0.0";
    private static double productivityScore = 0.0;
    private static List<Worker> workers = new ArrayList<>();
    private static List<Section> sections = new ArrayList<>();

    public static void main(String[] args) {
        initializeData();
        calculateProductivityScore();
        System.out.println("App Name: " + APP_NAME);
        System.out.println("Version: " + VERSION);
        System.out.printf("Productivity Score: %.2f%%\n", productivityScore);
    }

    private static void initializeData() {
        for (int i = 1; i <= 10; i++) {
            workers.add(new Worker(i, "Worker" + i, 80 + i, 100));
            sections.add(new Section("Section" + i, 100, 80 + i));
        }
    }

    private static void calculateProductivityScore() {
        double totalEfficiency = 0;
        for (Worker worker : workers) {
            totalEfficiency += worker.calculateEfficiency();
        }
        productivityScore = totalEfficiency / workers.size();
    }
}

// Worker and Section classes from previous commits
