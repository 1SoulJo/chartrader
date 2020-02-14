package ui.frame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Chart extends JInternalFrame {
    private static final String TITLE = "Chart";

    public Chart() {
        setTitle(TITLE);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setVisible(true);

        String stockSymbol = "S&P 500";
        final DateAxis domainAxis = new DateAxis("Date");
        NumberAxis rangeAxis = new NumberAxis("Price");
        CandlestickRenderer renderer = new CandlestickRenderer();
        XYDataset dataset = getDataSet(stockSymbol);
        XYPlot mainPlot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);

        // Do some setting up, see the API Doc
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setDrawVolume(false);
        rangeAxis.setAutoRangeIncludesZero(false);

        // Now create the chart and chart panel
        JFreeChart chart = new JFreeChart(stockSymbol, null, mainPlot, false);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        chartPanel.setSize(500, 300);
        mainPlot.setDomainPannable(true);
        mainPlot.setRangePannable(true);
        this.add(chartPanel);
    }

    private AbstractXYDataset getDataSet(String stockSymbol) {
        // This is the data needed for the dataset
        OHLCDataItem[] data = getData(stockSymbol);

        //Create a dataset, an Open, High, Low, Close dataset
        return new DefaultOHLCDataset(stockSymbol, data);
    }

    private OHLCDataItem[] getData(String stockSymbol) {
        // ToDo : link data source to actual provider
        List<OHLCDataItem> dataItems = new ArrayList<>();
        try {
            DateFormat df = new SimpleDateFormat("y-M-d");
            for (int i = 0; i < 60; i++) {
                Date date = df.parse("2019-03-" + (i + 1));
                double open = 12.0 - (i / 60);
                double high = 13.0 + (i % 4);
                double low = 10.0 - (i % 33);
                double close = 12.24 - (i % 10);
                double volume = 100000 + i;
                OHLCDataItem item = new OHLCDataItem(date, open, high, low, close, volume);
                dataItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        // Data from Yahoo is from newest to oldest. Reverse so it is oldest to newest
        Collections.reverse(dataItems);

        // Convert the list into an array
        return  dataItems.toArray(new OHLCDataItem[0]);
    }
}
