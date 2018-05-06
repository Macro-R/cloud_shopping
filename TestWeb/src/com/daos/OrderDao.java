package com.daos;

import java.io.Serializable;

import com.beans.Order;
/**
 * 订单接口继承主接口
 *
 * @author Macro
 *
 */
public interface OrderDao extends BaseDao<Order> {
	Order findById(Serializable t);
}
