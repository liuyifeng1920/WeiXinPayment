package com.miteno.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miteno.controller.queue.QueueMsgController;
import com.miteno.util.AuthenticateUtil;



/**
 * Servlet implementation class CoreServlet
 */
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		QueueMsgController queueMsg =new QueueMsgController(); 
	}
	/**
	 * 确认来自微信的验证
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String token="meitainuo";
		// 微信加密签名
		String signature = request.getParameter("signature");
		System.out.println("signature="+signature);
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		System.out.println("timestamp="+timestamp);
		// 随机数
		String nonce = request.getParameter("nonce");
		System.out.println("nonce="+nonce);
		// 随机字符串
		String echostr = request.getParameter("echostr");
		System.out.println("echostr="+echostr);
		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (AuthenticateUtil.check_Signature(signature, timestamp, nonce,token)) {
			//接入成功后，自定义菜单
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 处理微信服务器返回的消息
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String respMessage = CoreService.processRequest(request);

		PrintWriter writer = response.getWriter();
		writer.print(respMessage);
		
		writer.close();
	}

}
