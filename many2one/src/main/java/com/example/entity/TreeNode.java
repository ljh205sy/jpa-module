package com.example.entity;

import sun.reflect.generics.tree.Tree;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/8 10:14
 */
@Entity
@Table(name = "t_tree")
public class TreeNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nodename;
    @Column
    private String nodedesc;

    // 增加外键关联，一个node节点下面  -> 对应多个子节点

    // 增加外键关联， 只有一个父节点，多个子节点。  对于一个节点只有一个父节点，那么就是   多对一
    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "id")
    private TreeNode pTreeNode;

    public TreeNode getpTreeNode() {
        return pTreeNode;
    }

    public void setpTreeNode(TreeNode pTreeNode) {
        this.pTreeNode = pTreeNode;
    }

    public TreeNode() {
    }

    public TreeNode(String nodename) {
        this.nodename = nodename;
    }


    //    @OneToMany
//    @JoinColumn(name = "pid")
//    private Set<TreeNode> treeNodes;
//    public Set<TreeNode> getTreeNodes() {
//        return treeNodes;
//    }
//    public void setTreeNodes(Set<TreeNode> treeNodes) {
//        this.treeNodes = treeNodes;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public String getNodedesc() {
        return nodedesc;
    }

    public void setNodedesc(String nodedesc) {
        this.nodedesc = nodedesc;
    }
}
