package cn.com.forum.service.impl;

import java.util.List;

import cn.com.forum.dao.ThemeDao;
import cn.com.forum.dao.impl.ThemeDaoImpl;
import cn.com.forum.pojo.Theme;
import cn.com.forum.service.ThemeService;

public class ThemeServiceImpl implements ThemeService {

	private ThemeDao themeDao = new ThemeDaoImpl();
	@Override
	public void save(Theme theme) {
		themeDao.save(theme);
		
	}
	@Override
	public List<Theme> query() {
		List<Theme> themeList = themeDao.query();
		return themeList;
	}
	@Override
	public Theme get(Long id) {
		Theme theme = themeDao.get(id);
		return theme;
	}
	@Override
	public void update(Theme theme) {
		themeDao.update(theme);
	}

}
