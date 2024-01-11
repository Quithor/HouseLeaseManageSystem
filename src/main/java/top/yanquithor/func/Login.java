package top.yanquithor.func;

import top.yanquithor.sql.LandlordDB;
import top.yanquithor.sql.TenantDB;
import top.yanquithor.table.Landlord;
import top.yanquithor.table.Tenant;

/**
 * @author YanQuithor
 * @since 2024.1.1
 */
public interface Login {
    
    static Tenant tenantLogin(int id, String pw) {
        Tenant people;
        people = TenantDB.getTenant(id);
        if (people.getPassword().equals(pw)) return people;
        else {
            if (people.getPassword() == null)
                System.out.println("ERROR: Failed found this account in database!");
            else System.out.println("ERROR: Password mistake!");
            return null;
        }
    }
    
    static Landlord landlordLogin(int id, String pw) {
        Landlord landlord = LandlordDB.getLandlord(id);
        if (landlord.getPassword().equals(pw)) return landlord;
        else {
            if (landlord.getPassword() == null)
                System.out.println("ERROR: Failed found this account in database!");
            else System.out.println("ERROR: Password mistake!");
            return null;
        }
    }
}
