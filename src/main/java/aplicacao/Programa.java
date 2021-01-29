package aplicacao;

import model.entidade.AluguelCarro;
import model.entidade.Veiculo;
import model.servicos.BrasilTaxaServico;
import model.servicos.ServicoDeAluguel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner ler = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

        System.out.println("Insira a data de aluguel");
        System.out.print("Modelo do carro: ");
        String modeloCarro = ler.nextLine();
        System.out.print("pego no dia (dd/MM/yyy HH:ss): ");
        Date comeco = sdf.parse(ler.nextLine());
        System.out.print("retorno no dia  (dd/MM/yyy HH:ss): ");
        Date fim = sdf.parse(ler.nextLine());

        AluguelCarro carro = new AluguelCarro(comeco, fim, new Veiculo(modeloCarro));

        System.out.print("Entre com o preco por hora: ");
        double precoPorHora = ler.nextDouble();
        System.out.print("Entre com o preco por hora: ");
        double precoPorDia = ler.nextDouble();

        ServicoDeAluguel servicoDeAluguel = new ServicoDeAluguel(precoPorDia, precoPorHora, new BrasilTaxaServico());

        servicoDeAluguel.processarFatura(carro);

        System.out.println("FATURA: ");
        System.out.println("Pagamento basico: " + String.format("%.2f", carro.getPagameto().getPagamentoBasico()));
        System.out.println("Taxa: " + String.format("%.2f", carro.getPagameto().getTaxa()));
        System.out.println("Pagamento Total: " + String.format("%.2f", carro.getPagameto().getPagamentoTotal()));




        ler.close();
    }
}
