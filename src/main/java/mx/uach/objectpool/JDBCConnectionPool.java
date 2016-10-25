package mx.uach.objectpool;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Daniela Santillanes Castro
 * @version 1.0
 * @since 24/10/2016
 */
public class JDBCConnectionPool extends ObjectPool<Connection>{
    
    private String url,usr,pwd;

    public JDBCConnectionPool(String driver, String url, String usr, String pwd) {
        super();
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            //TODO
        }
        this.url = url;
        this.usr = usr;
        this.pwd = pwd;
    }
    
    @Override
    protected Connection create() {
        try {
            return (DriverManager.getConnection(url, usr, pwd));
        } catch (Exception e) {
            //TODO: Implementación error
            return (null);
        }
    }

    @Override
    public boolean validate(Connection obj) {
        try {
            return !obj.isClosed();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void expire(Connection obj) {
         try {
            obj.close();
        } catch (Exception e) {
        }
    }
    
}
