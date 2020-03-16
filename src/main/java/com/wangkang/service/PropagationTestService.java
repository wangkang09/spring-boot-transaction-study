package com.wangkang.service;

import com.wangkang.mapper.PropagationTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 22:49 2020/3/16
 * @Modified By:
 */
@Service
public class PropagationTestService {
    @Autowired
    PropagationTestMapper mapper;

    public void required() {
        mapper.required();
    }
    public void supports() {
        mapper.supports();
    }
    public void mandatory() {
        mapper.mandatory();
    }
    public void requires_new() {
        mapper.requires_new();
    }
    public void nested() {
        mapper.nested();
    }
    public void not_supported() {
        mapper.not_supported();
    }
    public void never() {
        mapper.never();
    }

    @Transactional
    public void REQUIRED() {
        mapper.REQUIRED();
    }
    @Transactional
    public void SUPPORTS() {
        mapper.SUPPORTS();
    }
    @Transactional
    public void MANDATORY() {
        mapper.MANDATORY();
    }
    @Transactional
    public void REQUIRES_NEW() {
        mapper.REQUIRES_NEW();
    }
    @Transactional
    public void NESTED() {
        mapper.NESTED();
    }
    @Transactional
    public void NOT_SUPPORTED() {
        mapper.NOT_SUPPORTED();
    }
    @Transactional
    public void NEVER() {
        mapper.NEVER();
    }
}
