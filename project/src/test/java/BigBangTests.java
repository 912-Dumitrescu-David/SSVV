import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class BigBangTests {


        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studentiTest.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "temeTest.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "noteTest.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);


    @Test
    public void saveStudent_uniteTest_id_null(){
        assertEquals(1,service.saveStudent("","Dave",932));
    }


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
    void saveNotaIncorrect(){
        try {
            assertEquals(-1, service.saveNota("", "tema2", 2, 7, "Skill issue lmao"));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    void BigBangWhatever(){
        try{
            service.saveStudent("10","Dave",932);
            service.saveTema("10", "test", 9, 5);
            assertEquals(0, service.saveNota("10","10",2,7,"Skill issue 2 Lmao"));
        }catch (Exception e){
            fail("Exception thrown: " + e.getMessage());
        }
        finally {
            service.deleteStudent("10");
            service.deleteStudent("10");
        }
    }





}
