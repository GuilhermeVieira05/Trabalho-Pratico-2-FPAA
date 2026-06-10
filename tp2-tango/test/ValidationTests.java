package test;

import model.*;
import rules.AdjacencyRule;
import rules.BalanceRule;
import rules.ConstraintRule;
import validation.RuleValidator;

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

    private static void testAdjacencyRule() {
        System.out.println("\n--- AdjacencyRule ---");
        AdjacencyRule rule = new AdjacencyRule();
        int N = 6;

        // 3 SUNs na mesma linha → inválido
        Board b1 = new Board(N);
        b1.setCell(0, 0, CellValue.SUN);
        b1.setCell(0, 1, CellValue.SUN);
        b1.setCell(0, 2, CellValue.SUN);
        check("3 SUNs na linha → inválido", !rule.isValidPlacement(b1, 0, 2));

        // 2 SUNs + MOON na linha → válido
        Board b2 = new Board(N);
        b2.setCell(0, 0, CellValue.SUN);
        b2.setCell(0, 1, CellValue.SUN);
        b2.setCell(0, 2, CellValue.MOON);
        check("2 SUNs + MOON na linha → válido", rule.isValidPlacement(b2, 0, 2));

        // 3 MOONs na mesma coluna → inválido
        Board b3 = new Board(N);
        b3.setCell(0, 0, CellValue.MOON);
        b3.setCell(1, 0, CellValue.MOON);
        b3.setCell(2, 0, CellValue.MOON);
        check("3 MOONs na coluna → inválido", !rule.isValidPlacement(b3, 2, 0));

        // isFullyValid: padrão válido em uma linha
        Board b4 = new Board(N);
        b4.setCell(0, 0, CellValue.SUN);
        b4.setCell(0, 1, CellValue.SUN);
        b4.setCell(0, 2, CellValue.MOON);
        b4.setCell(0, 3, CellValue.MOON);
        b4.setCell(0, 4, CellValue.SUN);
        b4.setCell(0, 5, CellValue.MOON);
        check("Padrão S-S-M-M-S-M → isFullyValid true", rule.isFullyValid(b4));

        // isFullyValid: 3 SUNs seguidos → inválido
        Board b5 = new Board(N);
        b5.setCell(0, 0, CellValue.SUN);
        b5.setCell(0, 1, CellValue.SUN);
        b5.setCell(0, 2, CellValue.SUN);
        check("3 SUNs seguidos → isFullyValid false", !rule.isFullyValid(b5));
    }
    private static void testBalanceRule() {
        System.out.println("\n--- BalanceRule ---");
        BalanceRule rule = new BalanceRule();
        int N = 6;

        // 4 SUNs na linha → inválido durante colocação
        Board b1 = new Board(N);
        b1.setCell(0, 0, CellValue.SUN);
        b1.setCell(0, 1, CellValue.SUN);
        b1.setCell(0, 2, CellValue.SUN);
        b1.setCell(0, 3, CellValue.SUN);
        check("4 SUNs na linha → isValidPlacement false", !rule.isValidPlacement(b1, 0, 3));

        // Exatamente 3 SUNs (limite) → ainda válido durante colocação
        Board b2 = new Board(N);
        b2.setCell(0, 0, CellValue.SUN);
        b2.setCell(0, 1, CellValue.SUN);
        b2.setCell(0, 2, CellValue.SUN);
        check("3 SUNs na linha (limite N/2) → isValidPlacement true", rule.isValidPlacement(b2, 0, 2));

        // Linha completa: 3 SUNs + 3 MOONs → isFullyValid true
        Board b3 = new Board(N);
        b3.setCell(0, 0, CellValue.SUN);
        b3.setCell(0, 1, CellValue.MOON);
        b3.setCell(0, 2, CellValue.SUN);
        b3.setCell(0, 3, CellValue.MOON);
        b3.setCell(0, 4, CellValue.SUN);
        b3.setCell(0, 5, CellValue.MOON);
        b3.setCell(1, 0, CellValue.MOON);
        b3.setCell(1, 1, CellValue.SUN);
        b3.setCell(1, 2, CellValue.MOON);
        b3.setCell(1, 3, CellValue.SUN);
        b3.setCell(1, 4, CellValue.MOON);
        b3.setCell(1, 5, CellValue.SUN);
        b3.setCell(2, 0, CellValue.SUN);
        b3.setCell(2, 1, CellValue.MOON);
        b3.setCell(2, 2, CellValue.SUN);
        b3.setCell(2, 3, CellValue.MOON);
        b3.setCell(2, 4, CellValue.SUN);
        b3.setCell(2, 5, CellValue.MOON);
        b3.setCell(3, 0, CellValue.MOON);
        b3.setCell(3, 1, CellValue.SUN);
        b3.setCell(3, 2, CellValue.MOON);
        b3.setCell(3, 3, CellValue.SUN);
        b3.setCell(3, 4, CellValue.MOON);
        b3.setCell(3, 5, CellValue.SUN);
        b3.setCell(4, 0, CellValue.SUN);
        b3.setCell(4, 1, CellValue.MOON);
        b3.setCell(4, 2, CellValue.SUN);
        b3.setCell(4, 3, CellValue.MOON);
        b3.setCell(4, 4, CellValue.SUN);
        b3.setCell(4, 5, CellValue.MOON);
        b3.setCell(5, 0, CellValue.MOON);
        b3.setCell(5, 1, CellValue.SUN);
        b3.setCell(5, 2, CellValue.MOON);
        b3.setCell(5, 3, CellValue.SUN);
        b3.setCell(5, 4, CellValue.MOON);
        b3.setCell(5, 5, CellValue.SUN);
        check("3 SUNs + 3 MOONs → isFullyValid true", rule.isFullyValid(b3));

        // Linha incompleta (células EMPTY) → isFullyValid false
        Board b4 = new Board(N);
        b4.setCell(0, 0, CellValue.SUN);
        b4.setCell(0, 1, CellValue.SUN);
        check("2 SUNs + EMPTYs → isFullyValid false", !rule.isFullyValid(b4));

        // 4 MOONs na coluna → inválido durante colocação
        Board b5 = new Board(N);
        b5.setCell(0, 2, CellValue.MOON);
        b5.setCell(1, 2, CellValue.MOON);
        b5.setCell(2, 2, CellValue.MOON);
        b5.setCell(3, 2, CellValue.MOON);
        check("4 MOONs na coluna → isValidPlacement false", !rule.isValidPlacement(b5, 3, 2));
    }
    private static void testConstraintRule() {
        System.out.println("\n--- ConstraintRule ---");
        ConstraintRule rule = new ConstraintRule();
        int N = 6;

        // EQUAL: SUN == SUN → válido
        Board b1 = new Board(N);
        b1.addConstraint(new Constraint(0, 0, 0, 1, ConstraintType.EQUAL));
        b1.setCell(0, 0, CellValue.SUN);
        b1.setCell(0, 1, CellValue.SUN);
        check("EQUAL: SUN = SUN → válido", rule.isValidPlacement(b1, 0, 1));

        // EQUAL: SUN vs MOON → inválido
        Board b2 = new Board(N);
        b2.addConstraint(new Constraint(0, 0, 0, 1, ConstraintType.EQUAL));
        b2.setCell(0, 0, CellValue.SUN);
        b2.setCell(0, 1, CellValue.MOON);
        check("EQUAL: SUN = MOON → inválido", !rule.isValidPlacement(b2, 0, 1));

        // OPPOSITE: SUN × MOON → válido
        Board b3 = new Board(N);
        b3.addConstraint(new Constraint(0, 0, 0, 1, ConstraintType.OPPOSITE));
        b3.setCell(0, 0, CellValue.SUN);
        b3.setCell(0, 1, CellValue.MOON);
        check("OPPOSITE: SUN × MOON → válido", rule.isValidPlacement(b3, 0, 1));

        // OPPOSITE: SUN × SUN → inválido
        Board b4 = new Board(N);
        b4.addConstraint(new Constraint(0, 0, 0, 1, ConstraintType.OPPOSITE));
        b4.setCell(0, 0, CellValue.SUN);
        b4.setCell(0, 1, CellValue.SUN);
        check("OPPOSITE: SUN × SUN → inválido", !rule.isValidPlacement(b4, 0, 1));

        // EQUAL com parceiro EMPTY → válido (estado parcial)
        Board b5 = new Board(N);
        b5.addConstraint(new Constraint(0, 0, 0, 1, ConstraintType.EQUAL));
        b5.setCell(0, 0, CellValue.SUN);
        check("EQUAL com parceiro EMPTY → válido (parcial)", rule.isValidPlacement(b5, 0, 0));

        // Constraint vertical OPPOSITE: MOON × MOON → inválido
        Board b6 = new Board(N);
        b6.addConstraint(new Constraint(0, 0, 1, 0, ConstraintType.OPPOSITE));
        b6.setCell(0, 0, CellValue.MOON);
        b6.setCell(1, 0, CellValue.MOON);
        check("OPPOSITE vertical: MOON × MOON → inválido", !rule.isValidPlacement(b6, 1, 0));

        // isFullyValid: todas as constraints satisfeitas
        Board b7 = new Board(N);
        b7.addConstraint(new Constraint(0, 0, 0, 1, ConstraintType.EQUAL));
        b7.addConstraint(new Constraint(0, 2, 0, 3, ConstraintType.OPPOSITE));
        b7.setCell(0, 0, CellValue.SUN);
        b7.setCell(0, 1, CellValue.SUN);
        b7.setCell(0, 2, CellValue.MOON);
        b7.setCell(0, 3, CellValue.SUN);
        check("isFullyValid: todas constraints satisfeitas → true", rule.isFullyValid(b7));

        // isFullyValid: uma constraint violada
        Board b8 = new Board(N);
        b8.addConstraint(new Constraint(0, 0, 0, 1, ConstraintType.EQUAL));
        b8.setCell(0, 0, CellValue.SUN);
        b8.setCell(0, 1, CellValue.MOON);
        check("isFullyValid: constraint EQUAL violada → false", !rule.isFullyValid(b8));
    }
    private static void testRuleValidator() {
        System.out.println("\n--- RuleValidator ---");
        RuleValidator validator = new RuleValidator();
        int N = 6;

        // Tabuleiro vazio → isBoardValid false (células EMPTY)
        Board b1 = new Board(N);
        check("Tabuleiro vazio → isBoardValid false", !validator.isBoardValid(b1));

        // Primeira célula SUN → isValidPlacement true
        Board b2 = new Board(N);
        b2.setCell(0, 0, CellValue.SUN);
        check("Primeira célula SUN → isValidPlacement true", validator.isValidPlacement(b2, 0, 0));

        // 3 SUNs seguidos → isValidPlacement false (viola adjacência)
        Board b3 = new Board(N);
        b3.setCell(0, 0, CellValue.SUN);
        b3.setCell(0, 1, CellValue.SUN);
        b3.setCell(0, 2, CellValue.SUN);
        check("3 SUNs seguidos → isValidPlacement false", !validator.isValidPlacement(b3, 0, 2));

        // Constraint violada → isValidPlacement false
        Board b4 = new Board(N);
        b4.addConstraint(new Constraint(0, 0, 0, 1, ConstraintType.OPPOSITE));
        b4.setCell(0, 0, CellValue.SUN);
        b4.setCell(0, 1, CellValue.SUN);
        check("Constraint OPPOSITE violada → isValidPlacement false", !validator.isValidPlacement(b4, 0, 1));

        // Tabuleiro 2x2 completamente válido
        Board b5 = new Board(2);
        b5.setCell(0, 0, CellValue.SUN);
        b5.setCell(0, 1, CellValue.MOON);
        b5.setCell(1, 0, CellValue.MOON);
        b5.setCell(1, 1, CellValue.SUN);
        check("Tabuleiro 2x2 válido → isBoardValid true", validator.isBoardValid(b5));

        // Tabuleiro 2x2 com coluna desequilibrada
        Board b6 = new Board(2);
        b6.setCell(0, 0, CellValue.SUN);
        b6.setCell(0, 1, CellValue.SUN);
        b6.setCell(1, 0, CellValue.MOON);
        b6.setCell(1, 1, CellValue.MOON);
        check("Tabuleiro 2x2 coluna desequilibrada → isBoardValid false", !validator.isBoardValid(b6));
    }
}
