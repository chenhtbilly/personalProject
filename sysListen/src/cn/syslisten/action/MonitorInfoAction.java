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
	 * ����cpuռ����ͼ
	 * @return
	 */
	public String showCpu() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();// ����ͼ����
		List<MonitorInfo> infos = monitorInfoService.query(); // ��ѯ���ݿ�
		Collections.reverse(infos);
		for (MonitorInfo monitorInfo : infos) {
			lineDataset.addValue(monitorInfo.getCpuRatio(), "cpuռ����",
								monitorInfo.getSavedTime().toString().substring(11, 19));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart("cpuռ����-ʱ��ͼ", "ʱ��/hh:mm:ss",// Ŀ¼�����ʾ��ǩ
				"cpuռ����/%", // ��ֵ�����ʾ��ǩ
				lineDataset,// ���ݼ�
				PlotOrientation.VERTICAL,// ͼ����ˮƽ����ֱ
				false,// �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				true,// �Ƿ����ɹ���
				false);// �Ƿ�����URL����
		jfreechart.getTitle().setFont(new Font("����", Font.BOLD, 26));// ����title����
		jfreechart.setBackgroundPaint(new Color(255, 255, 255));// ���ñ���ɫ
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();// ͼ����

		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("����", Font.ITALIC, 16)); // ������������(����)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("����", Font.ITALIC, 16)); // ����ʱ���壨���ᣩ
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
		// ͼƬ���·��
		request.put("image", "image/cpu.jpeg");
		return "show";
	}

	/**
	 * �����ڴ�ռ����ͼ
	 * @return
	 */
	public String showMemory() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();// ����ͼ����
		List<MonitorInfo> infos = monitorInfoService.query(); // ��ѯ���ݿ�
		Collections.reverse(infos);
		for (MonitorInfo monitorInfo : infos) {
			lineDataset.addValue(monitorInfo.getMemoryPercent(), "�ڴ�ռ����",
								monitorInfo.getSavedTime().toString().substring(11, 19));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart("�ڴ�ռ����-ʱ��ͼ", "ʱ��/hh:mm:ss",// Ŀ¼�����ʾ��ǩ
				"�ڴ�ռ����/%", // ��ֵ�����ʾ��ǩ
				lineDataset,// ���ݼ�
				PlotOrientation.VERTICAL,// ͼ����ˮƽ����ֱ
				false,// �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				true,// �Ƿ����ɹ���
				false);// �Ƿ�����URL����
		jfreechart.getTitle().setFont(new Font("����", Font.BOLD, 26));// ����title����
		jfreechart.setBackgroundPaint(new Color(255, 255, 255));// ���ñ���ɫ
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();// ͼ����

		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("����", Font.ITALIC, 16)); // ������������(����)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("����", Font.ITALIC, 16)); // ����ʱ���壨���ᣩ
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
		// ͼƬ���·��
		request.put("image", "image/memory.jpeg");
		return "show";
	}
	
	/**
	 * ����Ӳ��ռ����ͼ
	 * @return
	 */
	public String showDisc() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();// ����ͼ����
		List<MonitorInfo> infos = monitorInfoService.query(); // ��ѯ���ݿ�
		Collections.reverse(infos);
		for (MonitorInfo monitorInfo : infos) {
			lineDataset.addValue(monitorInfo.getDiscUsage(), "Ӳ��ռ����",
								monitorInfo.getSavedTime().toString().substring(11, 19));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart("Ӳ��ռ����-ʱ��ͼ", "ʱ��/hh:mm:ss",// Ŀ¼�����ʾ��ǩ
				"Ӳ��ռ����/%", // ��ֵ�����ʾ��ǩ
				lineDataset,// ���ݼ�
				PlotOrientation.VERTICAL,// ͼ����ˮƽ����ֱ
				false,// �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				true,// �Ƿ����ɹ���
				false);// �Ƿ�����URL����
		jfreechart.getTitle().setFont(new Font("����", Font.BOLD, 26));// ����title����
		jfreechart.setBackgroundPaint(new Color(255, 255, 255));// ���ñ���ɫ
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();// ͼ����

		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("����", Font.ITALIC, 16)); // ������������(����)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("����", Font.ITALIC, 16)); // ����ʱ���壨���ᣩ
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
		// ͼƬ���·��
		request.put("image", "image/disc.jpeg");
		return "show";
	}
	
	/**
	 * ���ɽ����ֽ���ͼ
	 * @return
	 */
	public String showRxBytes() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();// ����ͼ����
		List<MonitorInfo> infos = monitorInfoService.query(); // ��ѯ���ݿ�
		Collections.reverse(infos);
		for (MonitorInfo monitorInfo : infos) {
			lineDataset.addValue(monitorInfo.getRxBytes(), "�����ֽ���",
								monitorInfo.getSavedTime().toString().substring(11, 19));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart("���磺�����ֽ���-ʱ��ͼ", "ʱ��/hh:mm:ss",// Ŀ¼�����ʾ��ǩ
				"�����ֽ���/Byte", // ��ֵ�����ʾ��ǩ
				lineDataset,// ���ݼ�
				PlotOrientation.VERTICAL,// ͼ����ˮƽ����ֱ
				false,// �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				true,// �Ƿ����ɹ���
				false);// �Ƿ�����URL����
		jfreechart.getTitle().setFont(new Font("����", Font.BOLD, 26));// ����title����
		jfreechart.setBackgroundPaint(new Color(255, 255, 255));// ���ñ���ɫ
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();// ͼ����

		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("����", Font.ITALIC, 16)); // ������������(����)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("����", Font.ITALIC, 16)); // ����ʱ���壨���ᣩ
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
		// ͼƬ���·��
		request.put("image", "image/rxbytes.jpeg");
		return "show";
	}
	
	/**
	 * ���ɷ����ֽ���ͼ
	 * @return
	 */
	public String showTxBytes() {
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();// ����ͼ����
		List<MonitorInfo> infos = monitorInfoService.query(); // ��ѯ���ݿ�
		Collections.reverse(infos);
		for (MonitorInfo monitorInfo : infos) {
			lineDataset.addValue(monitorInfo.getTxBytes(), "�����ֽ���",
								monitorInfo.getSavedTime().toString().substring(11, 19));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart("���磺�����ֽ���-ʱ��ͼ", "ʱ��/hh:mm:ss",// Ŀ¼�����ʾ��ǩ
				"�����ֽ���/Byte", // ��ֵ�����ʾ��ǩ
				lineDataset,// ���ݼ�
				PlotOrientation.VERTICAL,// ͼ����ˮƽ����ֱ
				false,// �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				true,// �Ƿ����ɹ���
				false);// �Ƿ�����URL����
		jfreechart.getTitle().setFont(new Font("����", Font.BOLD, 26));// ����title����
		jfreechart.setBackgroundPaint(new Color(255, 255, 255));// ���ñ���ɫ
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();// ͼ����

		ValueAxis valueAxis = categoryplot.getRangeAxis();
		valueAxis.setLabelFont(new Font("����", Font.ITALIC, 16)); // ������������(����)
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLabelFont(new Font("����", Font.ITALIC, 16)); // ����ʱ���壨���ᣩ
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
		// ͼƬ���·��
		request.put("image", "image/txbytes.jpeg");
		return "show";
	}
	
	public String save() {
		log.info("save����info");
		MonitorInfo monitorInfo = null;
		try {
			monitorInfo = monitorInfoService.getMonitorInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}

		monitorInfoService.save(monitorInfo);
		request.put("message", "����ϵͳ�������ݳɹ�");
		return "query";
	}

}
