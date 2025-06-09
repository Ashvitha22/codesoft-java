package studentmanagementsystem.src;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            logger.info("\n=== Student Management System ===");
            logger.info("1. Add Student");
            logger.info("2. Remove Student");
            logger.info("3. Search Student");
            logger.info("4. Display All Students");
            logger.info("5. Edit Student");
            logger.info("6. Exit");
            logger.info("Choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    logger.info("Enter name: ");
                    String name = scanner.nextLine();
                    logger.info("Enter roll number: ");
                    String roll = scanner.nextLine();
                    logger.info("Enter grade: ");
                    String grade = scanner.nextLine();
                    logger.info("Enter age: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    sms.addStudent(new Student(name, roll, grade, age));
                    logger.info("Student added.");
                    break;

                case "2":
                    logger.info("Enter roll number to remove: ");
                    String r1 = scanner.nextLine();
                    sms.removeStudent(r1);
                    logger.info("Student removed if existed.");
                    break;

                case "3":
                    logger.info("Enter roll number to search: ");
                    String r2 = scanner.nextLine();
                    Student found = sms.searchStudent(r2);
                    if (found != null) {
                        if (logger.isLoggable(java.util.logging.Level.INFO)) {
                            logger.info(found.toString());
                        }
                    } else {
                        logger.info("Student not found.");
                    }
                    break;

                case "4":
                    sms.displayAllStudents();
                    break;

                case "5":
                    logger.info("Enter roll number to edit: ");
                    String r3 = scanner.nextLine();
                    Student toEdit = sms.searchStudent(r3);
                    if (toEdit != null) {
                        logger.info("Enter new name: ");
                        toEdit.setName(scanner.nextLine());
                        logger.info("Enter new grade: ");
                        toEdit.setGrade(scanner.nextLine());
                        logger.info("Enter new age: ");
                        toEdit.setAge(Integer.parseInt(scanner.nextLine()));
                        sms.saveStudentsToFile();
                        logger.info("Student updated.");
                    } else {
                        logger.info("Student not found.");
                    }
                    break;

                case "6":
                    running = false;
                    break;

                default:
                    logger.info("Invalid option.");
            }
        }

        scanner.close();
        logger.info("Goodbye!");
    }
}
