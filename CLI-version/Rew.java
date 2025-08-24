import java.util.ArrayList;
import java.util.Scanner;

interface DisplayInfo {
    String getanameInfo();
    int getrollnoInfo();
}

interface Displaydiscount {
    int getdiscount();
}

abstract class Student {
    protected String name;
    protected int rollno;
    protected String subject;
    protected String stream;

    public Student(String name, int rollno, String subject, String stream) {
        this.name = name;
        this.rollno = rollno;
        this.subject = subject;
        this.stream = stream;
    }

    public String getSubject() {
        return subject;
    }

    public String getStream() {
        return stream;
    }
}

class Batch extends Student implements DisplayInfo {
    private String status; // To store part-time or full-time status

    public Batch(String name, String subject, String stream, int rollno, String status) {
        super(name, rollno, subject, stream);
        this.status = status;
    }

    @Override
    public String getanameInfo() {
        // Use if-else to customize display based on part-time or full-time
        if (status.equalsIgnoreCase("part-time")) {
            return "Name: " + name + " (Part-Time)";
        } else if (status.equalsIgnoreCase("full-time")) {
            return "Name: " + name + " (Full-Time)";
        } else {
            return "Name: " + name + " (Status Unknown)";
        }
    }

    @Override
    public int getrollnoInfo() {
        return rollno;
    }

    public String getStatus() {
        return status;
    }
}

class CourseCost extends Batch implements Displaydiscount {
    private int oldprice;

    public CourseCost(String name, String subject, String stream, int rollno, String status, int oldprice) {
        super(name, subject, stream, rollno, status);
        this.oldprice = oldprice;
    }

    @Override
    public int getdiscount() {
        // Optional: Adjust discount based on status (example)
        if (getStatus().equalsIgnoreCase("part-time")) {
            return (int) (oldprice * 0.6); // 40% discount for part-time
        } else {
            return (int) (oldprice * 0.7); // 30% discount for full-time or other
        }
    }

    public int oldprice() {
        return oldprice;
    }
}

public class Rew {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<CourseCost> studentList = new ArrayList<>(); // To store student records

        while (true) {
            // Get student details
            System.out.println("Enter student name:");
            String name = scanner.nextLine();

            System.out.println("Enter roll number:");
            int rollno;
            try {
                rollno = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid roll number! Please enter a valid integer.");
                continue; // Skip to next iteration if input is invalid
            }

            System.out.println("Enter subject:");
            String subject = scanner.nextLine();

            System.out.println("Enter stream:");
            String stream = scanner.nextLine();

            System.out.println("Enter status (part-time or full-time):");
            String status = scanner.nextLine().trim();

            System.out.println("Enter original course price:");
            int oldprice;
            try {
                oldprice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price! Please enter a valid integer.");
                continue; // Skip to next iteration if input is invalid
            }

            // Create CourseCost object and add to list
            CourseCost student = new CourseCost(name, subject, stream, rollno, status, oldprice);
            studentList.add(student);

            // Display the added student's info
            System.out.println("Student added successfully!");
            System.out.println(student.getanameInfo());
            System.out.println("Roll Number: " + student.getrollnoInfo());
            System.out.println("Subject: " + student.getSubject());
            System.out.println("Stream: " + student.getStream());
            System.out.println("Status: " + student.getStatus());
            System.out.println("Original Price: " + student.oldprice());
            System.out.println("Discounted Price: " + student.getdiscount());

            // Ask if user wants to add another student
            System.out.println("Do you want to add another student? (yes/no)");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (!choice.equals("yes")) {
                break; // Exit loop if user doesn't say "yes"
            }
        }

        // Display all stored students
        System.out.println("\n--- Student Directory ---");
        if (studentList.isEmpty()) {
            System.out.println("No students in the directory.");
        } else {
            for (CourseCost student : studentList) {
                System.out.println("--------------------");
                System.out.println(student.getanameInfo());
                System.out.println("Roll Number: " + student.getrollnoInfo());
                System.out.println("Subject: " + student.getSubject());
                System.out.println("Stream: " + student.getStream());
                System.out.println("Status: " + student.getStatus());
                System.out.println("Original Price: " + student.oldprice());
                System.out.println("Discounted Price: " + student.getdiscount());
            }
        }

        scanner.close(); // Close the scanner
    }
}