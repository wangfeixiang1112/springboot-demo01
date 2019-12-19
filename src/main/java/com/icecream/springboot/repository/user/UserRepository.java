package com.icecream.springboot.repository.user;

import com.icecream.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//用户不需要做任何实现，甚至都无须在UserRepository 中定义任何方法
@Repository
public interface UserRepository extends CrudRepository<Long,User> {
    User saveOrUpdateUser(User user);//新增或修改用户

    void deleteUser(Long id);//删除用户

    User getUserById(Long id);//根据用户id获取用户

    List<User> userList();//获取所有用户得列表

    //通过名称来模糊查询
    List<User> findUsersByNameLike(String name);
}
