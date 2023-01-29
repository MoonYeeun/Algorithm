package LeetCode

import java.util.*

// 20. Valid Parentheses
class LC20_Valid_Parentheses {
    fun isValid(s: String): Boolean {
        var stack = Stack<Char>()
        s.toCharArray().forEach { s ->
            when (s) {
                ')' -> if (stack.isEmpty() || stack.pop() != '(') return false
                '}' -> if (stack.isEmpty() || stack.pop() != '{') return false
                ']' -> if (stack.isEmpty() || stack.pop() != '[') return false
                else -> stack.push(s)
            }
        }

        return stack.isEmpty()
    }
}