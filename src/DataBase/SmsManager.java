package DataBase;

import java.time.LocalDateTime;

public class SmsManager  implements  SmsManagerInterface{

    public boolean insertSms()                                  {return false;}
    public String retrieveSmsByDate(LocalDateTime dateTime)     { return null;};
    public String retrieveSmsByPromoCode()                      { return null;};
    public String retrieveSmsByMsisdn(String msisdn)            { return null;};
    public String retrieveSmsByMsisdn(String[] msisdn)          { return null;};
    public String retrieveSmsBySent()                           { return null;};
    public String retrieveSmsByReceive()                        {return null;};
}
