package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMS {
    protected String            msisdn;
    protected String            recipient;
    protected String            sender;
    protected String            short_code;
    protected String            transaction_id;
    protected LocalDateTime     timestamp;
    protected String            Sms;
    protected String            Promo;

    public String getMsisdn() {
        return msisdn;
    }

    public String getRecipient() { return recipient;}

    public String getSender() {
        return sender;
    }

    public String getShort_code() {
        return short_code;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public static Connection getCon() {
        return con;
    }

    public String getSms() {
        return Sms;
    }

    public String getPromo() {
        return Promo;
    }

    private static Connection con;

    final private static Logger logger = Logger.getLogger(SMS.class.getName());

    //SMS sent

    public SMS(String msisdn,String recipient,String sender,String short_code
            ,String transaction_id,LocalDateTime timestamp,String Sms,String Promo){

        this.msisdn         = msisdn;
        this.recipient      = recipient;
        this.sender         = sender;
        this.short_code     = short_code;
        this.transaction_id = transaction_id;
        this.timestamp      = timestamp;
        this.Sms            = Sms;
        this.Promo          = Promo;
    }


    //SMS CHECKER

    public Map<String, String> getSmsMap() {
        Map<String, String> smsMap = new HashMap<>();

        smsMap.put("msisdn",    this.getMsisdn());
        smsMap.put("message",   this.getSms());
        smsMap.put("shortCode", this.getShort_code());

        return smsMap;
    }

    public static boolean sendSms(String sender_msisdn, String receiver_msisdn, String text) {
        boolean sent = false;
        try {
            // Connect to the database
            con = DataBase.connect(con);

            // Check if connected to database
            if (!con.isClosed()) {
                logger.log(Level.INFO, "Connected to database");

                // prepare statement
                PreparedStatement sqlQuery = con.prepareStatement("INSERT INTO sms (sender_msisdn, receiver_msisdn, body) VALUES (?,?,?)");
                sqlQuery.setString   (1, sender_msisdn);
                sqlQuery.setString   (2, receiver_msisdn);
                sqlQuery.setString   (3, text);

                // execute statement
                int row = sqlQuery.executeUpdate();
                if (row > 0) {
                    logger.log(Level.INFO, "SMS sent");
                    sent = true;
                } else {
                    logger.log(Level.WARNING, "SMS FAILED TO SEND");
                }

                } else {
                logger.log(Level.SEVERE, "Connection to the database FAILED");
            }

            // Disconnect from the database
            DataBase.disconnect(con);
            if (con.isClosed()) {
                logger.log(Level.INFO, "Disconnected from the database");
            } else {
                logger.log(Level.WARNING, "Connection to database might still be open");
            }

            } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.INFO, "Connected to database", e);
             } catch (Exception e) {
            e.printStackTrace();
            } finally {
            return sent;
            }

    }



}

