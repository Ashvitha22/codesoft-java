package studentgrade;

import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user how many subjects
        System.out.print("📚 Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        int[] marks = new int[numberOfSubjects];
        int totalMarks = 0;

        // Input marks for each subject
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            int inputMark = scanner.nextInt();

            // Validate marks
            while (inputMark < 0 || inputMark > 100) {
                System.out.print("❌ Invalid input. Please enter marks between 0 and 100: ");
                inputMark = scanner.nextInt();
            }

            marks[i] = inputMark;
            totalMarks += marks[i];
        }

        // Calculate average percentage
        double average = (double) totalMarks / numberOfSubjects;

        // Determine grade
        char grade;
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else if (average >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        // Display result
        System.out.println("\n🎓 RESULT:");
        System.out.println("Total Marks: " + totalMarks + " out of " + (numberOfSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", average);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
