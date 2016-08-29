package com.concentrador.agrum.agrumconcentrador.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by diego on 26/08/16.
 */
//@DatabaseTable(tableName = "usuario")
public class Usuario implements Serializable {

    @DatabaseField(generatedId = true, columnName = "usuario_id")
    private int usuarioId;

    @DatabaseField(columnName = "usuario_name")
    private String usuarioName;

    @DatabaseField
    private String specialty;

    @DatabaseField
    private String phoneNumber;

    @DatabaseField
    private String avatarUri;

    public Usuario() {
    }

    public Usuario(final String name,
                   final String specialty, final String phoneNumber,
                   final String avatarUri) {

        this.usuarioName = name;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.avatarUri = avatarUri;
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
    //</editor-fold>
}