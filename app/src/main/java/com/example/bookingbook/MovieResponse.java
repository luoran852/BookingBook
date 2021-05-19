package com.example.bookingbook;

public class MovieResponse {
    private String total;

    private String lastBuildDate;

    private String display;

    private String start;

    private Items[] items;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getLastBuildDate ()
    {
        return lastBuildDate;
    }

    public void setLastBuildDate (String lastBuildDate)
    {
        this.lastBuildDate = lastBuildDate;
    }

    public String getDisplay ()
    {
        return display;
    }

    public void setDisplay (String display)
    {
        this.display = display;
    }

    public String getStart ()
    {
        return start;
    }

    public void setStart (String start)
    {
        this.start = start;
    }

    public Items[] getItems ()
    {
        return items;
    }

    public void setItems (Items[] items)
    {
        this.items = items;
    }

    @Override
    public String toString()
    {
        return "MovieResponse [total = "+total+", lastBuildDate = "+lastBuildDate+", display = "+display+", start = "+start+", items = "+items+"]";
    }
}
