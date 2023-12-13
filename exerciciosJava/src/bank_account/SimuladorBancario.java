package bank_account;

import javax.swing.JOptionPane;

public class SimuladorBancario {

	
	public void executarSimulador() {
        Banco banco = new Banco();

        while (true) {
            String[] options = {"Cadastrar conta", "Excluir conta", "Sacar", "Depositar", "Solicitar empréstimo", "Sair"};
            String opcao = (String) JOptionPane.showInputDialog(null, "Escolha uma opção:", "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (opcao == null || opcao.equals("Sair")) {
                // Metodo para salvar as contas antes de sair
                banco.salvarContas();
                JOptionPane.showMessageDialog(null, "Saindo do simulador...");
                break;
            }

            switch (opcao) {
                case "Cadastrar conta":
                    String tipoConta = JOptionPane.showInputDialog("Digite o tipo da conta (PF, PJ, Poupança): ");
                    String titularConta = JOptionPane.showInputDialog("Digite o nome do titular: ");
                    banco.cadastrarConta(tipoConta, titularConta);
                    break;

                case "Excluir conta":
                    String numeroContaExcluirStr = JOptionPane.showInputDialog("Digite o número da conta a ser excluída: ");
                    int numeroContaExcluir = Integer.parseInt(numeroContaExcluirStr);
                    banco.excluirConta(numeroContaExcluir);
                    break;

                case "Sacar":
                    String numeroContaSaqueStr = JOptionPane.showInputDialog("Digite o número da conta: ");
                    int numeroContaSaque = Integer.parseInt(numeroContaSaqueStr);
                    String valorSaqueStr = JOptionPane.showInputDialog("Digite o valor para saque: ");
                    double valorSaque = Double.parseDouble(valorSaqueStr);
                    banco.sacar(numeroContaSaque, valorSaque);
                    break;

                case "Depositar":
                    String numeroContaDepositoStr = JOptionPane.showInputDialog("Digite o número da conta: ");
                    int numeroContaDeposito = Integer.parseInt(numeroContaDepositoStr);
                    String valorDepositoStr = JOptionPane.showInputDialog("Digite o valor para depósito: ");
                    double valorDeposito = Double.parseDouble(valorDepositoStr);
                    banco.depositar(numeroContaDeposito, valorDeposito);
                    break;

                case "Solicitar empréstimo":
                    String numeroContaEmprestimoStr = JOptionPane.showInputDialog("Digite o número da conta: ");
                    int numeroContaEmprestimo = Integer.parseInt(numeroContaEmprestimoStr);
                    String valorEmprestimoStr = JOptionPane.showInputDialog("Digite o valor do empréstimo: ");
                    double valorEmprestimo = Double.parseDouble(valorEmprestimoStr);
                    banco.solicitarEmprestimo(numeroContaEmprestimo, valorEmprestimo);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}

	

