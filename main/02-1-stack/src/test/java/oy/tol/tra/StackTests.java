package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

/*

/**
 * Unit tests for testing the stack implementation.
 * 
 * DO NOT change anything here, just implement the StackInterface, instantiate it in
 * StackBuilder.createIntegerStack and perform the tests.
 */
@DisplayName("Basic tests for the StackImplementation class.")
@TestMethodOrder(OrderAnnotation.class)
public class StackTests {
    static int stackSize = 10;
    static Random randomizer = new Random();
    static final int MAX_STACK_SIZE = 10;
    static Integer numberFromStack = null;

    /**
     * Initialize the test.
     */
    public class Main {
        public static void main(String[] args) {
            Stack<Integer> stack = new Stack<>();

            // Test push
            stack.push(1);
            stack.push(2);
            stack.push(3);
            System.out.println("Stack after pushes: " + stack);

            // Test peek
            System.out.println("Peek: " + stack.peek());

            // Test pop
            System.out.println("Pop: " + stack.pop());
            System.out.println("Stack after pop: " + stack);

            // Test size
            System.out.println("Size: " + stack.size());

            // Test isEmpty
            System.out.println("Is empty: " + stack.isEmpty());

            // Test clear
            stack.clear();
            System.out.println("Stack after clear: " + stack);
            System.out.println("Is empty after clear: " + stack.isEmpty());
        }
    }
