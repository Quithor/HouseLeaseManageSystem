package top.yanquithor.table;

import java.sql.Date;
import java.util.Objects;

/**
 * <p>This is the rental contract category.</p>
 * @since 2023.12.26
 * @author YanQuithor
 */
public class Lease extends LinkTable {
    
    private Date begin;             //合同起始时间
    private Date end;               //合同结束时间
    private double deposit;         //押金
    
    public Lease(int house, int tenant, Date begin, Date end, double deposit) {
        super(house, tenant, new Date(System.currentTimeMillis()));
        this.begin = begin;
        this.end = end;
        this.deposit = deposit;
    }
    public Lease(int house, int tenant, Date begin, int time,double deposit) {
        super(house, tenant, new Date(System.currentTimeMillis()));
        this.begin = begin;
        this.end = begin;
        this.deposit = deposit;
        if (end.getMonth()+time>12) {
            end.setYear(end.getYear() + (time / 12));
            end.setMonth(end.getMonth() + (time % 12));
        } else end.setMonth(end.getMonth()+time);
    }
    
    public void setBegin(Date begin) {
        this.begin = begin;
    }
    public void setEnd(Date end) {
        this.end = end;
    }
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
    
    public void addTime(int month) {
        if (end.getMonth()+month>12) {
            end.setYear(end.getYear() + (month / 12));
            end.setMonth(end.getMonth() + (month % 12));
        } else end.setMonth(end.getMonth()+month);
    }
    
    public Date getBegin() {
        return begin;
    }
    public Date getEnd() {
        return end;
    }
    public double getDeposit() {
        return deposit;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Lease lease = (Lease) object;
        return Double.compare(deposit, lease.deposit) == 0 && Objects.equals(begin, lease.begin) && Objects.equals(end, lease.end);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), begin, end, deposit);
    }
    
    @Override
    public String toString() {
        return "Lease{" +
                "begin=" + begin +
                ", end=" + end +
                ", deposit=" + deposit +
                "} " + super.toString();
    }
    
    @Override
    public void display() {
    
    }
}
