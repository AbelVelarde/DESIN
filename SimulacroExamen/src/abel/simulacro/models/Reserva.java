package abel.simulacro.models;

import abel.simulacro.utils.DateUtils;

import java.util.Date;

public class Reserva {

    private String aula;
    private Date fecha;

    public String getAula() {
        return aula;
    }
    public void setAula(String aula) {
        this.aula = aula;
    }
    public String getFecha() {
        return DateUtils.convertToLocalDate(fecha).toString();
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Reserva(String aula, Date fecha){
        this.aula = aula;
        this.fecha = fecha;
    }
}


