package com.serivce.impls;

import java.io.Serializable;
import java.util.List;

import com.beans.UserAddress;
import com.daos.UserAddressDao;
import com.dao.impls.UserAddressDaoImpl;
import com.services.UserAddressService;

public class UserAddressServiceImpl implements UserAddressService{
	UserAddressDao ua=new UserAddressDaoImpl();
	@Override
	public int add(UserAddress t) {
		return ua.add(t);
	}

	@Override
	public int delete(Serializable t) {
		return ua.delete(t);
	}

	@Override
	public int updata(UserAddress t) {
		return ua.updata(t);
	}

	@Override
	public List<UserAddress> select() {
		return ua.select();
	}

}
