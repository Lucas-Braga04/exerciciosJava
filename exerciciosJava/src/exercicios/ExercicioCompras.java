package exercicios;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class ExercicioCompras {

    private String personName;
    private double salary;
    private String productName;
    private double valueItem;
    private int parcelas;
    private int quantity;
    private int days;
    private double hour;

    protected ExercicioCompras() {
    }

    protected ExercicioCompras(String personName, double salary, String productName, double valueItem, int quantity) {
        this.personName = personName;
        this.salary = salary;
        this.productName = productName;
        this.valueItem = valueItem;
        this.quantity = quantity;
    }

    protected int getParcelas() {
        return parcelas;
    }

    protected int setParcelas(int parcelas) {
        return this.parcelas = parcelas;
    }

    protected double getHour() {
        return hour;
    }

    protected double setHour(double hour) {
        return this.hour = hour;
    }

    protected String getPersonName() {
        return personName;
    }

    protected String setPersonName(String personName) {
        return this.personName = personName;
    }

    protected double getSalary() {
        return salary;
    }

    protected double setSalary(double salary) {
        return this.salary = salary;
    }

    protected String getProductName() {
        return productName;
    }

    protected String setProductName(String productName) {
        return this.productName = productName;
    }

    protected double getValueItem() {
        return valueItem;
    }

    protected double setValueItem(double valueItem) {
        return this.valueItem = valueItem;
    }

    protected int getQuantity() {
        return quantity;
    }

    protected int setQuantity(int quantity) {
        return this.quantity = quantity;
    }

    protected int getDays() {
        return days;
    }

    protected void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "ExercicioCompras [Olá Sr(a) " + personName + "! \n  Com base no rendimento informado você deverá trabalhar por " + days + " dias e " + hour + " horas]";
    }

    public String toStringParcelado(int parcelaAtual, int totalParcelas) {
        return "Parcela " + parcelaAtual + "/" + totalParcelas + " - " +
                "Olá Sr(a) " + personName +
                "! \n Com base no rendimento informado, você deverá trabalhar por " + days +
                " dias e " + hour + " horas para pagar esta parcela.";
    }

    protected String calcularDiasHoras(double salarioMensal, double valor, int parcelas, double valorRestante) {
        double horasNecessarias = (valor / salarioMensal) * 220; 
        days = (int) horasNecessarias / 8; 
        hour = horasNecessarias % 8;

        // Formatando a hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String formattedHour = dateFormat.format(new java.sql.Time((long) (hour * 60 * 60 * 1000)));

        return "Olá Sr(a) " + personName +
                "! \nCom base no rendimento informado, você deverá trabalhar por " +
                days + " dias e " + formattedHour + " horas para pagar esta parcela.\n" +
                "Valor da parcela: R$ " + String.format("%.2f", valor) +
                "\nSaldo restante: R$ " + String.format("%.2f", valorRestante);
    }

    protected void executavel() {
        personName = setPersonName(JOptionPane.showInputDialog(null, "Olá, por favor, digite seu nome: "));
        salary = setSalary(Double.parseDouble(JOptionPane.showInputDialog(null, "Por gentileza, informe seu rendimento mensal: ")));
        productName = setProductName(JOptionPane.showInputDialog(null, "Certo. Agora, por favor me informe o nome do produto que deseja comprar: "));
        valueItem = setValueItem(Double.parseDouble(JOptionPane.showInputDialog(null, "Agora digite o valor do produto: ")));
        quantity = setQuantity(Integer.parseInt(JOptionPane.showInputDialog(null, "Muito bem, agora me informe a quantidade: ")));

        String question = JOptionPane.showInputDialog(null, "Certo, mais uma coisa, como será pago o produto? Informe [a] para pagamento à vista, [c] para cartão de crédito:");

        if ("a".equals(question)) {
            // Pagamento à vista
            double valorRestante = salary - (getValueItem() * getQuantity());
            JOptionPane.showMessageDialog(null, calcularDiasHoras(getSalary(), getValueItem() * getQuantity(), 0, valorRestante), "Relatório Financeiro", JOptionPane.INFORMATION_MESSAGE);
            executavel();
        } else if ("c".equals(question)) {
            int escolhas = setParcelas(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade de parcelas:")));

            if (escolhas >= 1) {
                double salarioMensal = getSalary();
                double valorTotal = getValueItem() * getQuantity();

                // Pagamento parcelado
                int parcelas = escolhas;
                double valorParcela = valorTotal / parcelas;
                double valorRestante = salary - valorTotal;

                for (int i = 1; i <= parcelas; i++) {
                    JOptionPane.showMessageDialog(null, calcularDiasHoras(salarioMensal, valorParcela, parcelas, valorRestante), "Detalhes da Parcela " + i, JOptionPane.INFORMATION_MESSAGE);
                    valorRestante -= valorParcela; // Atualiza o valor restante para o próximo ciclo
                    executavel();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Opção de parcelamento inválida, por favor escolha outra opção.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}