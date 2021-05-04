package com.fish.business.service.impl;

import com.fish.business.dao.PedicureDao;
import com.fish.business.domain.Pedicure;
import com.fish.business.service.PedicureService;
import com.fish.business.vo.PedicureVo;
import com.fish.system.domain.User;
import com.fish.system.utils.DataGridView;
import com.fish.system.utils.WebUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PedicureServiceImpl
 * @Description 足疗项目业务层接口实现类
 * @Author 柚子茶
 * @Date 2021/3/5 12:01
 * @Version 1.0
 */
@Service
public class PedicureServiceImpl implements PedicureService {

	@Autowired
	private PedicureDao pedicureDao;

	/**
	 * @param pedicureVo 足疗项目实体类
	 * @return DataGridView
	 * @description 查询项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 12:21
	 **/
	@Override
	public DataGridView queryPedicureInfo(PedicureVo pedicureVo) {
		Page<Object> page = PageHelper.startPage(pedicureVo.getPage(), pedicureVo.getLimit());
		List<Pedicure> pedicureList = this.pedicureDao.selectPedicureInfoByList(pedicureVo);
		return new DataGridView(page.getTotal(), pedicureList);
	}


	/**
	 * @param pedicureVo
	 * @return void
	 * @description 添加项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 16:44
	 **/
	@Override
	public void addPedicureInfo(PedicureVo pedicureVo) {
		// 获取到项目收费类型
		String chargeType = pedicureVo.getChargeType();
		if ("次".equals(chargeType)) {
			pedicureVo.setPedicureChargeType(pedicureVo.getPedicureChargeAmount() + "元 / 次");
		}
		if ("分钟".equals(chargeType)) {
			pedicureVo.setPedicureChargeType(pedicureVo.getPedicureChargeAmount() + "元 / " + pedicureVo.getServiceTime() + "分钟");
		}
		if ("小时".equals(chargeType)) {
			pedicureVo.setPedicureChargeType(pedicureVo.getPedicureChargeAmount() + "元 / " + pedicureVo.getServiceTime() + "小时");
		}
		// 获取到当前登录用户
		User user = (User) WebUtils.getHttpSession().getAttribute("user");
		pedicureVo.setCreateUserId(user.getUserId());
		pedicureVo.setPedicureCreateTime(new Date());
		this.pedicureDao.insertSelective(pedicureVo);
	}

	/**
	 * @param pedicureVo
	 * @return void
	 * @description 更细项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 12:22
	 **/
	@Override
	public void updatePedicureInfo(PedicureVo pedicureVo) {
		// 获取到项目收费类型
		String chargeType = pedicureVo.getChargeType();
		if ("次".equals(chargeType)) {
			pedicureVo.setPedicureChargeType(pedicureVo.getPedicureChargeAmount() + "元 / 次");
		}
		if ("分钟".equals(chargeType)) {
			pedicureVo.setPedicureChargeType(pedicureVo.getPedicureChargeAmount() + "元 / " + pedicureVo.getServiceTime() + "分钟");
		}
		if ("小时".equals(chargeType)) {
			pedicureVo.setPedicureChargeType(pedicureVo.getPedicureChargeAmount() + "元 / " + pedicureVo.getServiceTime() + "小时");
		}

		this.pedicureDao.updateByPrimaryKeySelective(pedicureVo);
	}

	/**
	 * @param pedicureId
	 * @return void
	 * @description 删除想项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 12:22
	 **/
	@Override
	public void deletePedicureInfo(Integer pedicureId) {
		this.pedicureDao.deleteByPrimaryKey(pedicureId);
	}

	/**
	 * @return List<Pedicure>
	 * @description 查询所有足疗信息
	 * @author 柚子茶
	 * @date 2021/3/7 19:43
	 **/
	@Override
	public List<Pedicure> initPedicureInfo() {
		return this.pedicureDao.queryPedicureInfo();
	}

	/**
	 * @param pedicureVo
	 * @return Pedicure
	 * @description 根据项目名称查询项目金额
	 * @author 柚子茶
	 * @date 2021/3/7 20:40
	 **/
	@Override
	public Pedicure getPedicureByName(PedicureVo pedicureVo) {
		return this.pedicureDao.queryPedicureByName(pedicureVo.getPedicureId());
	}

	/**
	 * @param pedicureVo
	 * @return DataGridView
	 * @description 查询项目信息
	 * @author 柚子茶
	 * @date 2021/3/8 11:11
	 **/
	@Override
	public DataGridView queryPedicureInfoByState(PedicureVo pedicureVo) {
		Page<Object> page = PageHelper.startPage(pedicureVo.getPage(), pedicureVo.getLimit());
		// 只查询上架的足疗项目信息
		pedicureVo.setPedicureState(0);
		List<Pedicure> pedicureList = this.pedicureDao.selectPedicureInfoByList(pedicureVo);
		return new DataGridView(page.getTotal(), pedicureList);
	}
}
