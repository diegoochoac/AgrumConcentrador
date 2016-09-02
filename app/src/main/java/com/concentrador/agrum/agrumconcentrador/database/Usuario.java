package com.concentrador.agrum.agrumconcentrador.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by diego on 26/08/16.
 */
//@DatabaseTable(tableName = "usuario")
public class Usuario implements Serializable {

    public static final String ID = "usuario_id";
    public static final String NOMBRE = "usuario_name";

    @DatabaseField(generatedId = true, columnName = ID)
    private int usuarioId;

    @DatabaseField(columnName = NOMBRE)
    private String usuarioName;

    @DatabaseField
    private String specialty;

    @DatabaseField
    private String phoneNumber;

    @DatabaseField
    private String avatarUri;

    // Foreign key defined to hold associations
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public Contratista contratista;

    public Usuario() {
    }

    public Usuario(final String name,
                   final String specialty, final String phoneNumber,
                   final String avatarUri, Contratista contra) {

        this.usuarioName = name;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.avatarUri = avatarUri;
        this.contratista = contra;
    }

    //<editor-fold desc="Metodos Get-Set">
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioName() {
        return usuarioName;
    }

    public void setUsuarioName(String usuarioName) {
        this.usuarioName = usuarioName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    public Contratista getContratista() {
        return contratista;
    }

    public void setContratista(Contratista contratista) {
        this.contratista = contratista;
    }

    //</editor-fold>
}
