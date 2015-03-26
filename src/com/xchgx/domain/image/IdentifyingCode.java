package com.xchgx.domain.image;


public class IdentifyingCode {

	private String testStr;
	private String operator;
	private int first;
	private int second;
	private String equal;
	private int calcResult;
	private long startTime;
	private long stopTime;
	private long timeout;
	
	public IdentifyingCode(long timeout) {
		this.timeout = timeout;
	}
	public long getStopTime() {
		return stopTime;
	}
	public long getStartTime() {
		return startTime;
	}
	public String getTestStr() {
		return testStr;
	}
	public String getOperator() {
		return operator;
	}
	public int getFirst() {
		return first;
	}
	public int getSecond() {
		return second;
	}
	public String getEqual() {
		return equal;
	}
	public int getCalcResult() {
		return calcResult;
	}

	/**
	 * 生成第一个随机数
	 * @return
	 */
	private int randomFirst(){
		double temp = Math.random();
		int i_num = (int)(temp * 100);
		first = i_num==0?1:i_num;
		return first;
	}
	/**
	 * 生成操作符
	 * 下一次升级：
	 *    将加减乘除在字符和中文之间随机出现
	 * @return
	 */
	private String randomOperator(){
		String operators[] = {"+","-"};
		double temp = Math.random();
		int i_num = (int)(temp * 2);
		operator = operators[i_num];
		return operator ;
	}
	/**
	 * 生成第二个随机数
	 * @return
	 */
	private int randomSecond(){
		double temp = Math.random();
		int i_num = (int)(temp * 100);
		second = i_num==0?1:i_num;
		return second;
	}
	/**
	 * 生成等号
	 * @return
	 */
	private String randomEqual(){
		String equals[] = {"=","等于"};
		double temp = Math.random();
		int i_num = (int)(temp * 2);
		equal = equals[i_num] ;
		return equal;
	}
	/**
	 * 计算结果
	 * @return
	 */
	private int calcResult(){
		if(operator.equals("+")){
			calcResult = first + second;
		}else{
			calcResult = first - second;
		}
		return calcResult;
	}
	/**
	 * 生成表达式
	 * @return
	 */
	public String expression(){
		randomFirst();
		randomOperator();
		randomSecond();
		randomEqual();
		calcResult();
		startTime = System.currentTimeMillis();
		return ""+getFirst()+getOperator()+getSecond()+getEqual();
	}
	/**
	 * 验证结果
	 * @return
	 */
	public boolean validIdentifyingCode(String result){
		return checkTimeout()? result.equals(""+calcResult) : false;
	}
	/**
	 * 验证超时
	 * @return true:没有超时； false: 已经超时
	 */
	private boolean checkTimeout(){
		stopTime = System.currentTimeMillis();
//		System.out.println(stopTime - startTime);
		return (stopTime - startTime)<timeout;
	}
}
