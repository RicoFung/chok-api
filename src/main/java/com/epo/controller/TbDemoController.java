package com.epo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.epo.entity.TbDemo;
import com.epo.service.TbDemoService;

import chok.common.RestResult;
import chok.devwork.springboot.BaseRestController;

@RestController
@RequestMapping("/tbdemo")
public class TbDemoController extends BaseRestController<TbDemo>
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TbDemoService service;

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult add(@RequestBody JSONObject jsonParams)
	{
		if (log.isDebugEnabled())
		{
			log.debug("==> 请求参数：{}", jsonParams.toJSONString());
		}
		try
		{
			TbDemo po = JSONObject.parseObject(jsonParams.toJSONString(), TbDemo.class);
			service.add(po);
		}
		catch(Exception e)
		{
			log.error("<== 异常提示：{}", e);
			restResult.put("success", false);
			restResult.put("msg", e.getMessage());
		}
		return restResult;
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult del(@RequestBody Long[] tcRowids) 
	{
		if (log.isDebugEnabled())
		{
			log.debug("==> 请求参数：{}", tcRowids);
		}
		try
		{
			service.del(tcRowids);
			restResult.setSuccess(true);
		}
		catch(Exception e)
		{
			log.error("<== 异常提示：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}

	@RequestMapping(value = "/upd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult upd(@RequestBody JSONObject jsonParams) 
	{
		if (log.isDebugEnabled())
		{
			log.debug("==> 请求参数：{}", jsonParams.toJSONString());
		}
		try
		{
			TbDemo po = JSONObject.parseObject(jsonParams.toJSONString(), TbDemo.class);
			service.upd(po);
		}
		catch(Exception e)
		{
			log.error("<== 异常提示：{}", e);
			restResult.put("success", false);
			restResult.put("msg", e.getMessage());
		}
		return restResult;
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult get(@RequestBody Long tcRowid) 
	{
		if (log.isDebugEnabled())
		{
			log.debug("==> 请求参数：{}", tcRowid);
		}
		try
		{
			restResult.put("po", service.get(tcRowid));
		}
		catch(Exception e)
		{
			log.error("<== 异常提示：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult query(@RequestBody JSONObject jsonParams)
	{
		if (log.isDebugEnabled())
		{
			log.debug("==> 请求参数：{}", jsonParams.toJSONString());
		}
		try
		{
	        Map<String, Object> mapParams = JSONObject.parseObject(jsonParams.toJSONString(), Map.class);
	        restResult.put("total", service.getCount(mapParams));
	        restResult.put("rows", service.query(mapParams));
		}
		catch (Exception e)
		{
			log.error("<== 异常提示：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
}
