package cn.syslisten.action;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import cn.syslisten.pojo.MonitorInfo;
import cn.syslisten.service.MonitorInfoService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MonitorInfoAction extends ActionSupport implements RequestAware,
		ParameterAware {
	private Logger log = Logger.getLogger(MonitorInfoAction.class);
	private Map<String, Object> request;
	private Map<String, String[]> params;
	private MonitorInfoService monitorInfoService;
	private String rootPath;
	
	public MonitorInfoAction(){
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		this.rootPath = sc.getRealPath("/");
	}
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setParameters(Map<String, String[]> params) {
		this.params = params;
	}

	public void setMonitorInfoService(MonitorInfoService monitorInfoService) {
		this.monitorInfoService = monitorInfoService;
	}

	public String query() {
		long maxId = monitorInfoService.getMaxId();
		List<MonitorInfo> infos = monitorInfoService.query();
		// request.put("maxId", maxId);
		request.put("infos", infos);
		return "query";
	}

	/**
	 * 生成cpu占用率图
	 * @return
	 */
	public String showCpu() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();// 折线图数据
		List<MonitorInfo> infos = monitorInfoService.query(); // 查询数据库
		Collections.reverse(infos);
		for (MonitorInfo monitorInfo : infos) {
			lineDataset.addValue(monitorInfo.getCpuRatio(), "cpu占用率",
								monitorInfo.getSavedTime().toString().substring(11, 19));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart("cpu占用率-时间图", "时刻/hh:mm:ss",// 目录轴的显示标签
				"cpu占用率/%", // 数值轴的显示标签
				lineDataset,// 数据集
				PlotOrientation.VERTICAL,// 图表方向：水平、垂直
				false,// 是否显示图例(对于简单的柱状图必须是false)
				true,// 是否生成工具
				false);// 是否生成URL链接
		jfreechart.getTitle().setFont(new Font("隶书", Font.BOLD, 26));// 设置title标题
		jfreechart.setBackgroundPaint(new Color(255, 255, 255));// 设置背景色
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();// 图本身

		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("黑体", Font.ITALIC, 16)); // 设置数据字体(纵轴)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("黑体", Font.ITALIC, 16)); // 设置时字体（横轴）
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		FileOutputStream fos_jpg = null;
		String imagePath = "";
		try {
			imagePath = rootPath + "image/cpu.jpeg";
			log.info("imagePath="+imagePath);
			fos_jpg = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsJPEG(fos_jpg, jfreechart, 2000, 700);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 图片相对路径
		request.put("image", "image/cpu.jpeg");
		return "show";
	}

	/**
	 * 生成内存占用率图
	 * @return
	 */
	public String showMemory() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();// 折线图数据
		List<MonitorInfo> infos = monitorInfoService.query(); // 查询数据库
		Collections.reverse(infos);
		for (MonitorInfo monitorInfo : infos) {
			lineDataset.addValue(monitorInfo.getMemoryPercent(), "内存占用率",
								monitorInfo.getSavedTime().toString().substring(11, 19));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart("内存占用率-时间图", "时刻/hh:mm:ss",// 目录轴的显示标签
				"内存占用率/%", // 数值轴的显示标签
				lineDataset,// 数据集
				PlotOrientation.VERTICAL,// 图表方向：水平、垂直
				false,// 是否显示图例(对于简单的柱状图必须是false)
				true,// 是否生成工具
				false);// 是否生成URL链接
		jfreechart.getTitle().setFont(new Font("隶书", Font.BOLD, 26));// 设置title标题
		jfreechart.setBackgroundPaint(new Color(255, 255, 255));// 设置背景色
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();// 图本身

		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("黑体", Font.ITALIC, 16)); // 设置数据字体(纵轴)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("黑体", Font.ITALIC, 16)); // 设置时字体（横轴）
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		FileOutputStream fos_jpg = null;
		String imagePath = "";
		try {
			imagePath = rootPath + "image/memory.jpeg";
			fos_jpg = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsJPEG(fos_jpg, jfreechart, 2000, 700);
		} catch (Exception e) {
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
			}
		}
		// 图片相对路径
		request.put("image", "image/memory.jpeg");
		return "show";
	}
	
	/**
	 * 生成硬盘占用率图
	 * @return
	 */
	public String showDisc() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();// 折线图数据
		List<MonitorInfo> infos = monitorInfoService.query(); // 查询数据库
		Collections.reverse(infos);
		for (MonitorInfo monitorInfo : infos) {
			lineDataset.addValue(monitorInfo.getDiscUsage(), "硬盘占用率",
								monitorInfo.getSavedTime().toString().substring(11, 19));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart("硬盘占用率-时间图", "时刻/hh:mm:ss",// 目录轴的显示标签
				"硬盘占用率/%", // 数值轴的显示标签
				lineDataset,// 数据集
				PlotOrientation.VERTICAL,// 图表方向：水平、垂直
				false,// 是否显示图例(对于简单的柱状图必须是false)
				true,// 是否生成工具
				false);// 是否生成URL链接
		jfreechart.getTitle().setFont(new Font("隶书", Font.BOLD, 26));// 设置title标题
		jfreechart.setBackgroundPaint(new Color(255, 255, 255));// 设置背景色
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();// 图本身

		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("黑体", Font.ITALIC, 16)); // 设置数据字体(纵轴)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("黑体", Font.ITALIC, 16)); // 设置时字体（横轴）
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		FileOutputStream fos_jpg = null;
		String imagePath = "";
		try {
			imagePath = rootPath + "image/disc.jpeg";
			fos_jpg = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsJPEG(fos_jpg, jfreechart, 2000, 700);
		} catch (Exception e) {
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
			}
		}
		// 图片相对路径
		request.put("image", "image/disc.jpeg");
		return "show";
	}
	
	/**
	 * 生成接收字节数图
	 * @return
	 */
	public String showRxBytes() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();// 折线图数据
		List<MonitorInfo> infos = monitorInfoService.query(); // 查询数据库
		Collections.reverse(infos);
		for (MonitorInfo monitorInfo : infos) {
			lineDataset.addValue(monitorInfo.getRxBytes(), "接收字节数",
								monitorInfo.getSavedTime().toString().substring(11, 19));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart("网络：接收字节数-时间图", "时刻/hh:mm:ss",// 目录轴的显示标签
				"接收字节数/Byte", // 数值轴的显示标签
				lineDataset,// 数据集
				PlotOrientation.VERTICAL,// 图表方向：水平、垂直
				false,// 是否显示图例(对于简单的柱状图必须是false)
				true,// 是否生成工具
				false);// 是否生成URL链接
		jfreechart.getTitle().setFont(new Font("隶书", Font.BOLD, 26));// 设置title标题
		jfreechart.setBackgroundPaint(new Color(255, 255, 255));// 设置背景色
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();// 图本身

		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("黑体", Font.ITALIC, 16)); // 设置数据字体(纵轴)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("黑体", Font.ITALIC, 16)); // 设置时字体（横轴）
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		FileOutputStream fos_jpg = null;
		String imagePath = "";
		try {
			imagePath = rootPath + "image/rxbytes.jpeg";
			fos_jpg = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsJPEG(fos_jpg, jfreechart, 2000, 700);
		} catch (Exception e) {
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
			}
		}
		// 图片相对路径
		request.put("image", "image/rxbytes.jpeg");
		return "show";
	}
	
	/**
	 * 生成发送字节数图
	 * @return
	 */
	public String showTxBytes() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();// 折线图数据
		List<MonitorInfo> infos = monitorInfoService.query(); // 查询数据库
		Collections.reverse(infos);
		for (MonitorInfo monitorInfo : infos) {
			lineDataset.addValue(monitorInfo.getTxBytes(), "发送字节数",
								monitorInfo.getSavedTime().toString().substring(11, 19));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart("网络：发送字节数-时间图", "时刻/hh:mm:ss",// 目录轴的显示标签
				"发送字节数/Byte", // 数值轴的显示标签
				lineDataset,// 数据集
				PlotOrientation.VERTICAL,// 图表方向：水平、垂直
				false,// 是否显示图例(对于简单的柱状图必须是false)
				true,// 是否生成工具
				false);// 是否生成URL链接
		jfreechart.getTitle().setFont(new Font("隶书", Font.BOLD, 26));// 设置title标题
		jfreechart.setBackgroundPaint(new Color(255, 255, 255));// 设置背景色
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();// 图本身

		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("黑体", Font.ITALIC, 16)); // 设置数据字体(纵轴)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("黑体", Font.ITALIC, 16)); // 设置时字体（横轴）
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		FileOutputStream fos_jpg = null;
		String imagePath = "";
		try {
			imagePath = rootPath + "image/txbytes.jpeg";
			fos_jpg = new FileOutputStream(imagePath);
			ChartUtilities.writeChartAsJPEG(fos_jpg, jfreechart, 2000, 700);
		} catch (Exception e) {
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
			}
		}
		// 图片相对路径
		request.put("image", "image/txbytes.jpeg");
		return "show";
	}
	
	public String save() {
		log.info("save方法info");
		MonitorInfo monitorInfo = null;
		try {
			monitorInfo = monitorInfoService.getMonitorInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}

		monitorInfoService.save(monitorInfo);
		request.put("message", "保存系统监视数据成功");
		return "query";
	}

}
