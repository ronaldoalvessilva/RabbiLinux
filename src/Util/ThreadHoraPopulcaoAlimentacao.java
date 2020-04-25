/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Dao.ControleEntradasSaidasPopulacaoInternos;
import Dao.ConversaoDatasAtividadesUnidades;
import Dao.ListagemRegistroEntradaSaidaPopulcao;
import Modelo.AtividadesMensalRealizadaUnidades;
import Modelo.EntradaSaidasPolucaoInternos;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ronal
 */
public class ThreadHoraPopulcaoAlimentacao {

    ControleEntradasSaidasPopulacaoInternos populacao = new ControleEntradasSaidasPopulacaoInternos();
    EntradaSaidasPolucaoInternos objEntradaSaida = new EntradaSaidasPolucaoInternos();
    ListagemRegistroEntradaSaidaPopulcao listaRegistroES = new ListagemRegistroEntradaSaidaPopulcao();
    ConversaoDatasAtividadesUnidades converteData = new ConversaoDatasAtividadesUnidades();
    AtividadesMensalRealizadaUnidades objAtividade = new AtividadesMensalRealizadaUnidades();
    //
    int pHORAS = 00;
    int pMINUTOS = 1;
    int pSEGUNDOS = 0;
    //
    String tipoOperacao = "População";
    //
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss"); // HORAIO DE 24 HORAS, PARA O DE 12 HORAS UTILIZAR hh:mm:ss
    SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
    Date data = new Date();
    String hora = formatter.format(data);
    String date = formatter2.format(data);
    String nameUser = "ADMINISTRADOR DO SISTEMA";
    //
    String pHORA_PROCESSO = "";
    String pDATA_PROCESSO = "";

    
    public void threadHoraPopulcao() {
        Date data = new Date();
        String hora = formatter.format(data); // Data da conexão
        String date = formatter2.format(data); // Hora da conexão
        pDATA_PROCESSO = String.valueOf(date);
        pHORA_PROCESSO = String.valueOf(hora);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, pHORAS);
        calendar.set(Calendar.MINUTE, pMINUTOS);
        calendar.set(Calendar.SECOND, pSEGUNDOS);

        Date time = calendar.getTime();

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (pHORAS == new Date().getHours() && pMINUTOS == new Date().getMinutes()) {
                    ficha();
                    timer.cancel();
                    System.out.println("População foi gerada com sucesso.");
                } else {
                    System.out.println("População não lancada");
                }
            }
        }, time);
    }

    public void ficha() {      
            objEntradaSaida.setDataMovimento(data);
            objEntradaSaida.setHorarioMovimento(pHORA_PROCESSO);
            objEntradaSaida.setTipoOperacao(tipoOperacao);
            objEntradaSaida.setPopulacao(objEntradaSaida.getPopulacao());
            objEntradaSaida.setUsuarioInsert(nameUser);
            objEntradaSaida.setDataInsert(date);
            objEntradaSaida.setHorarioInsert(hora);
            listaRegistroES.selecionarRegistroEntrada(objEntradaSaida);
            populacao.incluirEntradaSaidaPopulacao(objEntradaSaida);
            //CONVERTER AS DADOS NA TABELA        
            converteData.alterarDataEntradasSaidasUnidades(objAtividade);
    }
}
