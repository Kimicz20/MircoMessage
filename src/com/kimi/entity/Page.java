package com.kimi.entity;
/**
 * ��ҳʵ����
 * @author geek
 *
 */
public class Page {

	/**
	 * ������
	 */
	private int totalNumber;
	
	/**
	 * ��ǰҳ��
	 */
	private int currentPage;
	
	/**
	 * ��ҳ��
	 */
	private int totalPage;
	
	/**
	 * ÿҳ��ʾ����
	 */
	private int pageNumber = 5;
	
	/**
	 * ���ݿ���limit�Ĳ������ӵڼ�����ʼȡ
	 */
	private int dbIndex;
	
	/**
	 * ���ݿ���limit�Ĳ�����һ��ȡ������
	 */
	private int dbNumber;
	
	/**
	 * ���㵱ǰҳ�� �Լ��������ݿ����
	 */
	public void count(){
		
		//1.������ҳ��
		int totalPageTemp = this.totalNumber / this.pageNumber;
		int plus = (this.totalNumber % this.pageNumber) == 0 ? 0:1;
		totalPageTemp += plus;
		if(totalPageTemp <= 0){
			totalPageTemp = 1;
		}
		this.totalPage = totalPageTemp;
		
		//2.���õ�ǰҳ��
		if(this.totalPage < this.currentPage){
			this.currentPage = this.totalPage;
		}
		if(this.currentPage < 1){
			this.currentPage = 1;
		}
		
		//3.����limit�Ĳ���
		this.dbIndex = (this.currentPage -1) * this.pageNumber;
		this.dbNumber = this.pageNumber;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
		this.count();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	public int getDbNumber() {
		return dbNumber;
	}

	public void setDbNumber(int dbNumber) {
		this.dbNumber = dbNumber;
	}

}
