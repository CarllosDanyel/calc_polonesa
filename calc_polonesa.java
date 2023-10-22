import javax.swing.JOptionPane;

public class calc_polonesa {
    public static void main(String[] args) {
        int n1, n2, res = 0; 
        String op;
        n1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro numero: "));
        n2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o segundo numero: "));
        op = JOptionPane.showInputDialog("Digite a operação: ");
        switch (op) {
            case "+":
                res = n1 + n2;
                JOptionPane.showMessageDialog(null, "O resultado é: " + res);
                break;
            case "-":
                res = n1 - n2;
                JOptionPane.showMessageDialog(null, "O resultado é: " + res);
                break;
            case "*":
                res = n1 * n2;
                JOptionPane.showMessageDialog(null, "O resultado é: " + res);
                break;
            case "/":
                res = n1 / n2;
                JOptionPane.showMessageDialog(null, "O resultado é: " + res);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Operação invalida");
                break;
        }
    System.out.println("primeiro numero:" + n1 +" " +"com o segundo numero:" + n2 +" "+"utilizando essa operacao:" + op +" " + "O resultado é:"+ res);
    
        


       
    }
}
