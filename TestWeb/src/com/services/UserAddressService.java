package com.services;

import java.io.Serializable;
import java.util.List;
import com.beans.UserAddress;

public interface UserAddressService {
	public int add(UserAddress t);
	public int delete(Serializable t);
	public int updata(UserAddress t);
	public List<UserAddress> select();
}
