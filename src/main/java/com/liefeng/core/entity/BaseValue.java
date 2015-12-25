package com.liefeng.core.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 所有值对象的父类
 * @author Huangama
 * @date 2015-09-16
 */
public class BaseValue implements Serializable {

	private static final long serialVersionUID = 3975157963411781560L;

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
