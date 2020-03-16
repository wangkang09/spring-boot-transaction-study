package com.wangkang;

import com.wangkang.service.PropagationTestService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.IllegalTransactionStateException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootTransactionStudyApplication.class)
@SuppressWarnings("all")
public class PropagationTest {

    @Autowired
    PropagationTestService service;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void required() {
        service.required();
        //mapper 代理层创建的事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.sun.proxy.");
    }

    @Test
    public void REQUIRED() {
        service.REQUIRED();
        //外层创建了事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.wangkang.service.PropagationTestService.REQUIRED]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT");
        //内层方法加入事务
        assertThat(systemOutRule.getLog()).contains("Participating in existing transaction");
    }

    //等待研究
    @Test
    public void supports() {
        service.supports();

    }

    @Test
    public void SUPPORTS() {
        service.SUPPORTS();
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

    @Test
    public void requires_new() {
        service.requires_new();
        //mapper 代理层创建的事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.sun.proxy.");
    }

    @Test
    public void REQUIRES_NEW() {
        service.REQUIRES_NEW();
        //外层创建了事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.wangkang.service.PropagationTestService.REQUIRES_NEW]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT");
        //内层挂起了外层事务，并创建了新事务
        assertThat(systemOutRule.getLog()).contains("Suspending current transaction, creating new transaction with name [com.sun.proxy.");
    }

    @Test
    public void nested() {
        service.nested();
        //mapper 代理层创建的事务
        assertThat(systemOutRule.getLog()).contains("Creating new transaction with name [com.sun.proxy.");
    }

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

    //等待研究
    @Test
    public void not_supported() {
        service.not_supported();
    }

    @Test
    public void NOT_SUPPORTED() {
        service.NOT_SUPPORTED();
    }

    @Test
    public void never() {
        service.never();

    }

    @Test(expected = IllegalTransactionStateException.class)
    public void NEVER() {
        service.NEVER();
    }
}
