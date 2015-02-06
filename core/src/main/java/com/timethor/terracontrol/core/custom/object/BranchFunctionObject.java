package com.timethor.terracontrol.core.custom.object;

import java.util.Comparator;

/**
 * @author   Timethor
 */
public class BranchFunctionObject {

    /**
	 */
    private Rotation rotation;
    /**
	 */
    private Integer chance;
    /**
	 */
    private CustomObject branch;

    /**
     *
     * @param rotation
     * @param chance
     * @param branch
     */
    public BranchFunctionObject(Rotation rotation, Integer chance, CustomObject branch) {
        this.rotation = rotation;
        this.chance = chance;
        this.branch = branch;
    }

    /**
	 * @return
	 */
    public Integer getChance() {
        return chance;
    }

    /**
	 * @return
	 */
    public Rotation getRotation() {
        return rotation;
    }

    /**
	 * @return
	 */
    public CustomObject getBranch() {
        return branch;
    }

    /**
     *
     */
    public static class BranchChanceComparator implements Comparator<BranchFunctionObject> {

        public int compare(BranchFunctionObject o1, BranchFunctionObject o2) {
            return o1.chance.compareTo(o2.chance);
        }
    }

    /**
     *
     * @return
     */
    public static Comparator<BranchFunctionObject> getComparator() {
        return new BranchChanceComparator();
    }
}
