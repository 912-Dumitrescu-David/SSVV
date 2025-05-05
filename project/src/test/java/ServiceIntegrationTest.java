import domain.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;


import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ServiceIntegrationTest {

  private static Service service;

  @Mock
  private static StudentXMLRepository studentXMLRepository;

  @Mock
  private static TemaXMLRepository temaXMLRepository;

  @Mock
  private static NotaXMLRepository notaXMLRepository;

  @BeforeAll
  public static void setup(){
    studentXMLRepository = mock(StudentXMLRepository.class);
    temaXMLRepository = mock(TemaXMLRepository.class);
    notaXMLRepository = mock(NotaXMLRepository.class);

    service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
  }

  @Test
  public void AddStudentUnitTest(){
    System.out.println("Testing saveStudent with Mockito");

    service.saveStudent("1", "TestName", 932);

    verify(studentXMLRepository).save(argThat(student -> student.getID().equals("1")));
  }
  
  @Test
  public void AddAssigmentIntegratedWithAddStudent(){
    System.out.println("Testing saveAssigment with saveStudent");

    service.saveStudent("1", "TestName", 932);
    service.saveTema("1", "TestAssigment", 9, 3);
    
    verify(studentXMLRepository).save(argThat(student -> student.getID().equals("1")));
    verify(temaXMLRepository).save(argThat(tema -> tema.getID().equals("1")));

  }

  @Test
  public void AddAssigmentIntegratedWithAddStudentIntegratedWithAddGrade(){
    System.out.println("Testing saveStudent, saveAssigment and saveGrade");

    service.saveStudent("1", "TestName", 932);
    service.saveTema("1", "TestAssigment", 9, 3);
    service.saveNota("1", "1", 2, 4, "lol");


    verify(studentXMLRepository).save(argThat(student -> student.getID().equals("1")));
    verify(temaXMLRepository).save(argThat(tema -> tema.getID().equals("1")));
    //verify(notaXMLRepository).save(argThat(nota -> nota.getID().equals(new Pair("1","1"))));


  }

}
