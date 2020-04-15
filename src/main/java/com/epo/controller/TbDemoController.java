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
	
//	public class TbDemoApi extends BaseController<TbDemo>
//	{
//		private final Logger log = LoggerFactory.getLogger(getClass());
//		
//		@Autowired
//		private TbDemoService service;
//		
//		@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//		public RestResult add(@RequestBody JSONObject jsonParams)
//		{
//			log.debug("==> 请求参数：{}", jsonParams.toJSONString());
//			RestResult r = new RestResult();
//			try
//			{
//				TbDemo po = JSONObject.parseObject(jsonParams.toJSONString(), TbDemo.class);
//				service.add(po);
//			}
//			catch(Exception e)
//			{
//				log.error("<== 异常提示：{}", e);
//				r.put("success", false);
//				r.put("msg", e.getMessage());
//			}
//			return r;
//		}
//		
//		@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//		public RestResult query(@RequestBody JSONObject jsonParams)
//		{
//			log.debug("==> 请求参数：{}", jsonParams.toJSONString());
//			RestResult r = new RestResult();
//			try
//			{
//				Map<String, Object> mapParams = JSONObject.parseObject(jsonParams.toJSONString(), Map.class);
//				r.put("total", service.getCount(mapParams));
//				r.put("rows", service.query(mapParams));
//			}
//			catch (Exception e)
//			{
//				log.error("<== 异常提示：{}", e);
//				r.setSuccess(false);
//				r.setMsg(e.getMessage());
//			}
//			return r;
//		}
	
//	@RequestMapping("/add")
//	public void add(TbDemo po) 
//	{
//		try
//		{
//			service.add(po);
//			printJson(result);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			result.setSuccess(false);
//			result.setMsg(e.getMessage());
//			printJson(result);
//		}
//	}
//	
//	@RequestMapping("/del")
//	public void del() 
//	{
//		try
//		{
//			service.del(CollectionUtil.strToLongArray(req.getString("tcRowid"), ","));
//			result.setSuccess(true);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			result.setSuccess(false);
//			result.setMsg(e.getMessage());
//		}
//		printJson(result);
//	}
//	
//	@RequestMapping("/upd")
//	public void upd(TbDemo po) 
//	{
//		try
//		{
//			service.upd(po);
//			printJson(result);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			result.setSuccess(false);
//			result.setMsg(e.getMessage());
//			printJson(result);
//		}
//	}
//
//	@RequestMapping("/get")
//	public void get() 
//	{
//		try
//		{
//			result.put("po", service.get(req.getLong("tcRowid")));
//			printJson(result);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			result.setSuccess(false);
//			result.setMsg(e.getMessage());
//			printJson(result);
//		}
//	}
//	
//	@RequestMapping("/query")
//	public void query()
//	{
//		try
//		{
//			Map<String, Object> m = req.getParameterValueMap(false, true);
//			result.put("total",service.getCount(m));
//			result.put("rows",service.query(req.getDynamicSortParameterValueMap(m)));
//			printJson(result);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			result.setSuccess(false);
//			result.setMsg(e.getMessage());
//			printJson(result);
//		}
//	}
}
