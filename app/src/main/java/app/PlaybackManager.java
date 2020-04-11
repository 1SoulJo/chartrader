package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PlaybackManager {
    private static PlaybackManager INSTANCE;

    private Timer timer;
    private TimerJob job;

    private Date currentTime;

    private PlaybackManager() {
    }

    public static PlaybackManager get() {
        if (INSTANCE == null) {
            INSTANCE = new PlaybackManager();
        }

        return INSTANCE;
    }

    public void setListener(IPlaybackListener l) {
        job = new TimerJob(this, l);
    }

    public void reset() {
        try {
            currentTime = new SimpleDateFormat("kk:mm").parse("09:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (timer != null) timer.cancel();

        timer = new Timer(true);
        timer.scheduleAtFixedRate(job, 1000, 1000);
    }

    public void stop() {
        if (timer != null) timer.cancel();
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date t) {
        currentTime = t;
    }

    // Callback interface
    public interface IPlaybackListener {
        void onTrigger(Date current);
    }

    private static class TimerJob extends TimerTask {
        private final PlaybackManager pm;
        private final IPlaybackListener listener;

        public TimerJob(PlaybackManager pm, IPlaybackListener l) {
            this.pm = pm;
            this.listener = l;
        }

        @Override
        public void run() {
            if (listener != null) {
                Date current = pm.getCurrentTime();

                Calendar c = Calendar.getInstance();
                c.setTime(current);
                c.add(Calendar.MINUTE, 1);
                current = c.getTime();

                pm.setCurrentTime(current);

                listener.onTrigger(current);
            }
        }
    }
}
