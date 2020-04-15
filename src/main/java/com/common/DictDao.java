package com.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import chok.devwork.springboot.BaseDao;

@Repository
public class DictDao extends BaseDao<Dict,Long>
{
	private final Map<String, Object> daoResult = new HashMap<String, Object>();
	
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	public Class<Dict> getEntityClass()
	{
		return Dict.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> pQueryBiImpType(String account) throws Exception
	{
		daoResult.clear();
		// proc 参数Map
		Map<Object, Object> procParam = new LinkedHashMap<Object, Object>();
		// proc 入参
		procParam.put("v_in_account", account);
		// proc 出参
		procParam.put("v_out_success", new String());
		procParam.put("v_out_msg", new String());
		procParam.put("v_out_cursor", new ArrayList<Map<String, String>>());
		this.query("p_query_bi_imp_type", procParam);
		// 判断PROC异常
		if (!"1".equals(String.valueOf(procParam.get("v_out_success"))))
		{
			throw new Exception(String.valueOf(procParam.get("v_out_msg")));
		}
		//
		return (List<Map<String, String>>) procParam.get("v_out_cursor");
	}
}
