package net.simpleframework.demo;

import net.simpleframework.ado.db.DbManagerFactory;
import net.simpleframework.app.AbstractApplicationContext;
import net.simpleframework.ctx.IApplicationContext;
import net.simpleframework.module.common.ICommonModuleContext;

public class DemoApplication extends AbstractApplicationContext implements IApplicationContext {

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

	@Override
	protected void onAfterInit() throws Exception {
		super.onAfterInit();

		final DbManagerFactory dbFactory = (DbManagerFactory) getADOManagerFactory();
		dbFactory.regist(ICommonModuleContext.SF_ATTACHMENT, ICommonModuleContext.SF_ATTACHMENT_LOB);
	}
}
