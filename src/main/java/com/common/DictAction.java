package com.common;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chok.devwork.BaseController;

@Scope("prototype")
@Controller
@RequestMapping("/dict")
public class DictAction extends BaseController<Dict>
{
	private static Logger log = LoggerFactory.getLogger(DictAction.class);
	
	@Autowired
	private DictService service;
	
	@RequestMapping("/queryBiImpType")
	public void queryBiImpType()
	{
		try
		{
			String account = req.getString("account");
			result.setData(new HashMap<Object, Object>(){
				private static final long serialVersionUID = 1L;
				{
					put("options", service.pQueryBiImpType(account));
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			log.error(e.getMessage());
		}
		printJson(result);
	}

}
