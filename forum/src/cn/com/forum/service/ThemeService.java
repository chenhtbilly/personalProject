package cn.com.forum.service;

import java.util.List;

import cn.com.forum.pojo.Theme;

public interface ThemeService {

	public void save(Theme theme);

	public List<Theme> query();

	public Theme get(Long id);

	public void update(Theme theme);

}
