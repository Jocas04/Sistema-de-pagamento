package sistemapagamento;
import java.util.ArrayList; //usado para armazenar os colaboradores
import java.util.Scanner; //usado para o menu

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
    super(matricula, nome, salarioBase); //chama a classe pai
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
    super(matricula, nome, salarioBase); //chama a c;asse pai
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
//Esse colaborador recebe por producao
    class Colaboradorproducao extends Colaborador {
    private int quantidadeProduzida; //quantidade produzida
    private double valorUnidade; //valor pago por unidade  produzida
    public Colaboradorproducao(String matricula, String nome, double salarioBase,
                                int quantidadeProduzida, double valorporUnidade) {
    super (matricula, nome, salarioBase); //chama a classe pai
    //inicializa atributos
    this.quantidadeProduzida = quantidadeProduzida; 
    this.valorUnidade = valorUnidade;
}
    //fórmula - produtividade = quantidade x valor por unidade
    //salário= salário base + produtividade
    @Override
    public double calcularSalario() {
    double produtividade = quantidadeProduzida * valorUnidade; //calcula produtividade
    return salarioBase + produtividade; //calcula salário final
}
}//classe principal
public class Main{   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
           //cria lista que armazena qualquer tipo de colaborador
        ArrayList<Colaborador> colaboradores = new ArrayList<>();
        do{
            System.out.println("MENU ");
            System.out.println("1 - Cadastrar Colaborador");
            System.out.println("2 - Listar Colaboradores");
            System.out.println("3 - Gerar folha de pagamento");
            System.out.println("4 - Resumo da folha de pagamento");
            System.out.println("0 - Sair");
            System.out.println("Insira um número: ");
            opcao = sc.nextInt();
            
            switch(opcao) {
                case 1: 
                    System.out.println("1 - Padrao");
                    System.out.println("2 - Comissionado");
                    System.out.println("3 - Producao");
                    int tipo = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Matricula: ");
                    String matricula = sc.nextLine();
                    System.out.println("Nome: ");
                    String nome= sc.nextLine();
                    System.out.println("Salário Base: ");
                    double salariobase= sc.nextDouble();
                    if(tipo == 1) {
                        colaboradores.add(new Colaboradorpadrao(matricula, nome, salariobase));
                        System.out.println("Cadastrado!");
                    }
                    if(tipo == 2) {
                        System.out.println("Valor das Vendas: ");
                        double vendas = sc.nextDouble();
                        System.out.println("Percentual de Comissao: ");
                        double comissao= sc.nextDouble();
                        colaboradores.add(new Colaboradorcomissionado(matricula, nome, salariobase, vendas, comissao));
                        System.out.println("Cadastrado!");
                    }
                    if(tipo == 3) {
                        System.out.println("Quantidade Produzida: ");
                        int quantidade = sc.nextInt();
                        System.out.println("Valor por Unidade: ");
                        double valor = sc.nextDouble();
                        colaboradores.add(new Colaboradorproducao(matricula, nome, salariobase, quantidade, valor));
                        System.out.println("Cadastrado!");
                    }
                    break;
                case 2: System.out.println("Colaboradores Registrados");
        for(Colaborador c : colaboradores) {
            System.out.println(c.getMatricula() + " | " + c.getNome()+ " | " + c.getTipo());
               }
                    break;
                case 3: break;
                case 4: break;
                case 0: System.out.println("Encerrando");
                deafault: System.out.println("INVÁLIDO");
            }          
        } while (opcao!=0);
        
     
        //cadastra colaborador padrao
        colaboradores.add(new Colaboradorpadrao(
        "001","Dorival", 3000));
        //cadastra colaborador comissionado
        colaboradores.add(new Colaboradorcomissionado(
        "002","Arlindo", 3000, 20000, 0.10));
        //cadastra colaborador por producao
        colaboradores.add(new Colaboradorproducao(
        "003", "Evandro", 3000, 100, 7));
        //exibe todos os colaboradores cadastrados
        
        System.out.println("Folha de Pagamento");   //exibe folha de pagamento
        double totalFolha = 0; //variavel responsavel por somar salarios
        for(Colaborador c : colaboradores) {    //percorre todos os colaboradores
            double salarioFinal = c.calcularSalario(); //chama o calculo de salario certo pra cada colaborador 
            System.out.printf("%s | %s | %s | R$ %.2f%n", //mnostra os dados do colaborador
                                c.getMatricula(), c.getNome(), c.getTipo(), salarioFinal);
            totalFolha += salarioFinal;
        }
        System.out.println("Resumo da Folha de Pagamento");
        System.out.println("Quantidade de Colaboradores " + colaboradores.size());
        System.out.printf("Total da Folha: R$ %.2f%n", totalFolha);
    }
    }
    

