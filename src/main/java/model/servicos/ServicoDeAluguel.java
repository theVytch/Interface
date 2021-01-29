package model.servicos;

import model.entidade.AluguelCarro;
import model.entidade.Pagamento;

public class ServicoDeAluguel {

    private Double precoPorDia;
    private Double precoPorHora;

    private  TaxaServico taxaServico;

    public ServicoDeAluguel(Double precoPorDia, Double precoPorHora, TaxaServico taxaServico) {
        this.precoPorDia = precoPorDia;
        this.precoPorHora = precoPorHora;
        this.taxaServico = taxaServico;
    }

    public void processarFatura(AluguelCarro aluguelCarro){
        long t1 = aluguelCarro.getComeco().getTime();
        long t2 = aluguelCarro.getFim().getTime();
        double hora = (double) (t2 - t1) / 1000 / 60 / 60;
        double pagamentoBasico;
        if(hora <= 12.0){
            pagamentoBasico = Math.ceil(hora) * precoPorHora;
        }else{
            pagamentoBasico = Math.ceil(hora / 24) * precoPorDia;
        }

        double tax = taxaServico.taxa(pagamentoBasico);

        aluguelCarro.setPagameto(new Pagamento(pagamentoBasico, tax));

    }
}
