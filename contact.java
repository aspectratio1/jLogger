import java.io.Serializable;

public class contact implements Serializable {

    public  contact(String call1, String destCall1, String grid1, String freq1, String time1, String date1){
        call = call1;
        destCall = destCall1;
        grid = grid1;
        freq = freq1;
        time = time1;
        date = date1;

    }

    private String call, destCall, grid, freq, time, date;


    public String getCall() {
        return call;
    }

    public String getDestCall() {
        return destCall;
    }

    public String getGrid() {
        return grid;
    }

    public String getFreq() {
        return freq;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public void setDestCall(String destCall) {
        this.destCall = destCall;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
