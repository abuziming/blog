
package com.myblog.blog.mapper;
import com.myblog.blog.entity.Type;
import com.myblog.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Usermapper {
        //添加保存user
        int saveUser(User user);

        //根据id查询
        User getUserById(Integer id);
        //根据分类名查询分类
        User getUserByName(String nickname);
        //修改分类
        int updateUser(User user);
        //删除分类
        void deleteUser(Integer id);
        //根据用户名和密码查询检验用户
        User getAllUsernameandPassword(String username,String password);
}