package cn.wangjie.learn;

import cn.wangjie.learn.dao.UserDao;
import cn.wangjie.learn.entity.User;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.net.URL;
import java.sql.Connection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadWriteApplicationTests {

    @Autowired
    private UserDao userDao;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Test
    public void contextLoads() {
    }

    @Test
    public void dbTest() {
        System.out.println(JSON.toJSONString(userDao.getById(3)));
    }

    @Test
    @Transactional
    @Rollback
    public void sqlScriptTest() {
       // SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection connection = sqlSession.getConnection();
        URL resource = this.getClass().getResource("/script.sql");
        FileSystemResource fileSystemResource = new FileSystemResource(resource.getFile());
        EncodedResource er = new EncodedResource(fileSystemResource,"UTF-8");
        ScriptUtils.executeSqlScript(connection,er);
        List<User> users = userDao.listByName("test3");
        System.out.println(JSON.toJSONString(users));

    }
}

