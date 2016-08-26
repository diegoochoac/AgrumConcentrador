package com.concentrador.agrum.agrumconcentrador.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by diego on 26/08/16.
 */
@DatabaseTable(tableName = "usuario")
public class Usuario implements Serializable {

    @DatabaseField(generatedId = true)
    private String id;

    @DatabaseField(index = true, canBeNull = false)
    private String name;

    @DatabaseField
    private String specialty;

    @DatabaseField
    private String phoneNumber;

    @DatabaseField
    private String avatarUri;

    public Usuario() {
    }

    public Usuario(String name,
                   String specialty, String phoneNumber,
                   String bio, String avatarUri) {

        this.name = name;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.avatarUri = avatarUri;
    }

    //<editor-fold desc="Metodos Get">
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAvatarUri() {
        return avatarUri;
    }
    //</editor-fold>

    //<editor-fold desc="Metodos Set">
    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }
    //</editor-fold>

    //<editor-fold desc="Metodos Equals hashCOde">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (!name.equals(usuario.name)) return false;
        if (specialty != null ? !specialty.equals(usuario.specialty) : usuario.specialty != null)
            return false;
        if (phoneNumber != null ? !phoneNumber.equals(usuario.phoneNumber) : usuario.phoneNumber != null)
            return false;
        return avatarUri != null ? avatarUri.equals(usuario.avatarUri) : usuario.avatarUri == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (specialty != null ? specialty.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (avatarUri != null ? avatarUri.hashCode() : 0);
        return result;
    }
    //</editor-fold>
}
