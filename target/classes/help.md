# 房屋租赁系统帮助文档

***

### 一、数据库设计

  1. 使用关系型数据库（MySQL）
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
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当lease表添加数据时同步更新house表的对应的leaseStatus列的数据为false

### 二、Java程序设计

<p style="font-size: 13px">
&nbsp;&nbsp;&nbsp;&nbsp;
注：本次Java开发是针对数据库设计的，用户看到的一切功能实现都是数据库优先
</p>

  1. 类设计  
     在Java中设计了个包，其中top.yanqiuthor.table包中的类是基于数据库中的表和理论模型设计的JavaBean类。