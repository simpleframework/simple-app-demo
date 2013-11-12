package net.simpleframework.demo;

import javax.servlet.ServletException;

import net.simpleframework.mvc.IMVCContext;
import net.simpleframework.mvc.MVCFilter;

/**
 * 此类覆盖MVCFilter,并通过init方法实例化Application
 * 
 * 由于基于SimpleMVC, 故DemoApplication选择继承MVCContext, 这样避免再一次初始化MVCContext对象
 */
public class DemoFilter extends MVCFilter {

	@Override
	protected IMVCContext createMVCContext() throws ServletException {
		return new DemoApplication();
	}
}
