package es.abel.dam;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Partido implements Comparable<Partido>, Serializable {

    private String local;
    private String visitante;
    private Division division;
    private Resultado resultado;
    private Date fecha;

    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public String getVisitante() {
        return visitante;
    }
    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }
    public Division getDivision() {
        return division;
    }
    public void setDivision(Division division) {
        this.division = division;
    }
    public Resultado getResultado() {
        return resultado;
    }
    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Partido(String local, String visitante, Division division, Resultado resultado, Date fecha) {
        this.local = local;
        this.visitante = visitante;
        this.division = division;
        this.resultado = resultado;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Partido: " + getLocal() + " - " + getVisitante() + " Division: " + getDivision() +
                "\nResultado " + getResultado().toString() +
                "\nFecha: " + sdf.format(getFecha()) +
                "\n-------------------------------";
    }

    @Override
    public int compareTo(Partido p) {
        if(p.getFecha().compareTo(this.getFecha()) > 0){
            return 1;
        }
        else if(p.getFecha().compareTo(this.getFecha()) < 0){
            return -1;
        }
        else{
            return 0;
        }
    }


}
