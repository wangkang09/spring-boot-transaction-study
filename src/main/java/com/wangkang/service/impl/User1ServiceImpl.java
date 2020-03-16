package com.wangkang.service.impl;

import com.wangkang.entity.User1;
import com.wangkang.mapper.User1Mapper;
import com.wangkang.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@SuppressWarnings("all")
public class User1ServiceImpl implements User1Service {
    @Autowired
    private User1Mapper user1Mapper;

    @Override
    public void truncate() {
        user1Mapper.truncated();
    }

    @Override
    public void add(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    public List<User1> get(String name) {
        return user1Mapper.selectByName(name);
    }

    @Override
    public void addException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    /* (non-Javadoc)
     * @see org.transaction.test.local_transaction.mybatis.service.impl.User1Service#add(org.transaction.test.local_transaction.mybatis.bean.User1)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User1 user) {
        user1Mapper.insert(user);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<User1> getRequired(String name) {
        return user1Mapper.selectByName(name);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void addSupports(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void addSupportsException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<User1> getRequiresNew(String name) {
        return user1Mapper.selectByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNewException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupported(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupportedException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<User1> getNotSupported(String name) {
        return user1Mapper.selectByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addMandatory(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addMandatoryException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void addNever(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void addNeverException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNested(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNestedException(User1 user) {
        user1Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public List<User1> getNested(String name) {
        return user1Mapper.selectByName(name);
    }
}
