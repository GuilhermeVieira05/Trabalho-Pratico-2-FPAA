package solver;

import model.*;

public class ValidationTests {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testAdjacencyRule();
        testBalanceRule();
        testConstraintRule();
        testRuleValidator();
        System.out.println("\n=== Resultados: " + passed + " passou, " + failed + " falhou ===");
        if (failed > 0) System.exit(1);
    }

    private static void check(String name, boolean condition) {
        if (condition) {
            System.out.println("  PASS: " + name);
            passed++;
        } else {
            System.out.println("  FAIL: " + name);
            failed++;
        }
    }

    private static void testAdjacencyRule()  { System.out.println("\n--- AdjacencyRule ---");  }
    private static void testBalanceRule()     { System.out.println("\n--- BalanceRule ---");     }
    private static void testConstraintRule()  { System.out.println("\n--- ConstraintRule ---");  }
    private static void testRuleValidator()   { System.out.println("\n--- RuleValidator ---");   }
}
