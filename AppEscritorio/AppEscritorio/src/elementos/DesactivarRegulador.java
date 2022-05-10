package elementos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import basedatos.ReservasBD;

/**
 * Clase que sirve para apagar el control de temperatura de una habitación a las 12:00 cuando acabe una reserva
 * @author Jon Clase
 */
public class DesactivarRegulador extends Thread{
	public final int diaEnMs = 86400000; //24h
	public final int medioDiaEnMs = 43200000; //12h
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	long msActual, sleep;
	LocalDateTime localDateTime;
	
	List<Integer> habitaciones;
	
	public DesactivarRegulador() {
		habitaciones = new ArrayList<>();
	}
	
	/**
	 * Método que esta en continua ejecución y calcula el tiempo que queda hasta las 12:00 y hace un sleep hasta las 12:00
	 * Realiza una query de las habitaciones que terminan hoy y apaga el control de temperatura de la habitacion
	 *@author Jon
	 */
	@Override
	public void run() {
		while(true) {
			this.localDateTime = LocalDateTime.now();
			msActual = (this.localDateTime.getHour() * 3600000) + (this.localDateTime.getMinute() * 60000) + (this.localDateTime.getSecond() * 1000);
			if(this.msActual > medioDiaEnMs) {
				this.sleep = diaEnMs - (msActual - medioDiaEnMs);
			}else {
				this.sleep = medioDiaEnMs - msActual;
			}
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ReservasBD reservasBd = new ReservasBD();
			this.habitaciones = reservasBd.queryHabitaciones();
			//enviarDatosPlaca(true);
			for(int i = 0; i<habitaciones.size(); i++) {
				System.out.println(habitaciones.get(i));
			}
			habitaciones.clear();
		}
	}

}
