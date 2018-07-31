package com.mv.schelokov.carent.model.db.repository;

import com.mv.schelokov.carent.model.db.dao.InvoiceLineDao;
import com.mv.schelokov.carent.model.db.dao.exceptions.DbException;
import com.mv.schelokov.carent.model.entity.InvoiceLine;
import com.mv.schelokov.carent.model.entity.builders.InvoiceLineBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maxim Chshelokov <schelokov.mv@gmail.com>
 */
public class InvoceLineRepositoryTest {
    
    private Connection connection;
    private InvoiceLineDao ilr;

    public InvoceLineRepositoryTest() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, 
            InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/car_rent_test?autoReconnect=true"
                        + "&useSSL=false&characterEncoding=utf-8",
                "car_rent_app", "Un3L41NoewVA");
        ilr = new InvoiceLineDao(connection);
    }
    
    @After
    public void tearDown() throws SQLException {
        connection.close();
    }
    
    @Test
    public void createNewInvoceType() throws DbException {
        assertTrue(ilr.add(new InvoiceLineBuilder()
                .setInvoiceId(1)
                .setDetails("Штраф за царапину")
                .setAmount(3000)
                .getInvoiceLine()));
    }
    
    @Test
    public void findByInvoiceIdAndDeleteLast() throws DbException {
        List<InvoiceLine> ill = ilr.read(new InvoiceLineDao
                .FindByInvoiceIdCriteria(1));
        assertTrue(ilr.remove(ill.get(ill.size()-1)));
    }
    
    @Test
    public void findByInvoiceIdAndUpdateFirst() throws DbException {
        InvoiceLine iLine = ilr.read(new InvoiceLineDao
                .FindByInvoiceIdCriteria(1)).get(0);
        iLine.setAmount(iLine.getAmount()+1);
        
        assertTrue(ilr.update(iLine));
    }
    
}