package com.timethor.terracontrol.core.configuration;

import com.timethor.terracontrol.core.TerraControl;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * This class holds data for a bukkit nms.BiomeMeta class. The name does not match but ours make more sense.
 */
public class WeightedMobSpawnGroup {

    /**
	 */
    protected String mob = null;
    /**
	 */
    protected int max = 0;
    /**
	 */
    protected int weight = 0;
    /**
	 */
    protected int min = 0;

    /**
     *
     * @param mobName
     * @param weight
     * @param min
     * @param max
     */
    public WeightedMobSpawnGroup(String mobName, int weight, int min, int max) {
        this.mob = mobName;
        this.weight = weight;
        this.min = min;
        this.max = max;
    }

    /**
     *
     * @return
     */
    public String getMobName() {
        return this.mob;
    }

    /**
	 * @return
	 */
    public int getWeight() {
        return this.weight;
    }

    /**
	 * @return
	 */
    public int getMin() {
        return this.min;
    }

    /**
	 * @return
	 */
    public int getMax() {
        return this.max;
    }

    /**
     *
     * @param originalJson
     * @return
     */
    public static List<WeightedMobSpawnGroup> fromJson(String originalJson) {
        // Example: [{"mob": "Sheep", "weight": 12, "min": 4, "max": 4}]
        List<WeightedMobSpawnGroup> mobGroups = new ArrayList<>();

        String json = originalJson.trim();
        if (json.length() <= 2) {
            // Empty Json
            return mobGroups;
        }
        // Remove the [..]
        json = removeFirstAndLastChar(json);

        // Every group is seperated by a , but in the group the , is also used.
        // So convert the ( to {, the ) to } and use an existing function to get
        // each group
        json = json.replace('{', '(');
        json = json.replace('}', ')');

        String[] groups = ConfigFile.readComplexString(json);

        try {
            for (String group : groups) {
                group = group.trim();
                group = removeFirstAndLastChar(group);
                String[] groupParts = group.split(",");
                String mobName = null;
                int weight = 0;
                int min = 0;
                int max = 0;
                for (String option : groupParts) {
                    option = option.trim();
                    String[] optionParts = option.split(":");
                    if (optionParts[0].trim().equalsIgnoreCase("\"mob\"")) {
                        // Remove the quotes
                        mobName = removeFirstAndLastChar(optionParts[1].trim());
                    }
                    if (optionParts[0].trim().equalsIgnoreCase("\"weight\"")) {
                        weight = Integer.parseInt(optionParts[1].trim());
                    }
                    if (optionParts[0].trim().equalsIgnoreCase("\"min\"")) {
                        min = Integer.parseInt(optionParts[1].trim());
                    }
                    if (optionParts[0].trim().equalsIgnoreCase("\"max\"")) {
                        max = Integer.parseInt(optionParts[1].trim());
                    }
                }
                if (mobName != null && weight > 0 && min > 0 && max >= min) {
                    mobGroups.add(new WeightedMobSpawnGroup(mobName, weight, min, max));
                }
            }
        } catch (NumberFormatException e) {
            TerraControl.log(Level.WARNING, "Incorrect number in JSON: " + originalJson);
            TerraControl.log(Level.WARNING, e.getStackTrace().toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            TerraControl.log(Level.WARNING, "Incorrect JSON: " + originalJson);
        }

        return mobGroups;
    }

    /**
     *
     * @param list
     * @return
     */
    public static String toJson(List<WeightedMobSpawnGroup> list) {
        StringBuilder json = new StringBuilder("[");
        for (WeightedMobSpawnGroup group : list) {
            json.append("{\"mob\": \"");
            json.append(group.getMobName());
            json.append("\", \"weight\": ");
            json.append(group.getWeight());
            json.append(", \"min\": ");
            json.append(group.getMin());
            json.append(", \"max\": ");
            json.append(group.getMax());
            json.append("}, ");
        }
        // Remove ", " at end
        if (json.length() != 1) {
            json.deleteCharAt(json.length() - 1);
            json.deleteCharAt(json.length() - 1);
        }
        // Add closing bracket
        json.append(']');
        return json.toString();
    }

    private static String removeFirstAndLastChar(String string) {
        return string.substring(1, string.length() - 1);
    }
}
