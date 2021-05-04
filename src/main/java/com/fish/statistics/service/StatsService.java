package com.fish.statistics.service;

import com.fish.statistics.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName StatsService
 * @Description 统计分析的业务层接口
 * @Author 柚子茶
 * @Date 2021/3/8 21:52
 * @Version 1.0
 */
public interface StatsService {


	/**
	 * @return Map<String, Object>
	 * @description 查询销售额
	 * @author 柚子茶
	 * @date 2021/3/8 22:33
	 **/
	Map<String, Object> loadSaleChartData();

	/**
	 * @return BigDecimal
	 * @description 查询总销售额
	 * @author 柚子茶
	 * @date 2021/3/8 23:13
	 **/
	BigDecimal loadSaleTotalAmount();

	/**
	 * @return List<BaseEntity>
	 * @description 统计足疗项目的使用次数
	 * @author 柚子茶
	 * @date 2021/3/9 0:18
	 **/
	List<BaseEntity> queryFanChartDataByJson();
}
