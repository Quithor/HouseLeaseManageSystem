# 房屋租赁系统帮助文档

***

### 一、数据库设计

  1. 使用关系型数据库（**MySQL**）
  2. 实体设计  
    a. 房东(<u>房东id</u>，姓名，密码，年龄，性别)  
    b. 租户(<u>租户id</u>，姓名，密码，年龄，性别)  
    c. 房子(<u>房子编号</u>，<u style="text-decoration: underline dotted;">房东id</u>，房屋租金，房屋户型，房屋状态，房屋地址)
  3. 联系关系  
    a. 合同签订(房子-租户，多对多)  
&nbsp;&nbsp;&nbsp;&nbsp;(
<u style="text-decoration: underline dotted;">房屋编号</u>，
<u style="text-decoration: underline dotted;">租户编号</u>，签订时间，合同开始时间，合同结束时间，合同押金)  
    b. 预约看房(房子-租户，多对多)  
&nbsp;&nbsp;&nbsp;&nbsp;(
<u style="text-decoration: underline dotted;">房屋编号</u>，
<u style="text-decoration: underline dotted;">租户编号</u>，预约的时间)  
    c. 房屋评论(房子-租户，多对多)  
     &nbsp;&nbsp;&nbsp;&nbsp;(
<u style="text-decoration: underline dotted;">房屋编号</u>，
<u style="text-decoration: underline dotted;">租户编号</u>，评论时间，评论内容，评论星级)
  4. 触发器设计  
     (1)、同步删除  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a. 删除房东联动删除房子  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b. 删除租户联动删除联系表中的对应数据  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c. 删除房子联动删除联系表中的对应数据     
     (2)、同步更新  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当```lease```表添加数据时同步更新```house```表的对应的```leaseStatus```列的数据为```false```

### 二、Java程序设计

<p style="font-size: 13px">
&nbsp;&nbsp;&nbsp;&nbsp;
注：本次Java开发是针对数据库设计的，用户看到的一切功能实现都是数据库优先
</p>

  1. 类设计  
    在Java中设计了6个包。  
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    其中```top.yanqiuthor.table```包中的类是基于数据库中的表和理论模型设计的*JavaBean*类，
    在这个包中，有两个抽象类：```People```和```LinkTable```。  
    &nbsp;&nbsp;&nbsp;(1).
    ```People```: 这个类对应的是两类用户的身份设计的，
    将两类用户的通用属性抽到抽象父类中，
    并且父类实现```ForgePassword```接口让子类可以多态调用。  
    &nbsp;&nbsp;&nbsp;(2).
    ```LinkTable```:这个类是典型的面向数据库的*JavaBean*类，
    这个类中的成员变量是数据库中三联系表的共有属性，
    这个类实现了```Display```接口方便子类可以快捷的输出到控制台。  
    在```top.yanquithor.sql```包中，我设计了关于数据库操作的几个类。
    当然，因为用第一次纯面向对象编程语言写完整系统的原因，
    我对纯面向对象思想了解还不够深入，
    我的思路就是对号入座，
    将数据库中的每张表都设计了一个对应的工具类进行对应表的数据交换，
    并且我设计了一个接口```DBData```，
    我利用了接口中变量默认是```public static final```修饰的特点，
    在这个接口中用于保存数据库的参数（服务器地址，数据库类型，数据库名，用户名，密码）。  
    在```top.yanquithor.func```包中，
    放着提供给其他类实现的接口，如登录、注册等功能。  
    在```top.yanquithor.exception```包中是我为这个系统创建的几个自定义异常类，
    这些异常可以将用户输入错误数据时抛出对应的错误类型。  
    在```top.yanquithor.course```包中写的是本系统对应的控制台程序，
    由于时间问题，这个包中还有个别功能尚未实现。  
  2. 后续升级
    在时间足够的情况下，可以将系统写成```JavaFX```窗口或```SpringBoot```web网页界面，
    并补齐目前缺失的功能。

###### Author: YanQuithor
###### Date: 2024.1.11
