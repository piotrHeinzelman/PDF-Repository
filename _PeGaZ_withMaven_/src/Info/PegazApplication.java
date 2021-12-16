package com.heinzelman.pegaz;


import com.heinzelman.pegaz.frontEnds.Window;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static java.awt.EventQueue.invokeLater;

public class PegazApplication {

	Object connecttion;

	public static void main  (String[] args) {

		invokeLater( new Runnable() {
			@Override public void run() {
				String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
				String appConfigPath = rootPath + "application.properties" ; //""config.ini";
				Properties appProps = new Properties();
				try ( FileInputStream inputStream = new FileInputStream(appConfigPath) ){
					appProps.load( inputStream );
				} catch(RuntimeException | IOException e ) { e.printStackTrace(); }



				//*****************************


				String connectionUrl = "jdbc:sqlserver://"+appProps.getProperty("serverAddr")+":"+appProps.getProperty("serverPort")+";databaseName="+appProps.getProperty("dbName")+";user="+appProps.getProperty("dbUser")+";password="+appProps.getProperty("dbPass")+";integratedSecurity=true;encrypt=true;trustServerCertificate=true";
				System.out.println( "connectionUrl : "  + connectionUrl  );

				try {
					final Class<?> aClass = Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//spring.datasource.driverClassName=
					System.out.println( aClass );
					DriverManager.getConnection( connectionUrl );
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}















				//String url = "jdbc:sqlserver://"+appProps.getProperty("serverAddr")+":1433;databaseName="+appProps.getProperty("dbName")+";integratedSecurity=true;encrypt=true;trustServerCertificate=true"; // useUnicode=true;characterEncoding=UTF-8;





				//String connectionUrl = "jdbc:sqlserver://<server>:<port>;databaseName=AdventureWorks;user=<user>;password=<password>";




				/*
				// Create test data as an example.
				StringBuffer buffer = new StringBuffer(4000);
				for (int i = 0; i < 4000; i++)
					buffer.append((char) ('A'));

				try (Connection con = DriverManager.getConnection(connectionUrl);
					 Statement stmt = con.createStatement();
					 PreparedStatement pstmt = con.prepareStatement("UPDATE Production.Document SET DocumentSummary = ? WHERE (DocumentID = 1)");) {

					pstmt.setString(1, buffer.toString());
					pstmt.executeUpdate();

					// In adaptive mode, the application does not have to use a server cursor
					// to avoid OutOfMemoryError when the SELECT statement produces very large
					// results.

					// Create and execute an SQL statement that returns some data.
					String SQL = "SELECT Title, DocumentSummary FROM Production.Document";

					// Display the response buffering mode.
					SQLServerStatement SQLstmt = (SQLServerStatement) stmt;
					System.out.println("Response buffering mode is: " + SQLstmt.getResponseBuffering());
					SQLstmt.close();

					// Get the updated data from the database and display it.
					ResultSet rs = stmt.executeQuery(SQL);

					while (rs.next()) {
						Reader reader = rs.getCharacterStream(2);
						if (reader != null) {
							char output[] = new char[40];
							while (reader.read(output) != -1) {
								// Do something with the chunk of the data that was
								// read.
							}

							System.out.println(rs.getString(1) + " has been accessed for the summary column.");
							// Close the stream.
							reader.close();
						}
					}
				}
				// Handle any errors that may have occurred.
				catch (Exception e) {
					e.printStackTrace();
				}

				*/




















                /*


    "jdbc:sqlserver://localhost:1433;" +
     "databaseName=AdventureWorks;integratedSecurity=true;" +
     "encrypt=true;trustServerCertificate=true

                 */




				//String url = "jdbc:sqlserver://"+appProps.getProperty("serverAddr")+10.40.1.112+";useUnicode=true;characterEncoding=UTF-8;databaseName=002"; =
				//String url = "jdbc:sqlserver://"+appProps.getProperty("serverAddr")+":1433;databaseName="+appProps.getProperty("dbName")+";integratedSecurity=true;encrypt=true;trustServerCertificate=true"; // useUnicode=true;characterEncoding=UTF-8;
				//System.out.println( url );
				//Properties suppliedProperties = new Properties();
				//suppliedProperties.setProperty("username", appProps.getProperty("dbUser"));
				//suppliedProperties.setProperty("password", appProps.getProperty("dbPass"));

				/*
				try {
					System.out.println(
					SSLContext.getInstance("TLS")
					);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				//com.ibm.jsse2.overrideDefaultTLS=True;

				 */

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

	/*

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

				} catch (SQLException   throwables) { throwables.printStackTrace(); }
				*/

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

