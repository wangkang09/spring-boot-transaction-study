package com.wangkang;

import com.wangkang.mapper.PropagationTestMapper;
import com.wangkang.service.PropagationTestService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 事务传播行为证明
 *
 * @author wangkang
 * @date 2020-03-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTransactionStudyApplication.class)
@SuppressWarnings("all")
public class PropagationTest {

    @Autowired
    PropagationTestService service;

    @Autowired
    PropagationTestMapper mapper;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    /**
     * 不存在，创建新事务
     */
    @Test
    public void required() {
        service.required();
        //mapper 代理层创建的事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.sun.proxy.");
    }

    /**
     * 存在则加入事务
     */
    @Test
    public void REQUIRED() {
        service.REQUIRED();
        //外层创建了事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.wangkang.service.PropagationTestService.REQUIRED]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT");
        //内层方法加入事务
        assertThat(systemOutRule.getLog()).contains("Participating in existing transaction");
    }

    /**
     * 不存在，以非事务执行
     */
    @Test
    public void supports() {
        service.supports();
        //没有创建可用的事务
        assertThat(systemOutRule.getLog()).doesNotContain("Creating new transaction");
        //仍然将 session 添加到线程中了
        assertThat(systemOutRule.getLog()).contains("Registering transaction synchronization for SqlSession");
        //因为事务不可用，所以不会进行手动 committing
        assertThat(systemOutRule.getLog()).doesNotContain("Committing JDBC transaction on Connection");
    }

    /**
     * 不可用事务，仍然会被挂起
     */
    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supports_REQUIRED() {
        mapper.null0();
        service.REQUIRED();
        //内层的 REQUIRED 的方法，判断外层没有事务(supports 没有创建 connection)，但是 线程已经是同步状态了(supports 设置的)，所以挂起了 supports 开启的不可用事务
        assertThat(systemOutRule.getLog()).contains("Transaction synchronization suspending SqlSession");
    }

    /**
     * 存在则加入事务
     */
    @Test
    public void SUPPORTS() {
        service.SUPPORTS();
        //外层创建了事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.wangkang.service.PropagationTestService.SUPPORTS]");
        //内层方法加入事务
        assertThat(systemOutRule.getLog()).contains("Participating in existing transaction");
    }

    /**
     * 不存在事务，抛出异常
     */
    @Test(expected = IllegalTransactionStateException.class)
    public void mandatory() {
        service.mandatory();
    }

    /**
     * 存在则加入事务
     */
    @Test
    public void MANDATORY() {
        service.MANDATORY();
        //外层创建了事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.wangkang.service.PropagationTestService.MANDATORY]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT");
        //内层方法加入事务
        assertThat(systemOutRule.getLog()).contains("Participating in existing transaction");
    }


    /**
     * 不存在则创建事务
     */
    @Test
    public void requires_new() {
        service.requires_new();
        //mapper 代理层创建的事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.sun.proxy.");
        //事务可用
        assertThat(systemOutRule.getLog()).contains("Committing JDBC transaction on Connection");
    }

    /**
     * 存在则挂起当前事务，并创建了新事务
     */
    @Test
    public void REQUIRES_NEW() {
        service.REQUIRES_NEW();
        //外层创建了事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.wangkang.service.PropagationTestService.REQUIRES_NEW]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT");
        //内层挂起了外层事务，并创建了新事务
        assertThat(systemOutRule.getLog()).contains("Suspending current transaction, creating new transaction with name [com.sun.proxy.");
    }

    /**
     * 不存在，创建新事务
     */
    @Test
    public void nested() {
        service.nested();
        //mapper 代理层创建的事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.sun.proxy.");
    }

    /**
     * 存在则创建嵌套事务
     */
    @Test
    public void NESTED() {
        service.NESTED();
        //外层创建了事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.wangkang.service.PropagationTestService.NESTED]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT");
        //内层创建了嵌套事务
        assertThat(systemOutRule.getLog()).contains("Creating nested transaction with name [com.sun.proxy.");
        //内层释放 savepoint，说明之前创建了 savepoint
        assertThat(systemOutRule.getLog()).contains("Releasing transaction savepoint");
    }

    /**
     * 不存在则以不可用事务运行
     */
    @Test
    public void not_supported() {
        service.not_supported();
        //没有创建可用的事务
        assertThat(systemOutRule.getLog()).doesNotContain("Creating new transaction");
        //仍然将 session 添加到线程中了
        assertThat(systemOutRule.getLog()).contains("Registering transaction synchronization for SqlSession");
        //因为事务不可用，所以不会进行手动 committing
        assertThat(systemOutRule.getLog()).doesNotContain("Committing JDBC transaction on Connection");
    }

    /**
     * 存在事务，则先挂起当前事务，在创建不可用事务
     */
    @Test
    public void NOT_SUPPORTED() {
        service.NOT_SUPPORTED();
        //外层创建了事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction");
        //NOT_SUPPORTED 挂起了外层的事务
        assertThat(systemOutRule.getLog()).contains("Suspending current transaction");
        //NOT_SUPPORTED 完成之后，又唤醒了外层的事务
        assertThat(systemOutRule.getLog()).contains("Resuming suspended transaction after completion of inner transaction");
    }

    /**
     * 以不可用事务运行
     */
    @Test
    public void never() {
        service.never();
        //没有创建可用的事务
        assertThat(systemOutRule.getLog()).doesNotContain("Creating new transaction");
        //仍然将 session 添加到线程中了
        assertThat(systemOutRule.getLog()).contains("Registering transaction synchronization for SqlSession");
        //因为事务不可用，所以不会进行手动 committing
        assertThat(systemOutRule.getLog()).doesNotContain("Committing JDBC transaction on Connection");
    }

    /**
     * 存在事务，抛出异常
     */
    @Test(expected = IllegalTransactionStateException.class)
    public void NEVER() {
        service.NEVER();
    }
}
