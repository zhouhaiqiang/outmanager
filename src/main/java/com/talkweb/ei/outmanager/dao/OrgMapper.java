package com.talkweb.ei.outmanager.dao;

import com.talkweb.ei.outmanager.model.TreeNode;

import java.util.List;

public interface OrgMapper {
  
    List<TreeNode> selectOrg();
    
}