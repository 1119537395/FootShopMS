package com.fish.business.service;

import com.fish.business.domain.Pedicure;
import com.fish.business.vo.PedicureVo;
import com.fish.system.utils.DataGridView;

import java.util.List;

/**
 * @InterfaceName PedicureService
 * @Description 足疗项目业务层接口
 * @Author 柚子茶
 * @Date 2021/3/5 12:01
 * @Version 1.0
 */
public interface PedicureService {

	/**
	 * @param pedicureVo 足疗项目实体类
	 * @return DataGridView
	 * @description 查询项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 12:07
	 **/
	DataGridView queryPedicureInfo(PedicureVo pedicureVo);


	/**
	 * @param pedicureVo
	 * @return void
	 * @description 添加项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 12:10
	 **/
	void addPedicureInfo(PedicureVo pedicureVo);

	/**
	 * @param pedicureVo
	 * @return void
	 * @description 更新项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 12:12
	 **/
	void updatePedicureInfo(PedicureVo pedicureVo);

	/**
	 * @param pedicureId
	 * @return void
	 * @description 删除项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 12:13
	 **/
	void deletePedicureInfo(Integer pedicureId);

	/**
	 * @return List<Pedicure>
	 * @description 查询所有足疗信息
	 * @author 柚子茶
	 * @date 2021/3/7 19:43
	 **/
	List<Pedicure> initPedicureInfo();

	/**
	 * @param pedicureVo
	 * @return Pedicure
	 * @description 根据项目名称查询项目金额
	 * @author 柚子茶
	 * @date 2021/3/7 20:40
	 **/
	Pedicure getPedicureByName(PedicureVo pedicureVo);

	/**
	 * @param pedicureVo
	 * @return DataGridView
	 * @description 查询项目信息
	 * @author 柚子茶
	 * @date 2021/3/8 11:11
	 **/
	DataGridView queryPedicureInfoByState(PedicureVo pedicureVo);
}
