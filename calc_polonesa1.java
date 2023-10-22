import java.util.Stack;
import javax.swing.JOptionPane;

public class calc_polonesa1 {
    public static String infixaParaPosfixa(String infixa) {
       
        String posfixa = "";
        Stack<Character> pilha = new Stack<>();
        for (int i = 0; i < infixa.length(); i++) {
            char c = infixa.charAt(i);
            if (Character.isDigit(c)) {
                posfixa += c;
            } else if (c == '(') {
                pilha.push(c);
            } else if (c == ')') {
                while (!pilha.isEmpty() && pilha.peek() != '(') {
                    posfixa += pilha.pop();
                }
                if (!pilha.isEmpty() && pilha.peek() != '(') {
                    return "Expressão inválida";
                } else {
                    pilha.pop();
                }
            } else {
                while (!pilha.isEmpty() && precedencia(c) <= precedencia(pilha.peek())) {
                    posfixa += pilha.pop();
                }
                pilha.push(c);
            }
        }
        while (!pilha.isEmpty()) {
            posfixa += pilha.pop();
        }
        return posfixa;
    }

    public static String infixaParaPrefixa(String infixa) {
      
        String prefixa = "";
        Stack<Character> pilha = new Stack<>();
        infixa = new StringBuilder(infixa).reverse().toString();
        for (int i = 0; i < infixa.length(); i++) {
            char c = infixa.charAt(i);
            if (c == '(') {
                c = ')';
                i++;
            } else if (c == ')') {
                c = '(';
                i++;
            }
            if (Character.isDigit(c)) {
                prefixa += c;
            } else if (c == '(') {
                pilha.push(c);
            } else if (c == ')') {
                while (!pilha.isEmpty() && pilha.peek() != '(') {
                    prefixa += pilha.pop();
                }
                if (!pilha.isEmpty() && pilha.peek() != '(') {
                    return "Expressão inválida";
                } else {
                    pilha.pop();
                }
            } else {
                while (!pilha.isEmpty() && precedencia(c) < precedencia(pilha.peek())) {
                    prefixa += pilha.pop();
                }
                pilha.push(c);
            }
        }
        while (!pilha.isEmpty()) {
            prefixa += pilha.pop();
        }
        prefixa = new StringBuilder(prefixa).reverse().toString();
        return prefixa;
    }

    public static double avaliarPosfixa(String posfixa) {
        // Implemente a avaliação de expressão pós-fixa usando uma pilha
        Stack<Double> pilha = new Stack<>();
        for (int i = 0; i < posfixa.length(); i++) {
            char c = posfixa.charAt(i);
            if (Character.isDigit(c)) {
                pilha.push((double) (c - '0'));
            } else {
                double val1 = pilha.pop();
                double val2 = pilha.pop();
                switch (c) {
                    case '+':
                        pilha.push(val2 + val1);
                        break;
                    case '-':
                        pilha.push(val2 - val1);
                        break;
                    case '*':
                        pilha.push(val2 * val1);
                        break;
                    case '/':
                        pilha.push(val2 / val1);
                        break;
                }
            }
        }
        return pilha.pop();
    }

    public static double avaliarPrefixa(String prefixa) {
        // Implemente a avaliação de expressão pré-fixa usando uma pilha e a função de avaliação de expressão pós-fixa
        prefixa = new StringBuilder(prefixa).reverse().toString();
        prefixa = infixaParaPosfixa(prefixa);
        prefixa = new StringBuilder(prefixa).reverse().toString();
        return avaliarPosfixa(prefixa);
    }

    public static int precedencia(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static void main(String[] args) {
        // Obter a expressão e o tipo de notação usando uma caixa de diálogo de entrada
        String expressao = JOptionPane.showInputDialog("Digite a expressão:");
        String tipoNotacao = JOptionPane.showInputDialog("Digite o tipo de notação (infixa, posfixa ou prefixa):");

        // Converter a expressão para pós-fixa ou pré-fixa, se necessário
        if (tipoNotacao.equals("infixa")) {
            String opcaoNotacao = JOptionPane.showInputDialog("Digite o tipo de notação desejado (posfixa ou prefixa):");
            if (opcaoNotacao.equals("posfixa")) {
                expressao = infixaParaPosfixa(expressao);
            } else if (opcaoNotacao.equals("prefixa")) {
                expressao = infixaParaPrefixa(expressao);
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida.");
                return;
            }
        } else if (tipoNotacao.equals("prefixa")) {
            expressao = new StringBuilder(expressao).reverse().toString();
            expressao = infixaParaPosfixa(expressao);
            expressao = new StringBuilder(expressao).reverse().toString();
        }

        // Avaliar a expressão usando uma pilha
        double resultado;
        if (tipoNotacao.equals("infixa")) {
            resultado = avaliarPosfixa(expressao);
        } else {
            resultado = avaliarPrefixa(expressao);
        }

        // Exibir o resultado usando uma caixa de diálogo de mensagem
        JOptionPane.showMessageDialog(null, "Resultado: " + resultado);
    }
}