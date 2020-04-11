package ui.frame;

import app.PlaybackManager;
import com.google.common.eventbus.Subscribe;
import event.ChartViewEvent;
import event.EventBusUtil;
import event.ProviderViewEvent;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import provider.dao.PriceDao;
import provider.entity.Price;
import app.TransactionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Chart extends JInternalFrame {
    private static final String TITLE = "Chart";

    private JFreeChart chart;
    private ChartPanel chartPanel;

    private OHLCSeries ohlcSeries;
    private TimeSeries volumeSeries;
    private XYPlot candlestickSubplot;
    private XYPlot volumeSubplot;
    private JLabel instLabel;
    private JComboBox<String> dateCombo;

    private Point panLast;

    private String selectedInst;
    private String selectedDate;

    public Chart() {
        setTitle(TITLE);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        setVisible(true);


        // Top panel
        JPanel topPanel = new JPanel();

        // Instrument name area
        instLabel = new JLabel();
        instLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(instLabel);

        // Combobox for date selection
        dateCombo = new JComboBox<>();
        dateCombo.addActionListener((ActionEvent e) -> {
            selectedDate = (String) dateCombo.getSelectedItem();
            ohlcSeries.clear();
            volumeSeries.clear();
            PlaybackManager.get().reset();
        });
        topPanel.add(dateCombo);

        // Play / stop buttons
        JButton b = new JButton("Start");
        topPanel.add(b);
        b.addActionListener((ActionEvent e) -> {
            PlaybackManager pm = PlaybackManager.get();
            pm.setListener(this::onTimeTick);
            pm.start();
        });
        b = new JButton("Stop");
        b.addActionListener((ActionEvent e) -> {
            PlaybackManager.get().stop();
        });
        topPanel.add(b);

        // Now create the chart and chart panel
        chartPanel = createChartPanel(createChart());

        add(topPanel, BorderLayout.PAGE_START);
        add(chartPanel, BorderLayout.CENTER);

        // Register event listener
        EventBusUtil.get().register(this);
    }

    private JFreeChart createChart() {
        // Create OHLCSeriesCollection as a price dataset for candlestick chart
        OHLCSeriesCollection candlestickDataset = new OHLCSeriesCollection();
        ohlcSeries = new OHLCSeries("Price");
        candlestickDataset.addSeries(ohlcSeries);

        // Create candlestick chart priceAxis
        NumberAxis priceAxis = new NumberAxis("Price");
        priceAxis.setAutoRangeIncludesZero(false);

        // Create candlestick chart renderer
        CandlestickRenderer candlestickRenderer = new CandlestickRenderer();
        candlestickRenderer.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_SMALLEST);

        // Create candlestickSubplot
        candlestickSubplot = new XYPlot(candlestickDataset, null, priceAxis, candlestickRenderer);
        candlestickSubplot.setBackgroundPaint(Color.white);
        candlestickSubplot.setDomainPannable(true);
        candlestickSubplot.setRangePannable(true);

        // creates TimeSeriesCollection as a volume dataset for volume chart
        TimeSeriesCollection volumeDataset = new TimeSeriesCollection();
        volumeSeries = new TimeSeries("Volume");
        volumeDataset.addSeries(volumeSeries);

        // Create volume chart volumeAxis
        NumberAxis volumeAxis = new NumberAxis("Volume");
        volumeAxis.setAutoRangeIncludesZero(false);

        // Set to no decimal
        volumeAxis.setNumberFormatOverride(new DecimalFormat("0"));

        // Create volume chart renderer
        XYBarRenderer timeRenderer = new XYBarRenderer();
        timeRenderer.setShadowVisible(false);
        timeRenderer.setDefaultToolTipGenerator(new StandardXYToolTipGenerator("Volume--> Time={1} Size={2}",
                new SimpleDateFormat("kk:mm"), new DecimalFormat("0")));

        // Create volumeSubplot
        volumeSubplot = new XYPlot(volumeDataset, null, volumeAxis, timeRenderer);
        volumeSubplot.setBackgroundPaint(Color.white);

        // Creating charts common dateAxis
        DateAxis dateAxis = new DateAxis("Time");
        dateAxis.setDateFormatOverride(new SimpleDateFormat("kk:mm"));

        // reduce the default left/right margin from 0.05 to 0.02
        dateAxis.setLowerMargin(0.1);
        dateAxis.setUpperMargin(0.1);

        // Create mainPlot
        CombinedDomainXYPlot mainPlot = new CombinedDomainXYPlot(dateAxis);
        mainPlot.setGap(10.0);
        mainPlot.add(candlestickSubplot, 3);
        mainPlot.add(volumeSubplot, 1);
        mainPlot.setOrientation(PlotOrientation.VERTICAL);

        chart = new JFreeChart(mainPlot);
        chart.removeLegend();
        chart.getXYPlot().getDomainAxis().setFixedAutoRange(3000000);

        return chart;
    }

    private ChartPanel createChartPanel(JFreeChart chart) {
        ChartPanel panel = new ChartPanel(chart, false);

        panel.setMouseZoomable(false);
        panel.setMouseWheelEnabled(true);
        panel.setDomainZoomable(true);
        panel.setRangeZoomable(false);
        panel.setZoomTriggerDistance(Integer.MAX_VALUE);
        panel.setFillZoomRectangle(false);
        panel.setZoomOutlinePaint(new Color(0f, 0f, 0f, 0f));
        panel.setZoomAroundAnchor(true);
        try {
            Field mask = ChartPanel.class.getDeclaredField("panMask");
            mask.setAccessible(true);
            mask.set(panel, 0);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        panel.addMouseWheelListener(arg0 -> panel.restoreAutoRangeBounds());

        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                panLast = mouseEvent.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        });

        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (panLast != null) {
                    double dx = e.getX() - panLast.getX();
                    double dy = e.getY() - panLast.getY();
                    if (dx == 0.0 && dy == 0.0) return;

                    double wPercent = -dx / panel.getWidth();
                    double hPercent = dy / panel.getHeight();

                    candlestickSubplot.setNotify(false);
                    candlestickSubplot.panDomainAxes(
                            wPercent, panel.getChartRenderingInfo().getPlotInfo(), panLast);
                    candlestickSubplot.panRangeAxes(
                            hPercent, panel.getChartRenderingInfo().getPlotInfo(), panLast);
                }
                panLast = e.getPoint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        return panel;
    }

    private void addCandle(String date, double o, double h, double l, double c, long v) {
        try {
            FixedMillisecond t = new FixedMillisecond(new SimpleDateFormat("yyyy-MM-dd kk:mm").parse(date));
            ohlcSeries.add(t, o, h, l, c);
            volumeSeries.add(t, v);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void onTimeTick(Date current) {
        SimpleDateFormat tf = new SimpleDateFormat("kk:mm");

        chart.getXYPlot().getDomainAxis().setFixedAutoRange(3000000);

        PriceDao dao = PriceDao.get();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (Price p : dao.getPriceData(selectedInst, df.parse(dateCombo.getItemAt(0)), tf.format(current))) {
                String date = new SimpleDateFormat("yyyy-MM-dd").format(p.getDate());
                addCandle(
                        date + " " + p.getMinute(),
                        p.getOpen(), p.getHigh(),
                        p.getLow(), p.getClose(),
                        p.getVolume());

                // update active information
                TransactionManager tm = TransactionManager.get();
                tm.setPrice(p.getClose());
                tm.setVolume(p.getVolume());
                tm.update();

                ProviderViewEvent ev = new ProviderViewEvent(ProviderViewEvent.CANDLE_ADDED);
                ev.setInstrument(selectedInst);
                EventBusUtil.get().post(ev);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Update x-Axis range
        DateAxis a = (DateAxis) chart.getXYPlot().getDomainAxis();

        Calendar c = Calendar.getInstance();
        c.setTime(a.getMaximumDate());
        c.add(Calendar.MINUTE, 1);
        a.setMaximumDate(c.getTime());

        c.setTime(a.getMinimumDate());
        c.add(Calendar.MINUTE, 1);
        a.setMinimumDate(c.getTime());
    }

    /**
     * Event Listener for any event that chart view is responsible
     * @param e ProviderViewEvent
     */
    @Subscribe
    public void onChartViewEvent(ChartViewEvent e) throws ParseException {
        switch (e.getType()) {
            case ChartViewEvent.SHOW_INSTRUMENT:
                PlaybackManager.get().stop();

                ohlcSeries.clear();
                volumeSeries.clear();

                selectedInst = e.getInstrument();
                instLabel.setText(selectedInst);
                TransactionManager.get().setActiveInstrument(selectedInst);

                PriceDao dao = PriceDao.get();
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

                dateCombo.removeAllItems();
                for (Date date : dao.getDates(selectedInst)) {
                    dateCombo.addItem(dt.format(date));
                }

                dateCombo.requestFocus();
                break;
            default:
                break;
        }
    }
}
