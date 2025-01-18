package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.HoaDon_DAO;

public class ThongKe_DoanhThu_UI extends JPanel {
	private JPanel panelTKThang;
	private JTextField tongDoanhThuTxt;
	private JComboBox<Integer> thangComboBox;
	private double tongDoanhThuThang;
	private HoaDon_DAO hoaDon_DAO;
	
	public ThongKe_DoanhThu_UI() {
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		setBackground(Color.white);
		
		JPanel northPanel = new JPanel();
		northPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		northPanel.setLayout(new GridLayout(2, 1));
		northPanel.setBackground(Color.white);
		
		JLabel thongKeDoanhThuLabel = new JLabel("Thống Kê Doanh Thu Theo Tháng", SwingConstants.CENTER);
		thongKeDoanhThuLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
			
		panelTKThang = new JPanel();
		panelTKThang.setBackground(Color.white);
		
		JPanel thangPanel =  new JPanel();
		thangPanel.setBackground(Color.white);
		JLabel thangLabel = new JLabel("Chọn Tháng:");
        Integer thang[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        thangComboBox = new JComboBox<Integer>(thang);
        thangComboBox.setPreferredSize(new Dimension(50, 20));
        thangComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				thongKeThang();
			}
		});
        
       
		thangPanel.add(thangLabel);
		thangPanel.add(thangComboBox);
		
		JPanel tongDoanhThuPanel = new JPanel();
		tongDoanhThuPanel.setBackground(Color.white);
		JLabel tongDoanhThuLabel = new JLabel("Tổng Doanh Thu:");
		tongDoanhThuTxt = new JTextField(10);
		tongDoanhThuTxt.setEditable(false);
		tongDoanhThuPanel.add(tongDoanhThuLabel);
		tongDoanhThuPanel.add(tongDoanhThuTxt);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.white);
		controlPanel.setLayout(new GridLayout(1, 2));
		controlPanel.add(thangPanel);
		controlPanel.add(tongDoanhThuPanel);
		
		northPanel.add(thongKeDoanhThuLabel);
		northPanel.add(controlPanel);
		
		thongKeThang();
		
		add(BorderLayout.NORTH, northPanel);
		add(BorderLayout.CENTER, panelTKThang);
	}
	
	  private JFreeChart showChartNull() {
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        for (int i = 1; i < 31; i++) {
	            String ngay = String.valueOf(i);
	            long tt = 0;
	            dataset.addValue(tt, "Tổng tiền", ngay);
	        }
	        JFreeChart barChart = ChartFactory.createBarChart("", "Ngày",
	                "Tổng tiền (VND)", dataset, PlotOrientation.VERTICAL, true, true, false);
	        
	        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
	        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setRange(0, 5000000);
	        
	        return barChart;
	        
	    }
	  
	  private void thongKeThang() {
	        panelTKThang.revalidate();
	        panelTKThang.repaint();
	        panelTKThang.removeAll();
	        
	        hoaDon_DAO = new HoaDon_DAO();
	      
	        int soHoaDon = hoaDon_DAO.tongSoHoaDonTheoThang(Integer.parseInt(thangComboBox.getSelectedItem().toString()));
	        
	        if (soHoaDon == 0) {
	            ChartPanel chartPanelNull = new ChartPanel(showChartNull());
	            chartPanelNull.setPreferredSize(new Dimension(1150, 450));
	            panelTKThang.add(chartPanelNull);
	        } else {
		        ChartPanel chartPanel = new ChartPanel(createChartThang());
		        chartPanel.setPreferredSize(new Dimension(1150, 450));
		        tongDoanhThuTxt.setText(String.format("%,.0f" + " VNĐ", tongDoanhThuThang));
		        panelTKThang.add(chartPanel);
	        }   
	  }
	  
	  private JFreeChart createChartThang() {
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        int month = thangComboBox.getSelectedIndex() + 1;
	        int n = 32;
	        Calendar c = Calendar.getInstance();
	        int currentYear = c.get(Calendar.YEAR);
	        
	        n = switch (month) {
	            case 1 -> 32;
	            case 2 -> ((currentYear % 4 == 0 || (currentYear % 100 != 0 && currentYear % 400 == 0)) && month == 2) ? 30 : 29;
	            case 3 -> 32;
	            case 5 -> 32;
	            case 7 -> 32;
	            case 8 -> 32;
	            case 10 -> 32;
	            case 12 -> 32;
	            default -> 31;
	        };
	        double tt = 0;
	        
	        hoaDon_DAO = new HoaDon_DAO();
	        for (int i = 1; i < n; i++) {
	            String ngay = String.valueOf(i);
	            tt = hoaDon_DAO.tongDoanhThuTheoTungNgayTrongThang(i, month);
	            tongDoanhThuThang += tt;
	            dataset.addValue(tt, "Tổng tiền", ngay);
	        }
	        JFreeChart barChart = ChartFactory.createBarChart("", "Ngày", "Tổng tiền (VND)", dataset, PlotOrientation.VERTICAL, true, true, false);
	        
	        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
	        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setRange(0, 5000000);
	        
	        return barChart;
	    }
}
