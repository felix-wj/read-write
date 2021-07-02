package cn.wangjie.learn.dao;

import cn.wangjie.learn.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

	User getById(@Param("id") Integer id);

	List<User> listByName(@Param("name") String name);
}
