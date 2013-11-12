package net.simpleframework.demo;

import net.simpleframework.app.AbstractApplicationContext;
import net.simpleframework.app.ApplicationSettings;
import net.simpleframework.ctx.IApplicationContext;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.ITemplateHandler;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.t2.T2TemplatePage;

import org.hsqldb.Server;

public class DemoApplication extends AbstractApplicationContext implements IApplicationContext {

	/**
	 * 覆盖应用级的初始化方法
	 */
	@Override
	protected void onApplicationInit() throws Exception {
		// 启动测试用的hsql数据库,生产环境不需要
		doHsql();

		super.onApplicationInit();
	}

	@Override
	public ITemplateHandler getTemplate(final PageParameter pp) {
		/**
		 * 这个方法的作用是提供模板的Handler,前提是用simple缺省提供的2套模板,如果自己写模板,就没有意义了
		 */
		if (AbstractMVCPage.get(pp) instanceof T2TemplatePage) {
			return singleton(MVCTemplateT2.class);
		}
		return singleton(MVCTemplateT1.class);
	}

	@Override
	public String getThrowableMessage(final Throwable th) {
		/**
		 * 获取异常的提示消息
		 */
		// if
		// (ThrowableUtils.getCause(SQLIntegrityConstraintViolationException.class,
		// th) != null) {
		// return "你插入了两条主键一样的数据，数据库里的主键是不能重复的!";
		// }
		return super.getThrowableMessage(th);
	}

	private void doHsql() {
		final ApplicationSettings settings = getContextSettings();
		if (!settings.getBoolProperty("hsql.start")) {
			return;
		}
		try {
			System.setProperty("hsqldb.reconfig_logging", "false");
			final Server svr = new Server();
			svr.setAddress(settings.getProperty("hsql.address"));
			svr.setPort(settings.getIntProperty("hsql.port"));
			svr.setDatabaseName(0, settings.getProperty("hsql.dbname"));
			svr.setDatabasePath(0, settings.getProperty("hsql.dbpath"));
			svr.setSilent(true);
			svr.start();
		} catch (final Exception e1) {
			log.warn(e1);
		}
	}
}
