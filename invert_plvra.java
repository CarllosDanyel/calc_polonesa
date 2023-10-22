import java.util.Stack;
import javax.swing.JOptionPane;

public class invert_plvra {
    public static String inverterPalavras(String input) {
        String[] palavras = input.split(" ");
        StringBuilder resultado = new StringBuilder();

        for (String palavra : palavras) {
            Stack<Character> pilha = new Stack<>();
            for (char letra : palavra.toCharArray()) {
                pilha.push(letra);
            }
            while (!pilha.isEmpty()) {
                resultado.append(pilha.pop());
            }
            resultado.append(" ");
        }

        return resultado.toString().trim();
    }

    public static void main(String[] args) {
        
        String input = JOptionPane.showInputDialog("Digite uma frase:");
       
        String output = inverterPalavras(input);

        JOptionPane.showMessageDialog(null, "Frase invertida:\n" + output);
    }
}