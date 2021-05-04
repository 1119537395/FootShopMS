package com.fish.statistics.dao;

import com.fish.statistics.domain.BaseEntity;

import java.util.List;

/**
 * @InterfaceName StatsMapper
 * @Description 用于统计分析的数据访问层
 * @Author 柚子茶
 * @Date 2021/3/8 21:51
 * @Version 1.0
 */
public interface StatsDao {


	/**
	 * @return List<BaseEntity>
	 * @description 查询出足疗项目对应的销售额度
	 * @author 柚子茶
	 * @date 2021/3/8 22:40
	 **/
	List<BaseEntity> querySaleChartData();

	/**
	 * @return BaseEntity
	 * @description 查询总销售额度
	 * @author 柚子茶
	 * @date 2021/3/8 23:14
	 **/
	BaseEntity querySaleTotalAmount();

	/**
	 * @return List<BaseEntity>
	 * @description 统计足疗项目的使用次数
	 * @author 柚子茶
	 * @date 2021/3/9 0:20
	 **/
	List<BaseEntity> queryFanChartDataByJson();
}
