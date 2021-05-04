package com.fish.statistics.controller;

import com.fish.statistics.domain.BaseEntity;
import com.fish.statistics.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StatsController
 * @Description 统计分析的前端控制器
 * @Author 柚子茶
 * @Date 2021/3/8 21:54
 * @Version 1.0
 */
@Controller
@RequestMapping("stats")
public class StatsController {

	@Autowired
	private StatsService statsService;


	/**
	 * @return String
	 * @description 跳转到销售额统计界面
	 * @author 柚子茶
	 * @date 2021/3/8 22:49
	 **/
	@RequestMapping("toSaleStatistics")
	public String toSaleStatistics() {
		return "statistics/SalesRevenueStatistics";
	}

	/**
	 * @return String
	 * @description 跳转到足疗项目使用次数统计界面
	 * @author 柚子茶
	 * @date 2021/3/9 0:23
	 **/
	@RequestMapping("toPedicureUseCount")
	public String toPedicureUseCount() {
		return "statistics/PedicureUseCountStatistics";
	}


	/**
	 * @return Map<String, Object>
	 * @description 足疗店足疗项目销售额统计
	 * @author 柚子茶
	 * @date 2021/3/8 22:30
	 **/
	@ResponseBody
	@RequestMapping("loadSaleChartData")
	public Map<String, Object> loadSaleChartData() {
		return this.statsService.loadSaleChartData();
	}

	/**
	 * @return List<BaseEntity>
	 * @description 统计足疗项目的使用次数
	 * @author 柚子茶
	 * @date 2021/3/9 0:19
	 **/
	@ResponseBody
	@RequestMapping("loadFanChartDataByJson")
	public List<BaseEntity> loadFanChartDataByJson() {
		return this.statsService.queryFanChartDataByJson();
	}

	/**
	 * @return BigDecimal
	 * @description 查询总销售额
	 * @author 柚子茶
	 * @date 2021/3/8 23:12
	 **/
	@ResponseBody
	@RequestMapping("loadSaleTotalAmount")
	public BigDecimal loadSaleTotalAmount() {
		return this.statsService.loadSaleTotalAmount();
	}


}
