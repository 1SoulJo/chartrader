package ui.frame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

/**
 * Chart frame.
 */
public class Chart extends JInternalFrame {
    public Chart() {
        init();
    }

    private void init() {
        setTitle("Chart");
        setBackground(Color.GRAY);

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setVisible(true);

        ChartPanel ch = new ChartPanel(getChart());
        add(ch, BorderLayout.CENTER);
    }

    public JFreeChart getChart() {
        // ToDo : connect data provider to chart

        // Test data
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();                // bar chart 1
        DefaultCategoryDataset dataset12 = new DefaultCategoryDataset();         // bar chart 2
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();                // line chart 1

        // Data setup
        // Graph 1
        dataset1.addValue(1.0, "S1", "Jan");
        dataset1.addValue(4.0, "S1", "Feb");
        dataset1.addValue(3.0, "S1", "Mar");
        dataset1.addValue(5.0, "S1", "Apr");
        dataset1.addValue(5.0, "S1", "May");
        dataset1.addValue(7.0, "S1", "Jul");
        dataset1.addValue(8.0, "S1", "Aug");
        dataset1.addValue(0, "S1", "Sep");
        dataset1.addValue(0, "S1", "Oct");
        dataset1.addValue(0, "S1", "Nov");
        dataset1.addValue(0, "S1", "Dec");

        // Graph 2
        dataset12.addValue(0, "S2", "Jan");
        dataset12.addValue(0, "S2", "Feb");
        dataset12.addValue(0, "S2", "Mar");
        dataset12.addValue(0, "S2", "Apr");
        dataset12.addValue(0, "S2", "May");
        dataset12.addValue(0, "S2", "Jun");
        dataset12.addValue(0, "S2", "Jul");
        dataset12.addValue(0, "S2", "Aug");
        dataset12.addValue(6.0, "S2", "Sep");
        dataset12.addValue(0, "S2", "Oct");
        dataset12.addValue(0, "S2", "Nov");
        dataset12.addValue(0, "S2", "Dec");

        // Graph 3
        dataset2.addValue(9.0, "T1", "Jan");
        dataset2.addValue(7.0, "T1", "Feb");
        dataset2.addValue(2.0, "T1", "Mar");
        dataset2.addValue(6.0, "T1", "Apr");
        dataset2.addValue(6.0, "T1", "May");
        dataset2.addValue(9.0, "T1", "Jun");
        dataset2.addValue(5.0, "T1", "Jul");
        dataset2.addValue(4.0, "T1", "Aug");
        dataset2.addValue(8.0, "T1", "Sep");
        dataset2.addValue(8.0, "T1", "Oct");
        dataset2.addValue(8.0, "T1", "Nov");
        dataset2.addValue(8.0, "T1", "Dec");

        // Renderers
        final BarRenderer renderer = new BarRenderer();
        final BarRenderer renderer12 = new BarRenderer();
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();

        // Common options
        Font axisF = new Font("Gulim", Font.PLAIN, 14);

        // Rendering setting
        // Graph 1
        renderer.setSeriesPaint(0, new Color(0,162,255));

        // Graph 2
        renderer12.setSeriesPaint(0, new Color(232,168,255));

        // Graph 3
        renderer2.setDrawOutlines(true);
        renderer2.setUseFillPaint(true);

        renderer2.setSeriesPaint(0,new Color(219,121,22));
        renderer2.setSeriesStroke(0,new BasicStroke(
                2.0f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                3.0f)
        );

        // create plot
        final CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);
        plot.setDataset(1,dataset12);
        plot.setRenderer(1,renderer12);
        plot.setDataset(2, dataset2);
        plot.setRenderer(2, renderer2);

        // plot setup
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);

        // Render order
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        // X-axis
        plot.setDomainAxis(new CategoryAxis());
        plot.getDomainAxis().setTickLabelFont(axisF);
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

        // Y-Axis
        plot.setRangeAxis(new NumberAxis());
        plot.getRangeAxis().setTickLabelFont(axisF);

        return new JFreeChart(plot);
    }
}
