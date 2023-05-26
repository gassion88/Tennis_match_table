import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test class")
public class AnnotationTest {

    @BeforeEach
    public void beforeEach(){
        System.out.println("before each");
    }

    @Test
    @DisplayName("Test method")
    public void test() {
        System.out.println("@Test");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("After each");
    }
}
