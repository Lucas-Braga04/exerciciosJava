package bank_account;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Banco {

	private ArrayList<Conta> contas;

    public Banco() {
        this.contas = new ArrayList<>();
    }

    public void cadastrarConta(String tipo, String titular) {
        int numeroConta = contas.size() + 1;
        Conta conta;

        if ("Poupança".equals(tipo)) {
            conta = new ContaPoupanca(numeroConta, titular);
        } else {
            conta = new Conta(numeroConta, tipo, titular);
        }

        int depositoInicial = JOptionPane.showConfirmDialog(null, "Deseja realizar um depósito inicial na conta " + numeroConta + "?", "Depósito Inicial", JOptionPane.YES_NO_OPTION);

        if (depositoInicial == JOptionPane.YES_OPTION) {
            String valorDepositoStr = JOptionPane.showInputDialog("Digite o valor do depósito inicial: ");
            double valorDeposito = Double.parseDouble(valorDepositoStr);
            conta.depositar(valorDeposito);
        }

        contas.add(conta);
        JOptionPane.showMessageDialog(null, "Conta " + numeroConta + " cadastrada com sucesso!\n" + conta);
    }

	    public void excluirConta(int numeroConta) {
	        for (Conta conta : contas) {
	            if (conta.numero == numeroConta) {
	                if (conta.saldo > 0) {
	                    if (!conta.sacar(conta.saldo)) {
	                        JOptionPane.showMessageDialog(null, "Não foi possível sacar o saldo da conta.");
	                        return;
	                    }
	                } else if (conta.emprestimoDisponivel > 0) {
	                    conta.saldo -= conta.emprestimoDisponivel;
	                    conta.emprestimoDisponivel = 0;
	                } else {
	                    JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!");
	                    contas.remove(conta);
	                    return;
	                }
	            }
	        }

	        JOptionPane.showMessageDialog(null, "Não foi possível excluir a conta.");
	    }

	    public void sacar(int numeroConta, double valor) {
	        for (Conta conta : contas) {
	            if (conta.numero == numeroConta) {
	                if (conta.sacar(valor)) {
	                    JOptionPane.showMessageDialog(null, "Saque de R$" + String.format("%.2f", valor) + " realizado com sucesso.\n" + conta);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar o saque.");
	                }
	                return;
	            }
	        }

	        JOptionPane.showMessageDialog(null, "Conta não encontrada.");
	    }

	    public void depositar(int numeroConta, double valor) {
	        for (Conta conta : contas) {
	            if (conta.numero == numeroConta) {
	                conta.depositar(valor);
	                JOptionPane.showMessageDialog(null, "Depósito de R$" + String.format("%.2f", valor) + " realizado com sucesso.\n" + conta);
	                return;
	            }
	        }

	        JOptionPane.showMessageDialog(null, "Conta não encontrada.");
	    }
    
	    public void solicitarEmprestimo(int numeroConta, double valor) {
	        for (Conta conta : contas) {
	            if (conta.numero == numeroConta) {
	                if (conta.solicitarEmprestimo(valor)) {
	                    JOptionPane.showMessageDialog(null, "Empréstimo de R$" + String.format("%.2f", valor) + " solicitado com sucesso.\n" + conta);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Valor de empréstimo excede o limite disponível.");
	                }
	                return;
	            }
	        }

	        JOptionPane.showMessageDialog(null, "Conta não encontrada.");
	    }
	
	
	public void salvarContas() {
		try (PrintWriter writer = new PrintWriter("contas.txt")) {
			for (Conta conta : contas) {
				writer.println(conta.salvarConta());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void carregarContas() {
        contas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("contas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Conta conta = new Conta(0, "", "");
                conta.carregarConta(linha);
                contas.add(conta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
