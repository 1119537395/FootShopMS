package com.fish.system.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.fish.system.domain.User;
import com.fish.system.service.UserService;
import com.fish.system.utils.MessageEnum;
import com.fish.system.utils.WebUtils;
import com.fish.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName LoginController
 * @Description 登录控制器
 * @Author 柚子茶
 * @Date 2021/2/24 11:16
 * @Version 1.0
 */
@Controller
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;


	/**
	 * @return String
	 * @description 跳转到登录界面
	 * @author 柚子茶
	 * @date 2021/2/24 11:17
	 **/
	@RequestMapping("toLogin")
	public String toLogin() {
		return "system/main/login";
	}

	/**
	 * @param userVo 用户实例化对象
	 * @param model  Model实例化对象
	 * @return String
	 * @description 登录验证
	 * @author 柚子茶
	 * @date 2021/2/24 13:01
	 **/
	@RequestMapping("userLogin")
	public String userLogin(UserVo userVo, Model model) {

		// 获取到前台验证码
		String code = (String) WebUtils.getHttpSession().getAttribute("code");
		// 判断输入的验证码是否正确
		boolean flag = userVo.getCode().equalsIgnoreCase(code);
		if (flag) {
			// 进行账户名和密码的验证
			User user = userService.userLogin(userVo);
			if (null != user) {
				// 记录登录的用户信息
				WebUtils.getHttpSession().setAttribute("user", user);
				// 记录用户登录的日志信息

				// 跳转到主界面
				return "system/main/index";

			} else {
				model.addAttribute("LOGIN_ERROR", MessageEnum.USER_LOGIN_ERROR_MSG.getMessage());
				return "system/main/login";
			}
		} else {
			model.addAttribute("CAPTCHA_ERROR", MessageEnum.USER_LOGIN_CODE_ERROR_MSG.getMessage());
			return "system/main/login";
		}
	}

	/**
	 * @param response 响应对象
	 * @param session  session
	 * @return void
	 * @description 获取验证码存入session中并将图片写回前台
	 * @author 柚子茶
	 * @date 2020/11/26 19:09
	 **/
	@RequestMapping("getCaptcha")
	public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
		// 定义验证码的宽高
		CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(116, 36, 4, 10);
		// 将生成的验证码存入到Session中
		session.setAttribute("code", captcha.getCode());
		// 得到字节输出流对象
		ServletOutputStream sos = response.getOutputStream();
		// 输出写到前端界面
		ImageIO.write(captcha.getImage(), "JPEG", sos);
	}


}
