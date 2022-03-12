package com.guest.controller;


import com.guest.pojo.po.Background;
import com.guest.pojo.vo.Response;
import com.guest.pojo.vo.ResponseMsg;
import com.guest.service.BackgroundService;
import com.guest.utils.JwtUtill;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 前端控制器 -- 后台管理员登录
 *
 * @author chuanguo.cao
 * @since 2022-03-02
 */
@CrossOrigin
@Transactional
@RestController
@Api(tags = {"后端管理员"})
public class BackgroundController {
	@Autowired
	private BackgroundService backgroundService;
	@Autowired
	private JwtUtill jwtUtill;

	@PostMapping("/backgroundLogin")
	@ApiOperation(value = "后台管理员登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "token，在这个请求中不填", required = false),
			@ApiImplicitParam(name = "backId", value = "后台管理员的工号", required = true),
			@ApiImplicitParam(name = "password", value = "后台管理员的密码", required = true)
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "请求成功"),
			@ApiResponse(code = 40105, message = "密码错误,请核对后重新输入"),
			@ApiResponse(code = 40005, message = "该用户不存在")
	})
	public Response backgroundLogin(Background background) {
		Background background1 = backgroundService.getById(background.getBackId());
		if (background1 != null) {
			if (background1.getPassword().equals(background.getPassword())) {
				String token = jwtUtill.updateJwt(background.getBackId());
				return (new Response()).success(token);
			}
			return new Response(ResponseMsg.PASSWORD_WRONG);
		}
		return new Response(ResponseMsg.NO_SUCH_USER);
	}

	@PostMapping("/modifyBackground")
	@ApiOperation(value = "修改后台管理员密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "填后台管理员的token", required = true),
			@ApiImplicitParam(name = "password", value = "后台管理员的密码", required = true),
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "请求成功"),
			@ApiResponse(code = 40104, message = "非法操作, 试图操作不属于自己的数据")
	})
	public Response modifyPassword(HttpServletRequest request, String newPassword){
		String num = (String)request.getAttribute("num");
		Background background = backgroundService.getById(num);
		if (background!=null&& background.getBackId().equals(num)){
			background.setPassword(newPassword);
			backgroundService.updateById(background);
			String token = jwtUtill.updateJwt(num);
			return (new Response()).success(token);
		}
		return new Response(ResponseMsg.ILLEGAL_OPERATION);
	}
}

