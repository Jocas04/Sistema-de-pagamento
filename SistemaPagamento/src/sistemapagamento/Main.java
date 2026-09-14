package sistemapagamento;
import java.util.ArrayList; //usado para armazenar os colaboradores

// a classe abstrata vai servir como classe pai para os tipos de colaboradores
abstract class Colaborador {
    //Dados obrigatórios dos colaboradores
    String matricula;
    String nome;
    double salarioBase;
    
    //construtor da classe , sempre que um colaborador é criado executa esse comando
   public Colaborador(String matricula, String nome, double salarioBase) {
       //guarda os valores
   this.matricula = matricula;
   this.nome = nome;
   this.salarioBase = salarioBase;
   }

   //
   public abstract double calcularSalario();

   public String getMatricula() {
   return matricula;
   }
   public String getNome() {
   return nome;
   }
   public double getSalarioBase() {
   return salarioBase;
   }
   public String getTipo() {
   return this.getClass().getSimpleName();
   }
   }
    class Colaboradorpadrao extends Colaborador {
    public Colaboradorpadrao(String matricula, String nome, double salarioBase) {
    super(matricula, nome, salarioBase);
    }
    @Override
    public double calcularSalario() {
        return salarioBase;
    }
    }
    class Colaboradorcomissionado extends Colaborador {
    private double valorVendas;
    private double percentualComissao;
    public Colaboradorcomissionado(String matricula, String nome, double salarioBase, double valorVendas, double percentualComissao) {
    super(matricula, nome, salarioBase);
    this.valorVendas = valorVendas;
    this.percentualComissao = percentualComissao;
    }
    @Override
    public double calcularSalario() {
        double comissao = valorVendas * percentualComissao;
        return salarioBase + comissao;
    }
    }
    class Colaboradorproducao extends Colaborador {
    private int quantidadeProduzida;
    private double valorUnidade;
    public Colaboradorproducao(String matricula, String nome, double salarioBase, int quantidadeProduzida, double valorporUnidade) {
    super (matricula, nome, salarioBase);
    this.quantidadeProduzida = quantidadeProduzida;
    this.valorUnidade = valorUnidade;
}
    @Override
    public double calcularSalario() {
    double produtividade = quantidadeProduzida * valorUnidade;
    return salarioBase + produtividade;
}
}
    public class Main{   
    public static void main(String[] args) {
        ArrayList<Colaborador> colaboradores = new ArrayList<>();
        colaboradores.add(new Colaboradorpadrao(
        "001","Dorival", 3000));
        colaboradores.add(new Colaboradorcomissionado(
        "002","Arlindo", 3000, 20000, 0.10));
        colaboradores.add(new Colaboradorproducao(
        "003", "Evandro", 3000, 100, 7));
        System.out.println("Colaboradores Registrados");
        for(Colaborador c : colaboradores) {
            System.out.println(c.getMatricula() + " | "
                               + c.getNome()+ " | "
                                + c.getTipo());
        }
        System.out.println("Folha de Pagamento");
        double totalFolha = 0;
        for(Colaborador c : colaboradores) {
            double salarioFinal = c.calcularSalario();
            System.out.printf("%s | %s | %s | R$ %.2f%n",
                                c.getMatricula(), c.getNome(), c.getTipo(), salarioFinal);
            totalFolha += salarioFinal;
        }
        System.out.println("Resumo da Folha de Pagamento");
        System.out.println("Quantidade de Colaboradores " + colaboradores.size());
        System.out.printf("Total da Folha: R$ %.2f%n", totalFolha);
    }
    }
    

