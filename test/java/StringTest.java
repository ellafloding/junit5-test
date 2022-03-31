import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class StringTest {

    private String str;

    @BeforeAll
    static void beforeAll(){
        System.out.println("Initialize connection to database");
    }

    @BeforeEach
    void beforeEach(TestInfo info){
        System.out.println("Initialize Test Data for " + info.getDisplayName());
    }

    @AfterEach
    void afterEach(TestInfo info){
        System.out.println("Clean up test data for " + info.getDisplayName());
    }

    @AfterAll
    static void afterAll(){
        System.out.println("All tests have been run");
    }

    @Test
    @Disabled
    void length_basic(){
        int actualLength = "ABCD".length();
        int expectedLength = 4;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    void length_greater_than_zero(){
        assertTrue("ABCD".length()>0);
        assertTrue("ABC".length()>0);
        assertTrue("A".length()>0);
        assertTrue("DEF".length()>0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABCD", "", "ABC", "A", "DEF"})
    void length_greater_than_zero_using_parameterized_test(String str){
        assertTrue(str.length()>0);
    }

    @Test
    void uppercase(){
        assertEquals("ABCD", "abcd".toUpperCase());
        assertEquals("ABC", "abc".toUpperCase());
        assertEquals("", "".toUpperCase());
        assertEquals("A", "a".toUpperCase());
        assertEquals("DEF", "def".toUpperCase());
    }

    @ParameterizedTest
    @CsvSource(value = {"abcd, ABCD", "abc, ABC", "'',''", "abcdefg, ABCDEFG"})
    void uppercase(String word, String capitalizedWord){
        assertEquals(capitalizedWord, word.toUpperCase());
    }

    @ParameterizedTest(name = "{0} length is {1}")
    @CsvSource(value = {"abcd, 4", "abc, 3", "'',0", "abcdefg, 7"})
    void length(String word, int expectedLength){
        assertEquals(expectedLength, word.length());
    }

    @ParameterizedTest(name = "{0} contains a is {1}")
    @CsvSource(value = {"abcd, true", "abc, true", "'',false", "abcdefg, true"})
    void contains(String word, boolean contains){
        assertEquals(contains, word.contains("a"));
    }

    @RepeatedTest(10)
    void contains_basic_repeated(){
        assertFalse("abcdefgh".contains("ijk"));
    }

    @Test
    @Disabled
    void performanceTest(){
        assertTimeout(Duration.ofSeconds(10),
                () -> {
                    for(int i = 0; i <= 1000000; i++){
                        int j = i;
                        System.out.println(j);
                    }
                }

                );
    }

    @Test
    @DisplayName("When string is null, throw an exception")
    void length_exception(){
        String str = null;
//        int actualLength = str.length();
        assertThrows(NullPointerException.class,
                () -> {
                    str.length();
                }
                );

    }

    @Test
    void toUpperCase_basic(){
        String str = "abcd";
        String result = str.toUpperCase();
        assertNotNull(result);
//        assertNull(result);
        assertEquals("ABCD", result);
    }

    @Test
    void contains_basic(){
        //        assertEquals(false, result);
        assertFalse("abcdefgh".contains("ijk"));
        //assertTrue
    }

    @Test
    void split_basic(){
        String str = "abc def ghi";
        String actualResult [] = str.split(" ");
        String[] expectedResult = new String[]{"abc", "def", "ghi"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Nested
    @DisplayName("For an empty String")
    class EmptyStringTests{

        @BeforeEach
        void setToEmpty(){
            str = "";
        }

        @Test
        @DisplayName("Length should be zero")
        void lengthIsZero(){
            assertEquals(0, str.length());
        }

        @Test
        @DisplayName("Uppercase is empty")
        void uppercaseIsEmpty(){
            assertEquals("", str.toUpperCase());
        }
    }

    @Nested
    @DisplayName("For large strings")
    class LargeStringTests{

        @BeforeEach
        void setToALargeString(){
            str = "jskfjksjgknkenkgneksgjgugiii";
        }

        @Test
        void lengthOfLargeString(){
            assertEquals(28, str.length());
        }

        @Test
        void largeStringContains(){
            assertEquals(true, str.contains("j"));
        }
    }


}
