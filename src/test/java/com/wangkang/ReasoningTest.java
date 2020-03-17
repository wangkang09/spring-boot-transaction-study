package com.wangkang;

import com.wangkang.entity.User1;
import com.wangkang.mapper.ReasoningTestMapper;
import com.wangkang.mapper.ReasoningTestMapper2;
import com.wangkang.service.ReasoningTestService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 推理证明
 *
 * @author wangkang
 * @date 2020-03-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTransactionStudyApplication.class)
@SuppressWarnings("all")
public class ReasoningTest {

    @Autowired
    ReasoningTestService service;

    @Autowired
    ReasoningTestMapper mapper;

    @Autowired
    ReasoningTestMapper2 mapper2;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void before() {
        mapper.truncate();
        mapper2.truncate();
    }
    /**
     * NULL 方法受事务影响，外层事务被回滚，NULL 方法也会被回滚
     */
    @Test
    public void required_NULL_error_rollback() {
        try {
            service.required_NULL_error_rollback();
        } catch (Exception e) {
            System.out.println(e);
        }
        // REQUIRED 的发生数据库异常
        assertThat(systemOutRule.getLog()).contains("Error querying database.  Cause: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table \"USER22\" not found; SQL statement:");
        assertThat(systemOutRule.getLog()).contains("org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only");
        //内部数据库错误，会通过事务切面传递到外部事务，设置外部事务为 rollback
        assertThat(systemOutRule.getLog()).contains("Participating transaction failed - marking existing transaction as rollback-only");
        //事务内部异常及时被捕获，也会造成异常抛出
        assertThat(systemOutRule.getLog()).contains("org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only");
        assertThat(systemOutRule.getLog()).contains("Rolling back JDBC transaction on Connection");
        //之前完成的数据库操作也会被回滚
        assert mapper.selectById(1)==null;
    }

    /**
     * required 内部的 NULL 方法发生 数据库错误，required 方法不会被回滚
     */
    @Test
    public void required_NULL_commit_error() {
        service.required_NULL_commit_error();
        //NULL 发生数据库错误
        assertThat(systemOutRule.getLog()).contains("### Error querying database.  Cause: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table \"USER22\" not found; SQL statement:");
        //因为 NULL 没有事务切面，不会设置外层事务的 rollback 状态
        assertThat(systemOutRule.getLog()).doesNotContain("Participating transaction failed - marking existing transaction as rollback-only");
        //事务仍然成功提交
        assert mapper.selectById(1) != null;
    }
    /**
     * required 内部的 REQUIRED 方法发生 数据库错误，required 方法被回滚
     */
    @Test
    public void required_REQUIRED_rollback_error() {
        try {
            service.required_REQUIRED_rollback_error();
        }catch (Exception e) {
            System.out.println(e);
        }
        // REQUIRED 的发生数据库异常
        assertThat(systemOutRule.getLog()).contains("Error querying database.  Cause: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table \"USER22\" not found; SQL statement:");
        assertThat(systemOutRule.getLog()).contains("org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only");
        //内部数据库错误，会通过事务切面传递到外部事务，设置外部事务为 rollback
        assertThat(systemOutRule.getLog()).contains("Participating transaction failed - marking existing transaction as rollback-only");
        //事务内部异常及时被捕获，也会造成异常抛出
        assertThat(systemOutRule.getLog()).contains("org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only");
        assertThat(systemOutRule.getLog()).contains("Rolling back JDBC transaction on Connection");
        //之前完成的数据库操作也会被回滚
        assert mapper.selectById(1)==null;
    }
    /**
     * required 内部的 SUPPORTS 方法发生 数据库错误，required 方法被回滚
     */
    @Test
    public void required_SUPPORTS_rollback_error() {
        try {
            service.required_SUPPORTS_rollback_error();
        }catch (Exception e) {
            System.out.println(e);
        }
        // SUPPORTS 的发生数据库异常
        assertThat(systemOutRule.getLog()).contains("Error querying database.  Cause: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table \"USER22\" not found; SQL statement:");
        assertThat(systemOutRule.getLog()).contains("org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only");
        //内部数据库错误，会通过事务切面传递到外部事务，设置外部事务为 rollback
        assertThat(systemOutRule.getLog()).contains("Participating transaction failed - marking existing transaction as rollback-only");
        //事务内部异常及时被捕获，也会造成异常抛出
        assertThat(systemOutRule.getLog()).contains("org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only");
        assertThat(systemOutRule.getLog()).contains("Rolling back JDBC transaction on Connection");
        //之前完成的数据库操作也会被回滚
        assert mapper.selectById(1)==null;
    }
    /**
     * required 内部的 MANDATORY 方法发生 数据库错误，required 方法被回滚
     */
    @Test
    public void required_MANDATORY_rollback_error() {
        try {
            service.required_MANDATORY_rollback_error();
        }catch (Exception e) {
            System.out.println(e);
        }
        // MANDATORY 的发生数据库异常
        assertThat(systemOutRule.getLog()).contains("Error querying database.  Cause: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table \"USER22\" not found; SQL statement:");
        assertThat(systemOutRule.getLog()).contains("org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only");
        //内部数据库错误，会通过事务切面传递到外部事务，设置外部事务为 rollback
        assertThat(systemOutRule.getLog()).contains("Participating transaction failed - marking existing transaction as rollback-only");
        //事务内部异常及时被捕获，也会造成异常抛出
        assertThat(systemOutRule.getLog()).contains("org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only");
        assertThat(systemOutRule.getLog()).contains("Rolling back JDBC transaction on Connection");
        //之前完成的数据库操作也会被回滚
        assert mapper.selectById(1)==null;
    }
    /**
     * required 内部的 REQUIRES_NEW 方法发生 数据库错误，required 方法不会被回滚
     */
    @Test
    public void required_REQUIRES_NEW_commit_error() {
        service.required_REQUIRES_NEW_commit_error();

        // 挂起外部事务，创建新的事务
        assertThat(systemOutRule.getLog()).contains("Suspending current transaction, creating new transaction with name [com.sun.proxy.");
        //同时挂起 session
        assertThat(systemOutRule.getLog()).contains("Transaction synchronization suspending SqlSession");
        // REQUIRES_NEW 的发生数据库异常
        assertThat(systemOutRule.getLog()).contains("Error querying database.  Cause: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table \"USER22\" not found; SQL statement:");
        //新事务被回滚
        assertThat(systemOutRule.getLog()).contains("Initiating transaction rollback");
        //唤醒外部事务和session
        assertThat(systemOutRule.getLog()).contains("Resuming suspended transaction after completion of inner transaction");
        assertThat(systemOutRule.getLog()).contains("Transaction synchronization resuming SqlSession");
        //外部事务成功提交
        assertThat(systemOutRule.getLog()).contains("Initiating transaction commit");
        assert mapper.selectById(1) != null;
    }
    /**
     * required 内部的 NESTED 方法发生 数据库错误，required 方法不会被回滚
     */
    @Test
    public void required_NESTED_commit_error() {
        service.required_NESTED_commit_error();
        //创建嵌套事务
        assertThat(systemOutRule.getLog()).contains("Creating nested transaction with name [com.sun.proxy.");
        //NESTED 发生数据库错误
        assertThat(systemOutRule.getLog()).contains("Error querying database.  Cause: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table \"USER22\" not found; SQL statement:");
        //将事务回滚到保存点位置
        assertThat(systemOutRule.getLog()).contains("Rolling back transaction to savepoint");
        //外部事务成功提交
        assertThat(systemOutRule.getLog()).contains("Initiating transaction commit");
        assert mapper.selectById(1) != null;
    }
    /**
     * required 内部的 NOT_SUPPORTED 方法发生 数据库错误，required 方法不会被回滚
     */
    @Test
    public void required_NOT_SUPPORTED_commit_error() {
        service.required_NOT_SUPPORTED_commit_error();
        //挂起当前事务
        assertThat(systemOutRule.getLog()).contains("Suspending current transaction");
        //NOTSUPPORTED 发生数据库错误
        assertThat(systemOutRule.getLog()).contains("Error querying database.  Cause: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table \"USER22\" not found; SQL statement:");
        //因为开启的是不可用事务（即空事务），所以没有 roll back 操作
        assertThat(systemOutRule.getLog()).contains("Should roll back transaction but cannot - no transaction available");
        //内部事务完成后，唤醒外部事务
        assertThat(systemOutRule.getLog()).contains("Resuming suspended transaction after completion of inner transaction");
        //外部事务成功提交
        assertThat(systemOutRule.getLog()).contains("Initiating transaction commit");
        assert mapper.selectById(1) != null;
    }

    /**
     * NULL 内部的  事务切面方法  发生  数据库错误  会导致外部事务回滚
     */
    @Test
    public void required_NULL_REQUIRED_rollback_0_error() {
        try {
            service.required_NULL_REQUIRED_rollback_0_error();
        }catch (Exception e) {
            System.out.println(e);
        }
        // NULL 内部的  事务切面方法  发生  数据库错误
        assertThat(systemOutRule.getLog()).contains("------------> NULL_ERROR <-------------");
        //NULL 内部事务通过事务切面设置 rollback 状态
        assertThat(systemOutRule.getLog()).contains("Participating transaction failed - marking existing transaction as rollback-only");
        assertThat(systemOutRule.getLog()).doesNotContain("------------------> 不会抛错出来，因为 NULL_ERROR 不会执行事务切面，就不会导致抛错 <----------------------");
        //外部事务被回退
        assertThat(systemOutRule.getLog()).contains("Initiating transaction rollback");
        assert mapper.selectById(1) == null;
    }

    /**
     * NESTED 内部的事务切面方法  发生 数据库错误 只会导致 NESTED 开启的嵌套事务回滚，而外层事务不会
     */
    @Test
    public void required_NESTED_REQUIRED_commit_0_error() {
        try {
            service.required_NESTED_REQUIRED_commit_0_error();
        } catch (Exception e) {
            System.out.println(e);
        }
        // NESTED 内部的  事务切面方法  发生  数据库错误
        assertThat(systemOutRule.getLog()).contains("------------> NESTED_ERROR <-------------");
        //NESTED 内部事务通过事务切面设置 rollback 状态
        assertThat(systemOutRule.getLog()).contains("Participating transaction failed - marking existing transaction as rollback-only");
        assertThat(systemOutRule.getLog()).contains("------------> 即使内部 catch 了，外部仍然报错，这符合正常的逻辑（虽然数据库错误被catch了，外部事务还是会抛错出来）<-------------");
        //事务被回退到保存点
        assertThat(systemOutRule.getLog()).contains("Rolling back transaction to savepoint");
        //外部事务正常提交
        assertThat(systemOutRule.getLog()).contains("Initiating transaction commit");
        assert mapper.selectById(1) != null;
    }

    /**
     * 外层事务发生 数据库错误 会导致 NESTED 方法回滚
     */
    @Test
    public void required_NESTED_error_rollback() {
        try {
            service.required_NESTED_error_rollback();
        } catch (Exception e) {
            System.out.println("------------------> 只要是设置了 rollback 状态就会抛错 <-------------------");
        }
        //创建嵌套事务
        assertThat(systemOutRule.getLog()).contains("Creating nested transaction with name [com.wangkang.service.ReasoningTestService.NESTED]");
        //回到保存点
        assertThat(systemOutRule.getLog()).contains("Releasing transaction savepoint");
        //外部事务发生数据库错误
        assertThat(systemOutRule.getLog()).contains("------------> REQUIRED_ERROR <-------------");
        //设置 rollback 状态
        assertThat(systemOutRule.getLog()).contains("Participating transaction failed - marking existing transaction as rollback-only");
        //事务回退
        assertThat(systemOutRule.getLog()).contains("Initiating transaction rollback");
        assertThat(systemOutRule.getLog()).contains("------------------> 只要是设置了 rollback 状态就会抛错 <-------------------");
        //嵌套事务也回退，因为本来就是同一个事务
        assert mapper2.selectById(1) == null;
    }

    /**
     * 外层事务发生 数据库错误 不会导致 REQUIRES_NEW 方法回滚
     */
    @Test
    public void required_REQUIRES_NEW_error_commit() {
        try {
            service.required_REQUIRES_NEW_error_commit();
        } catch (Exception e) {
            System.out.println("------------------> 只要是设置了 rollback 状态就会抛错 <-------------------");
        }
        //Suspending current transaction, creating new transaction with name [com.wangkang.service.ReasoningTestService.REQUIRES_NEW]
        assertThat(systemOutRule.getLog()).contains("------------------> 只要是设置了 rollback 状态就会抛错 <-------------------");
        //REQUIRES_NEW 方法完成后就提交事务了，因为是新事务
        //外部事务发生数据库错误
        assertThat(systemOutRule.getLog()).contains("------------> REQUIRED_ERROR <-------------");
        //外部事务设置 rollback 状态
        assertThat(systemOutRule.getLog()).contains("Global transaction is marked as rollback-only but transactional code requested commit");
        //外部事务回退
        assertThat(systemOutRule.getLog()).contains("Initiating transaction rollback");
        assertThat(systemOutRule.getLog()).contains("------------------> 只要是设置了 rollback 状态就会抛错 <-------------------");
        assert mapper2.selectById(1) != null;
    }

}
