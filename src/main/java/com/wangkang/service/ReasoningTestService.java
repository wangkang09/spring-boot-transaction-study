package com.wangkang.service;

import com.wangkang.mapper.ReasoningTestMapper;
import com.wangkang.mapper.ReasoningTestMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 19:21 2020/3/17
 * @Modified By:
 */
@Service
@SuppressWarnings("all")
public class ReasoningTestService {

    @Autowired
    ReasoningTestMapper mapper;

    @Autowired
    ReasoningTestMapper2 mapper2;

    @Autowired
    ReasoningTestService service;

    @Transactional
    public void required_NULL_error_rollback() {
        mapper.NULL0();
        try {
            mapper2.REQUIRED_ERROR();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Transactional
    public void required_NULL_commit_error() {
        try {
            mapper2.NULL0_ERROR();
        } catch (Exception e) {
            System.out.println(e);
        }
        mapper.REQUIRED();
    }

    @Transactional
    public void required_REQUIRED_rollback_error() {
        mapper.REQUIRED();
        try {
            mapper2.REQUIRED_ERROR();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Transactional
    public void required_SUPPORTS_rollback_error() {
        mapper.REQUIRED();
        try {
            mapper2.SUPPORTS_ERROR();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Transactional
    public void required_MANDATORY_rollback_error() {
        mapper.REQUIRED();
        try {
            mapper2.MANDATORY_ERROR();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Transactional
    public void required_REQUIRES_NEW_commit_error() {
        mapper.REQUIRED();
        try {
            mapper2.REQUIRES_NEW_ERROR();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Transactional
    public void required_NESTED_commit_error() {
        mapper.REQUIRED();
        try {
            mapper2.NESTED_ERROR();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Transactional
    public void required_NOT_SUPPORTED_commit_error() {
        mapper.REQUIRED();
        try {
            mapper2.NOT_SUPPORTED_ERROR();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Transactional
    public void required_NULL_REQUIRED_rollback_0_error() {
        mapper.REQUIRED();
        try {
            NULL_ERROR();
        } catch (Exception e) {
            System.out.println("------------------> 不会抛错出来，因为 NULL_ERROR 不会执行事务切面，就不会导致抛错 <----------------------");
        }
    }

    public void NULL_ERROR() {
        try {
            mapper2.REQUIRED_ERROR();
        } catch (Exception e) {
            System.out.println("------------> NULL_ERROR <-------------");
        }
    }

    @Transactional
    public void required_NESTED_REQUIRED_commit_0_error() {
        mapper.REQUIRED();
        try {
            service.NESTED_ERROR();
        } catch (Exception e) {
            System.out.println("------------> 即使内部 catch 了，外部仍然报错，这符合正常的逻辑（虽然数据库错误被catch了，外部事务还是会抛错出来）<-------------");
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void NESTED_ERROR() {
        try {
            mapper2.REQUIRED_ERROR();
        } catch (Exception e) {
            System.out.println("------------> NESTED_ERROR <-------------");
        }
    }

    @Transactional
    public void required_NESTED_error_rollback() {
        service.NESTED();
        try {
            mapper.REQUIRED_ERROR();
        } catch (Exception e) {
            System.out.println("------------> REQUIRED_ERROR <-------------");
        }
    }
    @Transactional(propagation = Propagation.NESTED)
    public void NESTED() {
        mapper2.NULL0();
    }

    @Transactional
    public void required_REQUIRES_NEW_error_commit() {
        service.REQUIRES_NEW();
        try {
            mapper.REQUIRED_ERROR();
        } catch (Exception e) {
            System.out.println("------------> REQUIRED_ERROR <-------------");
        }
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void REQUIRES_NEW() {
        mapper2.NULL0();
    }


}
