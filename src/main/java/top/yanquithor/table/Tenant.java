package top.yanquithor.table;

import top.yanquithor.exception.IdException;
import top.yanquithor.exception.StringToLongException;

/**
 * <p>This is a tenant class.</p>
 * @author YanQuithor
 * @since 2023.12.21
 */
public class Tenant extends People {
    
    public Tenant() {}
    
    public Tenant(int id, String username, String password) throws IdException, StringToLongException {
        super(id, username, password);
    }
    
    public Tenant(int id, String username, String password, int age, String sex) {
        super(id, username, password, age, sex);
    }
    
}
