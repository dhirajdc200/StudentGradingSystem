package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {

    public static List<Grade> getGradesByStudent(int studentId) {
        List<Grade> grades = new ArrayList<>();
        String query = "SELECT * FROM grades WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                grades.add(new Grade(
                        rs.getString("subject"), rs.getDouble("grade")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grades;
    }

    public static void addGrade(int studentId, String subject, double grade) {
        String query = "INSERT INTO grades (student_id, subject, grade) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setString(2, subject);
            stmt.setDouble(3, grade);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateGrade(int id, double newGrade) {
        String query = "UPDATE grades SET grade = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, newGrade);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteGrade(int id) {
        String query = "DELETE FROM grades WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
