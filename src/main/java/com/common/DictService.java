package com.common;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;

@Service
public class DictService extends BaseService<Dict,Long>
{
	@Autowired
	private DictDao dao;

	@Override
	public BaseDao<Dict,Long> getEntityDao() 
	{
		return dao;
	}
	
	public List<Map<String, String>> pQueryBiImpType(String account) throws Exception
	{
		return dao.pQueryBiImpType(account);
	}
}