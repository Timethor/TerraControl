package com.timethor.terracontrol.core.configuration;

import java.util.ArrayList;

/**
 * @author    Timethor
 */
public interface TerraSetting {

    /**
	 * @author    Timethor
	 */
    public enum SettingsType {

        /**
		 */
        String,
        /**
		 */
        Boolean,
        /**
		 */
        Int,
        /**
		 */
        Long,
        /**
		 */
        Enum,
        /**
		 */
        Double,
        /**
		 */
        Float,
        /**
		 */
        StringArray,
        /**
		 */
        IntArray,
        /**
		 */
        Color
    }

    /**
     *
     * @return
     */
    public String name();

    /**
     *
     * @return
     */
    public int intValue();

    /**
     *
     * @return
     */
    public long longValue();

    /**
     *
     * @return
     */
    public float floatValue();

    /**
     *
     * @return
     */
    public double doubleValue();

    /**
     *
     * @return
     */
    public Enum<?> enumValue();

    /**
     * 
     * @return 
     */
    public SettingsType getReturnType();

    /**
     *
     * @return
     */
    public String stringValue();

    /**
     *
     * @return
     */
    public ArrayList<String> stringArrayListValue();

    /**
     *
     * @return
     */
    public ArrayList<Integer> intArrayListValue();

    /**
     *
     * @return
     */
    public boolean booleanValue();
}
