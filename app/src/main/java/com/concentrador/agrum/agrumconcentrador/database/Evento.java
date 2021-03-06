package com.concentrador.agrum.agrumconcentrador.database;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by diego on 12/09/16.
 */
public class Evento {

    public static final String ID = "evento_id";


    @DatabaseField(generatedId = true, columnName = ID)
    private int eventoId;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public TipoEvento tipoEvento;

    @DatabaseField
    private String fecha;

    @DatabaseField
    private String horaInicio;

    @DatabaseField
    private String horaFin;


    public Evento() {
    }

    public Evento(final String horaInicio, final String horaFin, final String fecha) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
    }

    //<editor-fold desc="Metodos Get-Set">
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    //</editor-fold>
}
