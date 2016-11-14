package cn.aynu.manage.dao;

import java.util.List;

public class Pagebean {
	private List list;
	private int allrow;// ������
	private int allpage;// ��ҳ��
	private int currentpage;// ��ǰҳ
	private int pagesize;// ÿҳ����

	private boolean isfirstpage;// �Ƿ�Ϊ��һҳ
	private boolean islastpage;// �Ƿ�Ϊ���һҳ
	private boolean hasqianpage;// �Ƿ���ǰһҳ
	private boolean hasnextpage;// �Ƿ�����һҳ

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getAllrow() {
		return allrow;
	}

	public void setAllrow(int allrow) {
		this.allrow = allrow;
	}

	public int getAllpage() {
		return allpage;
	}

	public void setAllpage(int allpage) {
		this.allpage = allpage;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public boolean isIsfirstpage() {
		return (currentpage == 1);
	}

	public boolean isIslastpage() {

		return (currentpage == allpage);
	}

	public boolean isHasqianpage() {
		return (currentpage != 1);
	}

	public boolean isHasnextpage() {
		return (currentpage != allpage);
	}

	public void init() {

		this.isfirstpage = isIsfirstpage();
		this.islastpage = isIslastpage();
		this.hasqianpage = isHasqianpage();
		this.hasnextpage = isHasnextpage();
	}

	// �õ���ҳ��
	public int allPage(final int pagesize, final int allrow) {// ���ʺ�֮ǰ��Ϊ���������������Ϊ�棬�򷵻طֺ�֮ǰ��ֵ�����򷵻طֺ�֮���ֵ
		int allpage = allrow % pagesize == 0 ? allrow / pagesize : allrow / pagesize + 1;
		return allpage;
	}

	// �õ���ǰҳ��һ�������ݿ��е�λ��
	public int jilushu(final int pagesize, final int currentpage) {
		int jilushu = (currentpage - 1) * pagesize;
		return jilushu;
	}

	// �õ���ǰҳ
	public int currentpage(int page) {
		// ��ʼֵΪ1
		final int currentpage = (page == 0 ? 1 : page);
		return currentpage;
	}

}
