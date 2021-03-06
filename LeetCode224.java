package LeetCode;

import java.math.BigInteger;
import java.util.Stack;

public class LeetCode224 {
    public static void main(String[] args) {
        char c = '5';
        System.out.println(String.valueOf(c));
    }

    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        Stack<Character> symbol = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (c >= '0' && c <= '9') {
                int num = c - '0';
                i = i + 1;
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                stack.push(num);
            }
            if (c == '(')
                symbol.push('(');
            if (c == ')') {
                int res = 0;
                if (symbol.peek() == '(') {
                    res = stack.pop();
                } else {
                    while (true) {
                        int add_sub1 = symbol.pop() == '+' ? 1 : -1;
                        int num1 = stack.pop() * add_sub1;
                        res = num1 + res;
                        if (symbol.peek() == '(') {
                            res += stack.pop();
                            break;
                        }
                    }
                }
                symbol.pop();
                stack.push(res);
            }
            if (c == '+' || c == '-') {
                symbol.push(c);
            }
        }
        int res = 0;
        while (!stack.empty()) {
            while (true) {
                int add_sub1 = (symbol.empty() || symbol.pop() == '+') ? 1 : -1;
                int num1 = stack.pop() * add_sub1;
                res = num1 + res;
                if (symbol.empty())
                    break;
            }
            if (!stack.empty())
                res = stack.pop() + res;
        }
        return res;
    }
}
