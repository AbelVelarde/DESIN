package es.abel.dam;

import java.io.Serializable;

public class Resultado implements Serializable {
    private int resultadoLocal;
    private int resultadoVisitante;

    public Resultado(int resultadoLocal, int resultadoVisitante) {
        this.resultadoLocal = resultadoLocal;
        this.resultadoVisitante = resultadoVisitante;
    }

    public int getResultadoLocal() {
        return resultadoLocal;
    }

    public void setResultadoLocal(int resultadoLocal) {
        resultadoLocal = resultadoLocal;
    }

    public int getResultadoVisitante() {
        return resultadoVisitante;
    }

    public void setResultadoVisitante(int resultadoVisitante) {
        resultadoVisitante = resultadoVisitante;
    }


    @Override
    public String toString() {
        return getResultadoLocal() + "-" + getResultadoVisitante();
    }
}
