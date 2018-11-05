package vlibtour.vlibtour_tour_management.bean;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.corba.ee.impl.orbutil.codegen.Statement;
import com.sun.corba.ee.spi.legacy.connection.Connection;

import vlibtour.vlibtour_tour_management.entity.VlibTourTourManagementException;

public class createTourByAdmin {
	@BeforeClass
	public static void setUpClass() throws Exception {
	}
	@Test (expected = VlibTourTourManagementException.class)
	public void CreateTours
	{
	    private static String dbURL = "jdbc:derby://localhost:1527/VlibtourDB;create=true;user=me;password=mine";
	    private static String tableName = "Tours";
	    // jdbc Connection
	    private static Connection conn = null;
	    private static Statement stmt = null;

	    public static void main(String[] args)
	    {
	        createConnection();
	        insertTours(5, "Museum", "Lourve");
	        selectTours();
	        shutdown();
	    }
	    
	    private static void createConnection()
	    {
	        try
	        {
	            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
	            //Get a connection
	            conn = DriverManager.getConnection(dbURL); 
	        }
	        catch (Exception except)
	        {
	            except.printStackTrace();
	        }
	    }
	    
	    private static void insertTours(int tourID, String tourName, String tourDescription)
	    {
	        try
	        {
	            stmt = conn.createStatement();
	            stmt.execute("insert into " + tableName + " values (" +
	                    id + ",'" + tourName + "','" + tourDescription +"')");
	            stmt.close();
	        }
	        catch (SQLException sqlExcept)
	        {
	            sqlExcept.printStackTrace();
	        }
	    }
	    
	    private static void selectTours()
	    {
	        try
	        {
	            stmt = conn.createStatement();
	            ResultSet results = stmt.executeQuery("select * from " + tableName);
	            ResultSetMetaData rsmd = results.getMetaData();
	            int numberCols = rsmd.getColumnCount();
	            for (int i=1; i<=numberCols; i++)
	            {
	                //print Column Names
	                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
	            }

	            System.out.println("\n-------------------------------------------------");

	            while(results.next())
	            {
	                int tourID = results.getInt(1);
	                String tourName = results.getString(2);
	                String tourDescription = results.getString(3);
	                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
	            }
	            results.close();
	            stmt.close();
	        }
	        catch (SQLException sqlExcept)
	        {
	            sqlExcept.printStackTrace();
	        }
	    }
	    
	    private static votourID shutdown()
	    {
	        try
	        {
	            if (stmt != null)
	            {
	                stmt.close();
	            }
	            if (conn != null)
	            {
	                DriverManager.getConnection(dbURL + ";shutdown=true");
	                conn.close();
	            }           
	        }
	        catch (SQLException sqlExcept)
	        {
	            
	        }

	    }
	}



