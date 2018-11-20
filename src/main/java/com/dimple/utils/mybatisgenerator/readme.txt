功能：自定义注释（根据数据库中的comment生成注释）

配置方法：
mybatis-generator.xml
    需要配置数据库信息
      <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                            connectionURL="jdbc:mysql://localhost:3306/blog?useUnicode=true&amp;characterEncoding=UTF8"
                            userId="root" password="123456">
      </jdbcConnection>
    需要配置bean、dao、mapper的生成地址
    需要配置数据库信息
StartUp.java
    需要配置mybatis-generator.xml的绝对地址


使用方法：
    配置完成后点击StartUp运行
