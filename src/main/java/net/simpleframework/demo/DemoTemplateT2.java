package net.simpleframework.demo;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.app.template.SFTemplateT2;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.BlockElement;
import net.simpleframework.mvc.common.element.JS;

public class DemoTemplateT2 extends SFTemplateT2 {

	@Override
	public Class<? extends AbstractMVCPage> getHeaderPage(final AbstractMVCPage templatePage) {
		return _HeaderPageT2.class;
	}

	public static class _HeaderPageT2 extends HeaderPageT2 {

		@Override
		protected void onForward(final PageParameter pp) throws Exception {
			super.onForward(pp);

			pp.addImportCSS(DemoApplication.class, "/t2.css");

			addComponent_logout(pp);

			DemoTemplateT1.addMenuComponent(pp);
		}

		@Override
		protected String toHtml(final PageParameter pp, final Map<String, Object> variables,
				final String currentVariable) throws IOException {
			final StringBuilder sb = new StringBuilder();
			sb.append("<div class='HeaderPageT2'>");
			sb.append(" <div class='logo' onclick=\"").append(JS.loc("/")).append("\"></div>");
			sb.append(" <div class='re'>").append(DemoTemplateT1.toActionsHTML(pp, this))
					.append("</div>");
			sb.append(BlockElement.CLEAR);
			sb.append("</div>");
			return sb.toString();
		}
	}
}
