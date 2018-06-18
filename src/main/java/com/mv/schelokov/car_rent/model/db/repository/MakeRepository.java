package com.mv.schelokov.car_rent.model.db.repository;

import com.mv.schelokov.car_rent.model.db.repository.exceptions.CriteriaMismatchException;
import com.mv.schelokov.car_rent.model.db.repository.interfaces.AbstractSqlRepository;
import com.mv.schelokov.car_rent.model.db.repository.interfaces.Criteria;
import com.mv.schelokov.car_rent.model.db.repository.criteria.make.MakeDeleteCriteria;
import com.mv.schelokov.car_rent.model.db.repository.criteria.make.MakeReadCriteria;
import com.mv.schelokov.car_rent.model.entities.Make;
import com.mv.schelokov.car_rent.model.entities.builders.MakeBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Maxim Chshelokov <schelokov.mv@gmail.com>
 */
public class MakeRepository extends AbstractSqlRepository<Make> {
    
    private static final String CREATE_QUERY = "INSERT INTO makes (name) "
            + "VALUES (?)";
    private static final String REMOVE_QUERY = "DELETE FROM makes WHERE"
            + " make_id=?";
    private static final String UPDATE_QUERY = "UPDATE makes SET name=? "
            + "WHERE make_id=?";
    
    /**
     * The Field enum has column names for read methods and number of column for
     * the update method and the add method (in the NUMBER attribute)
     */
    enum Fields {
        MAKE_ID(2), NAME(1);
        
        int NUMBER;
        
        Fields(int number) {
            this.NUMBER = number;
        }
    }

    public MakeRepository(Connection connection) {
        super(connection);
    }
    
    @Override
    protected String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected String getRemoveQuery() {
        return REMOVE_QUERY;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected Make createItem(ResultSet rs) throws SQLException {
        return new MakeBuilder()
                .setId(rs.getInt(Fields.MAKE_ID.name()))
                .setName(rs.getString(Fields.NAME.name()))
                .getMake();
    }

    @Override
    protected void setStatement(PreparedStatement ps, Make item, 
            boolean isUpdateStatement) throws SQLException {
        ps.setString(Fields.NAME.NUMBER, item.getName());
        if (isUpdateStatement)
            ps.setInt(Fields.MAKE_ID.NUMBER, item.getId());
    }

    @Override
    protected boolean checkCriteriaInstance(Criteria criteria, 
            boolean isDeleteCriteria) throws CriteriaMismatchException {
        if (isDeleteCriteria) {
            if (criteria instanceof MakeDeleteCriteria)
                return true;
        } else if (criteria instanceof MakeReadCriteria)
            return true;
        throw new CriteriaMismatchException();
    }
    
}