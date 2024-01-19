package com.mimsoft.manuales;

public class Configuration {
    public static final boolean SERVER = false;
    public static final String SERVER_PATH = SERVER
            ?""
            :"http://localhost:8080/Manuales-1.0";
    public static final String ROOT_PATH_FILE_SERVER = SERVER
            ?""
            :"C:/Users/AMCODE/Desktop/projects/Manuales/MigrationManuales/development/storage/";
}
