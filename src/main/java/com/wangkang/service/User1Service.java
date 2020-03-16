package com.wangkang.service;


import com.wangkang.entity.User1;

import java.util.List;

public interface User1Service {

	void addRequired(User1 user);

	void addRequiredException(User1 user);
	
	void truncate();

	void addSupports(User1 user);

	void addSupportsException(User1 user);

	void addRequiresNew(User1 user);

	void addRequiresNewException(User1 user);

	void addNotSupported(User1 user);

	void addNotSupportedException(User1 user);

	void add(User1 user);

	void addException(User1 user);

	void addMandatory(User1 user);

	void addMandatoryException(User1 user);

	void addNever(User1 user);

	void addNeverException(User1 user);

	void addNested(User1 user);

	void addNestedException(User1 user);

	List<User1> get(String name);
	List<User1> getRequired(String name);
	List<User1> getRequiresNew(String name);

	List<User1> getNested(String name);

	List<User1> getNotSupported(String name);

}