# jpa-module
manytomany 的测试用例

1. 测试joinTable ， mappedBy的用法和含义
2. junit的test测试用例，模拟数据的增删改查操作

由Person对象来维护关系。 在多对多种，不要求有级联删除数据，级联新增数据。
mappedBy和@joinTable、@JoinColumn互斥    
mappedBy=“对方实体的属性，即多的一方的属性值”， 表示不维护关系，

@JoinTable注解name属性定义第三方表的表名 person_address
@JoinColumn注解， 外键person_id1 ， 与当前实体的主键关联
inverseJoinColumn注解 外键address_id2 与 另外一张表Address实体关联

所有第三方表数据统一由Person进行管理

Person.java实体
```
Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String guid;
    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_address", joinColumns = @JoinColumn(name = "person_id1"),
            inverseJoinColumns = @JoinColumn(name = "address_id2"))
    private Set<Address> addresss = new HashSet<>();

```

Address.java实体
```
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addresszz;
    @Column
    private String detail;

    // addresss是Person实体中的属性。 此实体中不维护关系，中间表中的数据由Person维护，例如删除，修改等，address实体没有无法操作
    // mapppedBy 与 JoinColumn或者JoinTable互斥。 addresss是对方的实体属性值
    @ManyToMany(mappedBy = "addresss",fetch = FetchType.EAGER)
    private Set<Person> persons = new HashSet<>();

    public Address(String detail) {
        this.detail = detail;
    }
```
