package com.miteno.controller.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miteno.entity.OrderData;
import com.miteno.entity.Phoneaccess;
import com.miteno.service.MobService;
import com.miteno.util.ConstUtils;
import com.miteno.util.DateUtils;
import com.miteno.util.TenpayUtil;

@Controller
@RequestMapping("/background2/")
// 微信前台页面所在的文件夹
public class MobController {
	@Autowired
	private MobService mobService;

	/**
	 * 初始化时手机号为空,则显示可充值的全部面额，不考虑库存 手机号不为空,根据手机号信息获取归属地，并显示此手机号可以充值的面额
	 */
	// 查询面值
	@RequestMapping("query")
	public String query(Model model, String pageNow, HttpServletRequest request) {
		List<String> plist = mobService.queryFace();
		request.setAttribute("plist", plist);
		// 返回优惠价
		List<String> listyhj = mobService.searchYhPrice();
		request.setAttribute("listyhj", listyhj);
		String def = listyhj.get(0);
		request.setAttribute("def", def);
		return "/background/microui/AmobList";
	}

	/**
	 * 跳转action 用户点击微信上的菜单后，进入action查询出产品等后，调转到充值页面
	 * 
	 * @param model
	 * @param pageNow
	 * @param request
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		// 页面写死，省略查询产品等
		return "/background/weixin/index";
	}

	@RequestMapping("article1")
	public String article1(Model model) {
		// 页面写死，省略查询产品等
		return "/background/weixin/article1";
	}

	@RequestMapping("article2")
	public String article2(Model model) {
		// 页面写死，省略查询产品等
		return "/background/weixin/article2";
	}

	/**
	 * 调转action到order查询页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("order")
	public String order(Model model) {

		return "/background/weixin/order";
	}

	@RequestMapping("pay")
	public String pay(Model model) {

		return "/background/weixin/pay";
	}

	// 返回归属地
	@RequestMapping("searchghd")
	public void getBelong(HttpServletRequest request,
			HttpServletResponse response, String param) throws Exception {
		// 手机号码前七位必须正确(在表中必须有字段)
		Phoneaccess pojo = mobService.searchmobileBelong(param.substring(0, 7));
		String firstMes = pojo.getProvince()+ pojo.getOperator()+"话费10元直冲";
		System.out.println(firstMes);
		String Belonging = pojo.getProvince() + pojo.getCity()
				+ pojo.getOperator();
		String result = "{\"belonging\":\" " + Belonging + " \"}";
		PrintWriter out = null;
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 交易历史记录查询
	@RequestMapping("searchHistory")
	public String searchHistory(HttpServletRequest request,
			String phone_number, String recharge_date, String type)
			throws Exception {
		List<OrderData> orList = null;
		OrderData data = null;
		// 走到查询订单页面
		if (phone_number == null && recharge_date == null) {
			// request.setAttribute("orList", orList);
			return "/background/microui/order";
		}

		// 判斷日期
		String trhMounth = "";
		if (recharge_date == null || "".equals(recharge_date)) {
			trhMounth = DateUtils.getDate();
		} else {
			trhMounth = recharge_date;
		}
		// 根据参数判断显示几条数据
		if (type != null && !"".equals(type)) {
			// String trhMounth = "";
			// if (recharge_date != null && !"".equals(recharge_date)) {
			trhMounth = DateUtils.getTimeAfterMonthTime(trhMounth);// recharge_date前三个月的日期
																	// 日期不能空
			// }
			data = new OrderData();
			data.setPhone_number(phone_number);
			data.setRecharge_date(trhMounth);
		} else {
			data = new OrderData();
			data.setPhone_number(phone_number);
			data.setRecharge_date(trhMounth);
		}

		// 订单页面查询数据
		try {
			orList = mobService.searchOrder(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (type != null && !"".equals(type)) {
			request.setAttribute("begin", 0);
			request.setAttribute("end", orList.size());
			request.setAttribute("orList", orList);
		} else {
			request.setAttribute("begin", 0);
			request.setAttribute("end", 2);
			request.setAttribute("orList", orList);
		}
		return "/background/microui/orderList";
	}

	// 分页
	/*
	 * public String searchHistory(Model model,Order order ,String pageNow) {
	 * PageView pageView = null; if (Common.isEmpty(pageNow)) { pageView = new
	 * PageView(1); } else { pageView = new PageView(Integer.parseInt(pageNow));
	 * } pageView = mobService.searchHistory(pageView, order);
	 * model.addAttribute("pageView", pageView); return
	 * "/background/microui/orderList"; }
	 */

	// 返回优惠价
	// @RequestMapping("searchyhj")
	// public String searchyhj(HttpServletRequest request , HttpServletResponse
	// response ){
	// List<String> listyhj = mobService.searchYhPrice();
	// JSONArray arr =new JSONArray();
	// for (Object object : listyhj) {
	// arr.add(object);
	// }
	// PrintWriter out = null;
	// response.setContentType("application/json");
	// response.setCharacterEncoding("UTF-8");
	// try {
	// out = response.getWriter();
	// //out.write(arr);
	// out.print(arr);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// request.setAttribute("listyhj", listyhj);
	// return "/background/microui/AmobList";
	// }
	/*
	 * public void searchyhj(HttpServletRequest request , HttpServletResponse
	 * response,String param) throws Exception{
	 * 
	 * if(param !=null && !"".equals(param)){ param=param.replace("元" ,""); }
	 * Product pojo =mobService.searchyhj(param);
	 * 
	 * String result = "{\"yhj\":\" "+ pojo.getPrice_scope() +" \"}";
	 * PrintWriter out = null; response.setContentType("application/json");
	 * response.setCharacterEncoding("UTF-8"); try { out = response.getWriter();
	 * out.write(result); } catch (IOException e) { e.printStackTrace(); } }
	 */
	// 更新订单信息
	@RequestMapping("saveOrderData")
	public void saveOrderData(HttpServletRequest request,HttpServletResponse  response,
			String phone_number, String money)
			throws Exception {
		
		//判断归属地信息
		Phoneaccess pojo = mobService.searchmobileBelong(phone_number.substring(0, 7));
		String msg =""; //返回前台消息
		if(pojo == null){
			msg ="{\"success\":false,\"message\":\"目前支持归属地为 江苏 的手机号\"}";
			/*request.setAttribute("msg",msg);
			return "/background/microui/AmobList";*/
		}else{
			Date now = new Date();
			SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowdate = outFormat.format(now);// 订单充值日期

			OrderData od = new OrderData();
			String currTime = TenpayUtil.getCurrTime(); // 14为日期
			String strTime = currTime.substring(10, currTime.length());// 日期末四位
			String strRandom = TenpayUtil.buildRandom(4) + "";// 四位随机数

			od.setInner_order_number("WX" + currTime + strTime); // 内部订单号，微信平台的订单号
			od.setSend_order_number(currTime + strRandom); // 提交给话费充值平台的订单号
			od.setPhone_number(phone_number); // 手机号11位
			od.setMoney(money);// 充值金额 页面上显示的面值
			/**
			 * pay_money 支付金额 是根据  product 表中 price(售价)字段信息
			 */
			String pay_money = mobService.searchPrice(money);
			System.out.println("支付金额" + pay_money);
			od.setPay_money(pay_money);
			od.setRecharge_date(nowdate); // 订单充值日期
			od.setOrder_status(ConstUtils.STATE_NON_PAYMENT); // 充值状态 默认是3 处理中
			try {
				mobService.saveOrder(od);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//获取授权信息
			// 更新订单之后返回付款页面 根据订单号去查并返回
			String wxOrderNo = "WX"+currTime + strTime; //根据订单返回信息
			String redirect_uri = URLEncoder.encode(ConstUtils.REDIRECT_URI, "utf-8");
			/*request.setAttribute("redirect_uri",URLEncoder.encode(ConstUtils.REDIRECT_URI, "utf-8"));
			request.setAttribute("wxOrderNo",wxOrderNo);*/
			msg ="{\"success\":\"true\",\"message\":\"ok\",\"wxOrderNo\":\""+wxOrderNo+"\",\"redirect_uri\":\""+redirect_uri+"\"}";
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(msg);
		// phonePay 手机号 money金额
		/*
		 * private String order_id; //主键自增 private String phone_number; //
		 * 手机号11位 private String recharge_date; //充值日期（第一次入库时间，微信用户提交时间） private
		 * String inner_order_number; //内部订单号，微信平台的订单号 private String
		 * send_order_number; //提交给话费充值平台的订单号 private String money; //充值金额
		 * private String order_status; //充值状态（1未支付，2已付款，3处理中，4成功，5失败） private
		 * String describe; //描述信息
		 */
		
		/*return "/background/microui/GetCodeList";*/
	}

	// 返回code参数
	@RequestMapping("backCode")
	public String backCode(String code, String state, HttpServletRequest request) { // state为订单编号
		// 0013430ffe949505f73a1e74ccf8599f
		String getOpenId = "";
		try {
			getOpenId = WxGetOpenId.getOpenId(code);
			System.out.println("getOpenId" + getOpenId);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 根据订单号 查询此订单下的所有信息
		OrderData payData = mobService.searchpayDataList(state);
		//根据openid 获取预支付id
		String finaPackage = WxPay.getPackage(payData, getOpenId);
		//返回预支付ID的相应信息
		JSONObject obj = JSONObject.fromObject(finaPackage);
		String appid = obj.getString("appId");
		String timeStamp = obj.getString("timeStamp");
		String nonceStr = obj.getString("nonceStr");
		String packageParam = obj.getString("package");
		String signType = obj.getString("signType");
		String paySign = obj.getString("paySign");

		request.setAttribute("timeStamp", timeStamp);
		System.out.println("timeStamp="+timeStamp);
		request.setAttribute("nonceStr", nonceStr);
		System.out.println("nonceStr="+nonceStr);
		request.setAttribute("packageParam", packageParam);
		System.out.println("packageParam="+packageParam);
		request.setAttribute("paySign", paySign);
		System.out.println("paySign="+paySign);
		request.setAttribute("pd", payData);
		//支付页面需要的信息开始 ↓
		//优惠金额
		request.setAttribute("pay_money",payData.getPay_money()); 
		//充值日期（第一次入库时间）
		request.setAttribute("recharge_date",payData.getRecharge_date()); 
		// 订单编号
		request.setAttribute("porderNo", state);
		//手机号
		String phoneNo = payData.getPhone_number();
		//根据手机号前七位查询归属地
		Phoneaccess pojo = mobService.searchmobileBelong(phoneNo.substring(0, 7));
		//返回归属地信息
		String Belonging = pojo.getProvince() + pojo.getCity()+ pojo.getOperator();
		if(StringUtils.isNotEmpty(Belonging)){
			//手机号（用于支付成功返回到查詢訂單信息）
			request.setAttribute("Belonging", Belonging);
		}else{
			request.setAttribute("Belonging", "暂不支持"+phoneNo.substring(0, 7)+"开头的手机号码");
		}
		//显示的位置在北京移动话费10元直冲
		//面值
		String moeny = payData.getMoney();
		String firstMes = pojo.getProvince()+ pojo.getOperator()+"话费"+moeny+"元直冲";
		request.setAttribute("firstMes", firstMes);
		//支付页面需要的信息结束 ↑
		request.setAttribute("phoneNo", phoneNo);
		return "/background/microui/wxPay";
		//return "redirect:payList.action";?timeStamp=" + timeStamp + "&nonceStr="
			//	+ nonceStr + "&packageParam=" + packageParam + "&paySign="
			//	+ paySign + "&payData=" + payData + "&porderNo=" + state;*/
	}

	/*@RequestMapping("payList")
	public String payList(Model model, HttpServletRequest request,
			HttpServletResponse response, String timeStamp, String nonceStr,
			String packageParam,String paySign,String payData,String porderNo) {

		
		 * String timeStamp = (String) request.getAttribute("timeStamp"); String
		 * nonceStr = (String) request.getAttribute("nonceStr");
		 
		String packageParam = (String) request.getAttribute("packageParam");

		String signType = (String) request.getAttribute("signType");
		String paySign = (String) request.getAttribute("paySign");
		String payData = (String) request.getAttribute("payData");
		String porderNo = (String) request.getAttribute("porderNo");
		request.setAttribute("timeStamp", timeStamp);
		request.setAttribute("nonceStr", nonceStr);
		request.setAttribute("packageParam", packageParam);
		request.setAttribute("paySign", paySign);
		request.setAttribute("pd", payData);
		// 订单编号
		request.setAttribute("porderNo", porderNo);
		request.setAttribute("timeStamp", "1432004929");
		request.setAttribute("nonceStr", "1108496759");
		request.setAttribute("packageParam", "prepay_id=wx20150519110939e87f34487c0383561278");
		request.setAttribute("paySign", "ED3D77A1C6CC9459E2CE173B3101A1A7");
		//request.setAttribute("pd", payData);
		// 订单编号
		request.setAttribute("porderNo", porderNo);
		return "/background/microui/payList";
	}
*/
	// 五秒后返回页面设置
	@RequestMapping("back")
	public String back(HttpServletRequest request ,String phoneNo) {
		request.setAttribute("phoneNo", phoneNo);
		return "/background/microui/backList";
	}

	public static void main(String[] args) {
	}

	public void ceshi() {
		// URLEncoder.encode("http://50dbfa34.ngrok.io/WeiXinPayment/background/microui/backCode.action");

	}
}
