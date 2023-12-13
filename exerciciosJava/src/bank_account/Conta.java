package bank_account;

public class Conta {
    protected int numero;
    protected String tipo;
    protected String titular;
    protected double saldo;
    protected double emprestimoDisponivel;

    public Conta(int numero, String tipo, String titular) {
        this.numero = numero;
        this.tipo = tipo;
        this.titular = titular;
        this.saldo = 0;
        this.emprestimoDisponivel = 0;
    }

    protected void depositar(double valor) {
        saldo += valor;
    }

    protected boolean sacar(double valor) {
        if (tipo.equals("PF")) {
            valor+= 1.75;
        } 
        
        
        if(saldo >= valor){
        	saldo -= valor;
        	return true;
        }else {
           return false;
        }
		
		
    }

    protected boolean solicitarEmprestimo(double valor) {
    	
    	if(tipo.equals("Poupança")) {
    		return false; // Conta poupança não pode realizar emprestimos
    	}
    	
        double limite;

        if ("pf".equals(tipo) || "PF".equals(tipo)) {
            limite = 1100.50;
        } else if ("pj".equals(tipo) || "PJ".equals(tipo)) {
            limite = 2500;
        } else {
            return false;
        }

        if (emprestimoDisponivel + valor <= limite) {
            emprestimoDisponivel += valor;
            saldo += valor;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Conta " + numero + " - Titular: " + titular + " - Saldo: R$" + String.format("%.2f", saldo);
    }

    public String salvarConta() {
        return numero + "," + tipo + "," + titular + "," + saldo + "," + emprestimoDisponivel;
    }
	public void carregarConta(String dados) {
        String[] partes = dados.split(",");
        numero = Integer.parseInt(partes[0]);
        tipo = partes[1];
        titular = partes[2];
        saldo = Double.parseDouble(partes[3]);
        emprestimoDisponivel = Double.parseDouble(partes[4]);
    }

}
