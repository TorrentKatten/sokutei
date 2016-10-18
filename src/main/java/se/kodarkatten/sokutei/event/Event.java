package se.kodarkatten.sokutei.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jone on 2016-10-17.
 */
public class Event
{
    private Map<String,List<Long>> data = new HashMap<String,List<Long>>();
    private EventType type;

    public Event(EventType eventType, List<DataPoint> dataPoints) {
        type=eventType;
        for(DataPoint p:dataPoints) {
            List<Long> valueStore = data.get(p.getName());
            if(valueStore == null) {
                valueStore = new ArrayList<Long>();
                data.put(p.getName(),valueStore);
            }
            valueStore.add(p.getValue());
        }
    }

    public EventType getType(){
        return type;
    }

    public Map<String, List<Long>> getData()
    {
        return data;
    }
}
