package com.talkweb.ei.outmanager.dao;

import com.talkweb.ei.outmanager.model.KeyValue;
import com.talkweb.ei.outmanager.model.TreeNode;

import java.util.List;

public interface OrgMapper {
  
    List<TreeNode> selectOrg();
    
    List<KeyValue> selectUser(String companyid);

    List<TreeNode> getSubOrgByName(String name);
    
    List<TreeNode> getSubOrgByID(String orgid);
    
}