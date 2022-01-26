package com.heinzelman.pegaz.tools;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@ToString
@Getter
public class MyConfig {

    private final String user;
    private final String password;
    private final String servername;
    private final String portnumber;
    private final String databasename;
    private final String path;

    public MyConfig()  {

        String appConfigPath =  "..\\config.ini" ;
        File f = new File( appConfigPath );
        if ( !f.exists()) {
           appConfigPath =  "config.ini" ;
           f = new File( appConfigPath );
        }
        try {
            if ( f.exists() )  System.out.println( "[ OK ] Load config from: " + f.getCanonicalPath() );
        } catch (IOException e) { e.printStackTrace();}

        Properties appProps = new Properties();
        try ( FileInputStream inputStream = new FileInputStream(appConfigPath) ){
            appProps.load( inputStream );
        } catch(RuntimeException | IOException e ) { e.printStackTrace(); }

        this.user = appProps.getProperty("myconfig.user");
        this.password = appProps.getProperty("myconfig.password");
        this.servername = appProps.getProperty("myconfig.servername");
        this.portnumber = appProps.getProperty("myconfig.portnumber");
        this.databasename = appProps.getProperty("myconfig.databasename");
        this.path = appProps.getProperty("myconfig.path");

        System.out.println( "[ OK ] Settings loaded" );
    }

    public String getUser() { return user; }
    public String getPassword() { return password; }
    public String getServername() { return servername; }
    public String getPortnumber() { return portnumber; }
    public String getDatabasename() { return databasename; }
    public String getPath() { return path; }

}










