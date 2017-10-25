package org.mockitousage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.UnfinishedStubbingException;
import org.mockito.junit5.MockitoExtension;
import org.mockito.junit5.Strictness;

import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;
import static org.mockito.quality.Strictness.STRICT_STUBS;
import static org.mockito.quality.Strictness.WARN;

@ExtendWith(MockitoExtension.class)
class StricnessTest {

    @Mock
    Predicate mock;

    @Test
    void strictnessFromTestRoot_defaultIsWarn_shouldThrowUnnecessaryStubbingException() {
        when(mock.test(1)).thenReturn(true);

        //FIXME how to trigger MockitoSession.finishMocking() here and check that unneccesssay stubbing was logged

    }

    @Test
    @Strictness(LENIENT)
    void strictnessFromTestMethod_lenient_shouldIgnoreUnnecessaryStubbing() {
        when(mock.test(1)).thenReturn(true);


        //FIXME how to trigger MockitoSession.finishMocking() here and check that unneccesssay stubbing was ignored
    }

    @Nested
    @Strictness(WARN)
    class NestedTest{

        @Mock
        Predicate mock;

        @Test
        void strictnessFromNestedTest_warn_shouldLogUnnecessaryStubbing() {
            when(mock.test(1)).thenReturn(true);

            //FIXME how to trigger MockitoSession.finishMocking() here and check that unneccesssay stubbing was logged
        }

        @Nested
        @Strictness(STRICT_STUBS)
        class NestedTest2{
            @Mock
            Predicate mock;

            @Test
            void strictnessFromNestedTest_warn_shouldLogUnnecessaryStubbing() {
                when(mock.test(1)).thenReturn(true);

                //FIXME how to trigger MockitoSession.finishMocking() here and check that unneccesssay stubbing was logged
            }
        }
    }
}
