package ext

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StringExtensionsKtTest {

    @Test
    fun string_splitInTwo() {
        assertEquals("abcd".splitInTwo(), Pair("ab", "cd"))
    }

    @Test
    fun string_transpose() {
        val input = """
            [D]    
        [N] [C]    
        [Z] [M] [P]
         1   2   3
        """.trimIndent()

        val expected = """
         [[ 
         NZ1
         ]] 
            
        [[[ 
        DCM2
        ]]] 
            
          [ 
          P3
          ] 
            
        """.trimIndent()

        assertEquals(expected, input.transpose())
    }

    @Test
    fun string_reverseLines() {
        val input = """
            abc
            def
        """.trimIndent()

        val expected = """
            cba
            fed
        """.trimIndent()

        assertEquals(expected, input.reversedPerLine())
    }
}
