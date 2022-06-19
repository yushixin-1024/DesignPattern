package com.yushixin.uml.association;

import java.util.List;

// 多对多关联
public class Association_05 {

    // 用户
    static class User {
        private List<Role> roleList;
    }

    // 角色
    static class Role {
        private List<User> userList;
    }
}
