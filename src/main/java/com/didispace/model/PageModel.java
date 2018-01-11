package com.didispace.model;

public class PageModel {
	
	private int id;//商品id
	
	private String name;//商品名
	
	private String leftImage;//商品小图
	
	private String desc;//商品描述
	
	private String date;//上架时间
	
	private String type;//商品分类
	
	private int saleNum;//月销售量
	
    private int rank;//商品等级

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeftImage() {
		return leftImage;
	}

	public void setLeftImage(String leftImage) {
		this.leftImage = leftImage;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
    
    
	

}
