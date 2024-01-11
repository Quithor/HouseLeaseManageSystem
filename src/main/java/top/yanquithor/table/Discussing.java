package top.yanquithor.table;

import java.sql.Date;
import java.util.Objects;

/**
 * This is discussing tenant to house.
 * @author YanQuithor
 * @since 2023.12.26
 */
public class Discussing extends LinkTable {
    
    private String content;             //评论内容
    
    public Discussing(int house, int tenant, String content) {
        super(
                house,          //房子的id
                tenant,         //租户的id
                new Date(System.currentTimeMillis())
                                //当前系统时间
        );
        this.content = content; //评论
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    @Override
    public void display() {
        System.out.println("Comment:{"
                + "\n\tTenant id: " + getTenant()
                + "\n\tHouse id: " + getHouse()
                + "\n\tComment content:\n"
                + content
                + "\n}");
    }
    
    @Override
    public String toString() {
        return "Discuss{" +
                "content='" + content + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Discussing that = (Discussing) object;
        return Objects.equals(content, that.content);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), content);
    }
}
