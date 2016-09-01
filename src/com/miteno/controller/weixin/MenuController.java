package com.miteno.controller.weixin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miteno.entity.weixin.AccessToken;
import com.miteno.entity.weixin.Button;
import com.miteno.entity.weixin.CommonButton;
import com.miteno.entity.weixin.CommonUrlButton;
import com.miteno.entity.weixin.ComplexButton;
import com.miteno.entity.weixin.Menu;
import com.miteno.util.WeiXinUtil;

@Controller
@RequestMapping("/background/weixinMenu")
public class MenuController {

	// 页面调用调用微信接口创建菜单
	@RequestMapping("createMenu")
	@Test
	public void createMenu(Model model, HttpServletResponse response)
			throws IOException {

		// 第三方用户唯一凭证
		String appId = "wx184bb69065e9bdef";
		// 第三方用户唯一凭证密钥
		String appSecret = "54f8a9a383fe2541889a39a1311bff96";

		// 调用接口获取access_token
		AccessToken at = WeiXinUtil.getAccessToken(appId, appSecret);
		System.out.println(at.getToken());
		if (null != at) {
			// 调用接口创建菜单
			int result = WeiXinUtil.createMenu(getMenu(), at.getToken());
			System.out.println(result);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			// 判断菜单创建结果
			if (0 == result)
				out.print("{\"msg\": \"操作成功！\"}");
			else
				out.print("{\"msg\": \"操作失败！\"}");
		}

	}

	public static void main(String[] args) {

		// 第三方用户唯一凭证
		//String appId = "wx184bb69065e9bdef";//测试环境
		String appId = "wxce16ebd3104459cd";//正式环境
		// 第三方用户唯一凭证密钥
		//String appSecret = "54f8a9a383fe2541889a39a1311bff96";//测试环境
		String appSecret = "62b9eddb8a5b933aed08f7571d1a93e6";//正式环境
		// 调用接口获取access_token
		AccessToken at = WeiXinUtil.getAccessToken(appId, appSecret);
		System.out.println(at.getToken());
		if (null != at) {
			// 调用接口创建菜单
			int result = WeiXinUtil.createMenu(getMenu(), at.getToken());
			System.out.println(result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonUrlButton btn11 = new CommonUrlButton();
		btn11.setName("话费充值");
		btn11.setType("view");

		//btn11.setUrl("http://58.240.96.236/WeiXinPayment/background/microui/index.action");
		btn11.setUrl("http://www.sopaylife.com/WeiXinPayment/background2/query.action");

		
		/*CommonUrlButton btn12 = new CommonUrlButton();
		btn12.setName("优惠券");
		btn12.setType("view");
		btn12.setUrl("http://weixintest009.ngrok.io/WeiXinPayment/background2/payList.action");*/
		CommonButton btn12 = new CommonButton();
		btn12.setName("优惠券");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("电影票");
		btn13.setType("click");
		btn13.setKey("13");
/*		CommonUrlButton btn13 = new CommonUrlButton();
		btn13.setName("电影票");
		btn13.setType("view");
		btn13.setUrl("http://weixintest009.ngrok.io/WeiXinPayment/background2/payList.action");
		*/
		CommonButton btn21 = new CommonButton();
		btn21.setName("苏付介绍");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn31 = new CommonButton();
		btn31.setName("问题反馈");
		btn31.setType("click");
		btn31.setKey("31");
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("苏付服务");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("苏付介绍");
		mainBtn2.setSub_button(new CommonButton[] { btn21 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("问题反馈");
		mainBtn3.setSub_button(new CommonButton[] { btn31 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}
