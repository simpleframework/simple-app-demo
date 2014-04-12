package net.simpleframework.demo;

import net.simpleframework.app.AbstractApplicationContext;
import net.simpleframework.ctx.IApplicationContext;

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
}
