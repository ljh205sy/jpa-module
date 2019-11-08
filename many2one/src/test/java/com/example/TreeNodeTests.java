package com.example;

import com.example.entity.Address;
import com.example.entity.Person;
import com.example.entity.TreeNode;
import com.example.repository.TreeNodeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TreeNodeTests {

    @Autowired
    private TreeNodeRepository treeNodeRepository;


    @Test
    public void testPerSaveTreeNode() {
        TreeNode treeNode = new TreeNode("nodename2");
        treeNode.setNodedesc("描述1111描述1222");
        treeNodeRepository.save(treeNode);
    }


    @Test
    public void testSaveTreeNodeRef() {
        TreeNode pnode = treeNodeRepository.findById(2).get();
        TreeNode curnode = new TreeNode("nodename3");
        curnode.setpTreeNode(pnode);
        treeNodeRepository.save(curnode);
    }

    @Test
    public void testFindTreeNodeRef() {
        TreeNode treeNode = new TreeNode();
        treeNode.setNodename("Nod");
        treeNode.setNodedesc("desc");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("nodename", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())//相当于lower(nodename) like 'Nod%'
                .withMatcher("nodename", ExampleMatcher.GenericPropertyMatchers.startsWith())  // 相当于nodename like 'Nod%'
                .withMatcher("nodedesc", ExampleMatcher.GenericPropertyMatchers.contains()) //全部模糊查询，即%{nodedesc}%
                .withIgnorePaths("nodedesc");//忽略字段，即不管nodedesc是什么值都不加入查询条件
        Example<TreeNode> example = Example.of(treeNode, exampleMatcher);
        List<TreeNode> all = treeNodeRepository.findAll(example);
        for (TreeNode node : all) {
            System.out.println(node.getId() + "-----------------" + node.getNodename());
            Optional<TreeNode> node1 = Optional.ofNullable(node.getpTreeNode());
            node1.ifPresent((u) -> {
                System.out.println(u.getId() + "++++++++++++" + u.getNodename());
            });
        }
    }

    @Test(expected = NullPointerException.class)
    public void testOptional() {
        TreeNode treeNode = null;
        Optional<TreeNode> treeNode1 = Optional.of(treeNode);
        String name = "John";
        Optional<String> opt = Optional.ofNullable(name);
        Assert.assertEquals("John", opt.get());
    }

    @Test
    public void testFindPageTreeNode() {
        int countPerPage = 5;
        long count = treeNodeRepository.count();
    }

    @Test
    public void testFindTreeNodeRef2() {
        TreeNode treeNode = new TreeNode();
        treeNode.setNodename("nodename2");
        Example<TreeNode> example = Example.of(treeNode);
        List<TreeNode> all = treeNodeRepository.findAll(example);
        for (TreeNode node : all) {
            System.out.println(node.getId() + "-----------------" + node.getNodename());
            // 创建 Optional实例，Optional.of(node); 如果node为空，则of()方法会抛出 NullPointerException：
//            Optional<TreeNode> treeNode1 = Optional.of(node.getpTreeNode());
            Optional<TreeNode> node1 = Optional.ofNullable(node.getpTreeNode());
            node1.ifPresent((u) -> {
                System.out.println(u.getId() + "++++++++++++" + u.getNodename());
            });
        }
    }


    @Test
    public void testdeleteTreeNode() {
        TreeNode treeNode = treeNodeRepository.findById(2).get();
        treeNodeRepository.delete(treeNode);
    }
}