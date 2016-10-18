package se.kodarkatten.sokutei.event;

/**
 * Created by jone on 2016-10-17.
 */
public class DataPoint
{

    public String getName()
    {
        return name;
    }

    private String name;

    public Long getValue()
    {
        return value;
    }

    private Long value;

    private long timestamp;

    public DataPoint(String name, Long value)
    {
        this.name = name;
        this.value = value;
        timestamp = System.currentTimeMillis();
    }


}
