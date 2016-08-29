package com.concentrador.agrum.agrumconcentrador.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by diego on 26/08/16.
 */
//@DatabaseTable(tableName = "trabajo")
public class Trabajo implements Serializable {

    @DatabaseField(generatedId = true, columnName = "trabajo_id")
    private int trabajoId;

    @DatabaseField
    private String trabajoName;

    public Trabajo() {
    }

    public Trabajo(final String name) {

        this.trabajoName = name;

    }

}
