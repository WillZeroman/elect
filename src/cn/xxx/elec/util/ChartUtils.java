package cn.xxx.elec.util;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;

import cn.xxx.elec.web.vo.ElecUserForm;

public class ChartUtils {
	/**  
	* @Name: getUserBarChart
	* @Description: 
	* @Author: wei
	* @Version: V1.00 
	* @Create Date: 2015-06-16
	* @Parameters: List<ElecUserForm> list
	* @Return: String 生成图片名称
	*/
	public static String getUserBarChart(List<ElecUserForm> list) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i=0;list!=null&&i<list.size();i++){
			ElecUserForm elecUserForm = list.get(i);
			dataset.addValue(Integer.parseInt(elecUserForm.getJctcount()), "所属单位", elecUserForm.getJctname());
		}
		JFreeChart chart = ChartFactory.createBarChart3D("用户报表", //主标题
									"所属单位名称", //x轴
									"数量", //y轴
									dataset,//图表数据集
									PlotOrientation.VERTICAL,//图像显示方式（水平 or 垂直）
									true,//是否显示子标题
									true,//是否生成提示标签
									true //是否产生url
									);
		//乱码问题
		//主标题乱码
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));
		//子标题乱码
		chart.getLegend().setItemFont(new Font("宋体",Font.BOLD,12));
		//
		CategoryPlot caregoryPlot =  (CategoryPlot) chart.getPlot();
		//处理x轴乱码
		CategoryAxis3D categoryAxis3D = (CategoryAxis3D) caregoryPlot.getDomainAxis();
		categoryAxis3D.setLabelFont(new Font("宋体",Font.BOLD,15));
		categoryAxis3D.setTickLabelFont(new Font("宋体",Font.BOLD,15));
		//处理y轴乱码
		NumberAxis3D numberAxis3D = (NumberAxis3D) caregoryPlot.getRangeAxis();
		numberAxis3D.setLabelFont(new Font("宋体",Font.BOLD,15));
		numberAxis3D.setTickLabelFont(new Font("宋体",Font.BOLD,15));
		
		//处理Y轴上显示的刻度，以2作为2格
		numberAxis3D.setAutoTickUnitSelection(false);
		NumberTickUnit unit = new NumberTickUnit(2);
		numberAxis3D.setTickUnit(unit);
		
		//绘图区
		BarRenderer3D barRenderer3D = (BarRenderer3D) caregoryPlot.getRenderer();
		//柱状图宽度
		barRenderer3D.setMaximumBarWidth(0.07);
		//地图上显示数字
		barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer3D.setBaseItemLabelsVisible(true);
		barRenderer3D.setBaseItemLabelFont(new Font("宋体",Font.BOLD,12));
		
		String realDir = ServletActionContext.getServletContext().getRealPath("/chart");
		String filename = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
		File file = new File(realDir + "\\" + filename);
		try {
			ChartUtilities.saveChartAsJPEG(file, chart,800, 600);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filename;
	}

}
