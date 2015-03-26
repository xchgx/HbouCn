package com.xchgx.zsbwork.bean;

import java.io.Serializable;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2459179135306419326L;
	private Integer id;
	private String city;// 省
	private String cengCi;// 办学层次，科类
	private String baoDao;// 是否报到
	private String wenLiKe;// 文理科
	private String kaoShengHao;// 考生号
	private String no; // 录取通知书编号 NO
	private String name; // 姓名
	private String sex; // 性别
	private String kaoShengTeZheng; // 考生特征
	private String touDangDanWei;// 投档单位
	private String waiYuYuZhong; // 外语语种
	private String touDangChengJi; // 投档成绩
	private String yuSheJiZhunFen;// 预设基准分
	private String touDangZhiYuan; // 投档志愿
	private String zhuanYe1; // 专业1
	private String zhuanYe2;// 专业2
	private String zhuanYe3;// 专业3
	private String zhuanYe4;// 专业4
	private String zhuanYe5;// 专业5
	private String zhuanYe6;// 专业6
	private String tiaoJi; // 调剂
	private String luQuZhuanYe; // 录取专业
	private String tuiDangYuanYin; // 退档原因
	private String tiJianShouXianBiaoZhi; // 体检受限标志
	private String tiJianJieLun; // 体检结论
	private String luQuFangShi; // 录取方式
	private String tuiDangYuanYinBeiZhu; // 退档原因备注
	private String yuSheZhuanYe; // 预投专业
	private String fuCongZhuanYeLeiBie; // 服从专业类别
	private String dingXiangTiaoJi; // 定向调剂
	private String zhiYuanTeZheng; // 志愿特征
	private String touDangDanBianHao; // 投档单编号
	private String yuSheFuJiaFen; // 预投附加分
	private String dengXiaoJiZhunFen; // 等效基准分
	private String yuanXiaoDaoRuChengJi; // 院校导入成绩
	private String ziGeFen; // 资格分
	private String teZhengChengJi; // 特征成绩
	private String zongFen; // 总分
	private String shouJianRen; // 收件人
	private String zhengShenYiJian; // 政审意见
	private String kaoShengJiangLiHeChuFen; // 考生奖励和处分
	private String kaoShengTeChang; // 考生特长
	private String huiKaoKaoHao; // 会考考号
	private String lianXiDianHua; // 联系电话
	private String youZhengBianMa; // 邮政编码
	private String jiaTingDiZhi; // 家庭地址
	private String shenFenZhengHao; // 身份证号
	private String huiKaoDengJi; // 会考等级
	private String yingShiJuanZhong; // 应试卷种
	private String waiYuTingLi; // 外语听力
	private String waiYuKouShi; // 外语口试
	private String kaoShiLeiXing; // 考试类型
	private String huKouSuoZaiDi; // 户口所在地
	private String xiTongDanWei; // 系统单位
	private String baoMingDanWei; // 报名单位
	private String biYeLeiBie; // 毕业类别
	private String kaoShengLeiBie; // 考生类别
	private String minZu; // 民族
	private String zhengZhiMianMao; // 政治面貌
	private String zhongXueMingCheng; // 中学名称
	private String zhongXueDaiMa; // 中学代码
	private String zhunKaoZhengHao; // 准考证号
	private String chuShengRiQi; // 出生日期
	private String xuHao; // 序号
	private Teacher teacher;
	public Student() {
	}
	
	/*
	 * 省 科类 文理科 考生号 No 姓名 性别 考生特征 投档单位 外语语种 投档成绩 预投基准分 投档志愿 专业1 专业2 专业3 调剂 录取专业
	 * 退档原因 体检受限标志 体检结论 录取方式 退档原因备注 预投专业 服从专业类别 定向调剂 志愿特征 专业6 专业5 专业4 投档单编号
	 * 预投附加分 等效基准分 院校导入成绩 资格分 特征成绩 总分 收件人 政审意见 考生奖励和处分 考生特长 会考考号 联系电话 邮政编码 家庭地址
	 * 身份证号 会考等级 应试卷种 外语听力 外语口试 考试类型 户口所在地 系统单位 报名单位 毕业类别 考生类别 民族 政治面貌 中学名称 中学代码
	 * 准考证号 出生日期 序号
	 */
	public Student(
			String city,// 省
			String cengCi,// 办学层次，科类
			String wenLiKe,// 文理科
			String kaoShengHao,// 考生号
			String no, // 录取通知书编号 NO
			String name, // 姓名
			String sex, // 性别
			String kaoShengTeZheng, // 考生特征
			String touDangDanWei,// 投档单位
			String waiYuYuZhong, // 外语语种
			String touDangChengJi, // 投档成绩
			String yuSheJiZhunFen,// 预设基准分
			String touDangZhiYuan, // 投档志愿
			String zhuanYe1, // 专业1
			String zhuanYe2,// 专业2
			String zhuanYe3,// 专业3
			String zhuanYe4,// 专业4
			String zhuanYe5,// 专业5
			String zhuanYe6,// 专业6
			String tiaoJi, // 调剂
			String luQuZhuanYe, // 录取专业
			String tuiDangYuanYin, // 退档原因
			String tiJianShouXianBiaoZhi, // 体检受限标志
			String tiJianJieLun, // 体检结论
			String luQuFangShi, // 录取方式
			String tuiDangYuanYinBeiZhu, // 退档原因备注
			String yuSheZhuanYe, // 预投专业
			String fuCongZhuanYeLeiBie, // 服从专业类别
			String dingXiangTiaoJi, // 定向调剂
			String zhiYuanTeZheng, // 志愿特征
			String touDangDanBianHao, // 投档单编号
			String yuSheFuJiaFen, // 预投附加分
			String dengXiaoJiZhunFen, // 等效基准分
			String yuanXiaoDaoRuChengJi, // 院校导入成绩
			String ziGeFen, // 资格分
			String teZhengChengJi, // 特征成绩
			String zongFen, // 总分
			String shouJianRen, // 收件人
			String zhengShenYiJian, // 政审意见
			String kaoShengJiangLiHeChuFen, // 考生奖励和处分
			String kaoShengTeChang, // 考生特长
			String huiKaoKaoHao, // 会考考号
			String lianXiDianHua, // 联系电话
			String youZhengBianMa, // 邮政编码
			String jiaTingDiZhi, // 家庭地址
			String shenFenZhengHao, // 身份证号
			String huiKaoDengJi, // 会考等级
			String yingShiJuanZhong, // 应试卷种
			String waiYuTingLi, // 外语听力
			String waiYuKouShi, // 外语口试
			String kaoShiLeiXing, // 考试类型
			String huKouSuoZaiDi, // 户口所在地
			String xiTongDanWei, // 系统单位
			String baoMingDanWei, // 报名单位
			String biYeLeiBie, // 毕业类别
			String kaoShengLeiBie, // 考生类别
			String minZu, // 民族
			String zhengZhiMianMao, // 政治面貌
			String zhongXueMingCheng, // 中学名称
			String zhongXueDaiMa, // 中学代码
			String zhunKaoZhengHao, // 准考证号
			String chuShengRiQi, // 出生日期
			String xuHao // 序号

	) {

		this.city  =  city  ;  
		this.cengCi  =  cengCi  ;  
		this.wenLiKe  =  wenLiKe  ;  
		this.kaoShengHao  =  kaoShengHao  ;  
		this.no  =   no  ;   
		this.name  =   name  ;   
		this.sex  =   sex  ;   
		this.kaoShengTeZheng  =   kaoShengTeZheng  ;   
		this.touDangDanWei  =  touDangDanWei  ;  
		this.waiYuYuZhong  =   waiYuYuZhong  ;   
		this.  touDangChengJi  =   touDangChengJi  ;   
		this.  yuSheJiZhunFen  =  yuSheJiZhunFen  ;  
		this.touDangZhiYuan  =   touDangZhiYuan  ;   
		this.zhuanYe1  =   zhuanYe1  ;   
		this.zhuanYe2  =  zhuanYe2  ;  
		this.zhuanYe3  =  zhuanYe3  ;  
		this.zhuanYe4  =  zhuanYe4  ;  
		this.zhuanYe5  =  zhuanYe5  ;  
		this.zhuanYe6  =  zhuanYe6  ;  
		this.tiaoJi  =   tiaoJi  ;   
		this.luQuZhuanYe  =   luQuZhuanYe  ;   
		this.tuiDangYuanYin  =   tuiDangYuanYin  ;   
		this.tiJianShouXianBiaoZhi  =   tiJianShouXianBiaoZhi  ;   
		this.tiJianJieLun  =   tiJianJieLun  ;   
		this.luQuFangShi  =   luQuFangShi  ;   
		this.tuiDangYuanYinBeiZhu  =   tuiDangYuanYinBeiZhu  ;   
		this.yuSheZhuanYe  =   yuSheZhuanYe  ;   
		this.fuCongZhuanYeLeiBie  =   fuCongZhuanYeLeiBie  ;   
		this.dingXiangTiaoJi  =   dingXiangTiaoJi  ;   
		this.zhiYuanTeZheng  =   zhiYuanTeZheng  ;   
		this.touDangDanBianHao  =   touDangDanBianHao  ;   
		this.yuSheFuJiaFen  =   yuSheFuJiaFen  ;   
		this.  dengXiaoJiZhunFen  =   dengXiaoJiZhunFen  ;   
		this.  yuanXiaoDaoRuChengJi  =   yuanXiaoDaoRuChengJi  ;   
		this.  ziGeFen  =   ziGeFen  ;   
		this.  teZhengChengJi  =   teZhengChengJi  ;   
		this.  zongFen  =   zongFen  ;   
		this.shouJianRen  =   shouJianRen  ;   
		this.zhengShenYiJian  =   zhengShenYiJian  ;   
		this.kaoShengJiangLiHeChuFen  =   kaoShengJiangLiHeChuFen  ;   
		this.kaoShengTeChang   =     kaoShengTeChang   ;     
		this.huiKaoKaoHao   =     huiKaoKaoHao   ;     
		this.lianXiDianHua   =     lianXiDianHua   ;     
		this.youZhengBianMa   =     youZhengBianMa   ;     
		this.jiaTingDiZhi   =     jiaTingDiZhi   ;     
		this.shenFenZhengHao   =     shenFenZhengHao   ;     
		this.huiKaoDengJi   =     huiKaoDengJi   ;     
		this.yingShiJuanZhong   =     yingShiJuanZhong   ;     
		this.waiYuTingLi   =     waiYuTingLi   ;     
		this.waiYuKouShi   =     waiYuKouShi   ;     
		this.kaoShiLeiXing   =     kaoShiLeiXing   ;     
		this.huKouSuoZaiDi   =     huKouSuoZaiDi   ;     
		this.xiTongDanWei   =     xiTongDanWei   ;     
		this.baoMingDanWei   =     baoMingDanWei   ;     
		this.biYeLeiBie   =     biYeLeiBie   ;     
		this.kaoShengLeiBie   =     kaoShengLeiBie   ;     
		this.minZu   =     minZu   ;     
		this.zhengZhiMianMao   =     zhengZhiMianMao   ;     
		this.zhongXueMingCheng   =     zhongXueMingCheng   ;     
		this.zhongXueDaiMa   =     zhongXueDaiMa   ;     
		this.zhunKaoZhengHao   =     zhunKaoZhengHao   ;     
		this.chuShengRiQi   =     chuShengRiQi   ;     
		this.xuHao     =   xuHao     ;   
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCengCi() {
		return cengCi;
	}

	public void setCengCi(String cengCi) {
		this.cengCi = cengCi;
	}

	public String getWenLiKe() {
		return wenLiKe;
	}

	public void setWenLiKe(String wenLiKe) {
		this.wenLiKe = wenLiKe;
	}

	public String getKaoShengHao() {
		return kaoShengHao;
	}

	public void setKaoShengHao(String kaoShengHao) {
		this.kaoShengHao = kaoShengHao;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getKaoShengTeZheng() {
		return kaoShengTeZheng;
	}

	public void setKaoShengTeZheng(String kaoShengTeZheng) {
		this.kaoShengTeZheng = kaoShengTeZheng;
	}

	public String getTouDangDanWei() {
		return touDangDanWei;
	}

	public void setTouDangDanWei(String touDangDanWei) {
		this.touDangDanWei = touDangDanWei;
	}

	public String getWaiYuYuZhong() {
		return waiYuYuZhong;
	}

	public void setWaiYuYuZhong(String waiYuYuZhong) {
		this.waiYuYuZhong = waiYuYuZhong;
	}

	public String getTouDangChengJi() {
		return touDangChengJi;
	}

	public void setTouDangChengJi(String touDangChengJi) {
		this.touDangChengJi = touDangChengJi;
	}

	public String getYuSheJiZhunFen() {
		return yuSheJiZhunFen;
	}

	public void setYuSheJiZhunFen(String yuSheJiZhunFen) {
		this.yuSheJiZhunFen = yuSheJiZhunFen;
	}

	public String getTouDangZhiYuan() {
		return touDangZhiYuan;
	}

	public void setTouDangZhiYuan(String touDangZhiYuan) {
		this.touDangZhiYuan = touDangZhiYuan;
	}

	public String getZhuanYe1() {
		return zhuanYe1;
	}

	public void setZhuanYe1(String zhuanYe1) {
		this.zhuanYe1 = zhuanYe1;
	}

	public String getZhuanYe2() {
		return zhuanYe2;
	}

	public void setZhuanYe2(String zhuanYe2) {
		this.zhuanYe2 = zhuanYe2;
	}

	public String getZhuanYe3() {
		return zhuanYe3;
	}

	public void setZhuanYe3(String zhuanYe3) {
		this.zhuanYe3 = zhuanYe3;
	}

	public String getZhuanYe4() {
		return zhuanYe4;
	}

	public void setZhuanYe4(String zhuanYe4) {
		this.zhuanYe4 = zhuanYe4;
	}

	public String getZhuanYe5() {
		return zhuanYe5;
	}

	public void setZhuanYe5(String zhuanYe5) {
		this.zhuanYe5 = zhuanYe5;
	}

	public String getZhuanYe6() {
		return zhuanYe6;
	}

	public void setZhuanYe6(String zhuanYe6) {
		this.zhuanYe6 = zhuanYe6;
	}

	public String getTiaoJi() {
		return tiaoJi;
	}

	public void setTiaoJi(String tiaoJi) {
		this.tiaoJi = tiaoJi;
	}

	public String getLuQuZhuanYe() {
		return luQuZhuanYe;
	}

	public void setLuQuZhuanYe(String luQuZhuanYe) {
		this.luQuZhuanYe = luQuZhuanYe;
	}

	public String getTuiDangYuanYin() {
		return tuiDangYuanYin;
	}

	public void setTuiDangYuanYin(String tuiDangYuanYin) {
		this.tuiDangYuanYin = tuiDangYuanYin;
	}

	public String getTiJianShouXianBiaoZhi() {
		return tiJianShouXianBiaoZhi;
	}

	public void setTiJianShouXianBiaoZhi(String tiJianShouXianBiaoZhi) {
		this.tiJianShouXianBiaoZhi = tiJianShouXianBiaoZhi;
	}

	public String getTiJianJieLun() {
		return tiJianJieLun;
	}

	public void setTiJianJieLun(String tiJianJieLun) {
		this.tiJianJieLun = tiJianJieLun;
	}

	public String getLuQuFangShi() {
		return luQuFangShi;
	}

	public void setLuQuFangShi(String luQuFangShi) {
		this.luQuFangShi = luQuFangShi;
	}

	public String getTuiDangYuanYinBeiZhu() {
		return tuiDangYuanYinBeiZhu;
	}

	public void setTuiDangYuanYinBeiZhu(String tuiDangYuanYinBeiZhu) {
		this.tuiDangYuanYinBeiZhu = tuiDangYuanYinBeiZhu;
	}

	public String getYuSheZhuanYe() {
		return yuSheZhuanYe;
	}

	public void setYuSheZhuanYe(String yuSheZhuanYe) {
		this.yuSheZhuanYe = yuSheZhuanYe;
	}

	public String getFuCongZhuanYeLeiBie() {
		return fuCongZhuanYeLeiBie;
	}

	public void setFuCongZhuanYeLeiBie(String fuCongZhuanYeLeiBie) {
		this.fuCongZhuanYeLeiBie = fuCongZhuanYeLeiBie;
	}

	public String getDingXiangTiaoJi() {
		return dingXiangTiaoJi;
	}

	public void setDingXiangTiaoJi(String dingXiangTiaoJi) {
		this.dingXiangTiaoJi = dingXiangTiaoJi;
	}

	public String getZhiYuanTeZheng() {
		return zhiYuanTeZheng;
	}

	public void setZhiYuanTeZheng(String zhiYuanTeZheng) {
		this.zhiYuanTeZheng = zhiYuanTeZheng;
	}

	public String getTouDangDanBianHao() {
		return touDangDanBianHao;
	}

	public void setTouDangDanBianHao(String touDangDanBianHao) {
		this.touDangDanBianHao = touDangDanBianHao;
	}

	public String getYuSheFuJiaFen() {
		return yuSheFuJiaFen;
	}

	public void setYuSheFuJiaFen(String yuSheFuJiaFen) {
		this.yuSheFuJiaFen = yuSheFuJiaFen;
	}

	public String getDengXiaoJiZhunFen() {
		return dengXiaoJiZhunFen;
	}

	public void setDengXiaoJiZhunFen(String dengXiaoJiZhunFen) {
		this.dengXiaoJiZhunFen = dengXiaoJiZhunFen;
	}

	public String getYuanXiaoDaoRuChengJi() {
		return yuanXiaoDaoRuChengJi;
	}

	public void setYuanXiaoDaoRuChengJi(String yuanXiaoDaoRuChengJi) {
		this.yuanXiaoDaoRuChengJi = yuanXiaoDaoRuChengJi;
	}

	public String getZiGeFen() {
		return ziGeFen;
	}

	public void setZiGeFen(String ziGeFen) {
		this.ziGeFen = ziGeFen;
	}

	public String getTeZhengChengJi() {
		return teZhengChengJi;
	}

	public void setTeZhengChengJi(String teZhengChengJi) {
		this.teZhengChengJi = teZhengChengJi;
	}

	public String getZongFen() {
		return zongFen;
	}

	public void setZongFen(String zongFen) {
		this.zongFen = zongFen;
	}

	public String getShouJianRen() {
		return shouJianRen;
	}

	public void setShouJianRen(String shouJianRen) {
		this.shouJianRen = shouJianRen;
	}

	public String getZhengShenYiJian() {
		return zhengShenYiJian;
	}

	public void setZhengShenYiJian(String zhengShenYiJian) {
		this.zhengShenYiJian = zhengShenYiJian;
	}

	public String getKaoShengJiangLiHeChuFen() {
		return kaoShengJiangLiHeChuFen;
	}

	public void setKaoShengJiangLiHeChuFen(String kaoShengJiangLiHeChuFen) {
		this.kaoShengJiangLiHeChuFen = kaoShengJiangLiHeChuFen;
	}

	public String getKaoShengTeChang() {
		return kaoShengTeChang;
	}

	public void setKaoShengTeChang(String kaoShengTeChang) {
		this.kaoShengTeChang = kaoShengTeChang;
	}

	public String getHuiKaoKaoHao() {
		return huiKaoKaoHao;
	}

	public void setHuiKaoKaoHao(String huiKaoKaoHao) {
		this.huiKaoKaoHao = huiKaoKaoHao;
	}

	public String getLianXiDianHua() {
		return lianXiDianHua;
	}

	public void setLianXiDianHua(String lianXiDianHua) {
		this.lianXiDianHua = lianXiDianHua;
	}

	public String getYouZhengBianMa() {
		return youZhengBianMa;
	}

	public void setYouZhengBianMa(String youZhengBianMa) {
		this.youZhengBianMa = youZhengBianMa;
	}

	public String getJiaTingDiZhi() {
		return jiaTingDiZhi;
	}

	public void setJiaTingDiZhi(String jiaTingDiZhi) {
		this.jiaTingDiZhi = jiaTingDiZhi;
	}

	public String getShenFenZhengHao() {
		return shenFenZhengHao;
	}

	public void setShenFenZhengHao(String shenFenZhengHao) {
		this.shenFenZhengHao = shenFenZhengHao;
	}

	public String getHuiKaoDengJi() {
		return huiKaoDengJi;
	}

	public void setHuiKaoDengJi(String huiKaoDengJi) {
		this.huiKaoDengJi = huiKaoDengJi;
	}

	public String getYingShiJuanZhong() {
		return yingShiJuanZhong;
	}

	public void setYingShiJuanZhong(String yingShiJuanZhong) {
		this.yingShiJuanZhong = yingShiJuanZhong;
	}

	public String getWaiYuTingLi() {
		return waiYuTingLi;
	}

	public void setWaiYuTingLi(String waiYuTingLi) {
		this.waiYuTingLi = waiYuTingLi;
	}

	public String getWaiYuKouShi() {
		return waiYuKouShi;
	}

	public void setWaiYuKouShi(String waiYuKouShi) {
		this.waiYuKouShi = waiYuKouShi;
	}

	public String getKaoShiLeiXing() {
		return kaoShiLeiXing;
	}

	public void setKaoShiLeiXing(String kaoShiLeiXing) {
		this.kaoShiLeiXing = kaoShiLeiXing;
	}

	public String getHuKouSuoZaiDi() {
		return huKouSuoZaiDi;
	}

	public void setHuKouSuoZaiDi(String huKouSuoZaiDi) {
		this.huKouSuoZaiDi = huKouSuoZaiDi;
	}

	public String getXiTongDanWei() {
		return xiTongDanWei;
	}

	public void setXiTongDanWei(String xiTongDanWei) {
		this.xiTongDanWei = xiTongDanWei;
	}

	public String getBaoMingDanWei() {
		return baoMingDanWei;
	}

	public void setBaoMingDanWei(String baoMingDanWei) {
		this.baoMingDanWei = baoMingDanWei;
	}

	public String getBiYeLeiBie() {
		return biYeLeiBie;
	}

	public void setBiYeLeiBie(String biYeLeiBie) {
		this.biYeLeiBie = biYeLeiBie;
	}

	public String getKaoShengLeiBie() {
		return kaoShengLeiBie;
	}

	public void setKaoShengLeiBie(String kaoShengLeiBie) {
		this.kaoShengLeiBie = kaoShengLeiBie;
	}

	public String getMinZu() {
		return minZu;
	}

	public void setMinZu(String minZu) {
		this.minZu = minZu;
	}

	public String getZhengZhiMianMao() {
		return zhengZhiMianMao;
	}

	public void setZhengZhiMianMao(String zhengZhiMianMao) {
		this.zhengZhiMianMao = zhengZhiMianMao;
	}

	public String getZhongXueMingCheng() {
		return zhongXueMingCheng;
	}

	public void setZhongXueMingCheng(String zhongXueMingCheng) {
		this.zhongXueMingCheng = zhongXueMingCheng;
	}

	public String getZhongXueDaiMa() {
		return zhongXueDaiMa;
	}

	public void setZhongXueDaiMa(String zhongXueDaiMa) {
		this.zhongXueDaiMa = zhongXueDaiMa;
	}

	public String getZhunKaoZhengHao() {
		return zhunKaoZhengHao;
	}

	public void setZhunKaoZhengHao(String zhunKaoZhengHao) {
		this.zhunKaoZhengHao = zhunKaoZhengHao;
	}

	public String getChuShengRiQi() {
		return chuShengRiQi;
	}

	public void setChuShengRiQi(String chuShengRiQi) {
		this.chuShengRiQi = chuShengRiQi;
	}

	public String getXuHao() {
		return xuHao;
	}

	public void setXuHao(String xuHao) {
		this.xuHao = xuHao;
	}

	public String getBaoDao() {
		return baoDao;
	}

	public void setBaoDao(String baoDao) {
		this.baoDao = baoDao;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
