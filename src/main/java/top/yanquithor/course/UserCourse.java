package top.yanquithor.course;

import top.yanquithor.func.Login;
import top.yanquithor.func.Registry;
import top.yanquithor.table.People;

public abstract class UserCourse implements Login, Registry {
    public abstract void forgePassword(People people,String newPW);
}
