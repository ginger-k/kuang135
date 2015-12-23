package com.kuang135.frame.easyui.util;

import java.util.ArrayList;
import java.util.List;

import com.kuang135.frame.easyui.bean.Tree;



public class TreeUtil {

	/**
	 * 将所有没有层级关系的Tree 组织成有层级关系的Tree
	 * @param beans 所有单个Tree
	 * @param topPid 最顶层Tree的pid
	 * @return
	 */
	public static List<Tree> recurse2tree(List<Tree> beans, String topPid) {
		ArrayList<Tree> list = new ArrayList<Tree>();
		if (beans == null || beans.size() == 0)
			return list;
		for (Tree bean : beans) {
			if (topPid == null) {
				if (bean.getPid() == null){
					list.add(bean);
				}
			} else {
				if (topPid.equals(bean.getPid())) {
					list.add(bean);
				}
			}
		}
		beans.removeAll(list);
		for (Tree topBean : list) {
			setChildren2ParentBean(beans, topBean);
		}
		return list;
	}
	private static void setChildren2ParentBean(List<Tree> beans, Tree parent) {
		if (beans == null || beans.size() == 0 || parent == null)
			return;
		List<Tree> tmp = new ArrayList<Tree>();
		for (Tree bean : beans) {
			if ((parent.getId()).equals(bean.getPid())) {
				tmp.add(bean);
			}
		}
		beans.removeAll(tmp);
		parent.setChildren(tmp);
		if (tmp.size()>0) {
			for (Tree p : tmp) {
				setChildren2ParentBean(beans, p);
			}
		}
	}
}
