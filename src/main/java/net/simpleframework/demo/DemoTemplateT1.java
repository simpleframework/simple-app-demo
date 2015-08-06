package net.simpleframework.demo;

import static net.simpleframework.common.I18n.$m;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.app.template.AbstractHeaderPage;
import net.simpleframework.app.template.SFTemplateT1;
import net.simpleframework.common.web.html.HtmlConst;
import net.simpleframework.ctx.ModuleContextFactory;
import net.simpleframework.module.favorite.web.IFavoriteWebContext;
import net.simpleframework.module.msg.web.IMessageWebContext;
import net.simpleframework.module.myportal.web.IMyPortalWebContext;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.AbstractElement;
import net.simpleframework.mvc.common.element.BlockElement;
import net.simpleframework.mvc.common.element.JS;
import net.simpleframework.mvc.common.element.LinkElement;
import net.simpleframework.mvc.common.element.SpanElement;
import net.simpleframework.mvc.component.ui.menu.EMenuEvent;
import net.simpleframework.mvc.component.ui.menu.MenuBean;
import net.simpleframework.mvc.component.ui.menu.MenuItem;
import net.simpleframework.organization.web.page.attri.t2.AbstractAttriPage.UserAttriPageT2;

public class DemoTemplateT1 extends SFTemplateT1 {

	@Override
	public Class<? extends AbstractMVCPage> getHeaderPage() {
		return _HeaderPageT1.class;
	}

	static void addMenuComponent(final PageParameter pp) {
		final MenuBean menu = (MenuBean) pp
				.addComponentBean("AbstractHeaderPage_menu", MenuBean.class)
				.setMenuEvent(EMenuEvent.mouseenter).setSelector(".re .acts .link_menuicon");
		final AbstractElement<?>[] eles = new AbstractElement[] {
				ModuleContextFactory.get(IMyPortalWebContext.class).toMyPortalElement(pp),
				ModuleContextFactory.get(IFavoriteWebContext.class).toMyFavoriteElement(pp) };
		for (final AbstractElement<?> ele : eles) {
			if (ele instanceof LinkElement) {
				final LinkElement link = (LinkElement) ele;
				if ("-".equals(link.getText())) {
					menu.addItem(MenuItem.sep());
				} else {
					menu.addItem(MenuItem.of(link.getText()).setUrl(link.getHref()));
				}
			}
		}
		menu.addItem(MenuItem.sep());
		menu.addItem(MenuItem.of($m("DemoTemplateT1.0")).setUrl(
				AbstractMVCPage.url(UserAttriPageT2.class)));
	}

	static String toActionsHTML(final PageParameter pp, final AbstractHeaderPage page) {
		final StringBuilder sb = new StringBuilder();
		if (pp.getLoginId() == null) {
			sb.append(page.str_Login(pp));
		} else {
			sb.append("<div class='acts'>");
			sb.append(ModuleContextFactory.get(IMessageWebContext.class).toMyMessageElement(pp)
					.setId("AbstractHeaderPage_sup"));
			sb.append(SpanElement.SEP).append(page.str_User(pp)).append(SpanElement.SEP)
					.append(page.str_Logout(pp));
			sb.append("</div>");
			sb.append("<div class='user'>").append(page.str_Photo(pp)).append("</div>");
			sb.append(BlockElement.CLEAR);
			sb.append(HtmlConst.TAG_SCRIPT_START);
			sb.append(AbstractHeaderPage.js_shake("#AbstractHeaderPage_sup .highlight"));
			sb.append(HtmlConst.TAG_SCRIPT_END);
		}
		return sb.toString();
	}

	public static class _HeaderPageT1 extends HeaderPageT1 {

		@Override
		protected void onForward(final PageParameter pp) throws Exception {
			super.onForward(pp);

			pp.addImportCSS(DemoApplication.class, "/t1.css");

			addComponent_logout(pp);
			addMenuComponent(pp);
		}

		@Override
		protected String toHtml(final PageParameter pp, final Map<String, Object> variables,
				final String currentVariable) throws IOException {
			final StringBuilder sb = new StringBuilder();
			sb.append(" <div class='logo' onclick=\"").append(JS.loc("/")).append("\"></div>");
			sb.append(" <div class='re'>").append(toActionsHTML(pp, this)).append("</div>");
			sb.append(BlockElement.CLEAR);
			return sb.toString();
		}
	}
}
