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

   //método abstrato, cada classe filha irá ter que criar seu próprio calcular salario
   public abstract double calcularSalario();
//retorna a matrícula
   public String getMatricula() {
   return matricula;
   }
   //retorna o nome
   public String getNome() {
   return nome;
   }
   //retorna o salario base
   public double getSalarioBase() {
   return salarioBase;
   }
   //retorna o tipo do colaborador
   public String getTipo() {
   return this.getClass().getSimpleName();
   }
   }
//colaborador padrao recebe só o salario base
    class Colaboradorpadrao extends Colaborador {
    public Colaboradorpadrao(String matricula, String nome, double salarioBase) //construtor
    {
    super(matricula, nome, salarioBase); //chama o construtor
    }
    @Override
    //retorna o salario base
    public double calcularSalario() {
        return salarioBase;
    }
    }
//colaborador comissionado recebe salario base + comissao
    class Colaboradorcomissionado extends Colaborador {
    private double valorVendas; //total vendido
    private double percentualComissao; //percentual de comissao por venda
    public Colaboradorcomissionado(String matricula, String nome, double salarioBase, 
    /* Construtor */               double valorVendas, double percentualComissao) {
    super(matricula, nome, salarioBase); //chama o construtor
    //inicializa total vendido e percentual de comissao
    this.valorVendas = valorVendas;
    this.percentualComissao = percentualComissao;
    }
    //calcula o salario 
    //comissao = vendas x percentual
    //salario = salario base + comissao
    @Override
    public double calcularSalario() {
        double comissao = valorVendas * percentualComissao; //calcula a comissao
        return salarioBase + comissao; //retorna o salario final
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
    

