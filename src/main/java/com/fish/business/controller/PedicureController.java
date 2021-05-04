package com.fish.business.controller;

import com.fish.business.service.PedicureService;
import com.fish.business.vo.PedicureVo;
import com.fish.system.utils.CommonReturnType;
import com.fish.system.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PedicureController
 * @Description 足疗项目管理前端控制器
 * @Author 柚子茶
 * @Date 2021/3/5 12:03
 * @Version 1.0
 */
@RestController
@RequestMapping("pedicure")
public class PedicureController {

	@Autowired
	private PedicureService pedicureService;


	/**
	 * @param pedicureVo 足疗项目实体类
	 * @return DataGridView
	 * @description
	 * @author 柚子茶
	 * @date 2021/3/5 12:06
	 **/
	@RequestMapping("loadPedicureInfo")
	public DataGridView loadPedicureInfo(PedicureVo pedicureVo) {
		return this.pedicureService.queryPedicureInfo(pedicureVo);
	}


	/**
	 * @param pedicureVo
	 * @return CommonReturnType
	 * @description 添加项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 12:08
	 **/
	@RequestMapping("addPedicureInfo")
	public CommonReturnType addPedicureInfo(PedicureVo pedicureVo) {
		try {
			this.pedicureService.addPedicureInfo(pedicureVo);
			return CommonReturnType.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.ADD_FAILURE;
		}
	}

	/**
	 * @param pedicureVo
	 * @return CommonReturnType
	 * @description 更新项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 12:11
	 **/
	@RequestMapping("updatePedicureInfo")
	public CommonReturnType updatePedicureInfo(PedicureVo pedicureVo) {
		try {
			this.pedicureService.updatePedicureInfo(pedicureVo);
			return CommonReturnType.MODIFY_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.MODIFY_FAILURE;
		}
	}

	/**
	 * @param pedicureVo
	 * @return CommonReturnType
	 * @description 删除项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 12:12
	 **/
	@RequestMapping("deletePedicureInfo")
	public CommonReturnType deletePedicureInfo(PedicureVo pedicureVo) {
		try {
			this.pedicureService.deletePedicureInfo(pedicureVo.getPedicureId());
			return CommonReturnType.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.DELETE_FAILURE;
		}
	}


}
