package se.kodarkatten.sokutei.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import se.kodarkatten.sokutei.event.Event;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by jone on 2016-10-17.
 */
public class AsyncSender
{

    public void report(Event event)
    {
        switch(event.getType())
        {
            case CALL_START:
                send(createMetricPoint("call_start", event.getData()));
        }
    }


    private Point createMetricPoint(String type,Map<String,List<Long>> metricValues) {
        PointBuilder point = Point.measurement(type);
        point.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        for(String key : metricValues.keySet()) {
            for(Long value: metricValues.get(key)) {
                point.addField(key,value);
            }
        }
        return point.build();
    }


    private void send(Point point)
    {
        InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "admin", "admin");
        BatchPoints batchPoints = BatchPoints
                .database("sokutei")
                .tag("async", "true")
                .retentionPolicy("autogen")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();

        batchPoints.point(point);

        influxDB.write(batchPoints);
    }


}