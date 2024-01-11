package top.yanquithor.table;

import top.yanquithor.func.Display;

import java.sql.Date;
import java.util.Objects;

/**
 * <p>This class is used connection about more to more in database.</p>
 * <p>All connection what is more to more want create object must use extend class of this class.</p>
 * @since 2023.12.26
 * @author YanQuithor
 * */
public abstract class LinkTable implements Display {
    
    private int house;              //房子id
    private int tenant;             //租户id
    private Date time;              //创建时间
    
    public LinkTable(int house, int tenant, Date time) {
        this.house = house;
        this.tenant = tenant;
        this.time = time;
    }
    
    public void setHouse(int house) {
        this.house = house;
    }
    public void setTenant(int tenant) {
        this.tenant = tenant;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    
    public int getHouse() {
        return house;
    }
    public int getTenant() {
        return tenant;
    }
    public Date getTime() {
        return time;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LinkTable that = (LinkTable) object;
        return house == that.house && tenant == that.tenant && Objects.equals(time, that.time);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(house, tenant, time);
    }
}
