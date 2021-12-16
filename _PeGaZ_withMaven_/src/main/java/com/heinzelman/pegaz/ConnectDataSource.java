package com.heinzelman.pegaz;

/*=====================================================================
File: 	 ConnectDataSource.java
Summary: This Microsoft JDBC Driver for SQL Server sample application
         demonstrates how to connect to a SQL Server database by 
	     using a data source object.
---------------------------------------------------------------------
This file is part of the Microsoft JDBC Driver for SQL Server Code Samples.
Copyright (C) Microsoft Corporation.  All rights reserved.
 
This source code is intended only as a supplement to Microsoft
Development Tools and/or on-line documentation.  See these other
materials for detailed information regarding Microsoft code samples.
 
THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF
ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
PARTICULAR PURPOSE.
=====================================================================*/

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class ConnectDataSource {

    private Long lastId;

    //get_like( @projName VARCHAR(50)




    public Map<Long, String> getLike( String likeName ) {

        Map<Long, String> map = new HashMap<>();

        SQLServerDataSource ds = new SQLServerDataSource();

        ds.setUser("PG_Znak_Wodny_User");
        ds.setPassword("PG4d@t@");
        ds.setServerName("argo.grupazpr.pl");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("PG_Znak_Wodny");

        try (Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("exec get_like ? ");) {
            cstmt.setString( 1, likeName );
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                map.put( rs.getLong(1 ) , rs.getString(2)  );
                //System.out.println( rs.getLong(1 ) + " : " + rs.getString(2)   );
            }
        }
        catch (SQLException e) {  e.printStackTrace();  }
        return map;
    }












    public Map<Long, String> getAllProject() {

        Map<Long, String> map = new HashMap<>();

        // Create datasource.
        SQLServerDataSource ds = new SQLServerDataSource();

        ds.setUser("PG_Znak_Wodny_User");
        ds.setPassword("PG4d@t@");
        ds.setServerName("argo.grupazpr.pl");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("PG_Znak_Wodny");


        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("execute get_all ");) {
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                map.put( rs.getLong(1 ) , rs.getString(2));
                //System.out.println( rs.getLong(1 ) + " : " + rs.getString(2)   );
            }
        }

        catch (SQLException e) { e.printStackTrace(); }
        return map;
    }











    public Long addProject( String projName ) {

        // Create datasource.
        SQLServerDataSource ds = new SQLServerDataSource();

        ds.setUser("PG_Znak_Wodny_User");
        ds.setPassword("PG4d@t@");
        ds.setServerName("argo.grupazpr.pl");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("PG_Znak_Wodny");


        try (Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("exec addProj ?");) {

            cstmt.setString(1, projName );
            ResultSet rs = cstmt.executeQuery();

            // Iterate through the data in the result set and display it.
            rs.next();
            lastId = rs.getLong(1);
            System.out.println("new Id: " + lastId );
        }

        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
            return lastId;
    }

}