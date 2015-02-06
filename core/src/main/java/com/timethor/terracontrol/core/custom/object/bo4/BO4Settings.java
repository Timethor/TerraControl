package com.timethor.terracontrol.core.custom.object.bo4;

import com.timethor.terracontrol.core.builtin.MaterialCatalog;
import com.timethor.terracontrol.core.configuration.TerraSetting;
import com.timethor.terracontrol.core.configuration.TerraSetting.SettingsType;
import com.timethor.terracontrol.core.custom.object.CustomObjectCoordinate.SpawnHeight;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Timethor
 */
public enum BO4Settings implements TerraSetting {
    // BO3

    /**
     *
     */
    author("Unknown"),
    /**
     *
     */
    description("No description given"),
    // Main settings
    /**
     *
     */
    tree(true),
    /**
     *
     */
    frequency(1),
    /**
     *
     */
    rarity(100.0),
    /**
     *
     */
    rotateRandomly(false),
    /**
     *
     */
    spawnHeight(SpawnHeightSetting.highestBlock),
    /**
     *
     */
    minHeight(0),
    /**
     *
     */
    maxHeight(256),
    /**
     *
     */
    maxBranchDepth(10),
    /**
     *
     */
    excludedBiomes("All", SettingsType.StringArray),
    // Source block settings
    /**
     *
     */
    sourceBlock(MaterialCatalog.AIR.id),
    /**
     *
     */
    outsideSourceBlock(OutsideSourceBlock.placeAnyway),
    /**
     *
     */
    maxPercentageOutsideSourceBlock(100);

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Integer> intArrayListValue() {
        return (ArrayList<Integer>) value;
    }

    // The spawn height
    /**
     * @author Timethor
     */
    public enum SpawnHeightSetting {

        /**
         */
        randomY(SpawnHeight.PROVIDED),
        /**
         */
        highestBlock(SpawnHeight.HIGHEST_BLOCK),
        /**
         */
        highestSolidBlock(SpawnHeight.HIGHEST_SOLID_BLOCK);
        /**
         */
        private SpawnHeight height;

        private SpawnHeightSetting(SpawnHeight height) {
            this.height = height;
        }

        /**
         *
         * @return
         */
        public SpawnHeight toSpawnHeight() {
            return height;
        }
    }

    // What to do when outside the source block
    /**
     * @author Timethor
     */
    public enum OutsideSourceBlock {

        /**
         */
        dontPlace,
        /**
         */
        placeAnyway
    }
    /**
     */
    private Object value;
    /**
     */
    private SettingsType returnType;

    private BO4Settings(int i) {
        value = i;
        returnType = SettingsType.Int;
    }

    private BO4Settings(long i) {
        value = i;
        returnType = SettingsType.Long;
    }

    private BO4Settings(double d) {
        value = d;
        returnType = SettingsType.Double;
    }

    private BO4Settings(float f) {
        value = f;
        returnType = SettingsType.Float;
    }

    private BO4Settings(String s) {
        value = s;
        returnType = SettingsType.String;
    }

    private BO4Settings(String s, SettingsType type) {
        returnType = type;

        if (type == SettingsType.StringArray) {
            ArrayList<String> list = new ArrayList<>();
            if (s.contains(",")) {
                Collections.addAll(list, s.split(","));
            } else if (!s.equals("")) {
                list.add(s);
            }
            value = list;
            return;
        }
        value = s;

    }

    private BO4Settings(Enum<?> e) {
        value = e;
        returnType = SettingsType.Enum;

    }

    private BO4Settings(Boolean b) {
        value = b;
        returnType = SettingsType.Boolean;
    }

    /**
     *
     * @return
     */
    @Override
    public int intValue() {
        return (Integer) value;
    }

    /**
     *
     * @return
     */
    @Override
    public double doubleValue() {
        return (Double) value;
    }

    /**
     *
     * @return
     */
    @Override
    public float floatValue() {
        return (Float) value;
    }

    /**
     *
     * @return
     */
    @Override
    public Enum<?> enumValue() {
        return (Enum<?>) value;
    }

    /**
     * @return
     */
    @Override
    public SettingsType getReturnType() {
        return returnType;
    }

    /**
     *
     * @return
     */
    @Override
    public String stringValue() {
        return (String) value;
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<String> stringArrayListValue() {
        return (ArrayList<String>) value;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean booleanValue() {
        return (Boolean) value;
    }

    /**
     *
     * @return
     */
    @Override
    public long longValue() {
        return (Long) value;
    }
}
