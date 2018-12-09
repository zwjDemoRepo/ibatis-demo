import com.ibatis.mysql.dao.impl.AccountDaoImpl;
import com.ibatis.mysql.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2018/12/9 0009.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext.xml")
public class TestAccountDAO {

    @Autowired
    private AccountDaoImpl accountDao;

    @Test
    public void test_add_acc(){
        Account accAdd = new Account("王五","p123","DL002","后台",201812);
        Integer result = accountDao.addAcc(accAdd);
        System.out.println(result);
    }

    @Test
    public void test_query_acc(){
        System.out.println(accountDao.selectAccById(3));
    }
}
