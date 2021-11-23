package com.heinzelman;

import com.heinzelman.frontEnds.Window;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static java.awt.EventQueue.invokeLater;

public class Main {
    Object connecttion;


    public static void main  (String[] args) {

        invokeLater(new Runnable() {
            @Override public void run() {
                String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                //String appConfigPath = rootPath + "com\\heinzelman\\config.ini";
                String appConfigPath = rootPath + "config.ini";
                Properties appProps = new Properties();
                try ( FileInputStream inputStream = new FileInputStream(appConfigPath) ){
                    appProps.load( inputStream );
                } catch(RuntimeException | IOException e ) { e.printStackTrace(); }


                
                
                /*


    "jdbc:sqlserver://localhost:1433;" +
     "databaseName=AdventureWorks;integratedSecurity=true;" +
     "encrypt=true;trustServerCertificate=true

                 */
                
                


                //String url = "jdbc:sqlserver://"+appProps.getProperty("serverAddr")+10.40.1.112+";useUnicode=true;characterEncoding=UTF-8;databaseName=002"; =
                String url = "jdbc:sqlserver://"+appProps.getProperty("serverAddr")+":1433;useUnicode=true;characterEncoding=UTF-8;databaseName="+appProps.getProperty("dbName")+"integratedSecurity=true;encrypt=true;trustServerCertificate=true";
                Properties suppliedProperties = new Properties();
                    suppliedProperties.setProperty("username", appProps.getProperty("dbUser"));
                    suppliedProperties.setProperty("password", appProps.getProperty("dbPass"));

                /*

                  CREATE TABLE projects (
                    id INT NOT NULL IDENTITY PRIMARY KEY,
                    name VARCHAR(255) NOT NULL UNIQUE,
                    active BIT ,
                    version INT
                 );


                CREATE OR ALTER  PROCEDURE  addProj( @projName VARCHAR(50) )
                AS
                IF (
                    ( SELECT COUNT(*) FROM dbo.projects WHERE [name]=@projName) = 0
                )
                INSERT INTO dbo.projects ( [name], [active] ) VALUES ( @projName, 1 );
                SELECT id, [name] FROM dbo.projects WHERE [name]=@projName;
                GO



                CREATE OR ALTER PROCEDURE get_all
                AS
                SELECT id, [name] FROM dbo.projects
                GO


                CREATE OR ALTER  PROCEDURE  get_like( @projName VARCHAR(50) )
                AS
	            SELECT id, [name] FROM dbo.projects WHERE [name] LIKE '%' + RTRIM(@projName) + '%';
	            GO


                -- usage:
                -- EXEC addProj 'abcd';

                 */

                //String sql = " EXEC addProj 'abcd' ";
                //String sql = " EXEC get_all  ";
                //String sql = " EXEC get_like 'c'  "; // TRIM UPPERCASE


                String sql = "   CREATE TABLE projects (\n" +
                        "                    id INT NOT NULL IDENTITY PRIMARY KEY,\n" +
                        "                    name VARCHAR(255) NOT NULL UNIQUE,\n" +
                        "                    active BIT ,\n" +
                        "                    version INT\n" +
                        "                 );\n" +
                        "\n" +
                        "\n" +
                        "                CREATE OR ALTER  PROCEDURE  addProj( @projName VARCHAR(50) )\n" +
                        "                AS\n" +
                        "                IF (\n" +
                        "                    ( SELECT COUNT(*) FROM dbo.projects WHERE [name]=@projName) = 0\n" +
                        "                )\n" +
                        "                INSERT INTO dbo.projects ( [name], [active] ) VALUES ( @projName, 1 );\n" +
                        "                SELECT id, [name] FROM dbo.projects WHERE [name]=@projName;\n" +
                        "                GO\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "                CREATE OR ALTER PROCEDURE get_all\n" +
                        "                AS\n" +
                        "                SELECT id, [name] FROM dbo.projects\n" +
                        "                GO\n" +
                        "\n" +
                        "\n" +
                        "                CREATE OR ALTER  PROCEDURE  get_like( @projName VARCHAR(50) )\n" +
                        "                AS\n" +
                        "\t            SELECT id, [name] FROM dbo.projects WHERE [name] LIKE '%' + RTRIM(@projName) + '%';\n" +
                        "\t            GO" +
                        "" +
                        "" +
                        "  EXEC addProj 'abcd' ;  " +
                        "  EXEC addProj 'abc' ;  " +
                        "" +
                        "  EXEC get_all ; " +
                        "" +
                        "  EXEC get_like 'c' ; " +
                        "" +
                        "";



                try (   Connection connection = new SQLServerDriver().connect( url, suppliedProperties )  ) {

                    Statement statement = connection.createStatement();

                    ResultSet rs = statement.executeQuery(sql);
                    ResultSetMetaData metaData = rs.getMetaData();
                    //int length = rs.getFetchSize();
                    int width = metaData.getColumnCount();

                    //System.out.println( length );
                    //System.out.println( width );

                    while (rs.next()) {
                        System.out.println( rs.getLong(1) + " : " + rs.getString(2) ); //rs.getNString(1)

                    }

                } catch (SQLException /*| IOException*/ throwables) { throwables.printStackTrace(); }
                /*




                            StringBuffer out =
                                }
                                out.append("</").append(columnNames[i]).append(">");
                            }
                            out.append(SUFFIX);
                            outStream.write(out.toString());
                        }

                    } catch (SQLException | IOException throwables) {
                        throwables.printStackTrace();
                    }
                }
                */

                System.out.println( appProps );



                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e ) { e.printStackTrace(); }
                new Window().setVisible(true);


            }
        });

    }

                           /*




                Properties catalogProps = new Properties();
                catalogProps.load(new FileInputStream(catalogConfigPath));


                String appVersion = appProps.getProperty("version");
                assertEquals("1.0", appVersion);

                assertEquals("files", catalogProps.getProperty("c1"));
                        */


        /*
        javax.swing.UIManager$LookAndFeelInfo[Metal javax.swing.plaf.metal.MetalLookAndFeel]
        javax.swing.UIManager$LookAndFeelInfo[Nimbus javax.swing.plaf.nimbus.NimbusLookAndFeel]
        javax.swing.UIManager$LookAndFeelInfo[CDE/Motif com.sun.java.swing.plaf.motif.MotifLookAndFeel]
        javax.swing.UIManager$LookAndFeelInfo[Windows com.sun.java.swing.plaf.windows.WindowsLookAndFeel]
        javax.swing.UIManager$LookAndFeelInfo[Windows Classic com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel]
        */

}

