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

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
            service.saveStudent("123", "Name", 932);
        } catch (Exception e) {
            fail();
        }
        service.deleteStudent("123");
        assertTrue(true);
    }

    @Test
    void saveStudent_EmptyId() {
        try {
            service.saveStudent("", "John Doe", 932);
            fail();
        } catch (Exception ignored) {
        }
        assertTrue(true);
    }

    @Test
    void saveStudent_NullId() {
        try {
            service.saveStudent(null, "John Doe", 932);
            fail();
        } catch (Exception ignored) {
        }
        assertTrue(true);
    }

    @Test
    void saveStudent_EmptyName() {
        try {
            service.saveStudent("123", "", 932);
            fail();
        } catch (Exception ignored) {
        }
        assertTrue(true);
    }

    @Test
    void saveStudent_NullName() {
        try {
            service.saveStudent("123", null, 932);
            fail();
        } catch (Exception ignored) {
        }
        assertTrue(true);
    }

    @Test
    void saveStudent_ZeroGroup() {
        try {
            service.saveStudent("123", "John Doe", 0);
            fail();
        } catch (Exception ignored) {
        }
        assertTrue(true);
    }

    @Test
    void saveStudent_NegativeGroup() {
        try {
            service.saveStudent("123", "John Doe", -1);
            fail();
        } catch (Exception ignored) {
        }
        assertTrue(true);
    }
}
