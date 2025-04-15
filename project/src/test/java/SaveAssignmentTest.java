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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SaveAssignmentTest {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studentiTest.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "temeTest.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "noteTest.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    @Test
    void saveAssignmentCorrect() {
        try {
            assertEquals(0, service.saveTema("2", "tema2", 9, 7));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            service.deleteTema("2");
        }
    }

    @Test
    void saveAssignmentEmptyId() {
        assertEquals(1, service.saveTema("", "descriere", 7, 6));
    }
    
    @Test
    void saveAssignmentEmptyDescriere(){
        assertEquals(1, service.saveTema("2", "", 7, 6));
    }

    @Test
    void saveAssignmentZeroDeadline(){
        assertEquals(1, service.saveTema("2", "descriere", 0, 6));
    }

    @Test
    void saveAssignmentZeroStartline(){
        assertEquals(1, service.saveTema("2", "descriere", 7, 0));
    }

}
