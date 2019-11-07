# jpa-module
manytomany 的测试用例

1. 测试joinTable ， mappedBy的用法和含义
2. junit的test测试用例，模拟数据的增删改查操作

由Person对象来维护关系。 在多对多种，不要求有级联删除数据，级联新增数据等。
mappedBy和@joinTable、@JoinColumn互斥，    mappedBy=“对方实体的属性，即多的一放的属性值”， 表示不维护关系
