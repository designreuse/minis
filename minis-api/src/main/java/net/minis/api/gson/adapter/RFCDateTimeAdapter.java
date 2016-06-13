package net.minis.api.gson.adapter;

import org.joda.time.format.DateTimeFormatterBuilder;


/**
 * The RFC data elements and interchange formats adapter.<br>
 * <br>
 * 
 * format sample: 2012-12-05T10:55:41.063+0800
 * 
 * @author yen.
 * 
 */
public class RFCDateTimeAdapter extends AbstractDateTimeTypeAdapter {

    public RFCDateTimeAdapter() {
        super(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").toFormatter());
    }

}
