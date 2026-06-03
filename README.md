# Trabalho Prático 2 — FPAA
**Disciplina:** Fundamentos de Projeto e Análise de Algoritmos  
**Curso:** Engenharia de Software — PUC Minas  

Resolução do quebra-cabeça **Tango** utilizando Força Bruta e Backtracking.

---

## Como compilar e rodar

### 1. Compilar
```powershell
cd tp2-tango
javac -d out (Get-ChildItem -Recurse -Path src -Filter *.java | Select-Object -ExpandProperty FullName)
```

### 2. Rodar
```powershell
java -cp out Main <caminho-do-puzzle> [modo]
```

O argumento `modo` é opcional e aceita:

| Modo | Descrição |
|------|-----------|
| `ambos` | Roda Força Bruta e Backtracking (**padrão**) |
| `bruteforce` | Roda somente Força Bruta |
| `backtracking` | Roda somente Backtracking |

### Exemplos
```powershell
# Ambos os algoritmos no puzzle 4x4 (recomendado para comparação)
java -cp out Main puzzles\easy_4x4.txt

# Só backtracking no puzzle 6x6
java -cp out Main puzzles\easy_6x6.txt backtracking

# Só backtracking no puzzle 6x6 médio
java -cp out Main puzzles\medium_6x6.txt backtracking
```

---

## Por que usar o puzzle 4×4 para o Força Bruta?

O Força Bruta testa **todas as combinações possíveis** de preenchimento do tabuleiro sem descartar nenhuma antecipadamente. O número de combinações cresce exponencialmente com o número de células vazias:

```
Combinações = 2^(células vazias)
```

| Puzzle | Células vazias | Combinações |
|--------|---------------|-------------|
| 4×4    | 10            | 1.024       |
| 6×6 fácil | 33        | ~8,6 bilhões |
| 6×6 médio | 30        | ~1 bilhão   |

Um puzzle 6×6 com 33 células vazias exigiria testar **8,6 bilhões de tabuleiros completos**, o que levaria horas para terminar. O puzzle 4×4, com apenas 10 células vazias, resolve em milissegundos — tornando viável demonstrar o algoritmo e comparar os resultados com o Backtracking.

Essa limitação do Força Bruta é exatamente o que motiva o uso do Backtracking, que resolve o mesmo problema em dezenas de tentativas ao invés de bilhões.

---

## Puzzles disponíveis

| Arquivo | Tamanho | Algoritmo recomendado |
|---------|---------|----------------------|
| `puzzles/easy_4x4.txt` | 4×4 | Ambos (comparação) |
| `puzzles/easy_6x6.txt` | 6×6 | Backtracking |
| `puzzles/medium_6x6.txt` | 6×6 | Backtracking |

---

## Formato dos puzzles

Os puzzles são arquivos `.txt` com o seguinte formato:

```
<tamanho>
<grade inicial>
<restrições>
```

Na grade: `S` = Sol, `L` = Lua, `.` = vazio.

Nas restrições: `H` = horizontal, `V` = vertical, seguidos de linha, coluna e tipo (`=` ou `X`).

```
4
S . L .
. L . .
S . . .
. S . S
H 0 0 =
V 0 2 X
```

A linha `H 0 0 =` significa: as células `(0,0)` e `(0,1)` devem ter o **mesmo** símbolo.  
A linha `V 0 2 X` significa: as células `(0,2)` e `(1,2)` devem ter símbolos **opostos**.

---

## Estrutura do projeto

```
tp2-tango/
├── puzzles/
│   ├── easy_4x4.txt
│   ├── easy_6x6.txt
│   └── medium_6x6.txt
└── src/
    ├── Main.java
    ├── io/
    │   ├── BoardReader.java
    │   └── BoardPrinter.java
    ├── model/
    │   ├── Board.java
    │   ├── Cell.java
    │   ├── CellValue.java
    │   ├── Constraint.java
    │   └── ConstraintType.java
    └── solver/
        ├── Rule.java
        ├── AdjacencyRule.java
        ├── BalanceRule.java
        ├── ConstraintRule.java
        ├── RuleValidator.java
        ├── BruteForceSolver.java
        └── BacktrackingSolver.java
```
