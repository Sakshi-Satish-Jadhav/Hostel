package dao;

import model.HostelStudent;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HostelStudentDAO {

    // Save a new hostel student
    public boolean saveHostelStudent(HostelStudent student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(student); // save student
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all hostel students
    public List<HostelStudent> getAllHostelStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<HostelStudent> query = session.createQuery("FROM HostelStudent", HostelStudent.class);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching hostel students: " + e.getMessage());
        }
    }

    // Get a hostel student by ID
    public HostelStudent getHostelStudent(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(HostelStudent.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching hostel student: " + e.getMessage());
        }
    }

    // Update hostel student details
    public boolean updateHostelStudent(HostelStudent student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(student); // merge is safer for detached objects
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error updating hostel student: " + e.getMessage());
        }
    }

    // Delete a hostel student by ID
    public boolean deleteHostelStudent(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            HostelStudent student = session.get(HostelStudent.class, id);
            if (student != null) {
                session.remove(student);
                session.getTransaction().commit();
                return true;
            } else {
                session.getTransaction().rollback(); // good practice if nothing is deleted
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting hostel student: " + e.getMessage());
        }
    }
}
