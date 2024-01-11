package top.yanquithor.table;

import java.sql.Date;

/**
 * <p>This class is the Java Bean class associated with the reservation table.</p>、
 * <p>Tenants can schedule viewings, so objects are not created with the current system's incoming constructs.</p>
 * @author YanQuithor
 * @since 2023.12.26
 */
public class Subscribe extends LinkTable {
    
    public Subscribe(int house, int tenant, Date time) {
        super(house, tenant, time);
    }
    
    @Override
    public void display() {
    
    }
}
