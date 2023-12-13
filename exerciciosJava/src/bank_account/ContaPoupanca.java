package bank_account;

public class ContaPoupanca extends Conta{
	
	 public ContaPoupanca(int numero, String titular) {
	        super(numero, "Poupança", titular);
	    }

	    @Override
	    public void depositar(double valor) {
	        super.depositar(valor);
	        // Para adicionar o rendimento de 1.5% na conta poupança
	        saldo += valor * 0.015;
	    }
	}


