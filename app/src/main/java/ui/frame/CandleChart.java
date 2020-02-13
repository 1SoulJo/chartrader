package ui.frame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.Timeline;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.xy.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CandleChart extends JInternalFrame {
    private static final String TITLE = "Chart";

    public CandleChart() {
        setTitle(TITLE);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setVisible(true);

        String stockSymbol = "AAPL";
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
        // Add time line toggle
//        final Timeline oldTimeline = domainAxis.getTimeline();
//        final Timeline newTimeline = SegmentedTimeline.newMondayThroughFridayTimeline();
//        this.add(new JCheckBox(new AbstractAction("Segmented Timeline") {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JCheckBox jcb = (JCheckBox) e.getSource();
//                if (jcb.isSelected()) {
//                    domainAxis.setTimeline(oldTimeline);
//                } else {
//                    domainAxis.setTimeline(oldTimeline);
//                }
//            }
//        }), BorderLayout.SOUTH);
    }

    private AbstractXYDataset getDataSet(String stockSymbol) {
        //This is the dataset we are going to create
        DefaultOHLCDataset result;
        //This is the data needed for the dataset
        OHLCDataItem[] data;
        //This is where we go get the data, replace with your own data source
        data = getData(stockSymbol);
        //Create a dataset, an Open, High, Low, Close dataset
        result = new DefaultOHLCDataset(stockSymbol, data);
        return result;
    }

    private OHLCDataItem[] getData(String stockSymbol) {
        List<OHLCDataItem> dataItems = new ArrayList<>();
        try {
//            String strUrl = "http://ichart.yahoo.com/table.csv?s=" + stockSymbol
//                    + "&a=4&b=1&c=2013&d=6&e=1&f=2013&g=d&ignore=.csv";
//            URL url = new URL(strUrl);
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(url.openStream()));
            DateFormat df = new SimpleDateFormat("y-M-d");
//            String inputLine;
//            in.readLine();
//            while ((inputLine = in.readLine()) != null) {
            for (int i = 0; i < 30; i++) {
//                StringTokenizer st = new StringTokenizer(inputLine, ",");
//                Date date = df.parse(st.nextToken());
//                double open = Double.parseDouble(st.nextToken());
//                double high = Double.parseDouble(st.nextToken());
//                double low = Double.parseDouble(st.nextToken());
//                double close = Double.parseDouble(st.nextToken());
//                double volume = Double.parseDouble(st.nextToken());
//                double adjClose = Double.parseDouble(st.nextToken());
                Date date = df.parse("2019-03-" + (i + 1));
                double open = 12.0 - i;
                double high = 13.0 + i;
                double low = 10.0 - i;
                double close = 12.24 - (i % 10);
                double volume = 100000 + i;
                OHLCDataItem item = new OHLCDataItem(date, open, high, low, close, volume);
                dataItems.add(item);
            }
//            in.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        //Data from Yahoo is from newest to oldest. Reverse so it is oldest to newest
        Collections.reverse(dataItems);
        //Convert the list into an array
        OHLCDataItem[] data = dataItems.toArray(new OHLCDataItem[dataItems.size()]);
        return data;
    }
}
