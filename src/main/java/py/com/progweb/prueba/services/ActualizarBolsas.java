package py.com.progweb.prueba.services;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ActualizarBolsas {
    // timer que se ejecuta cada minuto
    // @Schedule(hour = "*", minute = "*", second = "*/10", info ="Every minute timer",persistent=false)
    @Schedule(hour = "20", minute = "49", second = "00", info = "Every minute timer", persistent = false)
    public void printDate() {
        System.out.println(" It is " + new java.util.Date().toString());
    }
}
