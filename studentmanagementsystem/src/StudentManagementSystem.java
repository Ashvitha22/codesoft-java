package studentmanagementsystem.src;

import java.util.*;
import java.io.*;
import java.util.logging.Logger;
public class StudentManagementSystem {
    private static final Logger LOGGER = Logger.getLogger(StudentManagementSystem.class.getName());
    private List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.txt";

    public StudentManagementSystem() {
        loadStudentsFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
    }

    public void removeStudent(String rollNumber) {
        students.removeIf(student -> student.getRollNumber().equals(rollNumber));
        saveStudentsToFile();
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            LOGGER.info("No students found.");
            LOGGER.info("No students found.");
        } else {
            for (Student student : students) {
                if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
                    LOGGER.info(student.toString());
                }
            }
        }
    }

    public void saveStudentsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.println(s.getName() + "," + s.getRollNumber() + "," + s.getGrade() + "," + s.getAge());
            }
        } catch (IOException e) {
            LOGGER.severe("Error saving students: " + e.getMessage());
        }
    }

    private void loadStudentsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 4) {
                    students.add(new Student(data[0], data[1], data[2], Integer.parseInt(data[3])));
                }
            }
        } catch (FileNotFoundException _) {
            LOGGER.info("No saved student file found.");
        }
    }
}
