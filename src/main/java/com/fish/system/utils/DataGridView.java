package com.fish.system.utils;

/**
 * @ClassName DataGridView
 * @Description 对返回前台的数据进行格式化
 * @Author 柚子茶
 * @Date 2020/12/1 13:57
 * @Version 1.0
 */
public class DataGridView {

	private Integer code = 0;
	private String msg;
	private Long count;
	private Object data;

	public DataGridView() {
	}

	/**
	 * @param data
	 * @return null
	 * @description 数据构造器
	 * @author 柚子茶
	 * @date 2020/12/1 14:02
	 **/
	public DataGridView(Object data) {
		this.data = data;
	}

	/**
	 * @param code
	 * @param data
	 * @return null
	 * @description 构造树结构所需要的数据格式
	 * @author 柚子茶
	 * @date 2020/12/1 14:01
	 **/
	public DataGridView(Integer code, Object data) {
		this.code = code;
		this.data = data;
	}

	/**
	 * @param count
	 * @param data
	 * @return null
	 * @description 构造表格所需要的数据格式
	 * @author 柚子茶
	 * @date 2020/12/1 14:01
	 **/
	public DataGridView(Long count, Object data) {
		this.count = count;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


}
