package top.yanquithor.func;

import top.yanquithor.exception.StringToLongException;
import top.yanquithor.sql.LandlordDB;
import top.yanquithor.sql.TenantDB;
import top.yanquithor.table.Landlord;
import top.yanquithor.table.People;
import top.yanquithor.table.Tenant;

/**
 * @author YanQuithor
 * @since 2024.1.1
 */
public interface Registry {
    static boolean registry(People p) {
        boolean result = false;
        if (p instanceof Landlord) LandlordDB.add((Landlord) p);
        else if (p instanceof Tenant) TenantDB.add((Tenant) p);
        return result;
    }
    
    static boolean forgePassword(People p,String newPW) {
        boolean result = false;
        try {
            p.setPassword(newPW);
            if (p instanceof Landlord) result = LandlordDB.modify((Landlord) p);
            else if (p instanceof Tenant) result = TenantDB.modify((Tenant) p);
        } catch (StringToLongException e) {
            e.printStackTrace();
        }
        return result;
    }
}
