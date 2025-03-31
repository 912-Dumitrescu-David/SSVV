import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class SaveStudentTest {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studentiTest.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "temeTest.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "noteTest.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    @Test
    void saveStudentCorrect() {
        try {
            assertEquals(0, service.saveStudent("123", "Name", 932));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            service.deleteStudent("123"); // Cleanup
        }
    }

    @Test
    void saveStudent_EmptyId_ShouldFail() {
        assertEquals(1, service.saveStudent("", "John Doe", 932), "Empty ID should fail");
    }

    @Test
    void saveStudent_NullId_ShouldFail() {
        assertEquals(1, service.saveStudent(null, "John Doe", 932), "Null ID should fail");
    }

    @Test
    void saveStudent_EmptyName_ShouldFail() {
        assertEquals(1, service.saveStudent("123", "", 932), "Empty name should fail");
    }

    @Test
    void saveStudent_NullName_ShouldFail() {
        assertEquals(1, service.saveStudent("123", null, 932), "Null name should fail");
    }

    @Test
    void saveStudent_ZeroGroup_ShouldFail() {
        assertEquals(1, service.saveStudent("123", "John Doe", 0), "Group 0 should fail");
    }

    @Test
    void saveStudent_NegativeGroup_ShouldFail() {
        assertEquals(1, service.saveStudent("123", "John Doe", -1), "Negative group should fail");
    }


    @Test
    void saveStudent_Group109_ShouldFail() {
        assertEquals(1, service.saveStudent("123", "John Doe", 110), "Group 110 should fail (out of bounds)");
    }

//BVA

    @Test
    void saveStudent_Group110_ShouldPass() {
        try {
            assertEquals(0, service.saveStudent("123", "John Doe", 111));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            service.deleteStudent("123");
        }
    }

    @Test
    void saveStudent_Group938_ShouldPass() {
        try {
            assertEquals(0, service.saveStudent("123", "John Doe", 937));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            service.deleteStudent("123");
        }
    }

    @Test
    void saveStudent_Group939_ShouldFail() {
        assertEquals(1, service.saveStudent("123", "John Doe", 938), "Group 938 should fail (out of bounds)");
    }
}

