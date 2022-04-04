package py.com.progweb.prueba.services;

import py.com.progweb.prueba.ejb.BolsapuntosDAO;
import py.com.progweb.prueba.model.Bolsapuntos;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;


@Singleton
@Startup
public class ActualizarBolsas {
    @Inject
    private BolsapuntosDAO bolsaDAO;

    // timer que se ejecuta cada minuto
    // @Schedule(hour = "*", minute = "*", second = "*/10", info = "Every minute timer", persistent = false)
    @Schedule(hour = "00", minute = "43", second = "00", info = "Every minute timer", persistent = false)
    public void actualizarBolsas() {
        System.out.println(" Proceso ejecutado en fecha " + new java.util.Date().toString());
        // lista de bolsas vencidas
        List<Integer> idBolsas = bolsaDAO.listarVencidos();
        for (Integer id : idBolsas) {
            // por cada bolsa, poner puntos de bolsa a 0
            Bolsapuntos bolsa = bolsaDAO.obtener(id);
            bolsa.setPuntaje_asignado(0);
            bolsaDAO.actualizar(bolsa);
            System.out.println("Bolsa: " + id + " vencida");
        }

    }
}
