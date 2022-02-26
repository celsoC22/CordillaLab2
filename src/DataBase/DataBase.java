package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

    final private static Logger logger = Logger.getLogger(DataBase.class.getName());
    private static Connection con = null;

    public static Connection connect(Connection con) {

            // connecting to Database

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/celsodb?useTimezone=true&serverTimezone=UTC"
                    ,"root","admin");


            logger.info("Connected");

            // inserting to tbl_sms
            Statement statement = connect(con).createStatement();
            statement.executeUpdate("INSERT INTO tbl_sms"+"VALUES('example')");

            //SQL SELECT query
            String query =" SELECT * FROM tbl_sms";

            // execute the query, and get a java resultset

            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                int id = rs.getInt("id");
                String startDate = rs.getString("startDate");
                String endDate = rs.getString("endDate");
                Date promoCode = rs.getDate("promoCode");
                String msisdn = rs.getString("msisdn");
            }
            statement.close();

            return con;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Not Connected", e);
            return null;
        }
    }

    public static boolean disconnect(Connection con) {
        try {
            if (con != null) {
                con.close();
                logger.info("Disconnected");
                return true;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Not Connected", e);
        }
        return false;
    }

}

